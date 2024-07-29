package es.Sebas1705.quizzApp.presentation.common.boardingPage

import androidx.annotation.DrawableRes
import es.Sebas1705.quizzApp.R

data class Page (
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pageList = listOf(
    Page(
        "Bienvenida y Descripción General",
        "Bienvenidos a nuestra aplicación de preguntas, diseñada para ofrecerte una experiencia educativa y entretenida. Con una interfaz amigable e intuitiva, podrás acceder a una variedad de preguntas en diferentes categorías y niveles de dificultad. Ya sea que desees poner a prueba tus conocimientos en historia, ciencia, cultura general o cualquier otro tema, nuestra app está configurada para proporcionarte un desafío estimulante y enriquecedor.",
        R.drawable.back_boarding
    ),
    Page(
        "Funcionamiento y Características",
        "Al iniciar la aplicación, puedes seleccionar el tema y la dificultad que prefieras. Cada pregunta viene con varias opciones de respuesta, de las cuales solo una es correcta. Tienes la posibilidad de utilizar pistas y comodines para ayudarte en los momentos más difíciles. Además, después de cada respuesta, recibirás una breve explicación que te permitirá aprender más sobre el tema en cuestión, independientemente de si tu respuesta fue correcta o incorrecta.",
        R.drawable.back_boarding
    ),
    Page(
        "Seguimiento y Competencia",
        "Nuestra aplicación también incluye una función de seguimiento de tu progreso. Podrás ver estadísticas detalladas sobre tus respuestas, las categorías en las que destacas y las áreas en las que podrías mejorar. Además, puedes competir con tus amigos y otros usuarios, comparando puntuaciones y alcanzando nuevas metas. Estamos comprometidos en brindarte una herramienta educativa que no solo ponga a prueba tus conocimientos, sino que también te inspire a aprender más cada día. ¡Disfruta y sigue creciendo con nuestra aplicación de preguntas!",
        R.drawable.back_boarding
    )
)