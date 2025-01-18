package es.sebas1705.youknow.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Languages
import es.sebas1705.youknow.core.classes.enums.Letter
import es.sebas1705.youknow.data.local.database.config.SettingsDB
import es.sebas1705.youknow.domain.model.games.WordModel

/**
 * Entity to represent the word in the database
 *
 * @property word [String]: word
 * @property definitions [List]<[String]>: definitions of the word
 * @property letter [Int]: letter of the word
 * @property language [Int]: language of the word
 * @property difficulty [Int]: difficulty of the word
 * @property wordPassType [Int]: word pass type of the
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Entity(tableName = SettingsDB.WORD_TABLE)
data class WordEntity(
    @PrimaryKey val word: String,
    val definitions: List<String>,
    val letter: Letter,
    val language: Languages,
    val difficulty: Difficulty
) {
    fun toWordModel() = WordModel(
        word,
        definitions,
        letter,
        language,
        difficulty
    )

    companion object {
        fun wordDatabase(): List<WordEntity> {
            val list = mutableListOf<WordEntity>()
            list.addAll(a())
            list.addAll(b())
            list.addAll(c())
            list.addAll(d())
            list.addAll(e())
            list.addAll(f())
            list.addAll(g())
            list.addAll(h())
            list.addAll(i())
            list.addAll(j())
            list.addAll(k())
            list.addAll(l())
            list.addAll(m())
            list.addAll(n())
            list.addAll(o())
            list.addAll(p())
            list.addAll(q())
            list.addAll(r())
            list.addAll(s())
            list.addAll(t())
            list.addAll(u())
            list.addAll(v())
            list.addAll(w())
            list.addAll(x())
            list.addAll(y())
            list.addAll(z())
            return list.toList()
        }

        private fun a() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Apple",
                definitions = listOf("A fruit", "A tech company"),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ant",
                definitions = listOf("A small insect", "A hardworking creature in stories"),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Airplane",
                definitions = listOf("A vehicle that flies in the sky", "A mode of transportation"),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Animal",
                definitions = listOf(
                    "A living organism that is not a plant",
                    "A creature that moves on its own"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Angel",
                definitions = listOf("A spiritual being in heaven", "A kind person"),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Arm",
                definitions = listOf(
                    "A part of the human body",
                    "A mechanical component used for movement"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Arrow",
                definitions = listOf("A weapon used with a bow", "A symbol indicating direction"),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Art",
                definitions = listOf(
                    "A form of creative expression",
                    "Paintings, sculptures, or drawings"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Anchor",
                definitions = listOf(
                    "A heavy object used to moor a ship",
                    "A person who presents news on TV"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Almond",
                definitions = listOf("A type of nut", "An ingredient in desserts"),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Agua",
                definitions = listOf(
                    "Un líquido esencial para la vida",
                    "El principal componente de los océanos"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Abeja",
                definitions = listOf(
                    "Un insecto que produce miel",
                    "Un polinizador importante para las flores"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Amigo",
                definitions = listOf(
                    "Una persona con la que tienes una relación cercana",
                    "Un compañero o aliado"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Amor",
                definitions = listOf(
                    "Un sentimiento profundo de cariño",
                    "Un estado emocional de afecto intenso"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ajo",
                definitions = listOf(
                    "Un vegetal usado como condimento",
                    "Un ingrediente esencial en muchas salsas"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ave",
                definitions = listOf(
                    "Un animal con plumas que puede volar",
                    "Un símbolo de libertad en muchas culturas"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Arena",
                definitions = listOf(
                    "Pequeñas partículas de roca en la playa",
                    "Material usado en la construcción"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Árbol",
                definitions = listOf(
                    "Una planta grande con tronco y ramas",
                    "Un símbolo de vida y estabilidad"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Abrigo",
                definitions = listOf(
                    "Una prenda de ropa para proteger del frío",
                    "Algo que proporciona refugio"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Anillo",
                definitions = listOf(
                    "Un objeto circular que se usa como joya",
                    "Un símbolo de compromiso o unión"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Astronomy",
                definitions = listOf(
                    "The study of celestial objects",
                    "A branch of science focusing on space"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Atlas",
                definitions = listOf("A collection of maps", "A book of geographical information"),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Algorithm",
                definitions = listOf(
                    "A step-by-step procedure for solving a problem",
                    "A set of rules in computation"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Antique",
                definitions = listOf(
                    "An old and valuable object",
                    "A piece of furniture from a past era"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ambassador",
                definitions = listOf(
                    "A diplomatic representative",
                    "Someone who promotes a cause or brand"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Avenue",
                definitions = listOf(
                    "A broad road in a city",
                    "A way or method to achieve something"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Archipelago",
                definitions = listOf(
                    "A group of islands",
                    "A sea area containing multiple islands"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Archaeology",
                definitions = listOf(
                    "The study of human history through excavation",
                    "A field focused on ancient artifacts"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Artifact",
                definitions = listOf(
                    "An object made by humans, typically of cultural interest",
                    "A historical item found in excavations"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Altitude",
                definitions = listOf(
                    "The height above sea level",
                    "The vertical distance from a reference point"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Astrología",
                definitions = listOf(
                    "El estudio de los astros y sus movimientos",
                    "Una disciplina que busca interpretar el cosmos"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Álgebra",
                definitions = listOf(
                    "Una rama de las matemáticas",
                    "El estudio de las estructuras, relaciones y números"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Adivinanza",
                definitions = listOf(
                    "Un juego de ingenio con palabras",
                    "Una frase o verso que se propone como enigma"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Arcángel",
                definitions = listOf(
                    "Un ángel de alto rango",
                    "Una figura espiritual importante en varias religiones"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Antología",
                definitions = listOf(
                    "Una colección de obras literarias o musicales",
                    "Un conjunto representativo de trabajos"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Amanecer",
                definitions = listOf(
                    "El momento en que el sol aparece en el horizonte",
                    "El inicio del día"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Aspiración",
                definitions = listOf("Un deseo fuerte de alcanzar algo", "El acto de inhalar aire"),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Artefacto",
                definitions = listOf(
                    "Un objeto hecho por humanos, generalmente antiguo",
                    "Un elemento encontrado en excavaciones"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Asamblea",
                definitions = listOf(
                    "Una reunión de personas para un propósito específico",
                    "Un grupo que toma decisiones conjuntas"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Altitud",
                definitions = listOf(
                    "La altura sobre el nivel del mar",
                    "La distancia vertical desde un punto de referencia"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Aesthetics",
                definitions = listOf(
                    "The philosophical study of beauty and taste",
                    "A set of principles underlying artistic work"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Apocryphal",
                definitions = listOf(
                    "Of doubtful authenticity",
                    "Falsely attributed or fabricated"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Anthropomorphic",
                definitions = listOf(
                    "Attributing human characteristics to non-human entities",
                    "A concept used in mythology and art"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Amalgamation",
                definitions = listOf(
                    "The action or process of combining or uniting",
                    "A fusion or blend of diverse elements"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Anachronism",
                definitions = listOf(
                    "A thing belonging to a different time period",
                    "An error in chronology"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Annihilation",
                definitions = listOf(
                    "Complete destruction or obliteration",
                    "A state of non-existence"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Axiom",
                definitions = listOf(
                    "A statement accepted as true without proof",
                    "A self-evident truth in mathematics or philosophy"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Antithesis",
                definitions = listOf(
                    "A direct opposite or contrast",
                    "A rhetorical device used to emphasize contrast"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Anagram",
                definitions = listOf(
                    "A word formed by rearranging the letters of another",
                    "A type of wordplay"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Archetype",
                definitions = listOf(
                    "A very typical example of a certain thing",
                    "A recurrent symbol or motif in literature or art"
                ),
                letter = Letter.A,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Anacronismo",
                definitions = listOf(
                    "Algo que no corresponde al tiempo en el que está",
                    "Un error en la cronología de los eventos"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Antítesis",
                definitions = listOf(
                    "Una oposición o contraste directo entre dos cosas",
                    "Un recurso retórico usado para enfatizar"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Arquetipo",
                definitions = listOf(
                    "Un ejemplo típico de algo",
                    "Un símbolo recurrente en literatura o arte"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Axioma",
                definitions = listOf(
                    "Una verdad evidente que no requiere demostración",
                    "Un principio fundamental en matemáticas o filosofía"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Anamorfosis",
                definitions = listOf(
                    "Una imagen que requiere una perspectiva específica para verse correctamente",
                    "Un recurso usado en arte y óptica"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Apócrifo",
                definitions = listOf(
                    "De autenticidad dudosa",
                    "Atribuido falsamente a un autor o época"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Abolición",
                definitions = listOf(
                    "El acto de eliminar o suprimir algo",
                    "La derogación de una ley o práctica"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Amalgama",
                definitions = listOf(
                    "Una mezcla o fusión de elementos diferentes",
                    "Una combinación de materiales"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Análisis",
                definitions = listOf(
                    "El estudio detallado de algo para entenderlo",
                    "El proceso de examinar información"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Analogía",
                definitions = listOf(
                    "Una comparación entre dos cosas para explicar algo",
                    "Un razonamiento basado en similitudes"
                ),
                letter = Letter.A,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun b() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Ball",
                definitions = listOf(
                    "A round object used in games or sports",
                    "An event where people dance"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Bat",
                definitions = listOf(
                    "A flying mammal",
                    "A piece of sports equipment used in baseball or cricket"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Box",
                definitions = listOf(
                    "A container typically with a lid",
                    "An enclosed space for storing or carrying things"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Book",
                definitions = listOf("A set of written or printed pages", "A work of literature"),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Bee",
                definitions = listOf(
                    "An insect that produces honey",
                    "A pollinator important for plants"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Bridge",
                definitions = listOf(
                    "A structure built to span a gap",
                    "A card game played with four players"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Bed",
                definitions = listOf(
                    "A piece of furniture for sleeping",
                    "A place to grow plants in a garden"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Boat",
                definitions = listOf(
                    "A small vessel for traveling on water",
                    "A craft used for fishing or recreation"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Bread",
                definitions = listOf(
                    "A staple food made from flour and water",
                    "A type of baked food often sliced for sandwiches"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Button",
                definitions = listOf(
                    "A small fastener for clothes",
                    "A control used to perform an action on a machine or device"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Barco",
                definitions = listOf(
                    "Un medio de transporte acuático",
                    "Un vehículo usado para navegar ríos o mares"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Baño",
                definitions = listOf(
                    "Un lugar para asearse",
                    "El acto de sumergirse en agua para limpieza"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Bebida",
                definitions = listOf(
                    "Un líquido consumido para hidratarse",
                    "Algo que se toma como refresco o para acompañar alimentos"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Beso",
                definitions = listOf(
                    "Un gesto de afecto con los labios",
                    "Una expresión de cariño o amor"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Bola",
                definitions = listOf(
                    "Un objeto redondo usado en juegos",
                    "Una esfera sólida o hueca"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Bebé",
                definitions = listOf(
                    "Un niño recién nacido",
                    "Una persona en las primeras etapas de vida"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Barba",
                definitions = listOf(
                    "El vello que crece en el mentón y mejillas",
                    "Un símbolo de madurez en los hombres"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Bosque",
                definitions = listOf(
                    "Un área grande llena de árboles",
                    "Un hábitat natural para animales y plantas"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Bota",
                definitions = listOf(
                    "Un tipo de calzado alto",
                    "Un recipiente para llevar líquidos"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Banco",
                definitions = listOf(
                    "Un lugar donde se guardan y gestionan finanzas",
                    "Un asiento largo para varias personas"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Barrier",
                definitions = listOf(
                    "An obstacle that prevents movement or access",
                    "A structure that blocks passage"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Boulder",
                definitions = listOf("A large rock", "A massive stone, often rounded"),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Blueprint",
                definitions = listOf("A detailed technical plan", "A guide for creating something"),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Bravery",
                definitions = listOf(
                    "Courage in the face of danger",
                    "The quality of being fearless"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Banquet",
                definitions = listOf("A large formal meal", "A celebratory feast"),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Balance",
                definitions = listOf(
                    "An even distribution of weight",
                    "The ability to stay upright and steady"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Boundary",
                definitions = listOf("A dividing line or limit", "The edge of a defined area"),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Betrayal",
                definitions = listOf("An act of disloyalty", "The violation of trust"),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Bulwark",
                definitions = listOf(
                    "A defensive wall",
                    "Something serving as a protection or defense"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Beacon",
                definitions = listOf(
                    "A light or signal for guidance",
                    "A source of inspiration or hope"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Barrera",
                definitions = listOf(
                    "Un obstáculo que impide el paso",
                    "Una estructura que bloquea el movimiento"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Bolígrafo",
                definitions = listOf(
                    "Un instrumento para escribir con tinta",
                    "Un tipo de pluma moderna"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Boticario",
                definitions = listOf(
                    "Una persona que prepara y vende medicinas",
                    "Un farmacéutico en términos antiguos"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Burla",
                definitions = listOf(
                    "Un comentario sarcástico o irónico",
                    "Una acción para ridiculizar a alguien"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Balance",
                definitions = listOf(
                    "Un estado de equilibrio",
                    "Una distribución uniforme del peso"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Bodega",
                definitions = listOf(
                    "Un lugar para almacenar vino",
                    "Un almacén de productos en general"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Baluarte",
                definitions = listOf(
                    "Una fortificación para defensa",
                    "Un símbolo de protección o resistencia"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Bastón",
                definitions = listOf(
                    "Un palo usado para apoyarse al caminar",
                    "Un símbolo de autoridad o liderazgo"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Bendición",
                definitions = listOf(
                    "Un favor divino o aprobación",
                    "Un deseo positivo para alguien"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Barítono",
                definitions = listOf(
                    "Una voz masculina intermedia entre tenor y bajo",
                    "Un cantante con esta voz"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Benevolence",
                definitions = listOf(
                    "The quality of being kind and charitable",
                    "A disposition to do good"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Bifurcation",
                definitions = listOf(
                    "The division of something into two parts",
                    "A point where splitting occurs"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Bibliophile",
                definitions = listOf(
                    "A person who loves or collects books",
                    "An enthusiast for reading and literature"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Bipartisan",
                definitions = listOf(
                    "Involving or supported by two political parties",
                    "A cooperative effort across opposing groups"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Byzantine",
                definitions = listOf(
                    "Excessively complicated or intricate",
                    "Relating to the Byzantine Empire"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Brevity",
                definitions = listOf("Conciseness in speech or writing", "Shortness in duration"),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Burlesque",
                definitions = listOf(
                    "A literary or dramatic work mocking a subject",
                    "A variety show with humorous skits"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Brigand",
                definitions = listOf(
                    "A member of a gang of robbers",
                    "A bandit, especially in a forest or mountain region"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Bastion",
                definitions = listOf(
                    "A fortified place or stronghold",
                    "A strong defense or advocate for a principle"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ballast",
                definitions = listOf(
                    "Heavy material used to stabilize a ship or structure",
                    "A counterbalance for stability"
                ),
                letter = Letter.B,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Benevolencia",
                definitions = listOf(
                    "La inclinación a hacer el bien",
                    "Una disposición amable hacia los demás"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Bifurcación",
                definitions = listOf(
                    "La división de algo en dos partes",
                    "Un punto donde ocurre una separación"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Biblioteca",
                definitions = listOf(
                    "Un lugar donde se almacenan libros",
                    "Una colección organizada de libros y otros medios"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Biología",
                definitions = listOf(
                    "El estudio de los seres vivos",
                    "Una rama de la ciencia que explora la vida y sus procesos"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Burócrata",
                definitions = listOf(
                    "Una persona que trabaja en una administración pública",
                    "Alguien con poder en sistemas administrativos"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Barítono",
                definitions = listOf(
                    "Una voz masculina entre tenor y bajo",
                    "Un cantante con este tipo de voz"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Bastión",
                definitions = listOf(
                    "Una fortificación para defensa",
                    "Un símbolo de resistencia o protección"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Bilateral",
                definitions = listOf(
                    "Que involucra a dos partes o lados",
                    "Relativo a ambos lados de algo"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Boticario",
                definitions = listOf(
                    "Un farmacéutico en términos antiguos",
                    "Una persona que prepara medicinas"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Burguesía",
                definitions = listOf(
                    "La clase media acomodada en términos históricos",
                    "Un grupo social asociado al comercio y la industria"
                ),
                letter = Letter.B,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun c() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Cat",
                definitions = listOf(
                    "A small domesticated carnivorous mammal",
                    "An animal often kept as a pet"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Car",
                definitions = listOf(
                    "A road vehicle powered by an engine",
                    "A machine used for transportation"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Cake",
                definitions = listOf(
                    "A sweet baked dessert",
                    "A food often served during celebrations"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Chair",
                definitions = listOf("A piece of furniture for sitting", "A seat with a backrest"),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Clock",
                definitions = listOf(
                    "A device used to measure time",
                    "An instrument with hands to show the time of day"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Cloud",
                definitions = listOf(
                    "A visible mass of water vapor in the sky",
                    "A shape often seen during rainy weather"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Coin",
                definitions = listOf(
                    "A small round piece of metal used as money",
                    "A currency made of metal"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Cup",
                definitions = listOf(
                    "A small container used to drink liquids",
                    "An item often used for tea or coffee"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Crab",
                definitions = listOf(
                    "A sea creature with a hard shell",
                    "An animal with pincers and a flat body"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Cave",
                definitions = listOf(
                    "A large underground chamber",
                    "A natural hollow space in the earth"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Casa",
                definitions = listOf(
                    "Un lugar donde vive la gente",
                    "Un edificio que sirve como hogar"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Cama",
                definitions = listOf("Un mueble para dormir", "Un objeto con colchón y sábanas"),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Coche",
                definitions = listOf(
                    "Un vehículo para transporte terrestre",
                    "Un automóvil con motor"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Cielo",
                definitions = listOf(
                    "El espacio sobre la tierra donde están las nubes",
                    "El firmamento visible desde el suelo"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Cola",
                definitions = listOf(
                    "La parte trasera de un animal",
                    "Una fila de personas esperando"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Calle",
                definitions = listOf(
                    "Un camino en una ciudad o pueblo",
                    "Una vía pública para vehículos y personas"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Copa",
                definitions = listOf(
                    "Un recipiente usado para beber vino",
                    "Un premio en forma de trofeo"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Calor",
                definitions = listOf(
                    "La sensación causada por altas temperaturas",
                    "Una condición que provoca sudoración"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Cangrejo",
                definitions = listOf(
                    "Un animal marino con pinzas",
                    "Una criatura con caparazón duro que vive en la playa"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Cueva",
                definitions = listOf(
                    "Un espacio subterráneo natural",
                    "Un lugar oscuro dentro de una montaña o roca"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Courage",
                definitions = listOf(
                    "The ability to face fear or danger",
                    "A mental strength to persevere through challenges"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Calendar",
                definitions = listOf(
                    "A system for organizing days and months",
                    "A chart showing dates and events"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Cascade",
                definitions = listOf(
                    "A small waterfall",
                    "A process where something occurs in stages"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Caution",
                definitions = listOf(
                    "Care taken to avoid danger or mistakes",
                    "A warning to be careful"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ceremony",
                definitions = listOf(
                    "A formal event performed on special occasions",
                    "A ritual or series of actions with symbolic meaning"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Citizen",
                definitions = listOf(
                    "A legally recognized member of a country",
                    "A person who lives in a particular community"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Clarity",
                definitions = listOf(
                    "The quality of being clear and understandable",
                    "The state of being transparent or pure"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Column",
                definitions = listOf(
                    "A vertical structure used to support a building",
                    "A regular article in a newspaper or magazine"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Compass",
                definitions = listOf(
                    "An instrument used for navigation",
                    "A device that shows directions like north or south"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Conflict",
                definitions = listOf(
                    "A serious disagreement or argument",
                    "A clash between opposing forces or ideas"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Coraje",
                definitions = listOf(
                    "La habilidad para enfrentar el miedo o el peligro",
                    "La fortaleza mental para superar desafíos"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Calendario",
                definitions = listOf(
                    "Un sistema para organizar días y meses",
                    "Un cuadro que muestra fechas y eventos"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Cascada",
                definitions = listOf(
                    "Una pequeña caída de agua",
                    "Un flujo continuo que se desarrolla en etapas"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Cautela",
                definitions = listOf(
                    "La precaución para evitar peligros o errores",
                    "Una advertencia para proceder con cuidado"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ceremonia",
                definitions = listOf(
                    "Un evento formal realizado en ocasiones especiales",
                    "Un ritual con significado simbólico"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ciudadano",
                definitions = listOf(
                    "Un miembro reconocido legalmente de un país",
                    "Una persona que pertenece a una comunidad específica"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Claridad",
                definitions = listOf(
                    "La cualidad de ser claro y comprensible",
                    "El estado de ser transparente o puro"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Columna",
                definitions = listOf(
                    "Una estructura vertical que sostiene un edificio",
                    "Un artículo periódico en un diario o revista"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Compás",
                definitions = listOf(
                    "Un instrumento utilizado para la navegación",
                    "Un dispositivo que indica direcciones como el norte o el sur"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Conflicto",
                definitions = listOf(
                    "Un desacuerdo serio o argumento",
                    "Un choque entre fuerzas o ideas opuestas"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            )
        )

        private fun d() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Dog",
                definitions = listOf(
                    "A domesticated carnivorous mammal",
                    "A common pet known for loyalty"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Door",
                definitions = listOf(
                    "A movable barrier for opening or closing an entrance",
                    "A means of access or entry"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Day",
                definitions = listOf("A period of 24 hours", "The time between sunrise and sunset"),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Desk",
                definitions = listOf(
                    "A piece of furniture used for writing or working",
                    "A flat surface for tasks like studying"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Duck",
                definitions = listOf(
                    "A water bird with webbed feet",
                    "An animal that often quacks"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Drum",
                definitions = listOf(
                    "A musical instrument played by beating",
                    "A cylindrical container"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Dish",
                definitions = listOf(
                    "A container used for serving food",
                    "A particular kind of food prepared in a specific way"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Dust",
                definitions = listOf(
                    "Tiny particles of dirt or matter",
                    "A fine powder often found on surfaces"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Dream",
                definitions = listOf(
                    "A series of thoughts or images during sleep",
                    "A cherished aspiration or ambition"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Doll",
                definitions = listOf(
                    "A small figure of a human used as a toy",
                    "A model of a person for children to play with"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Día",
                definitions = listOf(
                    "Un período de 24 horas",
                    "El tiempo entre el amanecer y el atardecer"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Dedo",
                definitions = listOf(
                    "Una de las extremidades de la mano o el pie",
                    "Una parte del cuerpo humano usada para tocar o agarrar"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Dios",
                definitions = listOf(
                    "Una deidad o ser supremo",
                    "Una figura venerada en muchas religiones"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Delfín",
                definitions = listOf(
                    "Un mamífero marino conocido por su inteligencia",
                    "Un animal que vive en el océano y salta del agua"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Danza",
                definitions = listOf(
                    "El arte de moverse al ritmo de la música",
                    "Un tipo de expresión corporal"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Don",
                definitions = listOf(
                    "Un regalo o habilidad especial",
                    "Un título de respeto en algunos países hispanohablantes"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Dado",
                definitions = listOf(
                    "Un cubo usado en juegos para generar números al azar",
                    "Un objeto con caras numeradas del 1 al 6"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ducha",
                definitions = listOf(
                    "El acto de lavarse bajo agua corriente",
                    "Un dispositivo que expulsa agua para aseo personal"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Durazno",
                definitions = listOf(
                    "Una fruta jugosa con un hueso en el centro",
                    "Un alimento de sabor dulce y piel suave"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Dibujo",
                definitions = listOf(
                    "Una representación gráfica hecha a mano",
                    "Una obra creada con lápiz, pluma o colores"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Drought",
                definitions = listOf(
                    "A prolonged period of abnormally low rainfall",
                    "A shortage of water in a region"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Debate",
                definitions = listOf(
                    "A formal discussion on a particular topic",
                    "An argument or exchange of opposing views"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Delight",
                definitions = listOf(
                    "A feeling of great pleasure",
                    "Something that brings joy or satisfaction"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Dense",
                definitions = listOf(
                    "Closely packed together",
                    "Difficult to understand due to complexity"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Domain",
                definitions = listOf(
                    "An area of territory owned or controlled by someone",
                    "A specific field of knowledge or activity"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Dynamic",
                definitions = listOf(
                    "Characterized by constant change or activity",
                    "A force that stimulates development or progress"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Dismal",
                definitions = listOf(
                    "Causing a mood of gloom or depression",
                    "Lacking merit or interest"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Decipher",
                definitions = listOf(
                    "To interpret or make sense of something difficult to understand",
                    "To convert a coded message into normal text"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Dormant",
                definitions = listOf(
                    "In a state of rest or inactivity",
                    "Not active or growing but capable of becoming so"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Devotion",
                definitions = listOf(
                    "Love, loyalty, or enthusiasm for a person or activity",
                    "The act of dedicating oneself to a cause or purpose"
                ),
                letter = Letter.D,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Duda",
                definitions = listOf(
                    "La falta de certeza sobre algo",
                    "Un estado de indecisión o vacilación"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Decisión",
                definitions = listOf(
                    "El acto de elegir entre varias opciones",
                    "Un acuerdo firme para actuar de cierta manera"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Dinámica",
                definitions = listOf(
                    "Un proceso de cambio continuo",
                    "Una fuerza o sistema que genera movimiento"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Desafío",
                definitions = listOf(
                    "Una tarea difícil que pone a prueba habilidades",
                    "Una invitación a competir o superar algo"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Determinación",
                definitions = listOf(
                    "La firmeza para alcanzar un objetivo",
                    "El acto de decidir algo con resolución"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Destino",
                definitions = listOf(
                    "El lugar final al que se dirige alguien o algo",
                    "El supuesto poder que guía los eventos en la vida"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Derrota",
                definitions = listOf(
                    "La pérdida de una batalla o competencia",
                    "El fracaso en alcanzar un objetivo"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Diploma",
                definitions = listOf(
                    "Un documento que certifica la finalización de un curso",
                    "Un título académico otorgado por una institución"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Dibujo",
                definitions = listOf(
                    "Una representación gráfica hecha a mano",
                    "Un boceto o ilustración en papel"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Disfraz",
                definitions = listOf(
                    "Ropa usada para representar a alguien o algo más",
                    "Un atuendo que oculta o cambia la apariencia"
                ),
                letter = Letter.D,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Chronology",
                definitions = listOf(
                    "The arrangement of events in time order",
                    "A study of historical records and dates"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Conundrum",
                definitions = listOf(
                    "A difficult or confusing problem or question",
                    "A riddle, often with a pun in its answer"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Concatenation",
                definitions = listOf(
                    "The action of linking things together",
                    "A series of interconnected events or ideas"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Counterintuitive",
                definitions = listOf(
                    "Contrary to what one would intuitively expect",
                    "Opposite to common sense or logic"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Cognizance",
                definitions = listOf(
                    "Awareness or knowledge of something",
                    "Recognition or notice of an issue"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Cacophony",
                definitions = listOf(
                    "A harsh, discordant mixture of sounds",
                    "An unpleasant noise that lacks harmony"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Choreography",
                definitions = listOf(
                    "The sequence of steps and movements in a dance",
                    "The art of designing dance performances"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Contingency",
                definitions = listOf(
                    "A future event that may or may not occur",
                    "A provision for an unforeseen circumstance"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Cryptography",
                definitions = listOf(
                    "The practice of secure communication through codes",
                    "The study of encoding and decoding information"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Cartography",
                definitions = listOf(
                    "The science or practice of making maps",
                    "A field that involves map design and analysis"
                ),
                letter = Letter.C,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Cronología",
                definitions = listOf(
                    "La organización de eventos en orden temporal",
                    "El estudio de registros históricos y fechas"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Conundro",
                definitions = listOf(
                    "Un problema o pregunta difícil de resolver",
                    "Un acertijo con una respuesta ingeniosa"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Concatenación",
                definitions = listOf(
                    "La acción de enlazar cosas en una secuencia",
                    "Una serie de eventos o ideas interconectadas"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Contraintuitivo",
                definitions = listOf(
                    "Contrario a lo que se esperaría intuitivamente",
                    "Opuesto al sentido común o la lógica"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Cognición",
                definitions = listOf(
                    "La capacidad de adquirir conocimiento y comprensión",
                    "El proceso mental relacionado con el aprendizaje y la percepción"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Cacofonía",
                definitions = listOf(
                    "Una mezcla discordante de sonidos",
                    "Un ruido desagradable que carece de armonía"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Coreografía",
                definitions = listOf(
                    "La secuencia de movimientos en una danza",
                    "El arte de diseñar representaciones de baile"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Contingencia",
                definitions = listOf(
                    "Un evento futuro que puede o no ocurrir",
                    "Una provisión para una circunstancia imprevista"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Criptografía",
                definitions = listOf(
                    "La práctica de la comunicación segura mediante códigos",
                    "El estudio de cómo codificar y decodificar información"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Cartografía",
                definitions = listOf(
                    "La ciencia o práctica de hacer mapas",
                    "Un campo que involucra el diseño y análisis de mapas"
                ),
                letter = Letter.C,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
        )

        private fun e() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Ear",
                definitions = listOf(
                    "The organ of hearing and balance",
                    "The part of the body used to detect sound"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Egg",
                definitions = listOf(
                    "An oval object laid by birds and reptiles",
                    "A common food item with a shell and yolk"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Earth",
                definitions = listOf(
                    "The planet we live on",
                    "The soil or ground where plants grow"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Eye",
                definitions = listOf("The organ of sight", "A part of the body used to see"),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Eat",
                definitions = listOf(
                    "To consume food",
                    "The act of putting food in the mouth and swallowing it"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Edge",
                definitions = listOf(
                    "The boundary or border of something",
                    "A sharp side of a blade"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Elk",
                definitions = listOf(
                    "A large deer with antlers",
                    "An animal found in forests and plains"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Echo",
                definitions = listOf(
                    "A sound that reflects off a surface and is heard again",
                    "A repeated sound caused by reflection"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Elf",
                definitions = listOf(
                    "A small, mythical creature with magical powers",
                    "A character often seen in fairy tales"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Exit",
                definitions = listOf(
                    "A way out of a building or place",
                    "The act of leaving a location"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Eco",
                definitions = listOf(
                    "Un sonido que se repite al reflejarse en una superficie",
                    "Una reverberación que se escucha de nuevo"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Edad",
                definitions = listOf(
                    "El tiempo que ha vivido una persona o cosa",
                    "Un período de tiempo en la vida de alguien"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Eje",
                definitions = listOf(
                    "Una línea que atraviesa el centro de un objeto",
                    "Un soporte sobre el que gira algo"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Elfo",
                definitions = listOf(
                    "Una criatura pequeña y mágica de cuentos",
                    "Un ser mítico asociado con la naturaleza"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Estrella",
                definitions = listOf(
                    "Un cuerpo celeste que brilla en el cielo",
                    "Un símbolo con puntas que representa luz o éxito"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Espejo",
                definitions = listOf(
                    "Una superficie que refleja imágenes",
                    "Un objeto usado para ver el propio reflejo"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Espada",
                definitions = listOf(
                    "Un arma con una hoja larga y afilada",
                    "Un objeto usado en combates cuerpo a cuerpo"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Escuela",
                definitions = listOf(
                    "Un lugar donde los niños aprenden",
                    "Una institución para la educación formal"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Elefante",
                definitions = listOf(
                    "Un mamífero grande con trompa y colmillos",
                    "Un animal que vive en la selva o la sabana"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Éxito",
                definitions = listOf(
                    "El logro de un objetivo o propósito",
                    "El resultado positivo de un esfuerzo"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Eclipse",
                definitions = listOf(
                    "The obscuring of a celestial body by another",
                    "A temporary loss of significance or power"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Effort",
                definitions = listOf(
                    "A vigorous or determined attempt",
                    "The exertion of physical or mental energy"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Empire",
                definitions = listOf(
                    "An extensive group of states under a single ruler",
                    "A large organization controlled by one person or group"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Engage",
                definitions = listOf(
                    "To participate or become involved in something",
                    "To occupy the attention or efforts of someone"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Emerge",
                definitions = listOf(
                    "To come into view or become visible",
                    "To become apparent or prominent"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Enigma",
                definitions = listOf(
                    "Something that is mysterious or difficult to understand",
                    "A person or thing that is puzzling"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Erosion",
                definitions = listOf(
                    "The gradual destruction of something by natural forces",
                    "The process by which soil or rock is worn away"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Embark",
                definitions = listOf(
                    "To begin a journey or venture",
                    "To go on board a ship or aircraft"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Esteem",
                definitions = listOf(
                    "Respect and admiration for someone or something",
                    "To regard highly or favorably"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Expose",
                definitions = listOf(
                    "To reveal or make something visible",
                    "To leave something unprotected or uncovered"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Ética",
                definitions = listOf(
                    "El estudio de los principios morales",
                    "El conjunto de normas que rigen la conducta humana"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Esencia",
                definitions = listOf(
                    "La naturaleza fundamental de algo",
                    "El extracto concentrado de una sustancia"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Energía",
                definitions = listOf(
                    "La capacidad de realizar un trabajo",
                    "Un recurso que impulsa máquinas o sistemas"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Estructura",
                definitions = listOf(
                    "El diseño o arreglo de las partes de algo",
                    "Un edificio o construcción"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Eslabón",
                definitions = listOf(
                    "Un enlace en una cadena",
                    "Una conexión o vínculo entre cosas"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Equilibrio",
                definitions = listOf(
                    "Un estado de estabilidad o balance",
                    "La distribución uniforme del peso o fuerza"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Exilio",
                definitions = listOf(
                    "La expulsión de alguien de su país",
                    "El estado de vivir fuera de la patria por razones forzadas"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Esfuerzo",
                definitions = listOf(
                    "El uso de energía física o mental para lograr algo",
                    "Una acción ardua para alcanzar una meta"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Exhibición",
                definitions = listOf(
                    "Una muestra o presentación pública de algo",
                    "Un evento donde se expone arte o productos"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ecosistema",
                definitions = listOf(
                    "Una comunidad de seres vivos y su entorno",
                    "El sistema de interacciones entre organismos y su medio"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Ephemeral",
                definitions = listOf(
                    "Lasting for a very short time",
                    "Something that is transitory or fleeting"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Epitome",
                definitions = listOf(
                    "A person or thing that is a perfect example of a quality",
                    "A summary of a written work"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ebullient",
                definitions = listOf(
                    "Full of energy and enthusiasm",
                    "Overflowing with excitement or cheerfulness"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Esoteric",
                definitions = listOf(
                    "Intended for or understood by a small group of people",
                    "Difficult to understand; obscure"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ethereal",
                definitions = listOf(
                    "Extremely delicate and light in a way that seems heavenly",
                    "Relating to the upper regions of space"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Equivocate",
                definitions = listOf(
                    "To use ambiguous language to conceal the truth",
                    "To avoid committing oneself to a clear stance"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Egregious",
                definitions = listOf(
                    "Outstandingly bad or shocking",
                    "Remarkably negative in quality or action"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Entourage",
                definitions = listOf(
                    "A group of people attending or surrounding an important person",
                    "A retinue or escort for someone influential"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Epiphany",
                definitions = listOf(
                    "A sudden realization or insight",
                    "A manifestation of a divine being"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Enmity",
                definitions = listOf(
                    "A state of being actively opposed or hostile to someone",
                    "Deep-seated hatred or ill will"
                ),
                letter = Letter.E,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Epifanía",
                definitions = listOf(
                    "Una manifestación súbita de una idea o verdad",
                    "La festividad cristiana que celebra la adoración de los Reyes Magos"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Esoterismo",
                definitions = listOf(
                    "Conocimientos reservados para un grupo reducido de personas",
                    "Doctrinas o prácticas que son misteriosas o secretas"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Euforia",
                definitions = listOf(
                    "Una sensación intensa de felicidad o bienestar",
                    "Un estado de ánimo positivo y exaltado"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Eloquencia",
                definitions = listOf(
                    "La habilidad para expresarse con claridad y persuasión",
                    "El arte de hablar de manera fluida y convincente"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Eminencia",
                definitions = listOf(
                    "Una posición elevada o destacada en algo",
                    "Un título de respeto otorgado a figuras importantes"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Esteticismo",
                definitions = listOf(
                    "Un enfoque en la belleza y el arte por encima de otros valores",
                    "La teoría de que el arte debe ser bello y no funcional"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ecléctico",
                definitions = listOf(
                    "Que selecciona lo mejor de diferentes fuentes o estilos",
                    "Que combina ideas, métodos o gustos diversos"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Efectismo",
                definitions = listOf(
                    "El uso de efectos llamativos para impresionar",
                    "Un enfoque en causar impacto, a veces en detrimento del contenido"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Eponimia",
                definitions = listOf(
                    "La atribución de un nombre a un lugar, cosa o concepto en honor a una persona",
                    "La relación entre un epónimo y lo que denomina"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Efigie",
                definitions = listOf(
                    "Una representación o imagen de una persona",
                    "Una figura o escultura que simboliza a alguien"
                ),
                letter = Letter.E,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun f() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Fish",
                definitions = listOf(
                    "A cold-blooded animal that lives in water and has gills",
                    "A source of food commonly found in oceans and rivers"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Fire",
                definitions = listOf(
                    "The process of combustion that produces heat and light",
                    "A destructive burning of something"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Flower",
                definitions = listOf(
                    "The reproductive part of a plant",
                    "A colorful part of a plant often used for decoration"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Farm",
                definitions = listOf(
                    "A piece of land used to grow crops or raise animals",
                    "A place where agricultural activities occur"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Fan",
                definitions = listOf(
                    "A device used to create airflow",
                    "A person who admires or supports someone or something"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Fence",
                definitions = listOf(
                    "A barrier used to enclose an area",
                    "A structure for security or decoration around a property"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Fog",
                definitions = listOf(
                    "A thick cloud of tiny water droplets near the ground",
                    "A weather condition that reduces visibility"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Fox",
                definitions = listOf(
                    "A small, wild animal with a bushy tail",
                    "A cunning or clever person"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Fork",
                definitions = listOf(
                    "A utensil with prongs used for eating",
                    "A place where a road or river divides into branches"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Face",
                definitions = listOf(
                    "The front part of a person's head",
                    "An expression or look on someone's face"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Flor",
                definitions = listOf(
                    "La parte reproductiva de una planta",
                    "Un elemento colorido de las plantas usado en decoración"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Fuego",
                definitions = listOf(
                    "El proceso de combustión que produce calor y luz",
                    "Una quema que puede ser destructiva"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Fruta",
                definitions = listOf(
                    "El producto comestible de una planta",
                    "Un alimento dulce o ácido que crece en árboles o arbustos"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Familia",
                definitions = listOf(
                    "Un grupo de personas relacionadas por sangre o matrimonio",
                    "Un núcleo social básico en la sociedad humana"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Falda",
                definitions = listOf(
                    "Una prenda de vestir que cuelga desde la cintura",
                    "Un tipo de ropa usada comúnmente por mujeres"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Fiesta",
                definitions = listOf(
                    "Una reunión social para celebrar algo",
                    "Un evento alegre con música y comida"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Fuente",
                definitions = listOf(
                    "Una estructura que expulsa agua",
                    "El origen o lugar de donde proviene algo"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Fresa",
                definitions = listOf(
                    "Una fruta pequeña, roja y dulce",
                    "Una planta de la cual se obtienen fresas"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Faro",
                definitions = listOf(
                    "Una torre que emite luz para guiar a los barcos",
                    "Un sistema que ayuda a la navegación marítima"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Frío",
                definitions = listOf(
                    "La ausencia de calor o alta temperatura",
                    "Una sensación causada por bajas temperaturas"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Fable",
                definitions = listOf(
                    "A short story that teaches a moral lesson",
                    "A tale often involving animals with human traits"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Faction",
                definitions = listOf(
                    "A small organized group within a larger one",
                    "A group that disagrees with the majority"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Falcon",
                definitions = listOf(
                    "A bird of prey with long pointed wings",
                    "A fast bird known for its hunting skills"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Fatal",
                definitions = listOf("Causing death", "Leading to failure or disaster"),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Famine",
                definitions = listOf(
                    "An extreme scarcity of food",
                    "A situation causing widespread hunger"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Faction",
                definitions = listOf(
                    "A small group within a larger group with different opinions",
                    "A minority group often in conflict with the main body"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Fossil",
                definitions = listOf(
                    "The preserved remains of an ancient organism",
                    "Evidence of life from the geological past"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Facade",
                definitions = listOf(
                    "The front face of a building",
                    "An outward appearance that hides a less pleasant reality"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Fluent",
                definitions = listOf(
                    "Able to speak or write smoothly and effortlessly",
                    "Flowing easily or gracefully"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Friction",
                definitions = listOf(
                    "The resistance when one surface rubs against another",
                    "Conflict or disagreement between people"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Fábula",
                definitions = listOf(
                    "Un relato breve con una enseñanza moral",
                    "Una narración protagonizada por animales que hablan o actúan como humanos"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Faro",
                definitions = listOf(
                    "Una torre que emite luz para guiar a los navegantes",
                    "Un símbolo de guía o esperanza"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Falacia",
                definitions = listOf(
                    "Un razonamiento engañoso o incorrecto",
                    "Un error lógico en un argumento"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Fallecer",
                definitions = listOf(
                    "Perder la vida o morir",
                    "Un término formal para referirse a la muerte"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Feria",
                definitions = listOf(
                    "Un evento donde se exhiben productos o servicios",
                    "Un lugar de diversión con atracciones y juegos"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Frasco",
                definitions = listOf(
                    "Un recipiente de vidrio o plástico",
                    "Un envase pequeño usado para líquidos o sustancias"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Frontera",
                definitions = listOf(
                    "La línea que divide dos territorios",
                    "Un límite entre países o regiones"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Fricción",
                definitions = listOf(
                    "La resistencia al movimiento entre dos superficies en contacto",
                    "Un desacuerdo o conflicto entre personas o grupos"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Fósil",
                definitions = listOf(
                    "Los restos preservados de un organismo antiguo",
                    "Un vestigio de vida del pasado geológico"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Fortaleza",
                definitions = listOf(
                    "Una estructura defensiva como un castillo",
                    "La capacidad de resistir desafíos o adversidades"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Facade",
                definitions = listOf(
                    "The front face of a building, often decorative",
                    "An outward appearance that hides a less pleasant reality"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Fathom",
                definitions = listOf(
                    "To understand something deeply and completely",
                    "A unit of measurement used to measure water depth"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Frenzy",
                definitions = listOf(
                    "A state of uncontrolled excitement or wild behavior",
                    "Intense, often chaotic activity"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Fervor",
                definitions = listOf(
                    "Intense passion or enthusiasm",
                    "A strong and sincere emotion or feeling"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Fickle",
                definitions = listOf(
                    "Changing frequently, especially in loyalties or interests",
                    "Lacking consistency or stability"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Fidelity",
                definitions = listOf(
                    "Faithfulness to a person, cause, or belief",
                    "The degree of exactness in reproduction or details"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Fortitude",
                definitions = listOf(
                    "Courage and resilience in facing adversity",
                    "Mental and emotional strength to withstand challenges"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Feasibility",
                definitions = listOf(
                    "The practicality or possibility of something being accomplished",
                    "A measure of how easily something can be done"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Forlorn",
                definitions = listOf(
                    "Pitifully sad or abandoned",
                    "Hopeless or desolate in appearance or feeling"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Frivolous",
                definitions = listOf(
                    "Not having any serious purpose or value",
                    "Carefree and lacking in seriousness"
                ),
                letter = Letter.F,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Fachada",
                definitions = listOf(
                    "La parte frontal de un edificio",
                    "La apariencia exterior que oculta algo distinto en el interior"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Férreo",
                definitions = listOf(
                    "Que es firme, inflexible o inquebrantable",
                    "Relativo al hierro o que tiene su resistencia"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Fascismo",
                definitions = listOf(
                    "Un sistema político autoritario y nacionalista",
                    "Una ideología que exalta al estado por encima de los derechos individuales"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Falsedad",
                definitions = listOf(
                    "La cualidad de ser falso o engañoso",
                    "Una declaración que no se ajusta a la verdad"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Fervor",
                definitions = listOf(
                    "Una pasión intensa por algo",
                    "Un entusiasmo ardiente y sincero"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Fiabilidad",
                definitions = listOf(
                    "La capacidad de ser confiable y consistente",
                    "La cualidad de cumplir con lo prometido o esperado"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Finitud",
                definitions = listOf(
                    "La condición de ser limitado o tener un final",
                    "La cualidad de algo que no es infinito"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Foráneo",
                definitions = listOf(
                    "Que es extranjero o viene de otro lugar",
                    "Algo que no pertenece al lugar en el que se encuentra"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Frivolidad",
                definitions = listOf(
                    "La falta de seriedad o importancia",
                    "Una actitud ligera o superficial hacia temas importantes"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Frustración",
                definitions = listOf(
                    "El sentimiento de insatisfacción por no lograr algo",
                    "Un estado emocional generado por la imposibilidad de cumplir un objetivo"
                ),
                letter = Letter.F,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun g() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Game",
                definitions = listOf(
                    "An activity for amusement or fun",
                    "A structured form of play with rules"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Garden",
                definitions = listOf(
                    "A piece of land for growing plants",
                    "An area used for cultivating flowers or vegetables"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Gate",
                definitions = listOf(
                    "A hinged barrier used to close an opening",
                    "An entry point to a fenced area or building"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Gift",
                definitions = listOf(
                    "Something given voluntarily without payment",
                    "A present for someone special"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Glass",
                definitions = listOf(
                    "A transparent material made from sand",
                    "A container used for drinking liquids"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Goat",
                definitions = listOf(
                    "A domesticated animal kept for milk or meat",
                    "A small mammal with horns and a beard"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Gold",
                definitions = listOf("A precious yellow metal", "Something valuable or desirable"),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Goal",
                definitions = listOf("An aim or desired result", "A point scored in sports"),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Glue",
                definitions = listOf(
                    "A substance used to stick things together",
                    "An adhesive material"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Grain",
                definitions = listOf(
                    "The seeds of crops such as wheat or corn",
                    "A small, hard particle or piece of something"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Gato",
                definitions = listOf(
                    "Un mamífero doméstico con bigotes",
                    "Un animal conocido por su agilidad y sigilo"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Globo",
                definitions = listOf(
                    "Un objeto inflable hecho de goma",
                    "Algo que flota en el aire cuando se llena de gas"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Grano",
                definitions = listOf(
                    "Una semilla pequeña usada como alimento",
                    "Un pequeño fragmento o partícula"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Goma",
                definitions = listOf(
                    "Una sustancia elástica usada para borrar",
                    "Un material flexible derivado del caucho"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Gorro",
                definitions = listOf(
                    "Una prenda para cubrir la cabeza",
                    "Un accesorio usado para protegerse del frío"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Gallina",
                definitions = listOf(
                    "Un ave doméstica que pone huevos",
                    "Un animal criado para carne y huevos"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Guante",
                definitions = listOf(
                    "Una prenda que cubre la mano",
                    "Un accesorio para proteger o mantener las manos calientes"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Gigante",
                definitions = listOf(
                    "Un ser imaginario de gran tamaño",
                    "Una persona o cosa excepcionalmente grande"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Gas",
                definitions = listOf(
                    "Una sustancia que no tiene forma fija",
                    "Un combustible utilizado para cocinar o calentar"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Guitarra",
                definitions = listOf(
                    "Un instrumento musical de cuerdas",
                    "Un objeto usado para tocar música"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Galaxy",
                definitions = listOf(
                    "A system of millions or billions of stars, together with gas and dust",
                    "A large group of celestial objects bound by gravity"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Gesture",
                definitions = listOf(
                    "A movement of part of the body to express an idea",
                    "A signal or action to communicate without words"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Gravity",
                definitions = listOf(
                    "The force that attracts objects toward the Earth",
                    "The seriousness or importance of a situation"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Geometry",
                definitions = listOf(
                    "The branch of mathematics concerned with shapes and space",
                    "A study of points, lines, surfaces, and solids"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Glacier",
                definitions = listOf(
                    "A large mass of ice moving slowly over land",
                    "A natural formation of ice found in cold regions"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Glimmer",
                definitions = listOf(
                    "A faint or unsteady light",
                    "A small sign of something positive or hopeful"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Gateway",
                definitions = listOf(
                    "An entrance or passage that leads to another area",
                    "A means of access to a destination or goal"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Guild",
                definitions = listOf(
                    "An association of people with similar interests or skills",
                    "An organization for mutual aid or pursuit of a common goal"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Genetics",
                definitions = listOf(
                    "The study of heredity and inherited traits",
                    "A branch of biology dealing with DNA and genes"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Guardian",
                definitions = listOf(
                    "A person who protects or takes care of someone or something",
                    "A legal representative for a minor or dependent"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Galaxia",
                definitions = listOf(
                    "Un sistema de estrellas, gas y polvo unidos por gravedad",
                    "Una gran agrupación de cuerpos celestes en el espacio"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Gesto",
                definitions = listOf(
                    "Un movimiento corporal que expresa algo",
                    "Una acción que transmite un mensaje sin palabras"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Gravedad",
                definitions = listOf(
                    "La fuerza que atrae los objetos hacia la Tierra",
                    "La seriedad o importancia de una situación"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Geometría",
                definitions = listOf(
                    "La rama de las matemáticas que estudia formas y espacios",
                    "El análisis de puntos, líneas y figuras geométricas"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Glaciar",
                definitions = listOf(
                    "Una gran masa de hielo que se mueve lentamente",
                    "Una formación natural de hielo en regiones frías"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Guía",
                definitions = listOf(
                    "Una persona que orienta o dirige a otros",
                    "Un documento que contiene información útil"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Gema",
                definitions = listOf(
                    "Una piedra preciosa o semipreciosa",
                    "Algo valioso que se utiliza en joyería"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Genética",
                definitions = listOf(
                    "El estudio de la herencia y los genes",
                    "Una rama de la biología centrada en el ADN y las características hereditarias"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Guardia",
                definitions = listOf(
                    "Una persona encargada de la protección de algo",
                    "Una posición o rol de vigilancia y defensa"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Gremio",
                definitions = listOf(
                    "Una asociación de personas con intereses comunes",
                    "Un grupo organizado para ayudar a sus miembros en una actividad específica"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Galvanize",
                definitions = listOf(
                    "To shock or excite someone into taking action",
                    "To coat iron or steel with a layer of zinc to prevent rust"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gratuitous",
                definitions = listOf("Unnecessary or unwarranted", "Given or done free of charge"),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gregarious",
                definitions = listOf(
                    "Fond of company and sociable",
                    "Living in groups or flocks as an animal behavior"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gesticulate",
                definitions = listOf(
                    "To use dramatic gestures instead of speaking",
                    "To emphasize speech with movements of the hands"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gargantuan",
                definitions = listOf("Enormous in size or scale", "Exceedingly large or massive"),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Grandiloquent",
                definitions = listOf(
                    "Pompous or extravagant in language or style",
                    "Using high-sounding words to impress others"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Guile",
                definitions = listOf(
                    "Clever and deceitful behavior",
                    "Cunning intelligence used to achieve a goal"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Genesis",
                definitions = listOf(
                    "The origin or formation of something",
                    "The beginning of a significant process or event"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gaunt",
                definitions = listOf("Extremely thin and bony", "Bleak or desolate in appearance"),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gamut",
                definitions = listOf(
                    "The complete range or scope of something",
                    "A full spectrum of different elements or aspects"
                ),
                letter = Letter.G,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Galvanizar",
                definitions = listOf(
                    "Estimular o incitar a alguien a la acción",
                    "Cubrir un metal con una capa de zinc para evitar la corrosión"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gratuito",
                definitions = listOf(
                    "Algo que se da o hace sin costo",
                    "Innecesario o injustificado"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gregario",
                definitions = listOf(
                    "Que vive en grupos o comunidades",
                    "Una persona sociable y que disfruta de la compañía"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gesticulación",
                definitions = listOf(
                    "El acto de expresar algo mediante movimientos de las manos",
                    "Un recurso utilizado para enfatizar palabras o emociones"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gargantuesco",
                definitions = listOf(
                    "De tamaño o proporciones gigantescas",
                    "Algo excesivamente grande o descomunal"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Grandilocuente",
                definitions = listOf(
                    "Que usa palabras exageradas para impresionar",
                    "De estilo pomposo o ampuloso en el habla"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Guile",
                definitions = listOf(
                    "Astucia o habilidad para engañar",
                    "Inteligencia usada con fines engañosos o estratégicos"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Génesis",
                definitions = listOf(
                    "El origen o comienzo de algo",
                    "El punto de partida de un proceso significativo"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gaunt",
                definitions = listOf(
                    "Muy delgado y con apariencia agotada",
                    "Algo desolado o sombrío en apariencia"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Gama",
                definitions = listOf(
                    "El rango completo de algo",
                    "Un espectro que abarca todos los aspectos posibles"
                ),
                letter = Letter.G,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun h() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Hat",
                definitions = listOf(
                    "A piece of clothing worn on the head",
                    "A head covering for warmth or decoration"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "House",
                definitions = listOf(
                    "A building for people to live in",
                    "A home or place of residence"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Hand",
                definitions = listOf(
                    "The part of the body at the end of the arm",
                    "A pointer on a clock that indicates time"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Horse",
                definitions = listOf(
                    "A large domesticated animal used for riding",
                    "An animal often associated with farms or racing"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Heart",
                definitions = listOf(
                    "An organ that pumps blood through the body",
                    "A symbol often used to represent love"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Hill",
                definitions = listOf(
                    "A raised area of land smaller than a mountain",
                    "A sloping surface or incline"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Hero",
                definitions = listOf(
                    "A person admired for bravery or great achievements",
                    "A central figure in a story or myth"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Home",
                definitions = listOf(
                    "The place where one lives",
                    "A feeling of comfort and belonging"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Hut",
                definitions = listOf(
                    "A small, simple shelter or dwelling",
                    "A basic building often found in rural areas"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Hair",
                definitions = listOf(
                    "The strands that grow from the skin of mammals",
                    "A covering of filaments found on the head of humans"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Huevo",
                definitions = listOf(
                    "Un objeto ovalado producido por aves",
                    "Un alimento común con cáscara y yema"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Hoja",
                definitions = listOf(
                    "La parte de una planta que es verde y plana",
                    "Un pedazo de papel"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Hilo",
                definitions = listOf(
                    "Un material delgado usado para coser",
                    "Un elemento que conecta o une cosas"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Humo",
                definitions = listOf(
                    "El vapor o gas producido por la combustión",
                    "Una nube que resulta de quemar algo"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Horno",
                definitions = listOf(
                    "Un aparato para cocinar o hornear",
                    "Un lugar donde se generan altas temperaturas para preparar alimentos"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Huella",
                definitions = listOf(
                    "La marca dejada por un pie o zapato",
                    "Una señal que deja algo en una superficie"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Hombro",
                definitions = listOf(
                    "La parte del cuerpo que conecta el brazo al torso",
                    "Un punto de soporte para cargar algo"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Helado",
                definitions = listOf(
                    "Un postre frío hecho de leche y azúcar",
                    "Un alimento dulce y congelado"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Hierba",
                definitions = listOf(
                    "Una planta pequeña de hojas verdes",
                    "Un término para el césped o plantas silvestres"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Hacha",
                definitions = listOf(
                    "Una herramienta usada para cortar madera",
                    "Un objeto con una hoja afilada y un mango"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Harmony",
                definitions = listOf(
                    "A pleasing arrangement of parts",
                    "The combination of musical notes to produce a pleasing effect"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Harvest",
                definitions = listOf(
                    "The process of gathering crops",
                    "The result or product of an effort"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Horizon",
                definitions = listOf(
                    "The line where the earth meets the sky",
                    "The limit of a person's knowledge or experience"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Heritage",
                definitions = listOf(
                    "Property or traditions passed down from previous generations",
                    "Cultural or natural assets inherited from the past"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Hostile",
                definitions = listOf(
                    "Unfriendly or antagonistic in attitude",
                    "Relating to an enemy or conflict"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Habitat",
                definitions = listOf(
                    "The natural environment of an animal or plant",
                    "A place where a particular organism lives and thrives"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Haste",
                definitions = listOf(
                    "Excessive speed or urgency of movement",
                    "A quick action often done without enough care"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Heirloom",
                definitions = listOf(
                    "A valuable object passed down through generations",
                    "An antique or family treasure"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Hinder",
                definitions = listOf(
                    "To create difficulties for someone or something",
                    "To delay or obstruct progress"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Hollow",
                definitions = listOf(
                    "Having a hole or empty space inside",
                    "Lacking real value or sincerity"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Hábito",
                definitions = listOf(
                    "Una costumbre adquirida por repetición",
                    "Un patrón de comportamiento regular"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Hacienda",
                definitions = listOf(
                    "Una gran finca agrícola",
                    "Un sistema económico basado en grandes propiedades rurales"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Horizonte",
                definitions = listOf(
                    "La línea donde el cielo y la tierra parecen unirse",
                    "El alcance de conocimiento o experiencia de una persona"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Herencia",
                definitions = listOf(
                    "Lo que se transmite de una generación a otra",
                    "Un legado cultural, genético o material"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Hostilidad",
                definitions = listOf(
                    "Una actitud o comportamiento agresivo",
                    "Un estado de enemistad o conflicto"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Hábitat",
                definitions = listOf(
                    "El entorno natural de un organismo",
                    "Un lugar donde una especie vive y se desarrolla"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Hastío",
                definitions = listOf(
                    "Un sentimiento de aburrimiento o cansancio",
                    "Falta de interés o entusiasmo por algo"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Heredero",
                definitions = listOf(
                    "Una persona que recibe bienes o títulos al morir alguien",
                    "Alguien que continúa una tradición o legado"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Hilaridad",
                definitions = listOf(
                    "Una gran alegría o risa",
                    "Un estado de diversión o regocijo"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Hueco",
                definitions = listOf(
                    "Un espacio vacío en el interior de algo",
                    "Algo que carece de contenido o valor significativo"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Harbinger",
                definitions = listOf(
                    "A person or thing that announces or signals the approach of something",
                    "A forerunner or precursor"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hapless",
                definitions = listOf("Unfortunate or unlucky", "Marked by persistent misfortune"),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Heuristic",
                definitions = listOf(
                    "A problem-solving approach based on trial and error",
                    "A method to discover or learn something"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hubris",
                definitions = listOf(
                    "Excessive pride or self-confidence",
                    "Arrogance that often leads to downfall"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hermetic",
                definitions = listOf(
                    "Completely sealed and airtight",
                    "Relating to mystical or alchemical traditions"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hiatus",
                definitions = listOf(
                    "A pause or gap in a sequence or process",
                    "An interruption or break, especially in continuity"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hierarchy",
                definitions = listOf(
                    "A system of ranking people or things",
                    "An organization with levels of authority or importance"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hypothesis",
                definitions = listOf(
                    "A proposed explanation based on limited evidence",
                    "A starting point for further investigation"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Halcyon",
                definitions = listOf(
                    "Denoting a period of time that was peaceful and happy",
                    "A mythical bird associated with calm seas"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Heresy",
                definitions = listOf(
                    "A belief that contradicts established religious teachings",
                    "An opinion profoundly at odds with what is generally accepted"
                ),
                letter = Letter.H,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Hedonismo",
                definitions = listOf(
                    "La doctrina que considera el placer como el fin supremo de la vida",
                    "Una actitud centrada en la búsqueda de placer y satisfacción"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Herejía",
                definitions = listOf(
                    "Una creencia contraria a la doctrina religiosa establecida",
                    "Una idea que desafía las normas aceptadas"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hiato",
                definitions = listOf(
                    "Una pausa o interrupción en una acción o proceso",
                    "La separación entre dos vocales en distintas sílabas"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Holístico",
                definitions = listOf(
                    "Que considera algo en su totalidad y no solo en partes",
                    "Un enfoque que integra diversos aspectos de un sistema"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hermético",
                definitions = listOf(
                    "Que está completamente cerrado y no permite el paso de aire o líquidos",
                    "Relacionado con lo místico o secreto"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hegemonía",
                definitions = listOf(
                    "El dominio o influencia suprema de un grupo o estado sobre otros",
                    "Una posición de liderazgo o control en un contexto específico"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hipérbole",
                definitions = listOf(
                    "Una figura retórica que exagera para enfatizar algo",
                    "Una declaración que no debe tomarse literalmente"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hipocresía",
                definitions = listOf(
                    "El acto de fingir cualidades o sentimientos que no se poseen",
                    "Una discrepancia entre lo que alguien dice y hace"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Hilaridad",
                definitions = listOf(
                    "Una risa descontrolada o extrema alegría",
                    "Un estado de diversión que provoca risa"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Homogeneidad",
                definitions = listOf(
                    "La cualidad de ser uniforme o consistente",
                    "La falta de diversidad en una composición"
                ),
                letter = Letter.H,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun i() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Ice",
                definitions = listOf(
                    "Frozen water",
                    "A solid state of water commonly used to cool drinks"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Idea",
                definitions = listOf(
                    "A thought or suggestion for a course of action",
                    "A mental concept or image"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Island",
                definitions = listOf(
                    "A piece of land surrounded by water",
                    "A landmass smaller than a continent"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Iron",
                definitions = listOf(
                    "A strong, magnetic metal",
                    "A household tool used to press clothes"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ink",
                definitions = listOf(
                    "A liquid used for writing or printing",
                    "A colored fluid in pens or printers"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Image",
                definitions = listOf(
                    "A picture or representation of something",
                    "A visual likeness or reflection"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Igloo",
                definitions = listOf(
                    "A dome-shaped shelter built from snow or ice",
                    "A traditional home of some Arctic peoples"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ivy",
                definitions = listOf(
                    "A climbing plant with evergreen leaves",
                    "A plant that grows on walls and buildings"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Item",
                definitions = listOf(
                    "An individual object or unit",
                    "A thing included in a list or collection"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Iceberg",
                definitions = listOf(
                    "A large mass of ice floating in the sea",
                    "A part of a glacier that has broken off"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Isla",
                definitions = listOf(
                    "Un pedazo de tierra rodeado de agua",
                    "Un territorio más pequeño que un continente"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Idea",
                definitions = listOf(
                    "Un pensamiento o sugerencia para una acción",
                    "Un concepto o imagen mental"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Imán",
                definitions = listOf(
                    "Un objeto que atrae materiales como el hierro",
                    "Un dispositivo que genera un campo magnético"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Imagen",
                definitions = listOf("Una representación visual de algo", "Un reflejo o retrato"),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Iglesia",
                definitions = listOf(
                    "Un edificio para actividades religiosas cristianas",
                    "Una comunidad de creyentes"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Impresión",
                definitions = listOf(
                    "La acción de producir texto o imágenes en papel",
                    "La sensación o efecto causado por algo"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Invierno",
                definitions = listOf(
                    "La estación más fría del año",
                    "El periodo en el que las temperaturas son más bajas"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Infante",
                definitions = listOf(
                    "Un niño pequeño o bebé",
                    "Un miembro joven de la familia real en algunos países"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Instrumento",
                definitions = listOf(
                    "Un objeto usado para realizar una tarea específica",
                    "Un aparato utilizado para tocar música"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Insecto",
                definitions = listOf(
                    "Un pequeño animal invertebrado con seis patas",
                    "Una clase de artrópodo con cuerpo dividido en tres partes"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Impact",
                definitions = listOf(
                    "The action of one object coming forcibly into contact with another",
                    "A strong effect or influence"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Impose",
                definitions = listOf(
                    "To force something to be accepted or put in place",
                    "To take advantage of someone by demanding their attention"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Inception",
                definitions = listOf(
                    "The beginning or start of something",
                    "The establishment of an institution or activity"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Inference",
                definitions = listOf(
                    "A conclusion reached based on evidence and reasoning",
                    "The act of deriving logical conclusions from facts"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Interval",
                definitions = listOf(
                    "A period of time between two events",
                    "A space between two points or objects"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Instinct",
                definitions = listOf(
                    "An innate pattern of behavior in animals",
                    "A natural or intuitive way of acting or thinking"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Integrity",
                definitions = listOf(
                    "The quality of being honest and having strong moral principles",
                    "The state of being whole and undivided"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Indicate",
                definitions = listOf(
                    "To point out or show something",
                    "To suggest or signal something indirectly"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Influx",
                definitions = listOf(
                    "An arrival or entry of large numbers of people or things",
                    "A flow of liquid, gas, or money into a place"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ignite",
                definitions = listOf(
                    "To catch fire or cause something to catch fire",
                    "To arouse or inflame an emotion or situation"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Impacto",
                definitions = listOf(
                    "El efecto que algo tiene sobre otra cosa",
                    "El choque de un objeto contra otro"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Impuesto",
                definitions = listOf(
                    "Una cantidad de dinero que se paga al gobierno",
                    "Una carga financiera impuesta a las personas o empresas"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Inicio",
                definitions = listOf(
                    "El comienzo o principio de algo",
                    "El momento en que algo empieza"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Inferencia",
                definitions = listOf(
                    "Una conclusión obtenida a partir de datos o hechos",
                    "El proceso de deducir información de algo conocido"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Intervalo",
                definitions = listOf(
                    "El tiempo o espacio entre dos cosas",
                    "Una pausa o descanso en una actividad continua"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Instinto",
                definitions = listOf(
                    "Un comportamiento innato en los animales",
                    "Una reacción natural o intuitiva"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Integridad",
                definitions = listOf(
                    "La cualidad de ser honesto y tener principios morales fuertes",
                    "El estado de ser completo o sin divisiones"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Indicar",
                definitions = listOf("Señalar o mostrar algo", "Hacer saber o dar a entender algo"),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Influencia",
                definitions = listOf(
                    "El poder de afectar a alguien o algo",
                    "La capacidad de cambiar o dirigir el comportamiento de otro"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Iluminar",
                definitions = listOf(
                    "Hacer que algo sea más claro o visible con luz",
                    "Aclarar o explicar algo de manera comprensible"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Impetuous",
                definitions = listOf(
                    "Acting quickly without thought or care",
                    "Characterized by sudden or rash action or emotion"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Immutable",
                definitions = listOf(
                    "Unchanging over time or unable to be changed",
                    "Fixed and permanent in nature"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Imminent",
                definitions = listOf(
                    "About to happen or occur",
                    "Impending and likely to take place soon"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Impervious",
                definitions = listOf(
                    "Not allowing fluid to pass through",
                    "Unable to be affected by something"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Impeccable",
                definitions = listOf(
                    "In accordance with the highest standards; faultless",
                    "Flawless and free from any mistakes"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Indelible",
                definitions = listOf(
                    "Impossible to remove or forget",
                    "Making a permanent impression or mark"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ineffable",
                definitions = listOf(
                    "Too great or extreme to be expressed in words",
                    "Beyond description or comprehension"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Intrepid",
                definitions = listOf("Fearless and adventurous", "Displaying bravery and boldness"),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Incisive",
                definitions = listOf(
                    "Clear and direct in expression or thought",
                    "Sharply focused and analytical"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Inundate",
                definitions = listOf(
                    "To flood or overwhelm",
                    "To cover with a large amount of water or information"
                ),
                letter = Letter.I,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Impecable",
                definitions = listOf(
                    "Que no tiene fallas o defectos",
                    "Perfecto y libre de errores"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Impunidad",
                definitions = listOf(
                    "Ausencia de castigo por una acción indebida",
                    "Falta de consecuencias legales o sociales"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Inmutable",
                definitions = listOf(
                    "Que no puede cambiar o ser alterado",
                    "Constante y permanente en su naturaleza"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Inminente",
                definitions = listOf(
                    "Que está a punto de ocurrir",
                    "Algo que es inevitable y está por suceder pronto"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Indeleble",
                definitions = listOf(
                    "Que no se puede borrar o eliminar",
                    "Que deja una impresión permanente o imborrable"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Inefable",
                definitions = listOf(
                    "Tan extraordinario que no se puede expresar con palabras",
                    "Algo sublime o indescriptible"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Intrépido",
                definitions = listOf(
                    "Que muestra valentía y coraje",
                    "Alguien que enfrenta el peligro sin temor"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Incisivo",
                definitions = listOf(
                    "Agudo y penetrante en el análisis o expresión",
                    "Que corta o penetra profundamente"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Imperturbable",
                definitions = listOf(
                    "Que no se altera fácilmente por emociones o sucesos",
                    "Alguien que mantiene la calma en situaciones difíciles"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Inundar",
                definitions = listOf(
                    "Cubrir con agua una superficie",
                    "Abrumar con una gran cantidad de algo"
                ),
                letter = Letter.I,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun j() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Jam",
                definitions = listOf(
                    "A sweet spread made from fruit and sugar",
                    "A situation where something is stuck or blocked"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Job",
                definitions = listOf(
                    "A task or piece of work",
                    "A regular occupation or position for earning money"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jog",
                definitions = listOf("To run at a slow, steady pace", "To nudge or push gently"),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jar",
                definitions = listOf(
                    "A container made of glass or plastic",
                    "A sudden shock or jolt"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jelly",
                definitions = listOf(
                    "A sweet, clear spread made from fruit juice and sugar",
                    "A soft, wobbly dessert"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jump",
                definitions = listOf(
                    "To push oneself off the ground and into the air",
                    "To move suddenly and quickly from one place to another"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jungle",
                definitions = listOf(
                    "A dense forest in a tropical area",
                    "A chaotic or confusing environment"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Juice",
                definitions = listOf(
                    "A liquid extracted from fruits or vegetables",
                    "A flavorful drink made from natural ingredients"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jack",
                definitions = listOf(
                    "A device used to lift heavy objects",
                    "A card in a deck representing a young man"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Joke",
                definitions = listOf(
                    "Something said or done to provoke laughter",
                    "A humorous or funny story or remark"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Jabón",
                definitions = listOf(
                    "Una sustancia usada para lavar o limpiar",
                    "Un producto que produce espuma al contacto con agua"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jaula",
                definitions = listOf(
                    "Un recinto cerrado usado para mantener animales",
                    "Una estructura hecha de barras o rejas"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jarra",
                definitions = listOf(
                    "Un recipiente con asa para líquidos",
                    "Un utensilio usado para servir agua o bebidas"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jardín",
                definitions = listOf(
                    "Un terreno donde se cultivan plantas y flores",
                    "Un espacio al aire libre para recreación o decoración"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Juego",
                definitions = listOf(
                    "Una actividad recreativa con reglas específicas",
                    "Una forma de entretenimiento o diversión"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jirafa",
                definitions = listOf(
                    "Un animal alto con cuello largo y manchas en el cuerpo",
                    "Un mamífero que habita en las sabanas africanas"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jugo",
                definitions = listOf(
                    "El líquido extraído de frutas o verduras",
                    "Una bebida obtenida al exprimir alimentos naturales"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Juguete",
                definitions = listOf(
                    "Un objeto usado por los niños para jugar",
                    "Algo diseñado para entretener o educar a los pequeños"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Juicio",
                definitions = listOf(
                    "El proceso de evaluar o tomar decisiones",
                    "Un procedimiento legal para resolver disputas"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Jaqueca",
                definitions = listOf(
                    "Un dolor intenso de cabeza",
                    "Un malestar que afecta la zona craneal"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Justice",
                definitions = listOf(
                    "The quality of being fair and reasonable",
                    "The legal process of judging and enforcing laws"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Journey",
                definitions = listOf(
                    "The act of traveling from one place to another",
                    "A long or significant trip"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Junction",
                definitions = listOf(
                    "A point where two or more things are joined",
                    "An intersection of roads or railway lines"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Jurisdiction",
                definitions = listOf(
                    "The official power to make legal decisions",
                    "The territory or sphere of authority of a legal body"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Jovial",
                definitions = listOf("Cheerful and friendly", "Displaying high-spirited happiness"),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Jeopardy",
                definitions = listOf(
                    "Danger of loss, harm, or failure",
                    "A situation involving risk or peril"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Juxtapose",
                definitions = listOf(
                    "To place things close together for contrasting effect",
                    "To compare two or more things side by side"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Jargon",
                definitions = listOf(
                    "Special words or expressions used by a particular group",
                    "Language that is difficult to understand for outsiders"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Journal",
                definitions = listOf(
                    "A daily record of news or personal experiences",
                    "A professional publication of research and studies"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Judgment",
                definitions = listOf(
                    "The ability to make considered decisions",
                    "A legal decision made by a court or judge"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Justicia",
                definitions = listOf(
                    "La cualidad de ser justo y equitativo",
                    "El sistema legal encargado de impartir leyes y normas"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Jornada",
                definitions = listOf(
                    "Un día de trabajo o actividad",
                    "Un periodo continuo de tiempo dedicado a una tarea"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Juntura",
                definitions = listOf(
                    "El punto donde dos o más cosas se unen",
                    "Una conexión entre partes diferentes"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Jurisdicción",
                definitions = listOf(
                    "El poder o autoridad para aplicar la ley",
                    "La zona geográfica donde un ente tiene autoridad"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Jovialidad",
                definitions = listOf(
                    "Un carácter alegre y amistoso",
                    "Una disposición para mostrar felicidad y buen humor"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Jaqueca",
                definitions = listOf(
                    "Un dolor de cabeza intenso y persistente",
                    "Un trastorno asociado con migrañas"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Juguetón",
                definitions = listOf(
                    "Que muestra una actitud alegre y traviesa",
                    "Relacionado con el juego o la diversión"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Jubilación",
                definitions = listOf(
                    "El retiro del trabajo después de alcanzar cierta edad",
                    "El periodo de vida posterior al empleo activo"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Juxtaponer",
                definitions = listOf(
                    "Colocar dos cosas juntas para compararlas",
                    "Poner en proximidad elementos con fines contrastantes"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Juramento",
                definitions = listOf(
                    "Una declaración solemne o promesa formal",
                    "Un compromiso verbal de cumplir con algo"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Jurisprudence",
                definitions = listOf(
                    "The theory or philosophy of law",
                    "A body of legal principles established through past decisions"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Judicious",
                definitions = listOf(
                    "Having or showing good judgment",
                    "Marked by careful consideration or discretion"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Juxtaposition",
                definitions = listOf(
                    "The act of placing things side by side for contrast",
                    "A technique to highlight differences or similarities"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Jingoism",
                definitions = listOf(
                    "Extreme patriotism marked by aggressive foreign policy",
                    "A belligerent attitude toward other nations"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Jettison",
                definitions = listOf(
                    "To discard something unnecessary or burdensome",
                    "To throw goods overboard to lighten a ship or aircraft"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Jubilation",
                definitions = listOf(
                    "A feeling of great happiness and triumph",
                    "The expression of joy or exultation"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Jeopardize",
                definitions = listOf(
                    "To put something at risk or in danger",
                    "To endanger the success or safety of someone or something"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Jurisdictive",
                definitions = listOf(
                    "Relating to the administration of justice or law",
                    "Having legal authority or control over an area"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Juvenescent",
                definitions = listOf(
                    "Becoming young or youthful again",
                    "Relating to the rejuvenation of appearance or energy"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Janissary",
                definitions = listOf(
                    "A member of an elite military unit in the Ottoman Empire",
                    "A devoted follower or supporter"
                ),
                letter = Letter.J,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Jurisprudencia",
                definitions = listOf(
                    "El estudio teórico o filosófico del derecho",
                    "El conjunto de principios legales basados en decisiones previas"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Juicioso",
                definitions = listOf(
                    "Que muestra buen juicio o sensatez",
                    "Prudente y considerado en sus decisiones"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Justificación",
                definitions = listOf(
                    "La acción de demostrar que algo es razonable o necesario",
                    "Una explicación que defiende un acto o decisión"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Juxtaposición",
                definitions = listOf(
                    "La colocación de cosas juntas para compararlas",
                    "Un recurso para destacar diferencias o similitudes"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Jactancia",
                definitions = listOf(
                    "El acto de presumir o hablar con orgullo exagerado",
                    "Una actitud de vanidad o arrogancia"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Jerarquía",
                definitions = listOf(
                    "Una organización en niveles de importancia o autoridad",
                    "Un sistema de clasificación basado en rangos"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Jornada",
                definitions = listOf(
                    "Un día completo de trabajo o actividad",
                    "Un periodo continuo dedicado a un esfuerzo específico"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Jubileo",
                definitions = listOf(
                    "Una celebración especial de un aniversario significativo",
                    "Un evento marcado por alegría y festividad"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Jolgorio",
                definitions = listOf(
                    "Una fiesta o celebración animada",
                    "Un ambiente de diversión y alegría"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Jurisconsulto",
                definitions = listOf(
                    "Un experto en derecho o leyes",
                    "Una persona que interpreta y aplica las normas legales"
                ),
                letter = Letter.J,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun k() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Key",
                definitions = listOf(
                    "A small object used to unlock or lock something",
                    "An important or crucial element in a situation"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Kid",
                definitions = listOf("A young child", "A young goat"),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Kite",
                definitions = listOf(
                    "A lightweight frame covered with fabric flown in the wind",
                    "A bird of prey with long wings and a forked tail"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "King",
                definitions = listOf(
                    "A male ruler of a country",
                    "A playing card with the picture of a king"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Kick",
                definitions = listOf(
                    "To strike with the foot",
                    "A sudden forceful movement of the leg"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Knee",
                definitions = listOf(
                    "The joint connecting the thigh and lower leg",
                    "The front part of the leg that bends"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Knife",
                definitions = listOf(
                    "A tool with a blade used for cutting",
                    "A sharp object used in the kitchen"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Knob",
                definitions = listOf(
                    "A round handle on a door or drawer",
                    "A small round control on a device"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Knock",
                definitions = listOf(
                    "To hit a surface to make a noise",
                    "To cause something to move by striking it"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Kangaroo",
                definitions = listOf(
                    "A large marsupial from Australia",
                    "An animal that moves by jumping on its hind legs"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Kilómetro",
                definitions = listOf(
                    "Una unidad de medida equivalente a 1,000 metros",
                    "Una medida usada para distancias largas"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Kilo",
                definitions = listOf(
                    "Un peso equivalente a 1,000 gramos",
                    "Una medida común en el sistema métrico"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Kiwi",
                definitions = listOf(
                    "Una fruta pequeña con piel marrón y pulpa verde",
                    "Un ave no voladora originaria de Nueva Zelanda"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Koala",
                definitions = listOf(
                    "Un marsupial que vive en los árboles de Australia",
                    "Un animal conocido por alimentarse de hojas de eucalipto"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Karate",
                definitions = listOf(
                    "Un arte marcial originado en Japón",
                    "Un deporte que incluye técnicas de defensa y ataque"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Kimono",
                definitions = listOf(
                    "Una prenda tradicional japonesa",
                    "Un vestido largo con mangas anchas"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Kayak",
                definitions = listOf(
                    "Una pequeña embarcación para una o dos personas",
                    "Un bote impulsado con un remo de doble hoja"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Kármica",
                definitions = listOf(
                    "Relacionado con el karma o las acciones que generan consecuencias",
                    "Una filosofía que conecta acciones con resultados futuros"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Kabuki",
                definitions = listOf(
                    "Un estilo tradicional de teatro japonés",
                    "Una forma artística que combina danza, drama y música"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Karma",
                definitions = listOf(
                    "La creencia de que las acciones tienen consecuencias en el futuro",
                    "Un principio espiritual que conecta causa y efecto"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Keen",
                definitions = listOf(
                    "Having a sharp edge or point",
                    "Eager or enthusiastic about something"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kindred",
                definitions = listOf(
                    "Similar in kind or related by blood",
                    "Having a close connection or affinity"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kernel",
                definitions = listOf(
                    "The central or most important part of something",
                    "The edible seed of a plant, like corn or nuts"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Knack",
                definitions = listOf(
                    "A natural skill or talent for something",
                    "A clever or special way of doing something"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Knighthood",
                definitions = listOf(
                    "The rank or title of a knight",
                    "A group or system associated with chivalry"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kinetic",
                definitions = listOf(
                    "Relating to or resulting from motion",
                    "Characterized by activity or movement"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kindness",
                definitions = listOf(
                    "The quality of being friendly and considerate",
                    "An act of goodwill or compassion"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kiteboarding",
                definitions = listOf(
                    "A water sport using a kite to pull a board",
                    "A recreational activity combining windsurfing and paragliding"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kaleidoscope",
                definitions = listOf(
                    "A tube containing mirrors that create changing patterns",
                    "A constantly changing sequence of elements"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Knapsack",
                definitions = listOf(
                    "A bag carried on the back with straps",
                    "A lightweight backpack used for carrying supplies"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Kilovatio",
                definitions = listOf(
                    "Una unidad de potencia equivalente a 1,000 vatios",
                    "Una medida usada para describir energía eléctrica"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Karateka",
                definitions = listOf(
                    "Una persona que practica karate",
                    "Un experto en artes marciales japonesas"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kinesiología",
                definitions = listOf(
                    "El estudio del movimiento corporal humano",
                    "Una disciplina enfocada en la salud física y la mecánica del cuerpo"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kimono",
                definitions = listOf(
                    "Una prenda tradicional japonesa",
                    "Un vestido largo usado en ceremonias formales"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kayakista",
                definitions = listOf(
                    "Una persona que maneja un kayak",
                    "Un deportista especializado en remar en un bote pequeño"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kármica",
                definitions = listOf(
                    "Relacionado con el concepto de karma en las filosofías orientales",
                    "Un principio que conecta acciones con sus consecuencias"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kabuki",
                definitions = listOf(
                    "Un estilo teatral tradicional de Japón",
                    "Una forma artística que combina drama, música y danza"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kiwi",
                definitions = listOf(
                    "Una fruta pequeña y verde por dentro con piel marrón",
                    "Un ave no voladora originaria de Nueva Zelanda"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kilocaloría",
                definitions = listOf(
                    "Una unidad de energía utilizada en nutrición",
                    "Equivalente a 1,000 calorías"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Kilopondio",
                definitions = listOf(
                    "Una unidad de fuerza en el sistema métrico",
                    "La fuerza necesaria para levantar un kilogramo en la Tierra"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Kaleidoscopic",
                definitions = listOf(
                    "Having complex patterns of colors that change constantly",
                    "Relating to a series of changing phases or events"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kafkaesque",
                definitions = listOf(
                    "Marked by surreal distortion and a sense of impending danger",
                    "Relating to the oppressive qualities of Franz Kafka's works"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kerfuffle",
                definitions = listOf(
                    "A commotion or fuss caused by a disagreement",
                    "A chaotic or confused situation"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Knickerbocker",
                definitions = listOf(
                    "A resident of New York City",
                    "A style of baggy trousers gathered at the knees"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kinetoscope",
                definitions = listOf(
                    "A device for viewing a sequence of images to create an illusion of motion",
                    "An early motion picture exhibition device"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Knighthood",
                definitions = listOf(
                    "The title or rank given to a knight",
                    "The qualities or virtues associated with chivalry"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kleptomania",
                definitions = listOf(
                    "A recurrent urge to steal without economic motive",
                    "A mental health disorder involving impulsive stealing"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Knowledgeable",
                definitions = listOf(
                    "Well-informed and educated",
                    "Having or showing a good understanding of something"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Knucklehead",
                definitions = listOf(
                    "A foolish or stupid person",
                    "Someone who acts without thinking"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Karyotype",
                definitions = listOf(
                    "The number and appearance of chromosomes in the nucleus of a cell",
                    "A visual representation of chromosomes used for analysis"
                ),
                letter = Letter.K,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Kilopondio",
                definitions = listOf(
                    "Una unidad de fuerza en el sistema métrico",
                    "La fuerza necesaria para levantar un kilogramo en la Tierra"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kinesiología",
                definitions = listOf(
                    "El estudio científico del movimiento corporal",
                    "Una disciplina enfocada en la biomecánica y la salud física"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kármica",
                definitions = listOf(
                    "Relativo al concepto de karma en las filosofías orientales",
                    "La creencia en la conexión entre acciones y consecuencias"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kabuki",
                definitions = listOf(
                    "Un estilo de teatro tradicional japonés",
                    "Una forma de arte que combina actuación, música y danza"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kimono",
                definitions = listOf(
                    "Una prenda tradicional de origen japonés",
                    "Un vestido largo usado en ceremonias formales"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Koan",
                definitions = listOf(
                    "Un enigma o historia usada en la meditación zen",
                    "Una pregunta o declaración que desafía el pensamiento lógico"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kilocaloría",
                definitions = listOf(
                    "Una unidad de medida de energía usada en nutrición",
                    "Equivalente a 1,000 calorías"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kayakista",
                definitions = listOf(
                    "Una persona que practica el deporte del kayak",
                    "Un deportista que navega en un bote pequeño y ligero"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kefir",
                definitions = listOf(
                    "Una bebida fermentada hecha de leche y bacterias probióticas",
                    "Un alimento tradicional consumido por sus beneficios para la salud"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Kamikaze",
                definitions = listOf(
                    "Un piloto japonés que llevaba a cabo ataques suicidas durante la Segunda Guerra Mundial",
                    "Alguien que realiza una acción arriesgada o temeraria"
                ),
                letter = Letter.K,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun l() = listOf(
// EASY
///////////// EN /////////////
            WordEntity(
                word = "Lamp",
                definitions = listOf(
                    "A device that produces light",
                    "An object used to illuminate a room"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Leaf",
                definitions = listOf(
                    "The flat, green part of a plant",
                    "A page of a book or document"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Lake",
                definitions = listOf(
                    "A large body of water surrounded by land",
                    "A natural or artificial basin filled with water"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Lemon",
                definitions = listOf(
                    "A yellow, sour fruit from a citrus tree",
                    "A flavor or ingredient used in drinks and food"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Line",
                definitions = listOf(
                    "A long, narrow mark or band",
                    "A straight or curved continuous extent of length"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Lion",
                definitions = listOf(
                    "A large, wild cat with a mane",
                    "An animal known as the 'king of the jungle'"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Light",
                definitions = listOf(
                    "The natural agent that makes things visible",
                    "A source of illumination like the sun or a lamp"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Lock",
                definitions = listOf(
                    "A device for securing doors or containers",
                    "A mechanism requiring a key or code to open"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Log",
                definitions = listOf(
                    "A large piece of cut or fallen tree trunk",
                    "A record of events or data kept systematically"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Love",
                definitions = listOf(
                    "A strong feeling of affection",
                    "An intense emotional connection or attachment"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Lámpara",
                definitions = listOf(
                    "Un objeto que produce luz",
                    "Un aparato usado para iluminar una habitación"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Libro",
                definitions = listOf(
                    "Una colección de páginas escritas o impresas",
                    "Un objeto usado para leer y aprender"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Lago",
                definitions = listOf(
                    "Un cuerpo de agua rodeado de tierra",
                    "Una extensión de agua natural o artificial"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Limón",
                definitions = listOf(
                    "Una fruta amarilla de sabor ácido",
                    "Un ingrediente usado en alimentos y bebidas"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Línea",
                definitions = listOf(
                    "Una marca larga y delgada",
                    "Una secuencia continua de puntos en una dirección"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Luz",
                definitions = listOf(
                    "La energía que permite ver",
                    "Un elemento usado para iluminar un espacio"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Llave",
                definitions = listOf(
                    "Un objeto usado para abrir cerraduras",
                    "Un símbolo o elemento que da acceso a algo"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Lana",
                definitions = listOf(
                    "Un material obtenido de las ovejas",
                    "Una fibra usada para hacer ropa y mantas"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Lápiz",
                definitions = listOf(
                    "Un instrumento usado para escribir o dibujar",
                    "Un objeto que contiene mina de grafito"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Loro",
                definitions = listOf(
                    "Un ave colorida que puede imitar sonidos",
                    "Un pájaro tropical conocido por hablar"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Latitude",
                definitions = listOf(
                    "The angular distance of a place north or south of the equator",
                    "Freedom of action or thought"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Legacy",
                definitions = listOf(
                    "Something handed down from the past",
                    "An inheritance or gift left in a will"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Levity",
                definitions = listOf(
                    "Lightness of behavior or humor, especially in serious situations",
                    "A lack of seriousness"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Labyrinth",
                definitions = listOf(
                    "A complicated network of paths or passages",
                    "A confusing and intricate structure or situation"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Lament",
                definitions = listOf(
                    "To express sorrow or regret",
                    "A passionate expression of grief or mourning"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Liaison",
                definitions = listOf(
                    "A person who acts as a link between groups or individuals",
                    "A close relationship or connection"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Luminous",
                definitions = listOf(
                    "Emitting or reflecting light",
                    "Bright or shining, especially in the dark"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Lattice",
                definitions = listOf(
                    "A structure of crossed wooden or metal strips",
                    "An interwoven or crisscross pattern"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Leverage",
                definitions = listOf(
                    "The use of a lever to exert force",
                    "The power to influence people or situations"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Longevity",
                definitions = listOf(
                    "The length of time something lasts or lives",
                    "A long duration of life or service"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Latitud",
                definitions = listOf(
                    "La distancia angular al norte o sur del ecuador",
                    "Libertad de acción o pensamiento"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Legado",
                definitions = listOf(
                    "Algo transmitido del pasado",
                    "Una herencia o donación dejada en un testamento"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Liviandad",
                definitions = listOf(
                    "Falta de seriedad en el comportamiento",
                    "Ligereza o falta de peso en algo"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Laberinto",
                definitions = listOf(
                    "Una red complicada de caminos o pasajes",
                    "Una estructura intrincada que causa confusión"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Lamento",
                definitions = listOf(
                    "La expresión de tristeza o arrepentimiento",
                    "Un grito o expresión apasionada de dolor"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Luminiscencia",
                definitions = listOf(
                    "La emisión de luz sin calor perceptible",
                    "Un brillo suave y constante en la oscuridad"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ligadura",
                definitions = listOf(
                    "Algo usado para atar o sujetar",
                    "Una conexión o vínculo entre dos cosas"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Levitación",
                definitions = listOf(
                    "El acto de elevarse en el aire sin soporte",
                    "Una acción asociada con la magia o fenómenos físicos"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Lucidez",
                definitions = listOf(
                    "Claridad mental o de pensamiento",
                    "La capacidad de comprender con facilidad"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Longevidad",
                definitions = listOf(
                    "La duración prolongada de la vida o existencia",
                    "Un periodo largo de servicio o actividad"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Laconic",
                definitions = listOf(
                    "Using very few words; concise to the point of seeming rude",
                    "Brief and to the point in expression"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Lamentation",
                definitions = listOf(
                    "The passionate expression of grief or sorrow",
                    "An act of mourning or regret"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Larceny",
                definitions = listOf(
                    "The unlawful taking of personal property",
                    "A crime involving theft without force"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Largesse",
                definitions = listOf(
                    "Generosity in giving gifts or money",
                    "A liberal donation or contribution"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Legerdemain",
                definitions = listOf(
                    "Skillful use of one's hands when performing tricks",
                    "Deception or trickery"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Lethargy",
                definitions = listOf(
                    "A state of sluggishness or inactivity",
                    "Lack of energy or enthusiasm"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Libation",
                definitions = listOf(
                    "A drink poured out as an offering to a deity",
                    "An alcoholic beverage consumed ceremonially or socially"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Lugubrious",
                definitions = listOf(
                    "Looking or sounding sad and dismal",
                    "Excessively mournful or gloomy"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Litigious",
                definitions = listOf(
                    "Prone to engaging in lawsuits",
                    "Overly concerned with or inclined toward legal disputes"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Lucubration",
                definitions = listOf(
                    "Laborious work or study, especially at night",
                    "The product of intensive and prolonged mental effort"
                ),
                letter = Letter.L,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Lacónico",
                definitions = listOf(
                    "Que utiliza pocas palabras para expresarse",
                    "Conciso hasta el punto de parecer brusco"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Lamentación",
                definitions = listOf(
                    "La expresión apasionada de dolor o tristeza",
                    "Un acto de duelo o arrepentimiento"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Latrocinio",
                definitions = listOf(
                    "Un robo cometido con engaño o abuso de confianza",
                    "El acto de apropiarse de bienes ajenos de forma ilegal"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Largueza",
                definitions = listOf(
                    "La generosidad al dar, especialmente dinero o bienes",
                    "Un acto de liberalidad o donación"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Letargo",
                definitions = listOf(
                    "Un estado de somnolencia profunda o falta de energía",
                    "Una condición de inactividad prolongada"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Libación",
                definitions = listOf(
                    "La acción de verter un líquido en honor a una deidad",
                    "Una bebida alcohólica consumida en una ceremonia o celebración"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Lugubre",
                definitions = listOf(
                    "Que provoca tristeza o es oscuro y sombrío",
                    "Excesivamente triste o melancólico"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Litigioso",
                definitions = listOf(
                    "Propenso a iniciar disputas legales",
                    "Que se relaciona con pleitos o litigios"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Lucidez",
                definitions = listOf(
                    "La claridad mental para razonar o entender",
                    "La capacidad de actuar con precisión y claridad"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Lucubración",
                definitions = listOf(
                    "Un trabajo intelectual realizado con esfuerzo, especialmente de noche",
                    "El resultado de un estudio profundo y prolongado"
                ),
                letter = Letter.L,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun m() = listOf(
// EASY
///////////// EN /////////////
            WordEntity(
                word = "Map",
                definitions = listOf(
                    "A diagrammatic representation of an area",
                    "A visual representation of geography"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Milk",
                definitions = listOf(
                    "A white liquid produced by mammals",
                    "A common drink used in cooking and as a beverage"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Moon",
                definitions = listOf(
                    "A natural satellite of a planet",
                    "The celestial body that orbits the Earth"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Man",
                definitions = listOf(
                    "An adult male human",
                    "A term used to refer to humanity collectively"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mouse",
                definitions = listOf(
                    "A small rodent with a long tail",
                    "A device used to control a computer cursor"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mountain",
                definitions = listOf(
                    "A large natural elevation of the Earth's surface",
                    "A high and often steep landform"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mirror",
                definitions = listOf(
                    "A surface that reflects an image",
                    "An object often used for checking one's appearance"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Money",
                definitions = listOf(
                    "A medium of exchange in the form of coins or banknotes",
                    "A system used to buy goods and services"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Meal",
                definitions = listOf(
                    "An occasion when food is eaten",
                    "A combination of foods served together"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mark",
                definitions = listOf(
                    "A visible impression or stain on a surface",
                    "A grade or score in an examination or test"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Mapa",
                definitions = listOf(
                    "Una representación gráfica de una región",
                    "Un dibujo que muestra información geográfica"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Manzana",
                definitions = listOf(
                    "Una fruta redonda, generalmente roja, verde o amarilla",
                    "Una comida saludable que crece en los árboles"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mesa",
                definitions = listOf(
                    "Un mueble con una superficie plana usado para trabajar o comer",
                    "Un objeto en el que se colocan cosas"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Montaña",
                definitions = listOf(
                    "Una elevación natural del terreno",
                    "Un accidente geográfico alto y empinado"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Miel",
                definitions = listOf(
                    "Un líquido dulce producido por las abejas",
                    "Un alimento usado como endulzante natural"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mesa",
                definitions = listOf(
                    "Un mueble plano con patas",
                    "Un lugar para trabajar, escribir o comer"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mundo",
                definitions = listOf(
                    "El planeta Tierra y todo lo que hay en él",
                    "Un término que engloba todo lo conocido"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Moto",
                definitions = listOf(
                    "Un vehículo de dos ruedas motorizado",
                    "Un medio de transporte rápido y práctico"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Música",
                definitions = listOf(
                    "Un arte que combina sonidos y ritmos",
                    "Un conjunto de melodías que generan placer auditivo"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Manto",
                definitions = listOf(
                    "Una prenda de vestir que cubre el cuerpo",
                    "Una capa exterior que protege o adorna algo"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Mail",
                definitions = listOf(
                    "Letters or packages sent through a postal system",
                    "Electronic messages sent via email"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mat",
                definitions = listOf(
                    "A small piece of material placed on a floor",
                    "A protective layer or pad"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mask",
                definitions = listOf(
                    "A covering for the face, often worn for protection or disguise",
                    "A decorative item used in performances or celebrations"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mall",
                definitions = listOf(
                    "A large indoor shopping area with stores and restaurants",
                    "A public space for walking and gathering"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mop",
                definitions = listOf(
                    "A tool for cleaning floors",
                    "A bundle of thick, absorbent material used to soak up liquid"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mud",
                definitions = listOf(
                    "Wet dirt or soil",
                    "A mixture of water and earth often found after rain"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mug",
                definitions = listOf(
                    "A large cup used for drinking hot beverages",
                    "A container with a handle used for coffee or tea"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mine",
                definitions = listOf(
                    "A place where minerals are extracted from the Earth",
                    "Something that belongs to me"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mix",
                definitions = listOf(
                    "To combine different things together",
                    "A combination of ingredients or elements"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mask",
                definitions = listOf(
                    "A covering for the face",
                    "A representation of a face, often worn as a disguise"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Mango",
                definitions = listOf(
                    "Una fruta tropical dulce y jugosa",
                    "Una parte de utensilios como cuchillos o herramientas para sujetarlos"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Miel",
                definitions = listOf(
                    "Un líquido dulce producido por las abejas",
                    "Un alimento usado como endulzante natural"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mesa",
                definitions = listOf(
                    "Un mueble con una superficie plana para comer o trabajar",
                    "Un objeto utilizado para sostener cosas"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Manto",
                definitions = listOf(
                    "Una prenda que cubre el cuerpo",
                    "Una capa que recubre algo, como la Tierra"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Moto",
                definitions = listOf(
                    "Un vehículo de dos ruedas motorizado",
                    "Un medio de transporte pequeño y rápido"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mundo",
                definitions = listOf(
                    "El planeta Tierra y todo lo que contiene",
                    "Un concepto que abarca todo el entorno conocido"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Montaña",
                definitions = listOf(
                    "Una gran elevación natural de la superficie terrestre",
                    "Un accidente geográfico que sobresale del terreno"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Música",
                definitions = listOf(
                    "Una forma de arte que combina sonidos y ritmos",
                    "Una serie de melodías que generan placer auditivo"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Muñeca",
                definitions = listOf(
                    "Una figura en miniatura de una persona usada como juguete",
                    "La articulación que conecta la mano con el brazo"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Mente",
                definitions = listOf(
                    "La capacidad de pensar y razonar",
                    "El lugar de donde surgen los pensamientos y emociones"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Magnitude",
                definitions = listOf(
                    "The great size or importance of something",
                    "A measure of the strength of an earthquake"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Manifest",
                definitions = listOf(
                    "To show something clearly through actions or appearance",
                    "A document listing the cargo of a ship or plane"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Malice",
                definitions = listOf(
                    "The intention to do harm or cause distress",
                    "A feeling of hatred or ill will"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Manuscript",
                definitions = listOf(
                    "A handwritten or typed document",
                    "An author's written work submitted for publication"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Matrix",
                definitions = listOf(
                    "A rectangular array of numbers or symbols in mathematics",
                    "An environment in which something develops"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Melancholy",
                definitions = listOf(
                    "A feeling of deep sadness",
                    "A state of mind characterized by gloominess"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Momentum",
                definitions = listOf(
                    "The quantity of motion of a moving body",
                    "The driving force gained by the development of a process"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Metaphor",
                definitions = listOf(
                    "A figure of speech in which one thing represents another",
                    "An expression that compares two unrelated things symbolically"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Mirage",
                definitions = listOf(
                    "An optical illusion caused by atmospheric conditions",
                    "Something that appears real or possible but is not"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Milestone",
                definitions = listOf(
                    "An important event or stage in development",
                    "A stone marker used to indicate distance"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Magnitud",
                definitions = listOf(
                    "La importancia o tamaño de algo",
                    "Una medida de la fuerza de un terremoto"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Manifestación",
                definitions = listOf(
                    "Una demostración pública de opiniones o emociones",
                    "La acción de hacer visible o evidente algo"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Maldad",
                definitions = listOf(
                    "La cualidad de ser malo o dañino",
                    "Una intención deliberada de causar daño"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Manuscrito",
                definitions = listOf(
                    "Un texto escrito a mano o mecanografiado",
                    "Un documento preparado para publicación"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Matriz",
                definitions = listOf(
                    "Una estructura rectangular de números en matemáticas",
                    "El entorno donde algo se desarrolla o toma forma"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Melancolía",
                definitions = listOf(
                    "Un sentimiento profundo de tristeza",
                    "Un estado mental caracterizado por la nostalgia o el desánimo"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Momento",
                definitions = listOf(
                    "Una cantidad de movimiento de un cuerpo en física",
                    "Una etapa importante o significativa en el tiempo"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Metáfora",
                definitions = listOf(
                    "Una figura retórica que compara dos cosas diferentes",
                    "Una expresión simbólica que representa otra realidad"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Miraje",
                definitions = listOf(
                    "Una ilusión óptica causada por condiciones atmosféricas",
                    "Algo que parece real pero no lo es"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Mojón",
                definitions = listOf(
                    "Un marcador utilizado para indicar distancias",
                    "Un punto de referencia en el progreso o desarrollo de algo"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Magnanimous",
                definitions = listOf(
                    "Generous or forgiving, especially toward a rival or less powerful person",
                    "Showing a noble and kind spirit"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Malfeasance",
                definitions = listOf(
                    "Wrongdoing, especially by a public official",
                    "An illegal act carried out by someone in authority"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Manifesto",
                definitions = listOf(
                    "A public declaration of intentions, policies, or views",
                    "A written statement of principles by an organization or individual"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Machiavellian",
                definitions = listOf(
                    "Cunning, scheming, and unscrupulous in politics",
                    "Relating to the principles of manipulation for personal gain"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Malaise",
                definitions = listOf(
                    "A general feeling of discomfort or unease",
                    "A vague sense of ill-being or lack of purpose"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Metamorphosis",
                definitions = listOf(
                    "A complete change in form, structure, or substance",
                    "The process of transformation, especially in biology"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Multifarious",
                definitions = listOf(
                    "Having many varied parts or aspects",
                    "Diverse and wide-ranging in nature"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Moratorium",
                definitions = listOf(
                    "A temporary prohibition of an activity",
                    "A legally authorized suspension of a process or obligation"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Monolithic",
                definitions = listOf(
                    "Massive, uniform, and unyielding in structure",
                    "Relating to a large, powerful, and indivisible organization"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Myriad",
                definitions = listOf(
                    "A countless or extremely great number",
                    "Composed of innumerable elements or aspects"
                ),
                letter = Letter.M,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Magnánimo",
                definitions = listOf(
                    "Generoso o indulgente hacia los demás",
                    "Que muestra nobleza y grandeza de espíritu"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Malversación",
                definitions = listOf(
                    "Uso indebido de fondos públicos",
                    "Un delito relacionado con el desvío de recursos económicos"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Manifiesto",
                definitions = listOf(
                    "Una declaración pública de intenciones o principios",
                    "Un documento que expresa ideas políticas o sociales"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Maquiavélico",
                definitions = listOf(
                    "Astuto, manipulador y sin escrúpulos",
                    "Relacionado con las ideas políticas de Nicolás Maquiavelo"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Malestar",
                definitions = listOf(
                    "Una sensación general de incomodidad o inquietud",
                    "Un estado de insatisfacción o falta de bienestar"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Metamorfosis",
                definitions = listOf(
                    "Un cambio completo de forma o estructura",
                    "El proceso biológico en el que un organismo se transforma en otro"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Multifacético",
                definitions = listOf(
                    "Que tiene muchas facetas o aspectos",
                    "Diverso y variado en naturaleza o características"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Moratoria",
                definitions = listOf(
                    "Una suspensión temporal de una actividad o proceso",
                    "Un aplazamiento autorizado legalmente"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Monolítico",
                definitions = listOf(
                    "Que es masivo, uniforme y difícil de cambiar",
                    "Relacionado con algo grande y poderoso pero inflexible"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Miríada",
                definitions = listOf(
                    "Un número incalculable o extremadamente grande",
                    "Un conjunto innumerable de elementos o aspectos"
                ),
                letter = Letter.M,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun n() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Nail",
                definitions = listOf(
                    "A small metal spike used for fastening things together",
                    "The hard covering on the tips of fingers and toes"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Nest",
                definitions = listOf(
                    "A structure built by birds to lay eggs",
                    "A snug or sheltered place"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Net",
                definitions = listOf(
                    "A mesh of rope or thread used for catching animals or objects",
                    "A system of interconnected computers or people"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Night",
                definitions = listOf(
                    "The time between sunset and sunrise",
                    "A period of darkness in each 24-hour day"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Number",
                definitions = listOf(
                    "A mathematical object used to count or measure",
                    "A figure, symbol, or group of digits"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Nose",
                definitions = listOf(
                    "The part of the face used for breathing and smelling",
                    "The front part of an airplane or vehicle"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Nurse",
                definitions = listOf(
                    "A person trained to care for the sick or injured",
                    "To take care of someone or something with attention"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Name",
                definitions = listOf(
                    "A word used to identify a person, place, or thing",
                    "A reputation or character associated with someone or something"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Noise",
                definitions = listOf(
                    "A sound, especially one that is loud or unpleasant",
                    "Unwanted or disturbing sound"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Note",
                definitions = listOf(
                    "A brief written message or reminder",
                    "A single tone in music"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Naranja",
                definitions = listOf(
                    "Una fruta cítrica de color naranja",
                    "Un color similar al de la fruta del mismo nombre"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Nido",
                definitions = listOf(
                    "La estructura que construyen las aves para poner huevos",
                    "Un lugar cómodo o protegido"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Nube",
                definitions = listOf(
                    "Una masa visible de vapor de agua en el cielo",
                    "Algo que flota en el aire y bloquea el sol"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Noche",
                definitions = listOf(
                    "El período de oscuridad entre el atardecer y el amanecer",
                    "Un tiempo para descansar o dormir"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Número",
                definitions = listOf(
                    "Un símbolo o cifra usado para contar o medir",
                    "Una posición en una secuencia o lista"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Nariz",
                definitions = listOf(
                    "La parte de la cara usada para respirar y oler",
                    "La parte frontal de un vehículo o avión"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Nombre",
                definitions = listOf(
                    "Una palabra que identifica a una persona, lugar o cosa",
                    "Una denominación usada para designar algo"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Noticia",
                definitions = listOf(
                    "Información nueva o reciente sobre algo",
                    "Un informe publicado en medios de comunicación"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Nota",
                definitions = listOf(
                    "Un mensaje breve escrito",
                    "Un símbolo que representa un sonido musical"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Negocio",
                definitions = listOf(
                    "Una actividad económica para generar ingresos",
                    "Un acuerdo o trato entre dos o más partes"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Narrative",
                definitions = listOf(
                    "A spoken or written account of connected events",
                    "The way a story is structured and told"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Negotiation",
                definitions = listOf(
                    "The process of discussing terms to reach an agreement",
                    "A dialogue intended to resolve disputes or create contracts"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Neutral",
                definitions = listOf(
                    "Not taking sides in a conflict or argument",
                    "Having no strong characteristics or features"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Notable",
                definitions = listOf(
                    "Worthy of attention or notice",
                    "A person of high social status or prominence"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Nurture",
                definitions = listOf(
                    "To care for and encourage the growth or development of someone or something",
                    "The process of upbringing or training"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Network",
                definitions = listOf(
                    "A system of interconnected people or devices",
                    "A structure with multiple nodes connected for communication"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Nostalgia",
                definitions = listOf(
                    "A sentimental longing for the past",
                    "A bittersweet affection for memories of times gone by"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Notation",
                definitions = listOf(
                    "A system of symbols used to represent information",
                    "A note or annotation in a document"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Nominate",
                definitions = listOf(
                    "To propose someone for an honor or position",
                    "To formally suggest a candidate for an award or election"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Nuance",
                definitions = listOf(
                    "A subtle difference in meaning or expression",
                    "A fine or slight variation in tone or feeling"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Narrativa",
                definitions = listOf(
                    "Una historia o relato estructurado",
                    "El estilo en que se cuenta una historia"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Negociación",
                definitions = listOf(
                    "El proceso de dialogar para llegar a un acuerdo",
                    "Un intercambio de opiniones para resolver conflictos"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Neutralidad",
                definitions = listOf(
                    "La posición de no tomar partido en un conflicto",
                    "La ausencia de inclinación hacia un lado u otro"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Notable",
                definitions = listOf(
                    "Digno de atención o destacado",
                    "Una persona reconocida por su relevancia o importancia"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Nutrición",
                definitions = listOf(
                    "El proceso de obtener y usar nutrientes para el cuerpo",
                    "El estudio de los alimentos y sus efectos en la salud"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Novedad",
                definitions = listOf(
                    "Algo nuevo o diferente",
                    "Una característica que introduce frescura o cambio"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Normativa",
                definitions = listOf(
                    "Un conjunto de reglas o leyes que regulan algo",
                    "El marco legal que establece cómo actuar en un contexto"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Noción",
                definitions = listOf(
                    "Una idea básica o entendimiento de algo",
                    "Un concepto general o percepción"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Nombramiento",
                definitions = listOf(
                    "La designación oficial de alguien para un cargo o posición",
                    "El acto de asignar formalmente un puesto"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Nuance",
                definitions = listOf(
                    "Una diferencia sutil en el significado o tono",
                    "Una variación ligera que cambia la percepción de algo"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Narcissism",
                definitions = listOf(
                    "Excessive interest in or admiration of oneself",
                    "A personality trait characterized by self-centeredness and a lack of empathy"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Necromancy",
                definitions = listOf(
                    "The practice of communicating with the dead to predict the future",
                    "A form of magic involving the spirits of the deceased"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nascent",
                definitions = listOf(
                    "Just beginning to exist or develop",
                    "In an early stage of formation or growth"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nominal",
                definitions = listOf(
                    "Existing in name only, not real",
                    "Very small or insignificant in amount"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nefarious",
                definitions = listOf("Wicked or criminal in nature", "Extremely bad or villainous"),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nihilism",
                definitions = listOf(
                    "The rejection of all religious and moral principles",
                    "A belief that life is meaningless"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nuance",
                definitions = listOf(
                    "A subtle difference in meaning, expression, or tone",
                    "A fine or delicate variation in character"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nomenclature",
                definitions = listOf(
                    "The system of naming things, especially in science or art",
                    "A specialized set of terms used in a particular field"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nonchalant",
                definitions = listOf(
                    "Appearing casually calm and relaxed",
                    "Not displaying anxiety, interest, or enthusiasm"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nostrum",
                definitions = listOf(
                    "A medicine or remedy of doubtful effectiveness",
                    "A scheme or idea suggested to solve a problem, often unrealistic"
                ),
                letter = Letter.N,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Narcisismo",
                definitions = listOf(
                    "Un interés o admiración excesiva hacia uno mismo",
                    "Un rasgo de personalidad caracterizado por egoísmo y falta de empatía"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Necromancia",
                definitions = listOf(
                    "La práctica de comunicarse con los muertos para predecir el futuro",
                    "Un tipo de magia relacionada con los espíritus"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Naciente",
                definitions = listOf(
                    "Que está comenzando a desarrollarse o existir",
                    "En una etapa inicial de formación o crecimiento"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nominal",
                definitions = listOf(
                    "Que existe solo en el nombre, no en realidad",
                    "Algo pequeño o insignificante en cantidad o importancia"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nefasto",
                definitions = listOf(
                    "Que es muy malo o dañino",
                    "Algo con consecuencias extremadamente negativas"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nihilismo",
                definitions = listOf(
                    "La negación de todos los principios religiosos y morales",
                    "La creencia de que la vida no tiene sentido"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nuance",
                definitions = listOf(
                    "Una diferencia sutil en significado, expresión o tono",
                    "Una variación fina o delicada en carácter"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nomenclatura",
                definitions = listOf(
                    "Un sistema para nombrar cosas, especialmente en ciencia o arte",
                    "Un conjunto de términos especializados en un campo"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Noctámbulo",
                definitions = listOf(
                    "Una persona que tiene la costumbre de estar activa durante la noche",
                    "Alguien que realiza actividades mientras los demás duermen"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Nostalgia",
                definitions = listOf(
                    "Un sentimiento de melancolía por el pasado",
                    "Un deseo sentimental por tiempos o lugares anteriores"
                ),
                letter = Letter.N,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun o() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Ocean",
                definitions = listOf(
                    "A large body of saltwater covering most of the Earth's surface",
                    "A vast expanse or quantity of something"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Orange",
                definitions = listOf(
                    "A citrus fruit with a sweet and tangy flavor",
                    "A color between red and yellow in the spectrum"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Oil",
                definitions = listOf(
                    "A thick, liquid substance used for cooking or lubrication",
                    "A natural resource used as fuel or for energy"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Oven",
                definitions = listOf(
                    "A device used for baking or roasting food",
                    "An enclosed compartment that generates heat"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Open",
                definitions = listOf("Not closed or locked", "Accessible or available to everyone"),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Owl",
                definitions = listOf(
                    "A nocturnal bird known for its hooting sound",
                    "A bird with large eyes and a rotating head"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Over",
                definitions = listOf(
                    "Above or higher than something",
                    "Across or beyond a specific point"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Order",
                definitions = listOf(
                    "An arrangement or sequence of items",
                    "A command or instruction given by someone in authority"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Onion",
                definitions = listOf(
                    "A vegetable with a strong flavor and smell, often used in cooking",
                    "A bulbous plant grown for its edible root"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Office",
                definitions = listOf(
                    "A place where business or professional work is done",
                    "A room or building used for administrative purposes"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Ojo",
                definitions = listOf(
                    "El órgano de la vista en los seres vivos",
                    "Un símbolo usado para representar visión o percepción"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Oso",
                definitions = listOf(
                    "Un mamífero grande y peludo que vive en bosques o montañas",
                    "Un animal conocido por su fuerza y tamaño"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Oro",
                definitions = listOf(
                    "Un metal precioso de color amarillo brillante",
                    "Un símbolo de riqueza o valor"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Oveja",
                definitions = listOf(
                    "Un animal de granja conocido por su lana",
                    "Un símbolo de mansedumbre o docilidad"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Olivo",
                definitions = listOf(
                    "Un árbol que produce aceitunas",
                    "Una planta cultivada por su fruto y su aceite"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Oído",
                definitions = listOf(
                    "El sentido que permite percibir sonidos",
                    "La parte del cuerpo responsable de la audición"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Oreja",
                definitions = listOf(
                    "La parte externa del oído en los humanos y animales",
                    "Un término usado para describir la capacidad de escuchar"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Octubre",
                definitions = listOf(
                    "El décimo mes del año en el calendario gregoriano",
                    "Un periodo caracterizado por el otoño en el hemisferio norte"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Oeste",
                definitions = listOf(
                    "Un punto cardinal opuesto al este",
                    "Una dirección hacia donde se pone el sol"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ópera",
                definitions = listOf(
                    "Un género teatral donde se canta la mayor parte del texto",
                    "Una obra musical con cantantes y orquesta"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Objective",
                definitions = listOf(
                    "A goal or purpose intended to be achieved",
                    "Not influenced by personal feelings or opinions"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Obligation",
                definitions = listOf(
                    "A moral or legal duty to do something",
                    "A commitment that binds someone to a course of action"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Obscure",
                definitions = listOf(
                    "Not easily understood or clearly expressed",
                    "Not known or not well-known"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Offense",
                definitions = listOf(
                    "A violation of a law or rule",
                    "An act that causes someone to feel hurt or insulted"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Opponent",
                definitions = listOf(
                    "A person who disagrees with or competes against another",
                    "Someone who is on the opposite side in a contest"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Optimism",
                definitions = listOf(
                    "Hopefulness and confidence about the future",
                    "A tendency to expect the best possible outcome"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Orbit",
                definitions = listOf(
                    "The curved path of a celestial object around a star or planet",
                    "The range or area of influence of something"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Operation",
                definitions = listOf(
                    "A planned activity or task involving many people",
                    "A surgical procedure to treat a medical condition"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Outcome",
                definitions = listOf(
                    "The result or effect of an action or event",
                    "A consequence or end product"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Outrage",
                definitions = listOf(
                    "An extreme reaction of anger or shock",
                    "An act that causes strong indignation"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Objetivo",
                definitions = listOf(
                    "Una meta que se desea alcanzar",
                    "Algo que no está influido por emociones o prejuicios"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Obligación",
                definitions = listOf(
                    "Un deber moral o legal de hacer algo",
                    "Un compromiso que vincula a una persona a una acción"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Oscuro",
                definitions = listOf(
                    "Que no tiene luz o claridad",
                    "Difícil de entender o interpretar"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ofensa",
                definitions = listOf(
                    "Una acción que viola una ley o regla",
                    "Algo que causa daño o insulta a alguien"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Oponente",
                definitions = listOf(
                    "Una persona que compite o está en desacuerdo con otra",
                    "Alguien en el lado contrario de un conflicto o debate"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Optimismo",
                definitions = listOf(
                    "La tendencia a ver el lado positivo de las cosas",
                    "Una actitud de esperanza hacia el futuro"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Órbita",
                definitions = listOf(
                    "El camino curvado que un objeto sigue alrededor de otro",
                    "El alcance de influencia de algo"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Operación",
                definitions = listOf(
                    "Un conjunto planificado de actividades o tareas",
                    "Un procedimiento quirúrgico para tratar una enfermedad"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Resultado",
                definitions = listOf(
                    "El efecto o consecuencia de una acción o evento",
                    "El producto final de un proceso"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Indignación",
                definitions = listOf(
                    "Una fuerte reacción de enfado ante algo injusto",
                    "Un sentimiento de enojo o resentimiento"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Obfuscate",
                definitions = listOf(
                    "To deliberately make something unclear or difficult to understand",
                    "To darken or obscure the perception of something"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Obsequious",
                definitions = listOf(
                    "Excessively submissive or eager to please",
                    "Characterized by servile attentiveness"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Oblivion",
                definitions = listOf(
                    "The state of being completely forgotten",
                    "A state of unawareness or unconsciousness"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ostentatious",
                definitions = listOf(
                    "Designed to impress or attract attention",
                    "Characterized by pretentious or vulgar display"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Omnipotent",
                definitions = listOf("Having unlimited power or authority", "Able to do anything"),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Omniscient",
                definitions = listOf(
                    "Knowing everything",
                    "Having complete or unlimited knowledge or understanding"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ontology",
                definitions = listOf(
                    "The branch of philosophy dealing with the nature of being",
                    "The study of existence and its categories"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Opulent",
                definitions = listOf(
                    "Rich and luxurious in appearance or lifestyle",
                    "Characterized by great wealth or abundance"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Oxymoron",
                definitions = listOf(
                    "A figure of speech in which contradictory terms appear together",
                    "A combination of words with opposing meanings"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Onerous",
                definitions = listOf(
                    "Involving a burdensome amount of effort or difficulty",
                    "Oppressively heavy or troublesome"
                ),
                letter = Letter.O,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Obstrucción",
                definitions = listOf(
                    "El acto de bloquear o impedir algo",
                    "Una barrera o dificultad que retrasa un proceso"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Obcecación",
                definitions = listOf(
                    "Una actitud de terquedad extrema",
                    "La incapacidad de aceptar ideas diferentes o nuevas"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Obsolescencia",
                definitions = listOf(
                    "El proceso de volverse anticuado o inútil",
                    "La condición de ser reemplazado por algo más moderno"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Omnipotente",
                definitions = listOf(
                    "Que tiene poder ilimitado",
                    "Capaz de realizar cualquier cosa"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Omnisciente",
                definitions = listOf("Que sabe y conoce todo", "Alguien con conocimiento absoluto"),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ostentación",
                definitions = listOf(
                    "Un despliegue exagerado para impresionar a otros",
                    "Una demostración de lujo o riqueza"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ontología",
                definitions = listOf(
                    "La rama de la filosofía que estudia el ser",
                    "El análisis de la existencia y sus categorías"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Oprobio",
                definitions = listOf(
                    "Una deshonra pública causada por una acción vergonzosa",
                    "Un acto que provoca humillación o desprecio"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Opulencia",
                definitions = listOf(
                    "Riqueza y lujo excesivos",
                    "Una abundancia de recursos materiales o financieros"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Oscilación",
                definitions = listOf(
                    "El movimiento repetitivo de un lado a otro",
                    "La variación entre dos estados opuestos"
                ),
                letter = Letter.O,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun p() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Paper",
                definitions = listOf(
                    "A thin material used for writing or printing",
                    "A document containing information"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Pen",
                definitions = listOf(
                    "A tool used for writing with ink",
                    "A small enclosure for keeping animals"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Plant",
                definitions = listOf(
                    "A living organism that grows in soil and uses sunlight to produce energy",
                    "A factory or industrial building"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Plane",
                definitions = listOf("A flat surface", "A vehicle designed for air travel"),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Plate",
                definitions = listOf(
                    "A flat dish used for serving food",
                    "A flat piece of material often used as a base"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Pool",
                definitions = listOf(
                    "A small body of still water",
                    "A game played on a table with balls and cues"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Post",
                definitions = listOf(
                    "A piece of mail or a letter",
                    "A vertical stick or pole used for support"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Paint",
                definitions = listOf(
                    "A liquid used to add color to surfaces",
                    "The act of applying paint to something"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Path",
                definitions = listOf(
                    "A narrow way or track for walking",
                    "A course of action or direction to follow"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Park",
                definitions = listOf(
                    "A public area of land for recreation",
                    "To stop and leave a vehicle in a designated place"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Papel",
                definitions = listOf(
                    "Un material delgado usado para escribir o imprimir",
                    "Un documento con información"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Pluma",
                definitions = listOf(
                    "Un objeto usado para escribir con tinta",
                    "Una estructura que cubre el cuerpo de las aves"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Planta",
                definitions = listOf(
                    "Un ser vivo que crece en el suelo y realiza fotosíntesis",
                    "Un edificio utilizado para la producción industrial"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Plato",
                definitions = listOf(
                    "Un recipiente plano para servir comida",
                    "Una porción de alimento servida en un plato"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Piscina",
                definitions = listOf(
                    "Un lugar lleno de agua para nadar",
                    "Una estructura para almacenar agua para recreación"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Pintura",
                definitions = listOf(
                    "Un líquido utilizado para colorear superficies",
                    "Una obra artística hecha con colores"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Parque",
                definitions = listOf(
                    "Un área pública para recreación",
                    "Un lugar donde se pueden estacionar vehículos"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Pelota",
                definitions = listOf(
                    "Un objeto redondo usado en juegos o deportes",
                    "Un juguete que se puede lanzar o rebotar"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Puerta",
                definitions = listOf(
                    "Una estructura móvil que permite entrar o salir de un lugar",
                    "Un punto de acceso en una pared o valla"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Pan",
                definitions = listOf(
                    "Un alimento hecho de harina y horneado",
                    "Un objeto usado para cocinar en la estufa"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Paradigm",
                definitions = listOf(
                    "A typical example or pattern of something",
                    "A framework of concepts or beliefs used to understand a topic"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Parallel",
                definitions = listOf(
                    "Two lines or objects that are equidistant and never meet",
                    "Happening or existing at the same time"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Paradox",
                definitions = listOf(
                    "A statement that contradicts itself but might be true",
                    "A situation with seemingly conflicting elements"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Patron",
                definitions = listOf(
                    "A person who gives support to an individual or cause",
                    "A regular customer of a business or establishment"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Perceive",
                definitions = listOf(
                    "To become aware of something through the senses",
                    "To interpret or understand something in a certain way"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Persist",
                definitions = listOf(
                    "To continue doing something despite difficulty or opposition",
                    "To remain in a particular state or condition"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Pioneer",
                definitions = listOf(
                    "A person who is among the first to explore or settle a new area",
                    "Someone who develops or introduces new ideas or methods"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Plateau",
                definitions = listOf(
                    "An area of flat, high ground",
                    "A period of stability or little change after growth"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Prevalent",
                definitions = listOf(
                    "Widespread or commonly occurring",
                    "Dominant or having great influence in a particular area"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Provoke",
                definitions = listOf(
                    "To stimulate a reaction or emotion, often negative",
                    "To incite someone to take action"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Paradigma",
                definitions = listOf(
                    "Un modelo o ejemplo típico de algo",
                    "Un conjunto de creencias o conceptos que forman un marco de referencia"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Paralelo",
                definitions = listOf(
                    "Dos líneas que nunca se cruzan y están equidistantes",
                    "Algo que ocurre o existe al mismo tiempo"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Paradoja",
                definitions = listOf(
                    "Una declaración que parece contradictoria pero podría ser verdadera",
                    "Una situación con elementos aparentemente opuestos"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Patrón",
                definitions = listOf(
                    "Una figura o diseño que se repite",
                    "Una persona que apoya económicamente a otra o a una causa"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Percibir",
                definitions = listOf(
                    "Darse cuenta de algo a través de los sentidos",
                    "Interpretar o comprender algo de cierta manera"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Persistir",
                definitions = listOf(
                    "Continuar haciendo algo a pesar de las dificultades",
                    "Mantenerse en un estado o condición particular"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Pionero",
                definitions = listOf(
                    "Una persona que es la primera en explorar o establecerse en un lugar",
                    "Alguien que desarrolla o introduce nuevas ideas o métodos"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Meseta",
                definitions = listOf(
                    "Una extensión de terreno plano y elevado",
                    "Un periodo de estabilidad o poco cambio después de un crecimiento"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Prevalente",
                definitions = listOf(
                    "Que es común o está extendido en un área",
                    "Que domina o tiene gran influencia en un contexto"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Provocar",
                definitions = listOf(
                    "Estímular una reacción o emoción, a menudo negativa",
                    "Incitar a alguien a actuar de cierta manera"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Paradoxical",
                definitions = listOf(
                    "Seemingly contradictory but potentially true",
                    "Relating to a paradox or a self-contradictory statement"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Perfunctory",
                definitions = listOf(
                    "Carried out with minimum effort or reflection",
                    "Lacking enthusiasm or interest"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Perspicacity",
                definitions = listOf(
                    "The quality of having a ready insight into things",
                    "Sharpness in understanding or perception"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Precipice",
                definitions = listOf(
                    "A very steep cliff or edge",
                    "A hazardous situation that is on the brink of disaster"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Profligate",
                definitions = listOf(
                    "Recklessly extravagant or wasteful in the use of resources",
                    "A person who is wildly indulgent"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Propensity",
                definitions = listOf(
                    "An inclination or natural tendency to behave in a particular way",
                    "A predisposition or habit"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Pernicious",
                definitions = listOf(
                    "Having a harmful effect, especially in a gradual or subtle way",
                    "Destructive or dangerous"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Pulchritude",
                definitions = listOf(
                    "Physical beauty or attractiveness",
                    "The quality of being beautiful"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Pusillanimous",
                definitions = listOf(
                    "Showing a lack of courage or determination",
                    "Timid or cowardly"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Polyglot",
                definitions = listOf(
                    "A person who knows and is able to use several languages",
                    "Something written or composed in multiple languages"
                ),
                letter = Letter.P,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Paradojal",
                definitions = listOf(
                    "Que parece contradictorio pero podría ser verdadero",
                    "Relacionado con una paradoja o una declaración que desafía la lógica"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Perentorio",
                definitions = listOf(
                    "Urgente o inaplazable",
                    "Que no admite contradicción o réplica"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Perspicaz",
                definitions = listOf(
                    "Capaz de comprender o discernir rápidamente",
                    "Que tiene una percepción clara y aguda"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Precipicio",
                definitions = listOf(
                    "Un acantilado o borde extremadamente empinado",
                    "Una situación peligrosa al borde de un desastre"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Prolífico",
                definitions = listOf(
                    "Que produce o genera algo en abundancia",
                    "Altamente productivo o fructífero"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Propensión",
                definitions = listOf(
                    "Una inclinación natural hacia algo",
                    "Un hábito o predisposición"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Pernicioso",
                definitions = listOf(
                    "Que causa daño de manera gradual o sutil",
                    "Altamente destructivo o perjudicial"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Pulcritud",
                definitions = listOf(
                    "Cuidado o esmero en el aspecto personal o en el trabajo",
                    "La cualidad de ser limpio y ordenado"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Pusilánime",
                definitions = listOf(
                    "Que muestra falta de valor o determinación",
                    "Cobarde o con poca fortaleza de ánimo"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Políglota",
                definitions = listOf(
                    "Una persona que habla varios idiomas",
                    "Algo escrito o compuesto en múltiples lenguas"
                ),
                letter = Letter.P,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun q() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Queen",
                definitions = listOf(
                    "A female ruler of a country or kingdom",
                    "The wife of a king"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Question",
                definitions = listOf(
                    "A sentence or phrase used to ask for information",
                    "An issue or problem requiring a solution or discussion"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Quick",
                definitions = listOf(
                    "Happening in a short amount of time",
                    "Moving fast or able to react quickly"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Quiet",
                definitions = listOf(
                    "Making little or no noise",
                    "Free from disturbance or interruptions"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Quit",
                definitions = listOf(
                    "To stop doing something",
                    "To leave a job or position voluntarily"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Quiz",
                definitions = listOf(
                    "A test of knowledge, often short",
                    "A game or competition where questions are answered"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Quilt",
                definitions = listOf(
                    "A bed covering made of padded fabric",
                    "A decorative blanket made from multiple pieces of cloth"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Queue",
                definitions = listOf(
                    "A line of people or vehicles waiting their turn",
                    "A sequence of tasks or events waiting to be processed"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Quote",
                definitions = listOf(
                    "To repeat the words of someone else",
                    "A statement from a text or speech used as evidence"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Quack",
                definitions = listOf(
                    "The sound made by a duck",
                    "A person pretending to have medical knowledge"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Quema",
                definitions = listOf(
                    "El acto de prender fuego a algo",
                    "Un proceso de combustión controlada"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Queja",
                definitions = listOf(
                    "Una expresión de insatisfacción o molestia",
                    "Una reclamación hecha por descontento"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Queda",
                definitions = listOf("Tranquilo o silencioso", "Sin movimiento o perturbación"),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Quemar",
                definitions = listOf(
                    "Destruir algo con fuego",
                    "Causar daño mediante calor extremo"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Querer",
                definitions = listOf(
                    "Tener el deseo o la intención de algo",
                    "Sentir afecto o cariño por alguien"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Quebrar",
                definitions = listOf(
                    "Romper algo en pedazos",
                    "Fallar en cumplir una obligación, especialmente financiera"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Quinto",
                definitions = listOf(
                    "El número cinco en una secuencia",
                    "Una posición que sigue después de la cuarta"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Químico",
                definitions = listOf(
                    "Relacionado con la química o sus procesos",
                    "Una sustancia usada en experimentos o reacciones químicas"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Queso",
                definitions = listOf(
                    "Un alimento sólido hecho de leche",
                    "Un producto lácteo que puede ser fresco o añejo"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Quijada",
                definitions = listOf(
                    "El hueso de la mandíbula en humanos y animales",
                    "Una parte de la boca que se mueve al masticar"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Quarantine",
                definitions = listOf(
                    "A state of isolation to prevent the spread of disease",
                    "The act of separating individuals exposed to contagious conditions"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quota",
                definitions = listOf(
                    "A fixed share or portion assigned to someone",
                    "A limit on the amount of something that can be produced or sold"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Query",
                definitions = listOf(
                    "A question or inquiry seeking information",
                    "An act of questioning or expressing doubt"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quaint",
                definitions = listOf(
                    "Attractively old-fashioned or unusual",
                    "Having an antique charm or peculiar quality"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quench",
                definitions = listOf(
                    "To satisfy thirst by drinking",
                    "To extinguish a fire or suppress a feeling"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quadrant",
                definitions = listOf(
                    "One of four equal parts of a circle",
                    "A division of a plane used in geometry or navigation"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Qualm",
                definitions = listOf(
                    "A feeling of uneasiness or doubt",
                    "A sudden sensation of apprehension or regret"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quibble",
                definitions = listOf(
                    "A minor objection or criticism",
                    "To argue or raise objections about trivial matters"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quorum",
                definitions = listOf(
                    "The minimum number of members needed to conduct business",
                    "A group of people required to make decisions officially"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quicksand",
                definitions = listOf(
                    "A loose, wet mixture of sand and water that gives way under weight",
                    "A situation that is difficult to escape or resolve"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Quimera",
                definitions = listOf(
                    "Un sueño o fantasía imposible de realizar",
                    "Una criatura mitológica compuesta por partes de diferentes animales"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Química",
                definitions = listOf(
                    "La ciencia que estudia la composición y propiedades de las sustancias",
                    "Una relación especial entre personas o cosas"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quinta",
                definitions = listOf(
                    "Una propiedad rural destinada a la agricultura o recreo",
                    "La posición número cinco en una serie"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quiebra",
                definitions = listOf(
                    "La incapacidad de una empresa o persona para pagar sus deudas",
                    "Un colapso o fracaso total"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quórum",
                definitions = listOf(
                    "El número mínimo de personas necesarias para que una reunión sea válida",
                    "Una cantidad requerida para tomar decisiones oficiales"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quebrantamiento",
                definitions = listOf(
                    "La acción de romper una ley o norma",
                    "Un acto que provoca daño físico o moral"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Querella",
                definitions = listOf(
                    "Una disputa o desacuerdo entre personas",
                    "Un proceso legal iniciado por una acusación formal"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quinquenio",
                definitions = listOf(
                    "Un periodo de cinco años",
                    "Una unidad de tiempo utilizada para medir ciclos largos"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quiste",
                definitions = listOf(
                    "Una cavidad cerrada que contiene líquido o material semisólido",
                    "Una formación anormal en tejidos vivos"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Quijotesco",
                definitions = listOf(
                    "Que actúa de manera idealista o poco práctica",
                    "Relacionado con las aventuras de Don Quijote"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Quintessential",
                definitions = listOf(
                    "Representing the most perfect or typical example of something",
                    "The purest essence or form of something"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quiescent",
                definitions = listOf(
                    "In a state of inactivity or dormancy",
                    "Causing no trouble; calm and peaceful"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quandary",
                definitions = listOf(
                    "A state of perplexity or uncertainty about what to do",
                    "A difficult situation with no clear solution"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quibble",
                definitions = listOf(
                    "A minor objection or criticism",
                    "To argue or raise objections over trivial matters"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quintessence",
                definitions = listOf(
                    "The most perfect or typical example of a quality or class",
                    "The essence of a thing in its purest form"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quarantine",
                definitions = listOf(
                    "A state of enforced isolation to prevent disease spread",
                    "The act of separating people or animals to prevent contamination"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Querulous",
                definitions = listOf(
                    "Complaining in a petulant or whining manner",
                    "Easily irritated or prone to grumbling"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quagmire",
                definitions = listOf(
                    "A soft, boggy area of land that gives way underfoot",
                    "A complicated or hazardous situation difficult to escape"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quintuple",
                definitions = listOf(
                    "Five times as much or as many",
                    "To increase something by five times its original amount"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quixotic",
                definitions = listOf(
                    "Exceedingly idealistic and impractical",
                    "Romantic or chivalrous to the point of being unrealistic"
                ),
                letter = Letter.Q,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Quimérico",
                definitions = listOf(
                    "Que es fantástico o irreal",
                    "Relacionado con algo que solo existe en la imaginación"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quiescencia",
                definitions = listOf(
                    "Un estado de inactividad o reposo",
                    "La condición de algo que está en pausa pero no terminado"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Querencia",
                definitions = listOf(
                    "La inclinación o apego a un lugar determinado",
                    "El deseo de volver al sitio donde se siente seguro"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quijotesco",
                definitions = listOf(
                    "Idealista o soñador hasta el punto de la impracticabilidad",
                    "Relacionado con las aventuras de Don Quijote"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quimera",
                definitions = listOf(
                    "Un sueño imposible o una fantasía irrealizable",
                    "Una criatura mitológica compuesta por partes de diferentes animales"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quinquenal",
                definitions = listOf(
                    "Algo que ocurre o se repite cada cinco años",
                    "Un plan o periodo que dura cinco años"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quórum",
                definitions = listOf(
                    "El número mínimo de miembros necesarios para una reunión válida",
                    "La cantidad suficiente para tomar decisiones oficiales"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quiste",
                definitions = listOf(
                    "Una cavidad cerrada que contiene líquido o material semisólido",
                    "Una formación anormal en los tejidos de un organismo"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quiebra",
                definitions = listOf(
                    "La situación de insolvencia económica total",
                    "El colapso o ruptura de algo, como una empresa o estructura"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Quimerismo",
                definitions = listOf(
                    "Una mezcla de diferentes elementos en un solo cuerpo o concepto",
                    "La coexistencia de material genético de distintos orígenes en un mismo organismo"
                ),
                letter = Letter.Q,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun r() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Rain",
                definitions = listOf(
                    "Water droplets that fall from the sky",
                    "A type of weather involving precipitation"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "River",
                definitions = listOf(
                    "A large natural stream of water flowing into a sea, lake, or another river",
                    "A body of water used for transportation or irrigation"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Room",
                definitions = listOf(
                    "An enclosed area within a building",
                    "A space allocated for a specific purpose"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Rock",
                definitions = listOf(
                    "A solid mineral material found in nature",
                    "A genre of popular music with strong rhythm"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Red",
                definitions = listOf(
                    "A primary color associated with warmth and passion",
                    "A color that appears in the visible spectrum of light"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Run",
                definitions = listOf(
                    "To move swiftly on foot",
                    "To manage or operate something, such as a business"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Road",
                definitions = listOf(
                    "A paved way for vehicles and pedestrians",
                    "A route or path connecting two locations"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ring",
                definitions = listOf(
                    "A small circular band worn as jewelry",
                    "The sound made by a bell or phone"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Raincoat",
                definitions = listOf(
                    "A waterproof coat worn in the rain",
                    "An outer garment used for protection against wet weather"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Rabbit",
                definitions = listOf(
                    "A small, furry mammal with long ears",
                    "An animal often associated with quick movement"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Río",
                definitions = listOf(
                    "Una corriente natural de agua que fluye hacia un mar o lago",
                    "Un cuerpo de agua usado para transporte o riego"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Rojo",
                definitions = listOf(
                    "Un color primario asociado con la pasión y el calor",
                    "El color de muchas frutas maduras como la manzana o la fresa"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Rana",
                definitions = listOf(
                    "Un anfibio que salta y vive cerca del agua",
                    "Un animal conocido por su croar y piel húmeda"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Roca",
                definitions = listOf(
                    "Un material mineral sólido encontrado en la naturaleza",
                    "Un elemento básico que forma montañas y suelos"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Reloj",
                definitions = listOf(
                    "Un dispositivo usado para medir el tiempo",
                    "Un objeto que puede ser de pulsera o de pared"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ratón",
                definitions = listOf(
                    "Un pequeño mamífero con cola larga",
                    "Un dispositivo usado para controlar un cursor en la computadora"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Raíz",
                definitions = listOf(
                    "La parte de una planta que crece bajo tierra",
                    "El origen o causa principal de algo"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ruta",
                definitions = listOf(
                    "Un camino o vía para llegar a un lugar",
                    "Una dirección o curso a seguir"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ropa",
                definitions = listOf(
                    "Prendas utilizadas para cubrir el cuerpo",
                    "Vestimenta usada para protegerse o decorar"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ratón",
                definitions = listOf(
                    "Un pequeño roedor",
                    "Un dispositivo de entrada para computadoras"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Radiant",
                definitions = listOf("Emitting light or heat", "Expressing great joy or happiness"),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Realm",
                definitions = listOf(
                    "A kingdom or territory",
                    "A field or domain of interest or activity"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Rebound",
                definitions = listOf(
                    "To bounce back after hitting a surface",
                    "To recover quickly from a setback or loss"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Refuge",
                definitions = listOf(
                    "A place of safety or shelter",
                    "Protection from danger or distress"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Reform",
                definitions = listOf(
                    "To make changes in order to improve something",
                    "To correct or amend a fault or problem"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Relay",
                definitions = listOf(
                    "A team race in which participants take turns running",
                    "To pass a message or signal from one person to another"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Render",
                definitions = listOf(
                    "To provide or give a service, help, or expression",
                    "To cause something to become or be in a certain state"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Resilient",
                definitions = listOf(
                    "Able to recover quickly from difficulties",
                    "Capable of springing back into shape after being bent"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Revoke",
                definitions = listOf(
                    "To officially cancel or withdraw something",
                    "To take back or annul a decision or privilege"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ritual",
                definitions = listOf(
                    "A series of actions performed according to a prescribed order",
                    "A ceremonial act or practice"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Radiante",
                definitions = listOf(
                    "Que emite luz o calor",
                    "Que expresa alegría o felicidad extrema"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Reino",
                definitions = listOf(
                    "Un territorio gobernado por un rey o reina",
                    "Un campo o área de interés o actividad"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Rebote",
                definitions = listOf(
                    "El acto de saltar hacia atrás después de un impacto",
                    "La recuperación rápida tras un contratiempo o pérdida"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Refugio",
                definitions = listOf(
                    "Un lugar seguro o de protección",
                    "Un espacio que ofrece resguardo contra peligros o adversidades"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Reforma",
                definitions = listOf(
                    "Un cambio realizado para mejorar algo",
                    "La corrección o enmienda de un problema o defecto"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Relevo",
                definitions = listOf(
                    "Una carrera en equipo donde los participantes se turnan para correr",
                    "El acto de sustituir a una persona en una tarea"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Rendir",
                definitions = listOf(
                    "Entregar o producir algo",
                    "Hacer que algo se convierta en otro estado o condición"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Resiliencia",
                definitions = listOf(
                    "La capacidad de recuperarse rápidamente de dificultades",
                    "La habilidad de adaptarse ante la adversidad"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Revocar",
                definitions = listOf(
                    "Cancelar oficialmente algo previamente otorgado",
                    "Dejar sin efecto una decisión o privilegio"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ritual",
                definitions = listOf(
                    "Un conjunto de acciones realizadas de acuerdo a un orden establecido",
                    "Un acto ceremonial o práctica repetitiva"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Reciprocity",
                definitions = listOf(
                    "The practice of exchanging things with mutual benefit",
                    "A relationship where actions are returned equally"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Resplendent",
                definitions = listOf(
                    "Shining brilliantly; glowing with splendor",
                    "Attractive and impressive through being richly colorful or sumptuous"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Reverence",
                definitions = listOf(
                    "Deep respect for someone or something",
                    "An act of showing honor or admiration"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ramification",
                definitions = listOf(
                    "A consequence of an action or event, especially when complex",
                    "A subdivision or branching out of something"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Rectitude",
                definitions = listOf(
                    "Morally correct behavior or thinking",
                    "Righteousness or integrity in conduct"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Recalcitrant",
                definitions = listOf(
                    "Resistant to authority or control",
                    "Difficult to manage or deal with"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Repertoire",
                definitions = listOf(
                    "A collection of works that a performer is prepared to present",
                    "The range of skills or techniques a person can perform"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Reticent",
                definitions = listOf(
                    "Not revealing one's thoughts or feelings readily",
                    "Reluctant to share information or speak openly"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Resurgence",
                definitions = listOf(
                    "An increase or revival after a period of little activity",
                    "The reappearance of something that had been inactive"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Rejuvenate",
                definitions = listOf(
                    "To make someone or something look or feel younger",
                    "To restore vitality or energy"
                ),
                letter = Letter.R,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Reciprocidad",
                definitions = listOf(
                    "La práctica de intercambiar cosas con beneficio mutuo",
                    "Una relación donde las acciones se devuelven de manera equivalente"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Resplandeciente",
                definitions = listOf(
                    "Que brilla intensamente o emite esplendor",
                    "Atractivo e impresionante por su riqueza de color o lujo"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Reverencia",
                definitions = listOf(
                    "Un profundo respeto hacia alguien o algo",
                    "Un acto de mostrar honor o admiración"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ramificación",
                definitions = listOf(
                    "Una consecuencia de una acción, especialmente si es compleja",
                    "Una subdivisión o extensión de algo"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Rectitud",
                definitions = listOf(
                    "Comportamiento o pensamiento moralmente correcto",
                    "La cualidad de actuar con justicia o integridad"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Recalcitrante",
                definitions = listOf(
                    "Que resiste la autoridad o el control",
                    "Difícil de manejar o tratar"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Repertorio",
                definitions = listOf(
                    "Una colección de obras que un artista está preparado para presentar",
                    "El rango de habilidades o técnicas que alguien puede realizar"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Reticente",
                definitions = listOf(
                    "Que no revela fácilmente sus pensamientos o sentimientos",
                    "Reacio a compartir información o hablar abiertamente"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Resurgimiento",
                definitions = listOf(
                    "Un aumento o renacimiento después de un período de inactividad",
                    "La reaparición de algo que había estado inactivo"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Rejuvenecer",
                definitions = listOf(
                    "Hacer que alguien o algo parezca más joven",
                    "Restaurar la vitalidad o energía de algo o alguien"
                ),
                letter = Letter.R,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun s() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Sun",
                definitions = listOf(
                    "The star at the center of the solar system",
                    "A source of light and warmth for Earth"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Sea",
                definitions = listOf(
                    "A large body of saltwater that covers most of Earth's surface",
                    "A vast expanse of water connected to an ocean"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Star",
                definitions = listOf(
                    "A celestial body that emits light",
                    "A shape with points often used as a symbol"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ship",
                definitions = listOf(
                    "A large boat used for transportation on water",
                    "To send goods or items from one place to another"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Snow",
                definitions = listOf(
                    "Frozen water vapor that falls as white flakes",
                    "A weather condition involving snowfall"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Sky",
                definitions = listOf(
                    "The space above the Earth seen when looking up",
                    "The atmosphere as it appears from the ground"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Stone",
                definitions = listOf("A small piece of rock", "A hard seed inside some fruits"),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Soup",
                definitions = listOf(
                    "A liquid dish made by boiling meat, vegetables, or fish",
                    "A hot meal served in a bowl"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Sand",
                definitions = listOf(
                    "Small, loose particles of rock found on beaches or deserts",
                    "A granular material formed by weathering of rocks"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Salt",
                definitions = listOf(
                    "A white crystalline substance used to flavor food",
                    "A chemical compound formed by the reaction of an acid and a base"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Sol",
                definitions = listOf(
                    "La estrella en el centro del sistema solar",
                    "Una fuente de luz y calor para la Tierra"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Sal",
                definitions = listOf(
                    "Una sustancia blanca usada para sazonar alimentos",
                    "Un compuesto químico que resulta de la reacción entre un ácido y una base"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Sopa",
                definitions = listOf(
                    "Un plato líquido hecho al hervir carne, verduras o pescado",
                    "Una comida caliente servida en un recipiente"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Silla",
                definitions = listOf(
                    "Un mueble diseñado para sentarse",
                    "Un asiento con respaldo y, a menudo, con patas"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Sombra",
                definitions = listOf(
                    "Una zona donde la luz está bloqueada por un objeto",
                    "Un área oscura proyectada por algo que bloquea la luz"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Sendero",
                definitions = listOf(
                    "Un camino pequeño y estrecho",
                    "Una ruta usada para caminar o explorar"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Sonido",
                definitions = listOf(
                    "Una vibración que viaja a través del aire y puede ser escuchada",
                    "Algo que se percibe con el sentido del oído"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Sabor",
                definitions = listOf(
                    "La sensación que se percibe al comer o beber algo",
                    "Una de las cualidades principales de los alimentos"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Sierra",
                definitions = listOf(
                    "Una herramienta con dientes usada para cortar madera",
                    "Una cadena montañosa"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Silencio",
                definitions = listOf(
                    "La ausencia de ruido o sonido",
                    "Un estado de tranquilidad o calma"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Sanctuary",
                definitions = listOf(
                    "A place of safety or refuge",
                    "A sacred or holy place, such as a temple or church"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Sentiment",
                definitions = listOf(
                    "A feeling or emotion expressed in words or actions",
                    "A general attitude or opinion about something"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Solitude",
                definitions = listOf(
                    "The state of being alone, often by choice",
                    "A condition of peaceful isolation"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Sovereign",
                definitions = listOf(
                    "A supreme ruler, such as a king or queen",
                    "Possessing ultimate power or authority"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Spectrum",
                definitions = listOf(
                    "A range of different things, often colors or ideas",
                    "A band of colors produced by light through a prism"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Strategy",
                definitions = listOf(
                    "A plan designed to achieve a specific goal",
                    "A careful method of managing or planning something"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Symmetry",
                definitions = listOf(
                    "The quality of being made up of identical parts on both sides of an axis",
                    "Balanced proportions in design or structure"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Surplus",
                definitions = listOf(
                    "An amount that is more than what is needed",
                    "Excess quantity or resources"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Skeptic",
                definitions = listOf(
                    "A person inclined to doubt or question accepted beliefs",
                    "Someone who needs evidence before believing something"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Sustain",
                definitions = listOf(
                    "To support or maintain something over time",
                    "To endure or withstand a hardship"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Santuario",
                definitions = listOf(
                    "Un lugar de refugio o seguridad",
                    "Un espacio sagrado o religioso, como un templo o iglesia"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Sentimiento",
                definitions = listOf(
                    "Una emoción o estado de ánimo expresado de alguna manera",
                    "Una opinión o actitud general sobre algo"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Soledad",
                definitions = listOf(
                    "El estado de estar solo, generalmente por elección",
                    "Una condición de aislamiento pacífico"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Soberano",
                definitions = listOf(
                    "Un gobernante supremo, como un rey o reina",
                    "Que posee poder o autoridad máxima"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Espectro",
                definitions = listOf(
                    "Una gama de cosas diferentes, como colores o ideas",
                    "Una banda de colores producida al pasar luz a través de un prisma"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Estrategia",
                definitions = listOf(
                    "Un plan diseñado para alcanzar un objetivo específico",
                    "Un método cuidadoso para gestionar o planificar algo"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Simetría",
                definitions = listOf(
                    "La calidad de estar compuesto por partes idénticas en ambos lados de un eje",
                    "Proporciones equilibradas en diseño o estructura"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Excedente",
                definitions = listOf(
                    "Una cantidad que excede lo necesario",
                    "Recursos o bienes en exceso"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Escéptico",
                definitions = listOf(
                    "Una persona inclinada a dudar o cuestionar las creencias aceptadas",
                    "Alguien que necesita pruebas antes de creer en algo"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Sostener",
                definitions = listOf(
                    "Apoyar o mantener algo a lo largo del tiempo",
                    "Resistir o soportar una dificultad"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Sagacious",
                definitions = listOf(
                    "Having or showing keen mental discernment and good judgment",
                    "Wise or shrewd in practical matters"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Sanctimonious",
                definitions = listOf(
                    "Making a show of being morally superior to others",
                    "Hypocritically pious or self-righteous"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Scintillating",
                definitions = listOf(
                    "Brilliantly and excitingly clever or skillful",
                    "Sparkling or shining brightly"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Sycophant",
                definitions = listOf(
                    "A person who acts obsequiously toward someone to gain advantage",
                    "A flatterer or a self-serving individual"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Subterfuge",
                definitions = listOf(
                    "Deceit used to achieve a goal",
                    "A trick or stratagem used to evade a rule or escape a consequence"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Surreptitious",
                definitions = listOf(
                    "Kept secret, especially because it would not be approved of",
                    "Done stealthily or in a hidden manner"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Supercilious",
                definitions = listOf(
                    "Behaving or looking as though one is superior to others",
                    "Haughtily disdainful or arrogant"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Specious",
                definitions = listOf(
                    "Misleading in appearance, especially in being superficially plausible",
                    "Seemingly true or valid but actually false"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Semblance",
                definitions = listOf(
                    "The outward appearance or apparent form of something",
                    "A resemblance or similarity to something"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Sophistry",
                definitions = listOf(
                    "The use of fallacious arguments with the intention to deceive",
                    "Subtle and deceptive reasoning or logic"
                ),
                letter = Letter.S,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Sagaz",
                definitions = listOf(
                    "Que tiene agudeza mental y buen juicio",
                    "Perspicaz o hábil para entender y resolver situaciones"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Sanctimonioso",
                definitions = listOf(
                    "Que aparenta ser moralmente superior a los demás",
                    "Hipócritamente devoto o justo"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Escintilante",
                definitions = listOf(
                    "Que brilla intensamente o resplandece",
                    "Brillante en términos de ingenio o habilidad"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Servil",
                definitions = listOf(
                    "Excesivamente sumiso o dispuesto a complacer a los demás",
                    "Relacionado con la actitud de obediencia extrema"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Subterfugio",
                definitions = listOf(
                    "Un medio engañoso utilizado para alcanzar un objetivo",
                    "Una estrategia empleada para evadir responsabilidades"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Sibilino",
                definitions = listOf(
                    "Difícil de entender o misterioso",
                    "Algo que parece tener un significado oculto o enigmático"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Superfluo",
                definitions = listOf(
                    "Que no es necesario o resulta excesivo",
                    "Algo que excede lo que se considera suficiente"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Especioso",
                definitions = listOf(
                    "Que parece cierto o válido pero no lo es",
                    "Aparentemente lógico o verdadero, pero engañoso"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Semejanza",
                definitions = listOf(
                    "La similitud entre dos cosas",
                    "La apariencia o representación externa de algo"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Sofisma",
                definitions = listOf(
                    "Un razonamiento falso hecho con intención de engañar",
                    "Un argumento aparentemente correcto pero erróneo en su lógica"
                ),
                letter = Letter.S,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun t() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Tree",
                definitions = listOf(
                    "A tall plant with a trunk and branches made of wood",
                    "A living organism that provides oxygen and shade"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Table",
                definitions = listOf(
                    "A piece of furniture with a flat surface and legs",
                    "A structured arrangement of data, often in rows and columns"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Toy",
                definitions = listOf(
                    "An object designed for children to play with",
                    "Something used for entertainment or amusement"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Truck",
                definitions = listOf(
                    "A motor vehicle designed to transport goods",
                    "A large, heavy vehicle used for commercial purposes"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Train",
                definitions = listOf(
                    "A series of connected vehicles that run on tracks",
                    "To teach or develop skills in a particular area"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Tea",
                definitions = listOf(
                    "A drink made by steeping leaves of the tea plant in hot water",
                    "A type of beverage enjoyed worldwide"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Tent",
                definitions = listOf(
                    "A portable shelter made of fabric supported by poles",
                    "A structure used for camping or outdoor activities"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Tiger",
                definitions = listOf(
                    "A large wild cat with orange fur and black stripes",
                    "An animal known for its strength and agility"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Towel",
                definitions = listOf(
                    "A piece of fabric used for drying oneself",
                    "An absorbent cloth for cleaning or wiping surfaces"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Toy",
                definitions = listOf(
                    "An object for children to play with",
                    "A thing designed for amusement or decoration"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Tigre",
                definitions = listOf(
                    "Un felino grande con pelaje anaranjado y rayas negras",
                    "Un animal conocido por su fuerza y ferocidad"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Taza",
                definitions = listOf(
                    "Un recipiente usado para beber líquidos calientes",
                    "Un pequeño vaso con asa, generalmente de cerámica"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Tierra",
                definitions = listOf(
                    "El suelo o superficie del planeta",
                    "El planeta donde vivimos"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Tela",
                definitions = listOf(
                    "Un material hecho de fibras entrelazadas",
                    "Un tejido usado para confeccionar ropa o decorar"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Tenedor",
                definitions = listOf(
                    "Un utensilio usado para pinchar o recoger comida",
                    "Una herramienta de mesa con dientes"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Teléfono",
                definitions = listOf(
                    "Un dispositivo usado para realizar llamadas",
                    "Una herramienta para comunicarse a distancia"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Tomate",
                definitions = listOf(
                    "Una fruta roja y jugosa utilizada en la cocina",
                    "Un alimento básico en ensaladas y salsas"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Tornado",
                definitions = listOf(
                    "Un fenómeno meteorológico de viento en forma de espiral",
                    "Una tormenta con vientos extremadamente fuertes"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Túnel",
                definitions = listOf(
                    "Un pasaje subterráneo para vehículos o personas",
                    "Una estructura que atraviesa montañas o terrenos"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Tren",
                definitions = listOf(
                    "Un vehículo que viaja sobre rieles",
                    "Un medio de transporte compuesto por varios vagones"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Tangible",
                definitions = listOf(
                    "Capable of being touched or felt",
                    "Clear and definite; real or concrete"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Trajectory",
                definitions = listOf(
                    "The path followed by a moving object under the influence of forces",
                    "A course or route taken to achieve a goal"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Threshold",
                definitions = listOf(
                    "The point or level at which something begins or changes",
                    "The entry or doorway to a room or building"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Tenacity",
                definitions = listOf(
                    "The quality of being determined or persistent",
                    "The ability to hold onto something firmly"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Turbulent",
                definitions = listOf(
                    "Characterized by conflict, disorder, or confusion",
                    "Moving violently or unsteadily, such as air or water currents"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Tranquility",
                definitions = listOf(
                    "A state of peace and calm",
                    "The quality of being free from disturbance or agitation"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Tapestry",
                definitions = listOf(
                    "A decorative woven fabric with intricate designs",
                    "A complex or rich combination of ideas or elements"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Transcend",
                definitions = listOf(
                    "To go beyond the limits of something",
                    "To surpass in excellence or achievement"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Tribunal",
                definitions = listOf(
                    "A court of justice",
                    "A body established to settle disputes or administer laws"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Tether",
                definitions = listOf(
                    "A rope or chain used to tie an animal to a fixed object",
                    "To use a tether to secure something"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Tangente",
                definitions = listOf(
                    "Una línea que toca una curva en un solo punto",
                    "Una idea o tema que se desvía del principal en una conversación"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Trayectoria",
                definitions = listOf(
                    "El camino seguido por un objeto en movimiento",
                    "El desarrollo o curso de una carrera o vida"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Travesía",
                definitions = listOf(
                    "Un viaje largo o difícil, especialmente por mar o tierra",
                    "Un recorrido o desplazamiento de un lugar a otro"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Tenacidad",
                definitions = listOf(
                    "La cualidad de ser perseverante o firme",
                    "La capacidad de resistir o mantener algo con fuerza"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Turbulencia",
                definitions = listOf(
                    "Un movimiento violento o desordenado en el aire o agua",
                    "Una situación de conflicto o desorden"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Tranquilidad",
                definitions = listOf(
                    "Un estado de calma y paz",
                    "La ausencia de perturbaciones o tensiones"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Tapiz",
                definitions = listOf(
                    "Una tela decorativa tejida con diseños intrincados",
                    "Una combinación rica y compleja de ideas o elementos"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Trasciende",
                definitions = listOf(
                    "Ir más allá de los límites de algo",
                    "Superar en excelencia o logro"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Tribunal",
                definitions = listOf(
                    "Un organismo encargado de administrar justicia",
                    "Un lugar donde se resuelven disputas legales"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Término",
                definitions = listOf(
                    "Una palabra o expresión con un significado específico",
                    "El punto final de algo en tiempo o espacio"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Trepidation",
                definitions = listOf(
                    "A feeling of fear or anxiety about something that may happen",
                    "A trembling motion caused by fear or uncertainty"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Transcendent",
                definitions = listOf(
                    "Surpassing ordinary limits; extraordinary",
                    "Beyond or above the range of normal human experience"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Tenebrous",
                definitions = listOf(
                    "Dark, shadowy, or obscure",
                    "Gloomy and mysterious in nature"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Tantamount",
                definitions = listOf(
                    "Equivalent in value, significance, or effect",
                    "Virtually the same as something"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Taciturn",
                definitions = listOf(
                    "Reserved or uncommunicative in speech",
                    "Inclined to silence or reluctant to join conversation"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Temerity",
                definitions = listOf(
                    "Excessive confidence or boldness",
                    "A reckless disregard for danger or consequences"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Threnody",
                definitions = listOf(
                    "A lament or a mournful piece of music",
                    "A song or poem expressing sorrow or mourning"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Torpor",
                definitions = listOf(
                    "A state of physical or mental inactivity",
                    "Lethargy or lack of energy"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Truculent",
                definitions = listOf(
                    "Eager or quick to argue or fight",
                    "Aggressively defiant or hostile"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Tessellate",
                definitions = listOf(
                    "To cover a surface with repeated geometric shapes without gaps",
                    "To arrange patterns in a way that they fit perfectly together"
                ),
                letter = Letter.T,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Trepidante",
                definitions = listOf(
                    "Que produce una sensación de temor o inquietud",
                    "Relacionado con un movimiento tembloroso o vibrante"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Trascendental",
                definitions = listOf(
                    "Que va más allá de los límites normales de la experiencia",
                    "De gran importancia o significado en un contexto amplio"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Tenebroso",
                definitions = listOf(
                    "Oscuro o sombrío",
                    "Misterioso o inquietante en su naturaleza"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Tácito",
                definitions = listOf(
                    "Que no se expresa de manera directa pero se sobreentiende",
                    "Implícito o asumido sin necesidad de palabras"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Temerario",
                definitions = listOf(
                    "Que muestra una audacia excesiva sin considerar los riesgos",
                    "Imprudente o atrevido en extremo"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Tonalidad",
                definitions = listOf(
                    "La calidad o carácter de un sonido o color",
                    "El sistema de notas que define una obra musical"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Torpor",
                definitions = listOf(
                    "Un estado de inactividad física o mental",
                    "Falta de energía o entusiasmo"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Truculento",
                definitions = listOf(
                    "Que muestra una actitud feroz o agresiva",
                    "Que resulta intimidante o amenazante"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Textura",
                definitions = listOf(
                    "La apariencia o sensación de una superficie",
                    "La organización o estructura de los elementos en algo"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Teselado",
                definitions = listOf(
                    "Diseñado con patrones geométricos repetidos sin dejar espacios",
                    "Relacionado con el arte de ensamblar piezas en mosaico"
                ),
                letter = Letter.T,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )

        )

        private fun u() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Umbrella",
                definitions = listOf(
                    "A device used for protection against rain",
                    "A portable canopy supported by a central rod"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Unicorn",
                definitions = listOf(
                    "A mythical creature resembling a horse with a single horn",
                    "A symbol of rarity or uniqueness"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Uniform",
                definitions = listOf(
                    "A specific set of clothing worn by members of an organization",
                    "Consistent or unchanging in form or appearance"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Universe",
                definitions = listOf(
                    "All existing matter and space considered as a whole",
                    "The entire cosmos, including stars, planets, and galaxies"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Upstairs",
                definitions = listOf(
                    "The upper floor of a building",
                    "Towards or on a higher level"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Utensil",
                definitions = listOf(
                    "A tool or container used in the kitchen",
                    "An implement for practical use, especially for cooking or eating"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Uproot",
                definitions = listOf(
                    "To pull a plant or tree out of the ground",
                    "To remove someone or something from their usual environment"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Urgent",
                definitions = listOf(
                    "Requiring immediate attention or action",
                    "A situation that is pressing or critical"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Utility",
                definitions = listOf(
                    "A service provided for public use, such as electricity or water",
                    "The usefulness or practicality of something"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Utmost",
                definitions = listOf(
                    "Of the highest degree or the greatest extent",
                    "The maximum or most extreme effort or degree"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Umbra",
                definitions = listOf(
                    "La parte más oscura de una sombra",
                    "El área donde la luz está completamente bloqueada"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Uniforme",
                definitions = listOf(
                    "Un conjunto de ropa usado por miembros de un grupo o institución",
                    "Algo que mantiene consistencia en forma o apariencia"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Universo",
                definitions = listOf(
                    "El conjunto de toda la materia y el espacio existentes",
                    "El cosmos, que incluye estrellas, planetas y galaxias"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Uva",
                definitions = listOf(
                    "Una fruta pequeña y redonda que crece en racimos",
                    "Un ingrediente usado para hacer vino"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Urna",
                definitions = listOf(
                    "Un recipiente usado para depositar votos en elecciones",
                    "Un contenedor para guardar cenizas o reliquias"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Útil",
                definitions = listOf(
                    "Algo que es práctico o tiene un propósito funcional",
                    "Una herramienta que ayuda a realizar tareas"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ubicación",
                definitions = listOf(
                    "El lugar donde se encuentra algo",
                    "Una posición o punto específico en un mapa"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Urgente",
                definitions = listOf(
                    "Que necesita atención inmediata",
                    "Algo que no puede esperar o debe resolverse de inmediato"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Urraca",
                definitions = listOf(
                    "Un ave conocida por su plumaje negro y blanco",
                    "Un pájaro que a menudo recoge objetos brillantes"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Utensilio",
                definitions = listOf(
                    "Un objeto usado en la cocina para preparar o comer alimentos",
                    "Una herramienta práctica para tareas específicas"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Unison",
                definitions = listOf(
                    "Simultaneous performance of actions or speech",
                    "Agreement or harmony between people or groups"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Utopia",
                definitions = listOf(
                    "An imagined place or state where everything is perfect",
                    "An idealized society free from problems"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Undermine",
                definitions = listOf(
                    "To weaken or damage something gradually",
                    "To erode the base or foundation of something"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Unravel",
                definitions = listOf(
                    "To undo or untangle something",
                    "To solve or explain a mystery or problem"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Unilateral",
                definitions = listOf(
                    "Performed or decided by one party or side only",
                    "Affecting only one side of something"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Unearth",
                definitions = listOf(
                    "To discover something hidden or lost",
                    "To dig up something buried in the ground"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Upheaval",
                definitions = listOf(
                    "A violent or sudden change or disruption",
                    "A state of chaos or disorder"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Usher",
                definitions = listOf(
                    "To guide someone to a place",
                    "A person who shows others to their seats in an event or building"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Utensil",
                definitions = listOf(
                    "A tool used for cooking or eating",
                    "A practical implement for a specific purpose"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Utilitarian",
                definitions = listOf(
                    "Designed to be useful rather than attractive",
                    "Focused on practicality and functionality"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Unidad",
                definitions = listOf(
                    "La condición de ser uno o indivisible",
                    "Un elemento o grupo que forma parte de un todo"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Utopía",
                definitions = listOf(
                    "Un lugar imaginario donde todo es perfecto",
                    "Un estado idealizado libre de problemas"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Urdir",
                definitions = listOf(
                    "Planear o preparar algo de forma secreta",
                    "Tejer o entrelazar hilos para formar un tejido"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Unilateral",
                definitions = listOf(
                    "Que afecta o es realizado por una sola parte",
                    "Relacionado con solo un lado de algo"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Usurpar",
                definitions = listOf(
                    "Apoderarse de algo de manera indebida o ilegal",
                    "Tomar el lugar de alguien por la fuerza o sin derecho"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Urna",
                definitions = listOf(
                    "Un recipiente donde se depositan votos en elecciones",
                    "Un contenedor para guardar cenizas o reliquias"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Urgencia",
                definitions = listOf(
                    "La necesidad de atención inmediata",
                    "Un estado de apremio o rapidez en la acción"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ultimátum",
                definitions = listOf(
                    "Una demanda final con condiciones no negociables",
                    "Una declaración que exige acción inmediata"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Utilidad",
                definitions = listOf(
                    "La capacidad de algo para ser usado con eficacia",
                    "El valor práctico o funcional de algo"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Universidad",
                definitions = listOf(
                    "Una institución de educación superior",
                    "Un lugar donde se imparten carreras profesionales y de posgrado"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Ubiquitous",
                definitions = listOf(
                    "Present, appearing, or found everywhere",
                    "Omnipresent or existing in many places simultaneously"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ulterior",
                definitions = listOf(
                    "Existing beyond what is obvious or admitted",
                    "A hidden or underlying reason or motive"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Uncanny",
                definitions = listOf(
                    "Strangely unsettling or mysterious",
                    "Having an extraordinary or inexplicable nature"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Unassailable",
                definitions = listOf(
                    "Unable to be attacked, questioned, or defeated",
                    "Not subject to dispute or denial"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Undulating",
                definitions = listOf(
                    "Having a wavy form or appearance",
                    "Moving in a smooth, wave-like motion"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Unequivocal",
                definitions = listOf(
                    "Leaving no doubt; unambiguous",
                    "Clear and definite in meaning or expression"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Unilateralism",
                definitions = listOf(
                    "The policy of acting independently without seeking approval from others",
                    "A one-sided approach to decision-making"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Usurpation",
                definitions = listOf(
                    "The act of taking power or control illegally or by force",
                    "Wrongful or illegal encroachment or seizure"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Utilitarianism",
                definitions = listOf(
                    "The ethical theory that actions are right if they benefit the majority",
                    "A philosophy focused on maximizing overall happiness or utility"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Umbrage",
                definitions = listOf(
                    "Offense or annoyance",
                    "A feeling of resentment caused by perceived insult"
                ),
                letter = Letter.U,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Ubicuidad",
                definitions = listOf(
                    "La capacidad de estar presente en todas partes al mismo tiempo",
                    "La presencia constante o extendida en varios lugares"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ulterior",
                definitions = listOf(
                    "Que está más allá o detrás de lo evidente",
                    "Un motivo oculto o razón subyacente"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Unánime",
                definitions = listOf(
                    "Que es aceptado o compartido por todos los miembros de un grupo",
                    "Sin discrepancia o conflicto en opiniones"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Undécimo",
                definitions = listOf(
                    "Que ocupa la posición número once en una secuencia",
                    "Algo relacionado con el número once"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Usurpación",
                definitions = listOf(
                    "La toma de poder o control de manera ilegal o por la fuerza",
                    "Una apropiación indebida de derechos o propiedades"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ultramarino",
                definitions = listOf(
                    "Relacionado con territorios o países más allá del mar",
                    "De origen extranjero o ubicado al otro lado del océano"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Utilitarismo",
                definitions = listOf(
                    "La doctrina ética que evalúa las acciones según su utilidad",
                    "Una filosofía que busca maximizar la felicidad general"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Umbroso",
                definitions = listOf(
                    "Que está lleno de sombra",
                    "Relativo a un lugar o ambiente sombrío"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ultimátum",
                definitions = listOf(
                    "Una demanda final que no permite negociación",
                    "Un aviso que exige acción inmediata bajo amenaza de consecuencias"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Urólogo",
                definitions = listOf(
                    "Un médico especializado en el sistema urinario y el aparato reproductor masculino",
                    "Un experto en el diagnóstico y tratamiento de enfermedades urológicas"
                ),
                letter = Letter.U,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun v() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Van",
                definitions = listOf(
                    "A large vehicle used for transporting goods or people",
                    "A type of car often used for deliveries"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Vase",
                definitions = listOf(
                    "A container used for holding flowers",
                    "A decorative object made of glass, ceramic, or metal"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Vote",
                definitions = listOf(
                    "A formal indication of choice, often in an election",
                    "To express an opinion or preference officially"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Vest",
                definitions = listOf(
                    "A sleeveless garment worn over a shirt",
                    "A piece of clothing often part of formal attire"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Valley",
                definitions = listOf(
                    "A low area of land between hills or mountains",
                    "A region often formed by a river or stream"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Voice",
                definitions = listOf(
                    "The sound produced by humans when speaking or singing",
                    "The ability to express thoughts or opinions verbally"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Vanilla",
                definitions = listOf(
                    "A flavor derived from the seeds of an orchid",
                    "A common flavor used in desserts like ice cream"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Village",
                definitions = listOf(
                    "A small community of houses and buildings",
                    "A group of people living in a rural area"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Victory",
                definitions = listOf(
                    "A success or win in a contest or competition",
                    "The act of defeating an opponent or overcoming a challenge"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Vacation",
                definitions = listOf(
                    "A period of time spent away from work or school",
                    "A holiday or trip taken for relaxation or enjoyment"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Vaca",
                definitions = listOf(
                    "Un animal que produce leche y carne",
                    "Un mamífero grande criado en granjas"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Vaso",
                definitions = listOf(
                    "Un recipiente usado para beber líquidos",
                    "Un objeto hecho de vidrio, plástico o cerámica"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Voto",
                definitions = listOf(
                    "Una elección formal en un proceso democrático",
                    "Una expresión de preferencia en una decisión grupal"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Verde",
                definitions = listOf(
                    "El color asociado con la vegetación",
                    "Un tono que está entre el amarillo y el azul en el espectro"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Valor",
                definitions = listOf(
                    "El precio o importancia de algo",
                    "La cualidad de ser valiente o audaz"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Ventana",
                definitions = listOf(
                    "Una abertura en una pared para permitir la entrada de luz y aire",
                    "Un panel de vidrio que se puede abrir o cerrar"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Viento",
                definitions = listOf(
                    "El movimiento natural del aire en la atmósfera",
                    "Una corriente de aire perceptible"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Vida",
                definitions = listOf(
                    "La existencia de un ser vivo",
                    "El tiempo entre el nacimiento y la muerte"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Visita",
                definitions = listOf(
                    "La acción de ir a ver a alguien o algo",
                    "Un periodo de tiempo en el que se pasa en casa de otra persona"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Viaje",
                definitions = listOf(
                    "El acto de ir de un lugar a otro",
                    "Una experiencia que implica moverse a una nueva ubicación"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Validate",
                definitions = listOf(
                    "To confirm or prove the accuracy of something",
                    "To recognize or affirm the value or legitimacy of something"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Venture",
                definitions = listOf(
                    "A risky or daring journey or undertaking",
                    "To undertake a risky or daring course of action"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Vivid",
                definitions = listOf(
                    "Producing powerful feelings or strong, clear images",
                    "Bright, intense, or full of life"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Versatile",
                definitions = listOf(
                    "Able to adapt or be used for many different purposes",
                    "Having many skills or talents"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Viable",
                definitions = listOf(
                    "Capable of working successfully or being effective",
                    "Able to survive or sustain life"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Volatile",
                definitions = listOf(
                    "Liable to change rapidly and unpredictably",
                    "Easily evaporated at normal temperatures"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Vortex",
                definitions = listOf(
                    "A whirling mass of air or water",
                    "A situation or state resembling a whirlpool in intensity"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Vigilant",
                definitions = listOf(
                    "Keeping careful watch for possible danger or difficulties",
                    "Alert and attentive to prevent issues or harm"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Void",
                definitions = listOf(
                    "Completely empty or lacking substance",
                    "A space or gap, often representing nothingness"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Vitality",
                definitions = listOf(
                    "The state of being strong, active, or energetic",
                    "A sense of life or vibrancy"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Validar",
                definitions = listOf(
                    "Confirmar o verificar la autenticidad de algo",
                    "Reconocer o afirmar el valor o legitimidad de algo"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ventura",
                definitions = listOf(
                    "Un suceso afortunado o inesperado",
                    "Un riesgo o empresa audaz"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Vívido",
                definitions = listOf(
                    "Que produce imágenes claras o intensas en la mente",
                    "Brillante, intenso o lleno de vida"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Versátil",
                definitions = listOf(
                    "Capaz de adaptarse o usarse para diferentes propósitos",
                    "Que tiene habilidades o talentos variados"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Viable",
                definitions = listOf(
                    "Que puede funcionar o ser efectivo con éxito",
                    "Capaz de sobrevivir o sostenerse"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Volátil",
                definitions = listOf(
                    "Propenso a cambiar rápida e impredeciblemente",
                    "Que se evapora fácilmente a temperaturas normales"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Vórtice",
                definitions = listOf(
                    "Una masa giratoria de aire o agua",
                    "Una situación o estado que parece absorber todo con intensidad"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Vigilante",
                definitions = listOf(
                    "Que presta atención cuidadosa a posibles peligros",
                    "Alguien que está alerta y atento para evitar problemas"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Vacío",
                definitions = listOf(
                    "Un espacio completamente desocupado",
                    "La ausencia de algo sustancial o significativo"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Vitalidad",
                definitions = listOf(
                    "El estado de estar fuerte, activo o lleno de energía",
                    "Un sentido de vida o dinamismo"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Vernacular",
                definitions = listOf(
                    "The language or dialect spoken by ordinary people in a specific region",
                    "A specialized or everyday expression used by a particular group"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Vicissitude",
                definitions = listOf(
                    "A change of circumstances or fortune, typically one that is unwelcome",
                    "A variation or alteration occurring over time"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Vitriol",
                definitions = listOf(
                    "Cruel and bitter criticism",
                    "A highly caustic or severe expression of disapproval"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Vestigial",
                definitions = listOf(
                    "Relating to a part of something that has become functionless",
                    "A small remnant of something that was once larger"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Vanguard",
                definitions = listOf(
                    "The leading position in a movement or field",
                    "The foremost part of an advancing group or force"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Virulent",
                definitions = listOf(
                    "Extremely harmful or severe in its effects",
                    "Bitterly hostile or antagonistic in nature"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Vacillate",
                definitions = listOf(
                    "To waver between different opinions or actions",
                    "To be indecisive or hesitant"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Volition",
                definitions = listOf(
                    "The faculty or power of using one's will",
                    "A deliberate choice or decision made voluntarily"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Venality",
                definitions = listOf(
                    "The quality of being open to bribery or corruption",
                    "A tendency to act dishonestly for personal gain"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Veneration",
                definitions = listOf(
                    "Great respect or reverence for someone or something",
                    "A feeling of profound admiration or awe"
                ),
                letter = Letter.V,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Vernáculo",
                definitions = listOf(
                    "El idioma o dialecto hablado por las personas en una región específica",
                    "Un término cotidiano o especializado usado por un grupo particular"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Vicisitud",
                definitions = listOf(
                    "Un cambio de circunstancias, generalmente desfavorable",
                    "Una variación o alteración que ocurre con el tiempo"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Vitriolo",
                definitions = listOf(
                    "Una crítica cruel y amarga",
                    "Un comentario altamente cáustico o severo"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Vestigial",
                definitions = listOf(
                    "Relacionado con una parte que ha perdido su función original",
                    "Un remanente pequeño de algo que fue más grande"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Vanguardia",
                definitions = listOf(
                    "La posición de liderazgo en un movimiento o campo",
                    "La parte delantera de un grupo o fuerza en avance"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Virulento",
                definitions = listOf(
                    "Extremadamente dañino o severo en sus efectos",
                    "Hostil o antagonista de manera intensa"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Vacilar",
                definitions = listOf(
                    "Dudar entre diferentes opiniones o acciones",
                    "Mostrar indecisión o titubeo"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Volición",
                definitions = listOf(
                    "La capacidad o poder de usar la voluntad",
                    "Una decisión deliberada tomada de manera voluntaria"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Venalidad",
                definitions = listOf(
                    "La disposición a aceptar sobornos o corrupción",
                    "La tendencia a actuar deshonestamente por ganancia personal"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Veneración",
                definitions = listOf(
                    "Un gran respeto o reverencia hacia alguien o algo",
                    "Una profunda admiración o asombro"
                ),
                letter = Letter.V,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun w() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Water",
                definitions = listOf(
                    "A colorless liquid essential for all forms of life",
                    "A substance used for drinking, cooking, and cleaning"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Wind",
                definitions = listOf(
                    "Moving air caused by differences in atmospheric pressure",
                    "A natural flow of air that can vary in speed"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Window",
                definitions = listOf(
                    "An opening in a wall or vehicle to let in light or air",
                    "A pane of glass used to cover such an opening"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Wolf",
                definitions = listOf(
                    "A wild carnivorous mammal that lives in packs",
                    "An animal known for its howling and sharp senses"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Wall",
                definitions = listOf(
                    "A vertical structure that encloses or divides a space",
                    "A surface that supports a building or forms a room"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Walk",
                definitions = listOf(
                    "To move at a regular pace by lifting and setting down each foot",
                    "A short journey on foot for exercise or leisure"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Warm",
                definitions = listOf(
                    "Slightly hot, especially in a pleasant way",
                    "Having a temperature that is comfortable for living beings"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Wave",
                definitions = listOf(
                    "A raised movement of water on the surface of the sea",
                    "To move a hand or object to greet or signal someone"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Wheel",
                definitions = listOf(
                    "A circular object that turns around a central axle",
                    "A part of a vehicle used to allow it to move or steer"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Wood",
                definitions = listOf(
                    "The hard material that forms the trunks of trees",
                    "A material used for building and making furniture"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Wafle",
                definitions = listOf(
                    "Un postre hecho de masa cocida en una plancha con forma de rejilla",
                    "Un alimento que se sirve con jarabe, frutas o crema"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Whisky",
                definitions = listOf(
                    "Una bebida alcohólica destilada hecha de granos fermentados",
                    "Una bebida comúnmente añejada en barriles de madera"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Web",
                definitions = listOf(
                    "Una red de hilos creada por arañas para atrapar presas",
                    "Un sistema de páginas conectadas en Internet"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Wifi",
                definitions = listOf(
                    "Una tecnología que permite la conexión inalámbrica a Internet",
                    "Un sistema que conecta dispositivos sin cables"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Wafer",
                definitions = listOf(
                    "Un dulce delgado y crujiente hecho de harina",
                    "Un alimento ligero y plano, a menudo relleno con crema"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Walkie-talkie",
                definitions = listOf(
                    "Un dispositivo de comunicación inalámbrica portátil",
                    "Un aparato usado para hablar a corta distancia sin teléfono"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Windsurf",
                definitions = listOf(
                    "Un deporte acuático que combina el surf y la vela",
                    "Una actividad que utiliza una tabla con una vela"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Webinar",
                definitions = listOf(
                    "Una conferencia o presentación realizada en línea",
                    "Un evento educativo transmitido por Internet"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Wonder",
                definitions = listOf(
                    "Un sentimiento de asombro y admiración",
                    "Algo que causa sorpresa por su belleza o rareza"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Watt",
                definitions = listOf(
                    "Una unidad de medida de potencia eléctrica",
                    "Un término usado para medir el consumo de energía"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Wander",
                definitions = listOf(
                    "To move around without a fixed direction or purpose",
                    "To stray from a path or plan"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Warrant",
                definitions = listOf(
                    "A document issued by a legal authority allowing an action",
                    "Justification or authority for an action or belief"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Wilt",
                definitions = listOf(
                    "To lose freshness or strength, as a plant does without water",
                    "To weaken or become less energetic"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Whirl",
                definitions = listOf(
                    "To move rapidly in a circular motion",
                    "A state of constant or chaotic activity"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Witty",
                definitions = listOf(
                    "Showing cleverness and humor in expression",
                    "Quick in thinking and inventing amusing remarks"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Woe",
                definitions = listOf(
                    "Great sorrow or distress",
                    "A problem or difficulty that causes sadness"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Wreckage",
                definitions = listOf(
                    "The remains of something that has been badly damaged",
                    "Debris left after destruction or an accident"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Wrath",
                definitions = listOf(
                    "Intense anger, often with a desire for revenge",
                    "A strong emotional response to a perceived injustice"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Wholesome",
                definitions = listOf(
                    "Conducive to health or moral well-being",
                    "Suggestive of goodness, purity, or integrity"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Wane",
                definitions = listOf(
                    "To decrease gradually in size, strength, or intensity",
                    "To appear smaller as in the phases of the moon"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Whisky",
                definitions = listOf(
                    "Una bebida alcohólica destilada hecha de granos fermentados",
                    "Un licor envejecido en barriles de madera"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Webinar",
                definitions = listOf(
                    "Una presentación o conferencia en línea",
                    "Un evento educativo realizado a través de Internet"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Wafer",
                definitions = listOf(
                    "Un dulce fino y crujiente usado en postres",
                    "Un producto comestible ligero y plano"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Windsurf",
                definitions = listOf(
                    "Un deporte acuático que combina el surf y la vela",
                    "La práctica de navegar sobre una tabla con una vela"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Walkie-talkie",
                definitions = listOf(
                    "Un dispositivo portátil de comunicación inalámbrica",
                    "Un aparato usado para hablar en distancias cortas sin teléfono"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Wonder",
                definitions = listOf(
                    "Un sentimiento de asombro y admiración",
                    "Algo que causa sorpresa o admiración por su rareza o belleza"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Watt",
                definitions = listOf(
                    "Una unidad de medida de potencia eléctrica",
                    "Un término que mide la cantidad de energía consumida o generada"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Wireless",
                definitions = listOf(
                    "Tecnología que permite comunicación sin cables",
                    "Un sistema que conecta dispositivos de forma inalámbrica"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Widget",
                definitions = listOf(
                    "Un elemento gráfico en interfaces de usuario",
                    "Una herramienta o accesorio pequeño y funcional"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Wiki",
                definitions = listOf(
                    "Un sitio web colaborativo donde los usuarios pueden editar contenido",
                    "Una herramienta para crear y compartir información en línea"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Wainscoting",
                definitions = listOf(
                    "Wooden paneling that lines the lower part of the walls of a room",
                    "A decorative feature used to protect walls and enhance interiors"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Warp",
                definitions = listOf(
                    "To twist or distort the shape of something",
                    "The threads that run lengthwise in a woven fabric"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Wastrel",
                definitions = listOf(
                    "A person who wastes resources or opportunities",
                    "A lazy or good-for-nothing individual"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Welter",
                definitions = listOf(
                    "A confused mass or jumble of things",
                    "To move in a turbulent or disorderly way"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Winnow",
                definitions = listOf(
                    "To separate valuable parts from worthless ones",
                    "To blow air through grain to remove chaff"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Wistful",
                definitions = listOf(
                    "Having a feeling of vague or regretful longing",
                    "Full of melancholy and yearning"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Wrangle",
                definitions = listOf(
                    "To argue or dispute noisily and persistently",
                    "To herd or manage livestock, especially cattle"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Wont",
                definitions = listOf(
                    "A habitual way of doing something",
                    "Accustomed or used to a specific behavior"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Wheedle",
                definitions = listOf(
                    "To influence someone by flattery or smooth words",
                    "To persuade by using charm or deceit"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Whorl",
                definitions = listOf(
                    "A pattern of spirals or circles",
                    "A circular arrangement of leaves, petals, or other parts of a plant"
                ),
                letter = Letter.W,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Whisky",
                definitions = listOf(
                    "Una bebida alcohólica destilada hecha de granos fermentados",
                    "Un licor envejecido en barriles de madera para obtener sabor"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Walkie-talkie",
                definitions = listOf(
                    "Un dispositivo portátil de comunicación inalámbrica",
                    "Un aparato usado para comunicarse en distancias cortas"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Windsurf",
                definitions = listOf(
                    "Un deporte acuático que combina el surf y la vela",
                    "Una actividad que usa una tabla con una vela para moverse en el agua"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Webinar",
                definitions = listOf(
                    "Una presentación o conferencia en línea",
                    "Un evento educativo realizado a través de Internet"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Wafle",
                definitions = listOf(
                    "Un postre hecho de masa cocida en una plancha con forma de rejilla",
                    "Un alimento servido con jarabe, frutas o crema"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Widget",
                definitions = listOf(
                    "Un elemento gráfico en interfaces de usuario",
                    "Una herramienta pequeña y funcional usada en sistemas tecnológicos"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Wonder",
                definitions = listOf(
                    "Un sentimiento de asombro y admiración",
                    "Algo que causa sorpresa o admiración por su rareza o belleza"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Wireless",
                definitions = listOf(
                    "Un sistema que conecta dispositivos sin cables",
                    "Tecnología que permite comunicación inalámbrica"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Wiki",
                definitions = listOf(
                    "Un sitio web colaborativo donde los usuarios pueden editar contenido",
                    "Una herramienta para crear y compartir información en línea"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Watt",
                definitions = listOf(
                    "Una unidad de medida de potencia eléctrica",
                    "Un término que mide la cantidad de energía consumida o generada"
                ),
                letter = Letter.W,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )

        )

        private fun x() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "X-ray",
                definitions = listOf(
                    "A type of radiation used for medical imaging",
                    "An image produced by passing X-rays through an object or body"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xenon",
                definitions = listOf(
                    "A chemical element used in light bulbs and lasers",
                    "A noble gas with the atomic number 54"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xylophone",
                definitions = listOf(
                    "A musical instrument with wooden bars struck by mallets",
                    "An instrument that produces sound through resonating keys"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xerox",
                definitions = listOf(
                    "A brand name for photocopying machines",
                    "To make a copy of a document using a xerographic process"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "X-axis",
                definitions = listOf(
                    "The horizontal axis in a two-dimensional graph",
                    "A reference line used in graphs and charts"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xenophile",
                definitions = listOf(
                    "A person who is attracted to foreign cultures or customs",
                    "Someone who appreciates things from other countries"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xenophobia",
                definitions = listOf(
                    "A fear or dislike of people from other countries",
                    "A prejudice against or distrust of foreign things"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xylitol",
                definitions = listOf(
                    "A sugar substitute derived from plants",
                    "A sweetener commonly used in sugar-free products"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xenogenesis",
                definitions = listOf(
                    "The production of offspring that are unlike the parent",
                    "A phenomenon where something new or different arises"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xerophyte",
                definitions = listOf(
                    "A plant adapted to survive in dry environments",
                    "A type of vegetation that requires minimal water"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Xilófono",
                definitions = listOf(
                    "Un instrumento musical compuesto de barras de madera",
                    "Un objeto que produce sonido al ser golpeado con baquetas"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xenón",
                definitions = listOf(
                    "Un elemento químico usado en bombillas y láseres",
                    "Un gas noble con número atómico 54"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xerografía",
                definitions = listOf(
                    "Un proceso usado en fotocopiadoras para reproducir imágenes",
                    "Una técnica para imprimir documentos"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xenofobia",
                definitions = listOf(
                    "El temor o rechazo a personas extranjeras",
                    "Una actitud de prejuicio contra cosas o culturas foráneas"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xilografía",
                definitions = listOf(
                    "Un método de impresión en madera",
                    "Una técnica de grabado que usa bloques de madera"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xerófito",
                definitions = listOf(
                    "Una planta adaptada para sobrevivir en ambientes secos",
                    "Un tipo de vegetación que necesita poca agua"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xerox",
                definitions = listOf(
                    "Una marca de máquinas fotocopiadoras",
                    "El acto de hacer una copia usando tecnología xerográfica"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xantofila",
                definitions = listOf(
                    "Un pigmento amarillo presente en las plantas",
                    "Un compuesto que contribuye a los colores del otoño"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xenón",
                definitions = listOf(
                    "Un gas usado en lámparas y tecnologías de iluminación",
                    "Un elemento químico conocido por sus propiedades luminosas"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Xerocopia",
                definitions = listOf(
                    "Una copia hecha mediante un proceso xerográfico",
                    "Una reproducción de documentos en papel"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Xenophile",
                definitions = listOf(
                    "A person attracted to foreign cultures or customs",
                    "Someone who has a strong interest in things from other countries"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xenophobia",
                definitions = listOf(
                    "A fear or dislike of people from other countries",
                    "An aversion to foreign cultures or customs"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xerophyte",
                definitions = listOf(
                    "A plant adapted to dry environments",
                    "A type of vegetation requiring minimal water to survive"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xylophone",
                definitions = listOf(
                    "A percussion instrument made of wooden bars",
                    "An instrument that produces sound when struck with mallets"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xenogenesis",
                definitions = listOf(
                    "The production of offspring that is completely different from the parent",
                    "A phenomenon of creating something new and different"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xenolith",
                definitions = listOf(
                    "A piece of rock trapped inside another type of rock",
                    "A geological formation indicating different origins of materials"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xenotransplantation",
                definitions = listOf(
                    "The transplantation of organs or tissues between species",
                    "A medical procedure involving cross-species grafting"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xenotropic",
                definitions = listOf(
                    "Relating to organisms that infect hosts different from their usual species",
                    "Describing something that thrives in foreign environments"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xylography",
                definitions = listOf(
                    "The art of engraving on wood",
                    "A printing technique that uses wooden blocks"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xanthelasma",
                definitions = listOf(
                    "A condition where yellowish plaques form on the skin, usually near the eyes",
                    "A cosmetic concern caused by fatty deposits"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Xilografía",
                definitions = listOf(
                    "La técnica de grabar o imprimir con bloques de madera",
                    "El arte de crear imágenes o texto en madera para impresión"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xenofobia",
                definitions = listOf(
                    "El miedo o rechazo hacia personas extranjeras",
                    "Una actitud de aversión hacia culturas diferentes"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xerófito",
                definitions = listOf(
                    "Una planta adaptada para sobrevivir en climas secos",
                    "Vegetación que prospera con poca agua"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xenón",
                definitions = listOf(
                    "Un gas noble usado en lámparas y láseres",
                    "Un elemento químico conocido por su capacidad para iluminar"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xantofila",
                definitions = listOf(
                    "Un pigmento amarillo presente en las hojas y frutas",
                    "Un compuesto que contribuye al color amarillo de las plantas"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xantoma",
                definitions = listOf(
                    "Una acumulación de lípidos en la piel que forma bultos amarillos",
                    "Un signo de ciertas condiciones médicas relacionadas con el colesterol"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xerografía",
                definitions = listOf(
                    "Un proceso de impresión o copia que utiliza cargas eléctricas",
                    "Una técnica usada en fotocopiadoras y máquinas de impresión"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xantoma",
                definitions = listOf(
                    "Una acumulación de colesterol debajo de la piel",
                    "Un signo visible de trastornos metabólicos"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xenón",
                definitions = listOf(
                    "Un gas noble usado en tecnologías avanzadas",
                    "Un elemento químico conocido por su uso en lámparas de alta intensidad"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Xantofila",
                definitions = listOf(
                    "Un pigmento vegetal que da color amarillo",
                    "Una sustancia química natural encontrada en las hojas de las plantas"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Xerophthalmia",
                definitions = listOf(
                    "A medical condition characterized by dryness of the eyes",
                    "A symptom often caused by vitamin A deficiency"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xenotransplantation",
                definitions = listOf(
                    "The transplantation of organs or tissues between different species",
                    "A medical practice used to replace damaged organs using animal tissues"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xenolith",
                definitions = listOf(
                    "A fragment of rock embedded within another type of rock",
                    "A geological feature indicating different origins of materials"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xerophyte",
                definitions = listOf(
                    "A plant that has adapted to survive in dry environments",
                    "A vegetation type requiring minimal water for growth"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xylophage",
                definitions = listOf(
                    "An organism that feeds on wood, such as termites",
                    "A biological classification for wood-eating species"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xenogenesis",
                definitions = listOf(
                    "The creation of offspring that differ from the parents",
                    "A phenomenon where entirely new traits arise"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xiphoid",
                definitions = listOf(
                    "Relating to the small, cartilaginous lower end of the sternum",
                    "A structure resembling a sword in shape"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xenotropic",
                definitions = listOf(
                    "Referring to organisms that infect hosts other than their natural species",
                    "Adapted to thrive in foreign environments"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xeriscape",
                definitions = listOf(
                    "A landscaping method designed to reduce water usage",
                    "A gardening style suited for arid environments"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xenobiotic",
                definitions = listOf(
                    "A chemical substance foreign to a living organism",
                    "A compound introduced to an environment through artificial means"
                ),
                letter = Letter.X,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Xerofítico",
                definitions = listOf(
                    "Relativo a las plantas adaptadas a climas secos",
                    "Una característica de la vegetación resistente a la sequía"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xilófago",
                definitions = listOf(
                    "Un organismo que se alimenta de madera, como las termitas",
                    "Un término usado para describir especies consumidoras de celulosa"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xantopsia",
                definitions = listOf(
                    "Un trastorno visual donde todo parece tener un tono amarillento",
                    "Una condición a menudo asociada con intoxicaciones o enfermedades"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xeroftalmia",
                definitions = listOf(
                    "Una condición médica caracterizada por sequedad en los ojos",
                    "Un síntoma relacionado con la deficiencia de vitamina A"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xenofobia",
                definitions = listOf(
                    "Un temor o rechazo hacia lo extranjero",
                    "Una actitud de prejuicio contra personas o culturas diferentes"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xilografía",
                definitions = listOf(
                    "El arte de grabar o imprimir utilizando bloques de madera",
                    "Una técnica de impresión usada tradicionalmente en artes gráficas"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xantofila",
                definitions = listOf(
                    "Un pigmento amarillo presente en las plantas",
                    "Una sustancia química responsable de colores en hojas y frutas"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xerografía",
                definitions = listOf(
                    "Un proceso de copia o impresión mediante cargas eléctricas",
                    "Una tecnología utilizada en fotocopiadoras modernas"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xenón",
                definitions = listOf(
                    "Un gas noble utilizado en lámparas de alta intensidad",
                    "Un elemento químico con aplicaciones tecnológicas avanzadas"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Xerocopia",
                definitions = listOf(
                    "Una copia obtenida mediante un proceso xerográfico",
                    "Una reproducción de documentos en papel mediante tecnología avanzada"
                ),
                letter = Letter.X,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun y() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Yellow",
                definitions = listOf(
                    "A primary color found in the spectrum of light",
                    "The color of ripe lemons or the sun"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yes",
                definitions = listOf(
                    "An affirmative answer to a question",
                    "A word used to express agreement or approval"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yard",
                definitions = listOf(
                    "An outdoor area next to a house",
                    "A unit of measurement equal to 3 feet or 36 inches"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yak",
                definitions = listOf(
                    "A large, long-haired ox found in Asia",
                    "An animal used for its milk, meat, and as a pack animal"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yogurt",
                definitions = listOf(
                    "A creamy food made from fermented milk",
                    "A dairy product often flavored with fruits or sweeteners"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Year",
                definitions = listOf(
                    "A period of 12 months or 365 days",
                    "The time it takes for the Earth to orbit the Sun"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Youth",
                definitions = listOf(
                    "The period of life between childhood and adulthood",
                    "The quality of being young and energetic"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yolk",
                definitions = listOf(
                    "The yellow part of an egg",
                    "The nutrient-rich center of an egg that nourishes a developing embryo"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yawn",
                definitions = listOf(
                    "To open the mouth wide, often as a sign of tiredness",
                    "A reflex involving inhalation and stretching of the eardrums"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yarn",
                definitions = listOf(
                    "A long, continuous strand of fibers used for knitting or weaving",
                    "A tale or story, often long and exaggerated"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Yate",
                definitions = listOf(
                    "Un barco lujoso usado para viajes o recreación",
                    "Un medio de transporte marítimo privado"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yogur",
                definitions = listOf(
                    "Un alimento cremoso hecho de leche fermentada",
                    "Un producto lácteo frecuentemente combinado con frutas o endulzantes"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yeso",
                definitions = listOf(
                    "Un material blanco usado para cubrir paredes o fabricar moldes",
                    "Una sustancia utilizada para inmovilizar fracturas óseas"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yuca",
                definitions = listOf(
                    "Una raíz comestible usada en la cocina",
                    "Una planta cultivada en regiones tropicales"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yerba",
                definitions = listOf(
                    "Un término para hierba o planta utilizada en infusiones",
                    "Una planta usada en la preparación del mate o té"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yema",
                definitions = listOf(
                    "La parte amarilla de un huevo",
                    "Un brote de una planta o árbol en crecimiento"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yunta",
                definitions = listOf(
                    "Un par de animales, como bueyes, usados para trabajar la tierra",
                    "Una pareja o unión de dos elementos"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yogui",
                definitions = listOf(
                    "Una persona que practica yoga regularmente",
                    "Un maestro espiritual en la tradición hindú"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yerno",
                definitions = listOf(
                    "El esposo de la hija de una persona",
                    "Un miembro de la familia por matrimonio"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Yacimiento",
                definitions = listOf(
                    "Un lugar donde se encuentran minerales o fósiles",
                    "Un área rica en recursos naturales bajo tierra"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Yearn",
                definitions = listOf(
                    "To have an intense longing or desire for something",
                    "To feel a deep, often melancholic craving"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yield",
                definitions = listOf(
                    "To produce or provide a natural or industrial result",
                    "To give way to arguments, demands, or pressure"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yen",
                definitions = listOf(
                    "The basic monetary unit of Japan",
                    "A strong desire or craving for something"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yonder",
                definitions = listOf(
                    "At or in that place, usually far away but within sight",
                    "A distant location or point"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yaw",
                definitions = listOf(
                    "A side-to-side movement, especially of a ship or aircraft",
                    "To deviate temporarily from a straight course"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yew",
                definitions = listOf(
                    "An evergreen tree with dark green leaves and red berries",
                    "A type of tree often associated with graveyards and long life"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yeast",
                definitions = listOf(
                    "A type of fungus used in baking and brewing",
                    "A microorganism that helps ferment sugars into alcohol or carbon dioxide"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Youthful",
                definitions = listOf(
                    "Full of energy and vitality, resembling youth",
                    "Characteristic of someone or something young in appearance or spirit"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yacht",
                definitions = listOf(
                    "A medium-sized boat used for recreation or racing",
                    "A luxurious vessel often associated with leisure activities"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yardstick",
                definitions = listOf(
                    "A standard used for comparison or evaluation",
                    "A physical tool used for measuring length, typically a yard long"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Yugo",
                definitions = listOf(
                    "Un armazón usado para unir animales de tiro",
                    "Un símbolo de carga o trabajo pesado"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yerbero",
                definitions = listOf(
                    "Una persona que vende hierbas medicinales o aromáticas",
                    "Un experto en plantas usadas para tratamientos naturales"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yerto",
                definitions = listOf(
                    "Que está rígido o inmóvil, especialmente por el frío o la muerte",
                    "Algo que carece de flexibilidad o movimiento"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yacimiento",
                definitions = listOf(
                    "Un lugar donde se encuentran minerales o restos fósiles",
                    "Una acumulación natural de recursos debajo del suelo"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yate",
                definitions = listOf(
                    "Un barco utilizado para recreación o lujo",
                    "Un medio de transporte marítimo privado asociado al ocio"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yerba",
                definitions = listOf(
                    "Plantas usadas en infusiones o remedios medicinales",
                    "Un término que se refiere a hierba mate o té"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yunta",
                definitions = listOf(
                    "Un par de animales usados para labrar la tierra",
                    "Una asociación o unión de dos elementos para trabajar juntos"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yogurtería",
                definitions = listOf(
                    "Un establecimiento donde se vende yogur y productos relacionados",
                    "Una tienda especializada en postres a base de yogur"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yogui",
                definitions = listOf(
                    "Un practicante avanzado de yoga",
                    "Un maestro espiritual en tradiciones orientales"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Yedra",
                definitions = listOf(
                    "Una planta trepadora con hojas verdes brillantes",
                    "Una especie vegetal que crece adhiriéndose a muros y árboles"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
///////////// EN /////////////
            WordEntity(
                word = "Yesteryear",
                definitions = listOf(
                    "A time in the past, especially a nostalgic one",
                    "The previous era or bygone times"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yielding",
                definitions = listOf(
                    "Tending to give way under pressure; flexible",
                    "Inclined to agree or comply without resistance"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yen",
                definitions = listOf(
                    "A strong desire or craving for something",
                    "The basic monetary unit of Japan"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yoke",
                definitions = listOf(
                    "A wooden frame for joining a pair of animals together for work",
                    "A symbol of oppression or subjugation"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yawning",
                definitions = listOf(
                    "Extremely wide or open, often in a metaphorical sense",
                    "Relating to the involuntary act of opening one's mouth due to fatigue or boredom"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yew",
                definitions = listOf(
                    "An evergreen tree with dark green leaves and red berries",
                    "A tree historically associated with longevity and graveyards"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yarn",
                definitions = listOf(
                    "A continuous strand of twisted threads for knitting or weaving",
                    "A long, often elaborate story, sometimes exaggerated"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yellowcake",
                definitions = listOf(
                    "A concentrated form of uranium ore used in nuclear fuel production",
                    "A processed substance containing uranium oxide"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Ytterbium",
                definitions = listOf(
                    "A soft, silvery metallic element used in scientific instruments",
                    "A chemical element with the atomic number 70"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yohimbine",
                definitions = listOf(
                    "A chemical compound derived from the bark of a tree, used as medicine",
                    "A substance often used as an aphrodisiac or to treat erectile dysfunction"
                ),
                letter = Letter.Y,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
///////////// ES /////////////
            WordEntity(
                word = "Yermo",
                definitions = listOf(
                    "Un terreno desierto o sin cultivo",
                    "Un lugar inhóspito y carente de vida"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yunque",
                definitions = listOf(
                    "Un bloque de metal usado por herreros para golpear el hierro",
                    "Un símbolo de fuerza o resistencia"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yugo",
                definitions = listOf(
                    "Una pieza de madera usada para unir animales de carga",
                    "Un símbolo de opresión o trabajo forzado"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yerto",
                definitions = listOf(
                    "Rígido o inmóvil, especialmente por el frío o la muerte",
                    "Algo que carece de flexibilidad o movimiento"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yerra",
                definitions = listOf(
                    "Un error o equivocación",
                    "Una falta cometida por descuido o ignorancia"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yerbero",
                definitions = listOf(
                    "Una persona que vende hierbas medicinales",
                    "Un experto en el uso de plantas para remedios naturales"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yacimiento",
                definitions = listOf(
                    "Un lugar donde se encuentran minerales o fósiles",
                    "Una acumulación de recursos naturales en la tierra"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yugular",
                definitions = listOf(
                    "Una vena en el cuello que transporta sangre al corazón",
                    "Relacionado con algo vital o crucial para la vida"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yedra",
                definitions = listOf(
                    "Una planta trepadora con hojas verdes brillantes",
                    "Una especie que crece adhiriéndose a muros y árboles"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            WordEntity(
                word = "Yogurtería",
                definitions = listOf(
                    "Un establecimiento que vende yogures y postres relacionados",
                    "Una tienda especializada en productos lácteos fermentados"
                ),
                letter = Letter.Y,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun z() = listOf(
            // EASY
///////////// EN /////////////
            WordEntity(
                word = "Zebra",
                definitions = listOf(
                    "A wild animal with black and white stripes",
                    "A herbivorous mammal native to Africa"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zoo",
                definitions = listOf(
                    "A place where animals are kept for public display",
                    "An establishment for the care and conservation of wildlife"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zero",
                definitions = listOf(
                    "The number representing no quantity or value",
                    "A point on a scale, such as in temperature or speed"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zip",
                definitions = listOf(
                    "To close or fasten with a zipper",
                    "To move quickly or at high speed"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zone",
                definitions = listOf(
                    "An area or region with a specific characteristic",
                    "A designated area for a particular purpose"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zap",
                definitions = listOf(
                    "To destroy or kill suddenly with energy",
                    "To move quickly or with energy"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zest",
                definitions = listOf(
                    "The outer skin of citrus fruits, used as flavoring",
                    "Great enthusiasm and energy"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zipper",
                definitions = listOf(
                    "A fastening device consisting of two strips with interlocking teeth",
                    "A mechanism used in clothing, bags, or other items to join two edges"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zenith",
                definitions = listOf(
                    "The highest point or peak of something",
                    "A time when something is most powerful or successful"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zigzag",
                definitions = listOf(
                    "A pattern made of lines that move back and forth in sharp angles",
                    "To move or progress in sharp turns or angles"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
///////////// ES /////////////
            WordEntity(
                word = "Zorro",
                definitions = listOf(
                    "Un animal mamífero conocido por su astucia",
                    "Un cánido de tamaño mediano con un pelaje rojizo o gris"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zapato",
                definitions = listOf(
                    "Un calzado usado para proteger los pies",
                    "Un accesorio que cubre el pie y se usa para caminar"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zanahoria",
                definitions = listOf(
                    "Una verdura anaranjada alargada y crujiente",
                    "Un vegetal que se cultiva por su raíz comestible"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zona",
                definitions = listOf(
                    "Una área definida por características específicas",
                    "Una región designada para un propósito particular"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zoológico",
                definitions = listOf(
                    "Un lugar donde se exhiben animales para el público",
                    "Un espacio destinado al cuidado y conservación de especies"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zapote",
                definitions = listOf(
                    "Una fruta tropical con una pulpa dulce",
                    "Un alimento originario de América Central y México"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zumbido",
                definitions = listOf(
                    "Un sonido continuo y vibrante",
                    "El ruido producido por insectos como las abejas o avispas"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zanja",
                definitions = listOf(
                    "Un canal excavado en la tierra, generalmente para agua",
                    "Un surco alargado usado para drenaje o riego"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zafiro",
                definitions = listOf(
                    "Una piedra preciosa de color azul intenso",
                    "Una gema valorada por su dureza y belleza"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            WordEntity(
                word = "Zafra",
                definitions = listOf(
                    "El periodo de cosecha de caña de azúcar",
                    "Una actividad agrícola relacionada con la producción de azúcar"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
///////////// EN /////////////
            WordEntity(
                word = "Zenith",
                definitions = listOf(
                    "The point in the sky directly above an observer",
                    "The highest point of success or power"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zebrawood",
                definitions = listOf(
                    "A type of wood with striped grain resembling a zebra",
                    "A hardwood often used for decorative furniture or veneers"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zeal",
                definitions = listOf(
                    "Great energy or enthusiasm in pursuit of a cause",
                    "A strong eagerness or fervor for something"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zodiac",
                definitions = listOf(
                    "A belt-shaped region of the sky divided into 12 signs",
                    "An astrological system used to predict personality and destiny"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zircon",
                definitions = listOf(
                    "A mineral used as a gemstone and industrial material",
                    "A crystal often mistaken for diamond"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zestful",
                definitions = listOf(
                    "Full of enthusiasm and energy",
                    "Having a lively and spirited quality"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zoomorphic",
                definitions = listOf(
                    "Having the form of an animal",
                    "Relating to the use of animal forms in art or symbolism"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zeitgeist",
                definitions = listOf(
                    "The spirit or mood of a particular period in history",
                    "The defining ideas or beliefs of a specific era"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Ziggurat",
                definitions = listOf(
                    "A rectangular stepped tower from ancient Mesopotamia",
                    "A terraced temple used for religious practices"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zymology",
                definitions = listOf(
                    "The science of fermentation and its practical uses",
                    "The study of processes used in brewing and baking"
                ),
                letter = Letter.Z,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
///////////// ES /////////////
            WordEntity(
                word = "Zenit",
                definitions = listOf(
                    "El punto más alto en el cielo sobre un observador",
                    "El momento de mayor éxito o poder en algo"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zafiro",
                definitions = listOf(
                    "Una gema preciosa de color azul intenso",
                    "Una piedra valorada por su belleza y resistencia"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zaranda",
                definitions = listOf(
                    "Un utensilio usado para separar partículas por tamaño",
                    "Una herramienta utilizada en la agricultura y minería"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zafarrancho",
                definitions = listOf(
                    "Una acción desordenada o caótica",
                    "Una actividad de limpieza profunda o preparación para algo"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zócalo",
                definitions = listOf(
                    "La parte inferior de una pared que la protege",
                    "La base o soporte de un objeto o estructura"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zarzal",
                definitions = listOf(
                    "Un arbusto espinoso, como la zarza",
                    "Un lugar cubierto de estas plantas"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zumba",
                definitions = listOf(
                    "Una forma de ejercicio aeróbico al ritmo de música latina",
                    "Una actividad física que combina baile y acondicionamiento físico"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zarpar",
                definitions = listOf(
                    "Iniciar un viaje marítimo desde un puerto",
                    "Levantar anclas y partir en un barco"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zángano",
                definitions = listOf(
                    "Un macho de las abejas que no recolecta miel",
                    "Una persona perezosa o sin iniciativa"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            WordEntity(
                word = "Zancada",
                definitions = listOf(
                    "Un paso largo al caminar o correr",
                    "Un avance significativo en una tarea o proyecto"
                ),
                letter = Letter.Z,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
        )
    }
}