# YouKnow: una condición de carrera escondida en un ViewModel base

Cómo añadir tests a una app Android de trivial destapó un job de cobertura que no ejecutaba nada y después un fallo intermitente en CI cuya causa real era una carrera en el orden de construcción de la clase base MVI.

## Contexto

[YouKnow](https://github.com/Sebas1705/YouKnow) es una app Android de preguntas (Kotlin, Jetpack Compose, Clean Architecture multimódulo con Hilt). Los ViewModels de todas las pantallas heredan de un `MVIBaseViewModel` común, que gestiona el flujo de estado y expone un hook `onInit()` para la primera carga. Había pocos tests unitarios y me propuse ampliarlos.

## Problema

En [#54](https://github.com/Sebas1705/YouKnow/pull/54) añadí 124 tests: mappers y casos de uso del dominio, y tests con Robolectric de los cinco ViewModels de juego (Quiz, Mystery Number, Daily Challenge, Word Pass, Families). Esos ViewModels cargan en el `Dispatchers.IO` real, así que los tests esperan al `StateFlow` con Turbine, que usa timeouts de tiempo real.

Aparecieron dos problemas.

**El job de cobertura no hacía nada.** `JacocoConventionPlugin` buscaba `testDebugUnitTest` con `findByName` al aplicarse el plugin, antes de que AGP registrara la tarea. La búsqueda devolvía `null`, cada `jacocoTestReport` se quedaba sin datos de ejecución y salía `SKIPPED`, y el job "Unit Tests + Coverage" de CI llevaba tiempo ejecutando cero tests.

**`main` se puso en rojo justo tras la fusión.** Los mismos tests que pasaban en la PR fallaron en `main`: `DailyChallengeViewModelTest` daba `No value produced in 3s` ([ejecución 40221308373](https://github.com/Sebas1705/YouKnow/actions/runs/40221308373)).

## Enfoque

**Arreglar el plugin.** Las tareas se seleccionan ahora de forma diferida, así que la dependencia se resuelve al construir el grafo de tareas, y los datos de ejecución se leen de su ruta conocida. Un file tree en vez de un único fichero hace que un módulo sin tests se omita en lugar de fallar:

```kotlin
val unitTestTasks = tasks.matching { it.name == "testDebugUnitTest" }
val compileTasks = tasks.matching {
    it.name == "compileDebugKotlin" || it.name == "compileDebugJavaWithJavac"
}
val execFiles = fileTree(layout.buildDirectory.dir("jacoco")) {
    include("testDebugUnitTest.exec")
}
```

También añadí un informe combinado de todos los módulos y una tabla de cobertura en el resumen del job de CI.

**Los tests intermitentes: la primera hipótesis, equivocada.** Un timeout en un runner cargado parece lentitud, así que mi primer commit subió los timeouts de Turbine a 30 segundos. CI volvió a fallar, ahora con `No value produced in 30s` y en dos tests en lugar de uno. Esperar diez veces más no había cambiado nada, y esa era la pista útil: el valor no iba a llegar nunca. Algo estaba matando la carga, no retrasándola.

**Causa raíz.** El artefacto con el informe de tests de la ejecución fallida contenía una `NullPointerException` suprimida en `DailyChallengeViewModel.kt:58`, la línea que lee el `dailyChallengeUsesCases` inyectado. Un `val` inyectado por constructor no debería ser nunca null, salvo que se lea antes de asignarse. La clase base era así:

```kotlin
abstract class MVIBaseViewModel<S, I> : ViewModel() {
    init { onInit() }   // se ejecuta dentro del constructor de la superclase
}
```

En Kotlin (como en la JVM), el constructor de la superclase, bloques `init` incluidos, se ejecuta *antes* de que la subclase asigne sus propiedades de constructor. `DailyChallengeViewModel.onInit()` lanzaba `loadChallenge()` en `Dispatchers.IO`. Normalmente el constructor terminaba antes; en un runner de 2 vCPU, a veces ganaba el hilo de IO, leía un campo todavía null y la corrutina moría. El estado se quedaba para siempre en `LOADING` y fallaba por timeout el test que estuviera esperando; por eso el fallo saltaba de un test a otro. `HomeViewModel` y `SurveyViewModel` también cargan desde `onInit()`, así que la propia app podía sufrir la misma carrera.

**El arreglo ([#55](https://github.com/Sebas1705/YouKnow/pull/55)).** Una línea: `init { execute { onInit() } }`. `execute` lanza en `Dispatchers.Main`, que (a diferencia de `Main.immediate`) siempre despacha, así que `onInit()` se ejecuta cuando la construcción ha terminado, una sola vez y en Main. El compromiso queda explícito en la PR: el hook se ejecuta en el siguiente turno del hilo principal en vez de hacerlo de forma síncrona.

El test de regresión, `MVIBaseViewModelTest`, imita a los ViewModels de feature con una subclase que lee una dependencia del constructor en `onInit()`. Con un `StandardTestDispatcher` como Main, comprueba que justo tras construir no se ha ejecutado nada, y después que `onInit()` vio el valor inyectado y se ejecutó exactamente una vez. Uno de sus dos tests falla con la clase base antigua. Los timeouts de 30 segundos volvieron a los valores por defecto de Turbine: solo escondían el síntoma durante más tiempo.

## Resultado

- El pipeline de cobertura vuelve a ejecutar tests. La cobertura de líneas combinada pasó del 1,24 % (medida ya con el plugin arreglado) al 9,50 % (1.341 de 14.119 líneas). El denominador incluye toda la UI de Compose sin tests, así que la cifra absoluta es baja por construcción; lo importante era tener una base real y visible.
- En local, `:core:common` y `:feature:game` pasaron tres veces seguidas con `--rerun-tasks` y 2 workers (104 tests en cada ejecución), y `coverageUnitTestAll` pasó con 201 tests. El [Validation Pipeline de main](https://github.com/Sebas1705/YouKnow/actions/runs/40567949201) quedó en verde tras la fusión.
- El [CHANGELOG](https://github.com/Sebas1705/YouKnow/blob/main/CHANGELOG.md) recoge los dos arreglos, porque afectan a la app y no solo a CI.

## Lecciones

- **Si un timeout más largo no cambia el resultado, el problema no es el timeout.** Subirlo fue un experimento barato, y su fracaso fue el dato más revelador.
- **Hay que leer los artefactos, no solo la línea del log.** La aserción decía "no hay valor"; la excepción suprimida del informe decía por qué.
- **Nunca llames a código sobrescribible desde el constructor de una clase base.** Es una trampa clásica de la JVM, y las corrutinas la convierten de un fallo determinista en una carrera que solo aparece en una máquina más lenta.
- **Un test intermitente puede ser un bug real.** Este era una carrera de arranque en código de producción que los tests nuevos fueron los primeros en notar.
