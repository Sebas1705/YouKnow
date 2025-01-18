package es.sebas1705.youknow.data.local.database.entities
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.sebas1705.youknow.core.classes.enums.Category
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Languages
import es.sebas1705.youknow.core.classes.enums.QuizType
import es.sebas1705.youknow.data.local.database.config.SettingsDB
import es.sebas1705.youknow.domain.model.games.QuestionModel

/**
 * Data class to represent the user data and use as entity in the database
 *
 * @property question [String]: Question
 * @property answers [List]<[String]>: List of answers
 * @property correctAnswer [String]: Correct answer
 * @property category [Int]: Category of the question
 * @property language [Int]: Language of the question
 * @property difficulty [Int]: Difficulty of the question
 * @property quizType [Int]: Type of the question
 *
 */
@Entity(tableName = SettingsDB.QUESTION_TABLE)
data class QuestionEntity(
    @PrimaryKey val question: String,
    val answers: List<String>,
    val correctAnswer: String,
    val category: Category,
    val language: Languages,
    val difficulty: Difficulty,
    val quizType: QuizType
) {

    fun toQuestionModel() = QuestionModel(
        question,
        answers,
        correctAnswer,
        category,
        language,
        difficulty,
        quizType
    )

    companion object {
        fun questionDatabase(): List<QuestionEntity> {
            val list = mutableListOf<QuestionEntity>()
            list.addAll(generalKnowledge())
            list.addAll(books())
            list.addAll(films())
            list.addAll(music())
            list.addAll(musicalsAndTheatres())
            list.addAll(television())
            list.addAll(videoGames())
            list.addAll(boardGames())
            list.addAll(scienceAndNature())
            list.addAll(computers())
            list.addAll(mathematics())
            list.addAll(mythology())
            list.addAll(sports())
            list.addAll(geography())
            list.addAll(history())
            list.addAll(politics())
            list.addAll(art())
            list.addAll(celebrities())
            list.addAll(animals())
            list.addAll(vehicles())
            list.addAll(comics())
            list.addAll(gadgets())
            list.addAll(animeAndManga())
            list.addAll(cartoonAndAnimations())
            return list.toList()
        }

        private fun generalKnowledge() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the Earth round?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the capital of France?",
                answers = listOf("Paris", "London", "Berlin", "Rome"),
                correctAnswer = "Paris",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is water composed of hydrogen and oxygen?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which planet is known as the Red Planet?",
                answers = listOf("Mars", "Earth", "Jupiter", "Venus"),
                correctAnswer = "Mars",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does the sun rise in the east?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who wrote 'Hamlet'?",
                answers = listOf(
                    "William Shakespeare",
                    "Charles Dickens",
                    "Jane Austen",
                    "Mark Twain"
                ),
                correctAnswer = "William Shakespeare",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Mount Everest the tallest mountain in the world?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the chemical symbol for water?",
                answers = listOf("H2O", "CO2", "NaCl", "O2"),
                correctAnswer = "H2O",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Antarctica a desert?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which gas do plants use for photosynthesis?",
                answers = listOf("Carbon Dioxide", "Oxygen", "Nitrogen", "Hydrogen"),
                correctAnswer = "Carbon Dioxide",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es el agua un compuesto de hidrógeno y oxígeno?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la capital de España?",
                answers = listOf("Madrid", "Barcelona", "Sevilla", "Bilbao"),
                correctAnswer = "Madrid",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿La Tierra es redonda?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el planeta conocido como el planeta rojo?",
                answers = listOf("Marte", "Júpiter", "Saturno", "Venus"),
                correctAnswer = "Marte",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿El Sol sale por el este?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién escribió 'Don Quijote de la Mancha'?",
                answers = listOf(
                    "Miguel de Cervantes",
                    "Lope de Vega",
                    "Federico García Lorca",
                    "Benito Pérez Galdós"
                ),
                correctAnswer = "Miguel de Cervantes",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿El Everest es la montaña más alta del mundo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el símbolo químico del agua?",
                answers = listOf("H2O", "CO2", "NaCl", "O2"),
                correctAnswer = "H2O",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la Antártida un desierto?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué gas usan las plantas para la fotosíntesis?",
                answers = listOf("Dióxido de Carbono", "Oxígeno", "Nitrógeno", "Hidrógeno"),
                correctAnswer = "Dióxido de Carbono",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE,
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the speed of light faster than the speed of sound?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the largest organ in the human body?",
                answers = listOf("Skin", "Liver", "Heart", "Lungs"),
                correctAnswer = "Skin",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does the moon produce its own light?",
                answers = listOf("True", "False"),
                correctAnswer = "False",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the capital of Canada?",
                answers = listOf("Ottawa", "Toronto", "Montreal", "Vancouver"),
                correctAnswer = "Ottawa",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the chemical symbol for gold 'Au'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which ocean is the largest?",
                answers = listOf("Pacific", "Atlantic", "Indian", "Arctic"),
                correctAnswer = "Pacific",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Can humans breathe in space without a spacesuit?",
                answers = listOf("True", "False"),
                correctAnswer = "False",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the most spoken language in the world?",
                answers = listOf("Mandarin", "English", "Spanish", "Hindi"),
                correctAnswer = "Mandarin",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Great Wall of China visible from space?",
                answers = listOf("True", "False"),
                correctAnswer = "False",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which planet has the most moons?",
                answers = listOf("Saturn", "Jupiter", "Uranus", "Neptune"),
                correctAnswer = "Saturn",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es el agua salada más densa que el agua dulce?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el país con más población en el mundo?",
                answers = listOf("China", "India", "Estados Unidos", "Indonesia"),
                correctAnswer = "China",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿El Sol es una estrella?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el río más largo del mundo?",
                answers = listOf("Amazonas", "Nilo", "Yangtsé", "Misisipi"),
                correctAnswer = "Amazonas",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿La capa de ozono nos protege de la radiación ultravioleta?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la capital de Argentina?",
                answers = listOf("Buenos Aires", "Montevideo", "Lima", "Bogotá"),
                correctAnswer = "Buenos Aires",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿El cuerpo humano tiene más de 200 huesos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué planeta tiene un día más largo que su año?",
                answers = listOf("Venus", "Marte", "Mercurio", "Júpiter"),
                correctAnswer = "Venus",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿El diamante es el material natural más duro conocido?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué animal es conocido como el rey de la selva?",
                answers = listOf("León", "Tigre", "Elefante", "Gorila"),
                correctAnswer = "León",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Does the universe continue to expand?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the smallest prime number?",
                answers = listOf("2", "1", "3", "5"),
                correctAnswer = "2",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the speed of light constant in a vacuum?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which element has the highest melting point?",
                answers = listOf("Tungsten", "Iron", "Gold", "Platinum"),
                correctAnswer = "Tungsten",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is quantum entanglement a proven scientific phenomenon?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the approximate age of the Earth?",
                answers = listOf(
                    "4.5 billion years",
                    "3.5 billion years",
                    "2.5 billion years",
                    "5.5 billion years"
                ),
                correctAnswer = "4.5 billion years",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does dark matter make up more mass in the universe than visible matter?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which mathematician is known for the Fibonacci sequence?",
                answers = listOf("Leonardo of Pisa", "Isaac Newton", "Euclid", "Carl Gauss"),
                correctAnswer = "Leonardo of Pisa",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is zero considered an even number?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the most abundant element in the Earth's crust?",
                answers = listOf("Oxygen", "Silicon", "Aluminum", "Iron"),
                correctAnswer = "Oxygen",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿El universo continúa expandiéndose?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el número primo más pequeño?",
                answers = listOf("2", "1", "3", "5"),
                correctAnswer = "2",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿La velocidad de la luz es constante en el vacío?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué elemento tiene el punto de fusión más alto?",
                answers = listOf("Tungsteno", "Hierro", "Oro", "Platino"),
                correctAnswer = "Tungsteno",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿El entrelazamiento cuántico es un fenómeno científicamente probado?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la edad aproximada de la Tierra?",
                answers = listOf(
                    "4.5 mil millones de años",
                    "3.5 mil millones de años",
                    "2.5 mil millones de años",
                    "5.5 mil millones de años"
                ),
                correctAnswer = "4.5 mil millones de años",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿La materia oscura tiene más masa en el universo que la materia visible?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué matemático es conocido por la secuencia de Fibonacci?",
                answers = listOf("Leonardo de Pisa", "Isaac Newton", "Euclides", "Carl Gauss"),
                correctAnswer = "Leonardo de Pisa",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿El cero es considerado un número par?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el elemento más abundante en la corteza terrestre?",
                answers = listOf("Oxígeno", "Silicio", "Aluminio", "Hierro"),
                correctAnswer = "Oxígeno",
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),

            )

        private fun books() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Moby Dick' a novel written by Herman Melville?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who wrote 'Pride and Prejudice'?",
                answers = listOf("Jane Austen", "Emily Brontë", "Charlotte Brontë", "Mary Shelley"),
                correctAnswer = "Jane Austen",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'To Kill a Mockingbird' set in the United States?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the title of the first book in the 'Harry Potter' series?",
                answers = listOf(
                    "The Philosopher's Stone",
                    "The Chamber of Secrets",
                    "The Goblet of Fire",
                    "The Prisoner of Azkaban"
                ),
                correctAnswer = "The Philosopher's Stone",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Great Gatsby' set in the 1920s?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who wrote '1984'?",
                answers = listOf("George Orwell", "Aldous Huxley", "Ray Bradbury", "J.D. Salinger"),
                correctAnswer = "George Orwell",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Catcher in the Rye' a novel by J.D. Salinger?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who is the author of 'The Hobbit'?",
                answers = listOf("J.R.R. Tolkien", "C.S. Lewis", "J.K. Rowling", "Philip Pullman"),
                correctAnswer = "J.R.R. Tolkien",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Frankenstein' considered one of the first science fiction novels?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the name of the main character in 'The Hunger Games'?",
                answers = listOf("Katniss Everdeen", "Tris Prior", "Bella Swan", "Arya Stark"),
                correctAnswer = "Katniss Everdeen",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Fue 'Don Quijote de la Mancha' escrito por Miguel de Cervantes?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién escribió 'Cien Años de Soledad'?",
                answers = listOf(
                    "Gabriel García Márquez",
                    "Mario Vargas Llosa",
                    "Jorge Luis Borges",
                    "Isabel Allende"
                ),
                correctAnswer = "Gabriel García Márquez",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'El Principito' un libro de Antoine de Saint-Exupéry?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el nombre del protagonista de 'Crimen y Castigo'?",
                answers = listOf("Raskolnikov", "Dostoyevski", "Ivan Karamazov", "Pierre Bezukhov"),
                correctAnswer = "Raskolnikov",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Rayuela' una novela de Julio Cortázar?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién escribió 'El Amor en los Tiempos del Cólera'?",
                answers = listOf(
                    "Gabriel García Márquez",
                    "Pablo Neruda",
                    "Carlos Fuentes",
                    "Mario Benedetti"
                ),
                correctAnswer = "Gabriel García Márquez",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'La Casa de los Espíritus' una obra de Isabel Allende?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la obra más conocida de Jorge Luis Borges?",
                answers = listOf(
                    "Ficciones",
                    "Cien Años de Soledad",
                    "Don Segundo Sombra",
                    "El Aleph"
                ),
                correctAnswer = "Ficciones",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'El Aleph' una colección de cuentos de Jorge Luis Borges?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el título del libro más famoso de Federico García Lorca?",
                answers = listOf(
                    "Bodas de Sangre",
                    "Yerma",
                    "La Casa de Bernarda Alba",
                    "Romancero Gitano"
                ),
                correctAnswer = "Bodas de Sangre",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'The Catcher in the Rye' banned in some schools?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these novels was written by Ernest Hemingway?",
                answers = listOf(
                    "The Old Man and the Sea",
                    "Of Mice and Men",
                    "East of Eden",
                    "The Great Gatsby"
                ),
                correctAnswer = "The Old Man and the Sea",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Ulysses' by James Joyce considered a modernist novel?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who wrote 'The Picture of Dorian Gray'?",
                answers = listOf("Oscar Wilde", "George Orwell", "Mark Twain", "H.G. Wells"),
                correctAnswer = "Oscar Wilde",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Brothers Karamazov' set in Russia?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which author wrote 'Brave New World'?",
                answers = listOf(
                    "Aldous Huxley",
                    "Ray Bradbury",
                    "George Orwell",
                    "Philip K. Dick"
                ),
                correctAnswer = "Aldous Huxley",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does 'The Road' by Cormac McCarthy depict a post-apocalyptic world?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which book is part of 'The Lord of the Rings' trilogy?",
                answers = listOf(
                    "The Two Towers",
                    "The Hobbit",
                    "The Silmarillion",
                    "The Lion, the Witch and the Wardrobe"
                ),
                correctAnswer = "The Two Towers",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was 'Fahrenheit 451' written to critique censorship?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which novel features the character Jay Gatsby?",
                answers = listOf(
                    "The Great Gatsby",
                    "Tender Is the Night",
                    "This Side of Paradise",
                    "The Beautiful and Damned"
                ),
                correctAnswer = "The Great Gatsby",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es 'Don Quijote de la Mancha' considerado la primera novela moderna?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué novela fue escrita por Mario Vargas Llosa?",
                answers = listOf("La Ciudad y los Perros", "Rayuela", "El Aleph", "Ficciones"),
                correctAnswer = "La Ciudad y los Perros",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'Cien Años de Soledad' traducido a más de 40 idiomas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién es el autor de 'El Tunel'?",
                answers = listOf(
                    "Ernesto Sabato",
                    "Julio Cortázar",
                    "Gabriel García Márquez",
                    "Mario Benedetti"
                ),
                correctAnswer = "Ernesto Sabato",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'La Casa de los Espíritus' la primera novela de Isabel Allende?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué autor escribió 'El Llano en Llamas'?",
                answers = listOf(
                    "Juan Rulfo",
                    "Carlos Fuentes",
                    "Octavio Paz",
                    "Gabriel García Márquez"
                ),
                correctAnswer = "Juan Rulfo",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Rayuela' una novela estructurada de manera no lineal?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué obra de Federico García Lorca es una tragedia rural?",
                answers = listOf(
                    "Bodas de Sangre",
                    "La Casa de Bernarda Alba",
                    "Yerma",
                    "Romancero Gitano"
                ),
                correctAnswer = "Bodas de Sangre",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'Pedro Páramo' publicado en 1955?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué escritor es autor de 'Aura'?",
                answers = listOf(
                    "Carlos Fuentes",
                    "Octavio Paz",
                    "Mario Vargas Llosa",
                    "Gabriel García Márquez"
                ),
                correctAnswer = "Carlos Fuentes",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Finnegans Wake' considered one of the most complex novels ever written?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who wrote 'The Sound and the Fury'?",
                answers = listOf(
                    "William Faulkner",
                    "Ernest Hemingway",
                    "F. Scott Fitzgerald",
                    "John Steinbeck"
                ),
                correctAnswer = "William Faulkner",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'War and Peace' set during the Napoleonic Wars?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which novel features the characters Ishmael and Ahab?",
                answers = listOf(
                    "Moby Dick",
                    "Treasure Island",
                    "Heart of Darkness",
                    "The Old Man and the Sea"
                ),
                correctAnswer = "Moby Dick",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does 'In Search of Lost Time' span over 3,000 pages?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who is the author of 'The Trial'?",
                answers = listOf("Franz Kafka", "Albert Camus", "Jean-Paul Sartre", "Herman Hesse"),
                correctAnswer = "Franz Kafka",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Ulysses' set in Dublin, Ireland?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which literary work is written by Marcel Proust?",
                answers = listOf(
                    "In Search of Lost Time",
                    "Crime and Punishment",
                    "Anna Karenina",
                    "The Brothers Karamazov"
                ),
                correctAnswer = "In Search of Lost Time",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Crime and Punishment' a psychological novel?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which book is a dystopian novel by Margaret Atwood?",
                answers = listOf(
                    "The Handmaid's Tale",
                    "Brave New World",
                    "1984",
                    "Fahrenheit 451"
                ),
                correctAnswer = "The Handmaid's Tale",
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es 'Cien Años de Soledad' una obra de realismo mágico?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién es el autor de 'Pedro Páramo'?",
                answers = listOf(
                    "Juan Rulfo",
                    "Gabriel García Márquez",
                    "Carlos Fuentes",
                    "Mario Vargas Llosa"
                ),
                correctAnswer = "Juan Rulfo",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'El Aleph' escrito por Jorge Luis Borges?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la novela más famosa de Miguel de Cervantes?",
                answers = listOf(
                    "Don Quijote de la Mancha",
                    "La Galatea",
                    "El Coloquio de los Perros",
                    "El Ingenioso Hidalgo"
                ),
                correctAnswer = "Don Quijote de la Mancha",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Rayuela' una obra que permite leerla en orden no lineal?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién es el autor de 'La Regenta'?",
                answers = listOf(
                    "Leopoldo Alas 'Clarín'",
                    "Benito Pérez Galdós",
                    "Emilia Pardo Bazán",
                    "Miguel de Unamuno"
                ),
                correctAnswer = "Leopoldo Alas 'Clarín'",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'El Coronel no Tiene Quien le Escriba' una novela corta de Gabriel García Márquez?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué novela narra las aventuras de Martín Fierro?",
                answers = listOf("El Gaucho Martín Fierro", "Facundo", "El Matadero", "La Cautiva"),
                correctAnswer = "El Gaucho Martín Fierro",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Ficciones' una colección de cuentos de Borges?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué escritor escribió 'Sobre Héroes y Tumbas'?",
                answers = listOf(
                    "Ernesto Sabato",
                    "Jorge Luis Borges",
                    "Julio Cortázar",
                    "Mario Vargas Llosa"
                ),
                correctAnswer = "Ernesto Sabato",
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun films() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Was 'Titanic' directed by James Cameron?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which movie features the character Jack Sparrow?",
                answers = listOf("Pirates of the Caribbean", "The Hobbit", "The Matrix", "Avatar"),
                correctAnswer = "Pirates of the Caribbean",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Godfather' considered one of the greatest movies of all time?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which actor played the character of Harry Potter?",
                answers = listOf("Daniel Radcliffe", "Elijah Wood", "Rupert Grint", "Tom Felton"),
                correctAnswer = "Daniel Radcliffe",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Inception' a film directed by Christopher Nolan?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which movie features the character Tony Stark?",
                answers = listOf("Iron Man", "Superman", "Batman Begins", "Wonder Woman"),
                correctAnswer = "Iron Man",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Frozen' an animated Disney movie?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which film features the character Simba?",
                answers = listOf("The Lion King", "Zootopia", "Finding Nemo", "The Jungle Book"),
                correctAnswer = "The Lion King",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Jurassic Park' about dinosaurs?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which movie is known for the line 'May the Force be with you'?",
                answers = listOf("Star Wars", "Star Trek", "Guardians of the Galaxy", "Avatar"),
                correctAnswer = "Star Wars",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Fue 'Titanic' dirigida por James Cameron?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué película presenta al personaje Jack Sparrow?",
                answers = listOf("Piratas del Caribe", "El Hobbit", "Matrix", "Avatar"),
                correctAnswer = "Piratas del Caribe",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'El Padrino' considerada una de las mejores películas de la historia?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué actor interpretó al personaje de Harry Potter?",
                answers = listOf("Daniel Radcliffe", "Elijah Wood", "Rupert Grint", "Tom Felton"),
                correctAnswer = "Daniel Radcliffe",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'El Origen' una película dirigida por Christopher Nolan?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué película presenta al personaje Tony Stark?",
                answers = listOf("Iron Man", "Superman", "Batman Inicia", "Mujer Maravilla"),
                correctAnswer = "Iron Man",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Frozen' una película animada de Disney?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué película presenta al personaje Simba?",
                answers = listOf(
                    "El Rey León",
                    "Zootopia",
                    "Buscando a Nemo",
                    "El Libro de la Selva"
                ),
                correctAnswer = "El Rey León",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Jurassic Park' una película sobre dinosaurios?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué película es conocida por la frase 'Que la Fuerza te acompañe'?",
                answers = listOf("Star Wars", "Star Trek", "Guardianes de la Galaxia", "Avatar"),
                correctAnswer = "Star Wars",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Pulp Fiction' directed by Quentin Tarantino?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who played the role of the Joker in 'The Dark Knight'?",
                answers = listOf("Heath Ledger", "Joaquin Phoenix", "Jack Nicholson", "Jared Leto"),
                correctAnswer = "Heath Ledger",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was 'The Matrix' released in 1999?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which movie features the line 'You can't handle the truth'?",
                answers = listOf("A Few Good Men", "The Godfather", "Goodfellas", "Casablanca"),
                correctAnswer = "A Few Good Men",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Interstellar' a science fiction movie about space exploration?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who directed the movie 'Inglourious Basterds'?",
                answers = listOf(
                    "Quentin Tarantino",
                    "Martin Scorsese",
                    "Steven Spielberg",
                    "Christopher Nolan"
                ),
                correctAnswer = "Quentin Tarantino",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Shawshank Redemption' based on a Stephen King novella?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which movie features a character named Tyler Durden?",
                answers = listOf("Fight Club", "Se7en", "The Usual Suspects", "American Psycho"),
                correctAnswer = "Fight Club",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was 'Forrest Gump' released in the 1990s?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which movie features the song 'My Heart Will Go On'?",
                answers = listOf("Titanic", "The Bodyguard", "Pretty Woman", "Ghost"),
                correctAnswer = "Titanic",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es 'El Laberinto del Fauno' dirigida por Guillermo del Toro?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién interpreta al protagonista en 'El Secreto de sus Ojos'?",
                answers = listOf(
                    "Ricardo Darín",
                    "Javier Bardem",
                    "Gael García Bernal",
                    "Antonio Banderas"
                ),
                correctAnswer = "Ricardo Darín",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'Amores Perros' dirigida por Alejandro González Iñárritu?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué película española ganó el Oscar a la Mejor Película Extranjera en 1999?",
                answers = listOf(
                    "Todo sobre mi madre",
                    "Mar Adentro",
                    "El Laberinto del Fauno",
                    "Volver"
                ),
                correctAnswer = "Todo sobre mi madre",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Relatos Salvajes' una película compuesta por varias historias cortas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién dirigió la película 'Roma'?",
                answers = listOf(
                    "Alfonso Cuarón",
                    "Guillermo del Toro",
                    "Alejandro González Iñárritu",
                    "Pedro Almodóvar"
                ),
                correctAnswer = "Alfonso Cuarón",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Volver' una película de Pedro Almodóvar?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué actor protagoniza la película 'El Hijo de la Novia'?",
                answers = listOf(
                    "Ricardo Darín",
                    "Javier Bardem",
                    "Antonio Banderas",
                    "Gael García Bernal"
                ),
                correctAnswer = "Ricardo Darín",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'La Historia Oficial' la primera película argentina en ganar un Oscar?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué película mexicana es conocida por la frase '¡Ya me cansé!'?",
                answers = listOf(
                    "Roma",
                    "Amores Perros",
                    "Nosotros los Nobles",
                    "La Ley de Herodes"
                ),
                correctAnswer = "Amores Perros",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Citizen Kane' often considered one of the greatest films of all time?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who directed the film 'A Clockwork Orange'?",
                answers = listOf(
                    "Stanley Kubrick",
                    "Francis Ford Coppola",
                    "Alfred Hitchcock",
                    "David Lynch"
                ),
                correctAnswer = "Stanley Kubrick",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was '2001: A Space Odyssey' released in 1968?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which film won the first-ever Academy Award for Best Picture?",
                answers = listOf("Wings", "Sunrise", "The Jazz Singer", "Metropolis"),
                correctAnswer = "Wings",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does the film 'The Seventh Seal' feature a game of chess with Death?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who directed the movie 'Rashomon'?",
                answers = listOf(
                    "Akira Kurosawa",
                    "Yasujirō Ozu",
                    "Kenji Mizoguchi",
                    "Hirokazu Kore-eda"
                ),
                correctAnswer = "Akira Kurosawa",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Vertigo' directed by Alfred Hitchcock?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which film is considered a masterpiece of Italian neorealism?",
                answers = listOf(
                    "Bicycle Thieves",
                    "La Dolce Vita",
                    "8½",
                    "Rocco and His Brothers"
                ),
                correctAnswer = "Bicycle Thieves",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Andrei Rublev' a film by Andrei Tarkovsky?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which movie is set during the Battle of Stalingrad?",
                answers = listOf(
                    "Enemy at the Gates",
                    "Das Boot",
                    "The Thin Red Line",
                    "Schindler's List"
                ),
                correctAnswer = "Enemy at the Gates",
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es 'El Séptimo Sello' una película de Ingmar Bergman?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién dirigió la película 'La Batalla de Argel'?",
                answers = listOf(
                    "Gillo Pontecorvo",
                    "Federico Fellini",
                    "Michelangelo Antonioni",
                    "Pier Paolo Pasolini"
                ),
                correctAnswer = "Gillo Pontecorvo",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'Roma, Ciudad Abierta' una película dirigida por Roberto Rossellini?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué película marcó el inicio del neorrealismo italiano?",
                answers = listOf(
                    "Roma, Ciudad Abierta",
                    "Ladrón de Bicicletas",
                    "La Dolce Vita",
                    "Umberto D."
                ),
                correctAnswer = "Roma, Ciudad Abierta",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'El Acorazado Potemkin' una película muda soviética?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién dirigió 'La Pasión de Juana de Arco'?",
                answers = listOf(
                    "Carl Theodor Dreyer",
                    "Robert Bresson",
                    "F.W. Murnau",
                    "Jean Renoir"
                ),
                correctAnswer = "Carl Theodor Dreyer",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'El Árbol de la Vida' dirigida por Terrence Malick?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué película cuenta la vida del pintor Vincent van Gogh?",
                answers = listOf("Loving Vincent", "Pollock", "The Danish Girl", "Frida"),
                correctAnswer = "Loving Vincent",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'La Dolce Vita' un clásico del cine italiano dirigido por Federico Fellini?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué película aborda la Revolución Mexicana?",
                answers = listOf(
                    "Vámonos con Pancho Villa",
                    "El Compadre Mendoza",
                    "La Sombra del Caudillo",
                    "Todas las anteriores"
                ),
                correctAnswer = "Todas las anteriores",
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun music() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Beethoven considered a classical composer?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which artist is known as the 'King of Pop'?",
                answers = listOf("Michael Jackson", "Elvis Presley", "Prince", "Justin Bieber"),
                correctAnswer = "Michael Jackson",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the guitar a string instrument?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who sang 'Shape of You'?",
                answers = listOf("Ed Sheeran", "Adele", "Shawn Mendes", "Justin Timberlake"),
                correctAnswer = "Ed Sheeran",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a piano a percussion instrument?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which band released the album 'Abbey Road'?",
                answers = listOf("The Beatles", "The Rolling Stones", "Pink Floyd", "Queen"),
                correctAnswer = "The Beatles",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Bohemian Rhapsody' a song by Queen?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who is the lead singer of Coldplay?",
                answers = listOf("Chris Martin", "Adam Levine", "Bono", "Dave Grohl"),
                correctAnswer = "Chris Martin",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Thriller' one of the best-selling albums of all time?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which artist is known for the song 'Rolling in the Deep'?",
                answers = listOf("Adele", "Beyoncé", "Taylor Swift", "Alicia Keys"),
                correctAnswer = "Adele",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Mozart considerado un compositor clásico?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué artista es conocido como 'El Rey del Rock and Roll'?",
                answers = listOf(
                    "Elvis Presley",
                    "Michael Jackson",
                    "Johnny Cash",
                    "Frank Sinatra"
                ),
                correctAnswer = "Elvis Presley",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el violín un instrumento de cuerda?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién canta 'La Bicicleta' junto a Shakira?",
                answers = listOf("Carlos Vives", "Ricky Martin", "Luis Fonsi", "Maluma"),
                correctAnswer = "Carlos Vives",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la batería un instrumento de percusión?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué banda lanzó el álbum 'El Mal Querer'?",
                answers = listOf("Rosalía", "C. Tangana", "Bad Bunny", "Becky G"),
                correctAnswer = "Rosalía",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Despacito' una canción de Luis Fonsi?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién canta 'Felices los 4'?",
                answers = listOf("Maluma", "Daddy Yankee", "Ozuna", "J Balvin"),
                correctAnswer = "Maluma",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Mi Gente' una canción de J Balvin?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué cantante es conocido por 'Corazón Partío'?",
                answers = listOf("Alejandro Sanz", "Pablo Alborán", "David Bisbal", "Luis Miguel"),
                correctAnswer = "Alejandro Sanz",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Did Freddie Mercury perform at Live Aid in 1985?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which composer wrote 'The Four Seasons'?",
                answers = listOf(
                    "Antonio Vivaldi",
                    "Johann Sebastian Bach",
                    "Ludwig van Beethoven",
                    "Franz Schubert"
                ),
                correctAnswer = "Antonio Vivaldi",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Billie Eilish the youngest artist to win a Grammy for Album of the Year?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which band released the album 'The Wall'?",
                answers = listOf("Pink Floyd", "Led Zeppelin", "The Beatles", "Queen"),
                correctAnswer = "Pink Floyd",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did Elvis Presley serve in the U.S. Army?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who composed the opera 'The Magic Flute'?",
                answers = listOf(
                    "Wolfgang Amadeus Mozart",
                    "Giuseppe Verdi",
                    "Richard Wagner",
                    "Ludwig van Beethoven"
                ),
                correctAnswer = "Wolfgang Amadeus Mozart",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Kashmir' a song by Led Zeppelin?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who wrote 'Imagine'?",
                answers = listOf("John Lennon", "Paul McCartney", "George Harrison", "Bob Dylan"),
                correctAnswer = "John Lennon",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Stairway to Heaven' considered one of the greatest rock songs of all time?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which singer is known as 'The Queen of Soul'?",
                answers = listOf(
                    "Aretha Franklin",
                    "Whitney Houston",
                    "Mariah Carey",
                    "Diana Ross"
                ),
                correctAnswer = "Aretha Franklin",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Freddie Mercury cantó en el concierto Live Aid en 1985?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué compositor escribió 'Las Cuatro Estaciones'?",
                answers = listOf(
                    "Antonio Vivaldi",
                    "Johann Sebastian Bach",
                    "Ludwig van Beethoven",
                    "Franz Schubert"
                ),
                correctAnswer = "Antonio Vivaldi",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Billie Eilish es la artista más joven en ganar un Grammy al Álbum del Año?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué banda lanzó el álbum 'The Wall'?",
                answers = listOf("Pink Floyd", "Led Zeppelin", "The Beatles", "Queen"),
                correctAnswer = "Pink Floyd",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Elvis Presley sirvió en el Ejército de los Estados Unidos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién compuso la ópera 'La Flauta Mágica'?",
                answers = listOf(
                    "Wolfgang Amadeus Mozart",
                    "Giuseppe Verdi",
                    "Richard Wagner",
                    "Ludwig van Beethoven"
                ),
                correctAnswer = "Wolfgang Amadeus Mozart",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Kashmir' una canción de Led Zeppelin?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién escribió la canción 'Imagine'?",
                answers = listOf("John Lennon", "Paul McCartney", "George Harrison", "Bob Dylan"),
                correctAnswer = "John Lennon",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Stairway to Heaven' considerada una de las mejores canciones de rock de todos los tiempos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué cantante es conocida como 'La Reina del Soul'?",
                answers = listOf(
                    "Aretha Franklin",
                    "Whitney Houston",
                    "Mariah Carey",
                    "Diana Ross"
                ),
                correctAnswer = "Aretha Franklin",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Did Igor Stravinsky compose 'The Rite of Spring'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who composed the opera 'La Traviata'?",
                answers = listOf(
                    "Giuseppe Verdi",
                    "Wolfgang Amadeus Mozart",
                    "Richard Wagner",
                    "Ludwig van Beethoven"
                ),
                correctAnswer = "Giuseppe Verdi",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the album 'The Dark Side of the Moon' by Pink Floyd known for its complex themes?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which composer is known for 'Boléro'?",
                answers = listOf(
                    "Maurice Ravel",
                    "Claude Debussy",
                    "Camille Saint-Saëns",
                    "Georges Bizet"
                ),
                correctAnswer = "Maurice Ravel",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did Miles Davis release the album 'Kind of Blue'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who wrote 'Carmina Burana'?",
                answers = listOf("Carl Orff", "Gustav Mahler", "Richard Strauss", "Franz Liszt"),
                correctAnswer = "Carl Orff",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Appalachian Spring' a composition by Aaron Copland?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which artist released the album 'To Pimp a Butterfly'?",
                answers = listOf("Kendrick Lamar", "J. Cole", "Drake", "Childish Gambino"),
                correctAnswer = "Kendrick Lamar",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did Ludwig van Beethoven compose nine symphonies?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which composer is known for 'Clair de Lune'?",
                answers = listOf(
                    "Claude Debussy",
                    "Frédéric Chopin",
                    "Franz Schubert",
                    "Johannes Brahms"
                ),
                correctAnswer = "Claude Debussy",
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Compuso Igor Stravinsky 'La Consagración de la Primavera'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién compuso la ópera 'Carmen'?",
                answers = listOf(
                    "Georges Bizet",
                    "Giuseppe Verdi",
                    "Richard Wagner",
                    "Ludwig van Beethoven"
                ),
                correctAnswer = "Georges Bizet",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'El Lado Oscuro de la Luna' de Pink Floyd conocido por sus temas complejos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién es conocido por componer 'Boléro'?",
                answers = listOf(
                    "Maurice Ravel",
                    "Claude Debussy",
                    "Camille Saint-Saëns",
                    "Georges Bizet"
                ),
                correctAnswer = "Maurice Ravel",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Lanzó Miles Davis el álbum 'Kind of Blue'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién compuso 'Carmina Burana'?",
                answers = listOf("Carl Orff", "Gustav Mahler", "Richard Strauss", "Franz Liszt"),
                correctAnswer = "Carl Orff",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Primavera Apalache' una composición de Aaron Copland?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué artista lanzó el álbum 'To Pimp a Butterfly'?",
                answers = listOf("Kendrick Lamar", "J. Cole", "Drake", "Childish Gambino"),
                correctAnswer = "Kendrick Lamar",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Compuso Ludwig van Beethoven nueve sinfonías?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién es conocido por 'Clair de Lune'?",
                answers = listOf(
                    "Claude Debussy",
                    "Frédéric Chopin",
                    "Franz Schubert",
                    "Johannes Brahms"
                ),
                correctAnswer = "Claude Debussy",
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun musicalsAndTheatres() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'The Phantom of the Opera' a famous musical?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who composed the music for 'The Phantom of the Opera'?",
                answers = listOf(
                    "Andrew Lloyd Webber",
                    "Stephen Sondheim",
                    "Lin-Manuel Miranda",
                    "Jonathan Larson"
                ),
                correctAnswer = "Andrew Lloyd Webber",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Hamilton' a musical about Alexander Hamilton?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which musical features the song 'Defying Gravity'?",
                answers = listOf("Wicked", "The Wizard of Oz", "Cats", "Les Misérables"),
                correctAnswer = "Wicked",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Cats' a musical by Andrew Lloyd Webber?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which musical features the character Eliza Doolittle?",
                answers = listOf(
                    "My Fair Lady",
                    "Les Misérables",
                    "The Sound of Music",
                    "Hamilton"
                ),
                correctAnswer = "My Fair Lady",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Sound of Music' set during World War II?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which musical features the song 'Memory'?",
                answers = listOf("Cats", "Phantom of the Opera", "Wicked", "Chicago"),
                correctAnswer = "Cats",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Rent' a musical set in New York City?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who composed 'Les Misérables'?",
                answers = listOf(
                    "Claude-Michel Schönberg",
                    "Andrew Lloyd Webber",
                    "Lin-Manuel Miranda",
                    "Stephen Sondheim"
                ),
                correctAnswer = "Claude-Michel Schönberg",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es 'El Fantasma de la Ópera' un musical famoso?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién compuso la música de 'El Fantasma de la Ópera'?",
                answers = listOf(
                    "Andrew Lloyd Webber",
                    "Stephen Sondheim",
                    "Lin-Manuel Miranda",
                    "Jonathan Larson"
                ),
                correctAnswer = "Andrew Lloyd Webber",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Hamilton' un musical sobre Alexander Hamilton?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué musical incluye la canción 'Defying Gravity'?",
                answers = listOf("Wicked", "El Mago de Oz", "Cats", "Los Miserables"),
                correctAnswer = "Wicked",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Cats' un musical compuesto por Andrew Lloyd Webber?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué musical presenta al personaje Eliza Doolittle?",
                answers = listOf(
                    "My Fair Lady",
                    "Los Miserables",
                    "La Novicia Rebelde",
                    "Hamilton"
                ),
                correctAnswer = "My Fair Lady",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está ambientado 'La Novicia Rebelde' durante la Segunda Guerra Mundial?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué musical incluye la canción 'Memory'?",
                answers = listOf("Cats", "El Fantasma de la Ópera", "Wicked", "Chicago"),
                correctAnswer = "Cats",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está ambientado 'Rent' en la ciudad de Nueva York?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién compuso 'Los Miserables'?",
                answers = listOf(
                    "Claude-Michel Schönberg",
                    "Andrew Lloyd Webber",
                    "Lin-Manuel Miranda",
                    "Stephen Sondheim"
                ),
                correctAnswer = "Claude-Michel Schönberg",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Les Misérables' based on a novel by Victor Hugo?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which musical features the song 'The Circle of Life'?",
                answers = listOf("The Lion King", "Aladdin", "Frozen", "Beauty and the Beast"),
                correctAnswer = "The Lion King",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did Stephen Sondheim compose 'Sweeney Todd'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which musical features the characters Riff and Tony?",
                answers = listOf("West Side Story", "The Sound of Music", "My Fair Lady", "Wicked"),
                correctAnswer = "West Side Story",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Fiddler on the Roof' set in Russia?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which musical includes the song 'America'?",
                answers = listOf("West Side Story", "Chicago", "The Producers", "Cabaret"),
                correctAnswer = "West Side Story",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was 'The Book of Mormon' co-created by the creators of South Park?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which musical features the character Jean Valjean?",
                answers = listOf("Les Misérables", "Phantom of the Opera", "Hamilton", "Chicago"),
                correctAnswer = "Les Misérables",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Chicago' set during the Prohibition era?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who composed the music for 'West Side Story'?",
                answers = listOf(
                    "Leonard Bernstein",
                    "Andrew Lloyd Webber",
                    "Claude-Michel Schönberg",
                    "Lin-Manuel Miranda"
                ),
                correctAnswer = "Leonard Bernstein",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Está 'Los Miserables' basada en una novela de Victor Hugo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué musical incluye la canción 'El Ciclo de la Vida'?",
                answers = listOf("El Rey León", "Aladdín", "Frozen", "La Bella y la Bestia"),
                correctAnswer = "El Rey León",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Compuso Stephen Sondheim 'Sweeney Todd'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué musical presenta a los personajes Riff y Tony?",
                answers = listOf(
                    "Amor sin Barreras",
                    "La Novicia Rebelde",
                    "My Fair Lady",
                    "Wicked"
                ),
                correctAnswer = "Amor sin Barreras",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está ambientada 'El Violinista en el Tejado' en Rusia?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué musical incluye la canción 'América'?",
                answers = listOf("Amor sin Barreras", "Chicago", "Los Productores", "Cabaret"),
                correctAnswer = "Amor sin Barreras",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'El Libro de Mormón' creado por los creadores de South Park?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué musical presenta al personaje Jean Valjean?",
                answers = listOf(
                    "Los Miserables",
                    "El Fantasma de la Ópera",
                    "Hamilton",
                    "Chicago"
                ),
                correctAnswer = "Los Miserables",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está ambientado 'Chicago' durante la Ley Seca?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién compuso la música de 'Amor sin Barreras'?",
                answers = listOf(
                    "Leonard Bernstein",
                    "Andrew Lloyd Webber",
                    "Claude-Michel Schönberg",
                    "Lin-Manuel Miranda"
                ),
                correctAnswer = "Leonard Bernstein",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Sunday in the Park with George' inspired by a painting by Georges Seurat?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which composer created 'Into the Woods'?",
                answers = listOf(
                    "Stephen Sondheim",
                    "Andrew Lloyd Webber",
                    "Lin-Manuel Miranda",
                    "Claude-Michel Schönberg"
                ),
                correctAnswer = "Stephen Sondheim",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did 'Cats' win the Tony Award for Best Musical in 1983?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which musical features the character Tevye?",
                answers = listOf("Fiddler on the Roof", "The Producers", "Cabaret", "Chicago"),
                correctAnswer = "Fiddler on the Roof",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Book of Mormon' set in Uganda?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which musical includes the song 'You’ll Never Walk Alone'?",
                answers = listOf("Carousel", "South Pacific", "Oklahoma!", "The King and I"),
                correctAnswer = "Carousel",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did 'Hamilton' win 11 Tony Awards in 2016?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which musical is based on the novel by Gaston Leroux?",
                answers = listOf("The Phantom of the Opera", "Les Misérables", "Cats", "Wicked"),
                correctAnswer = "The Phantom of the Opera",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Sweeney Todd: The Demon Barber of Fleet Street' set in London?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who composed the music for 'Evita'?",
                answers = listOf(
                    "Andrew Lloyd Webber",
                    "Stephen Sondheim",
                    "Claude-Michel Schönberg",
                    "Leonard Bernstein"
                ),
                correctAnswer = "Andrew Lloyd Webber",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Está 'Domingo en el Parque con George' inspirada en una pintura de Georges Seurat?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién compuso 'Into the Woods'?",
                answers = listOf(
                    "Stephen Sondheim",
                    "Andrew Lloyd Webber",
                    "Lin-Manuel Miranda",
                    "Claude-Michel Schönberg"
                ),
                correctAnswer = "Stephen Sondheim",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Ganó 'Cats' el premio Tony al Mejor Musical en 1983?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué musical presenta al personaje Tevye?",
                answers = listOf(
                    "El Violinista en el Tejado",
                    "Los Productores",
                    "Cabaret",
                    "Chicago"
                ),
                correctAnswer = "El Violinista en el Tejado",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está ambientado 'El Libro de Mormón' en Uganda?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué musical incluye la canción 'You’ll Never Walk Alone'?",
                answers = listOf("Carrusel", "South Pacific", "Oklahoma!", "El Rey y Yo"),
                correctAnswer = "Carrusel",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Ganó 'Hamilton' 11 premios Tony en 2016?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué musical está basado en la novela de Gaston Leroux?",
                answers = listOf("El Fantasma de la Ópera", "Los Miserables", "Cats", "Wicked"),
                correctAnswer = "El Fantasma de la Ópera",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está ambientado 'Sweeney Todd: El Barbero Diabólico de la Calle Fleet' en Londres?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién compuso la música de 'Evita'?",
                answers = listOf(
                    "Andrew Lloyd Webber",
                    "Stephen Sondheim",
                    "Claude-Michel Schönberg",
                    "Leonard Bernstein"
                ),
                correctAnswer = "Andrew Lloyd Webber",
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun television() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Friends' a sitcom about six friends living in New York City?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which show features the character Sheldon Cooper?",
                answers = listOf(
                    "The Big Bang Theory",
                    "How I Met Your Mother",
                    "Friends",
                    "Modern Family"
                ),
                correctAnswer = "The Big Bang Theory",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Breaking Bad' about a chemistry teacher turned drug dealer?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which TV show is set in the fictional town of Springfield?",
                answers = listOf("The Simpsons", "Family Guy", "Futurama", "South Park"),
                correctAnswer = "The Simpsons",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Game of Thrones' based on books by George R.R. Martin?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which series features the character Walter White?",
                answers = listOf("Breaking Bad", "The Sopranos", "Mad Men", "Better Call Saul"),
                correctAnswer = "Breaking Bad",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Stranger Things' set in the 1980s?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which show features the character Jon Snow?",
                answers = listOf("Game of Thrones", "Vikings", "The Witcher", "The Crown"),
                correctAnswer = "Game of Thrones",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Office' a mockumentary sitcom?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which show is about the lives of the Dunphy and Pritchett families?",
                answers = listOf(
                    "Modern Family",
                    "Friends",
                    "Parks and Recreation",
                    "Brooklyn Nine-Nine"
                ),
                correctAnswer = "Modern Family",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es 'La Casa de Papel' una serie española sobre un robo a la Fábrica Nacional de Moneda y Timbre?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie presenta al personaje El Profesor?",
                answers = listOf("La Casa de Papel", "Vis a Vis", "El Internado", "Élite"),
                correctAnswer = "La Casa de Papel",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'El Chavo del 8' una comedia mexicana?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie tiene lugar en el pueblo ficticio de San Ángel Inn?",
                answers = listOf(
                    "El Chavo del 8",
                    "La Familia P. Luche",
                    "Vecinos",
                    "El Chapulín Colorado"
                ),
                correctAnswer = "El Chavo del 8",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está ambientada 'Élite' en un instituto privado de España?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie presenta a los personajes Guzmán y Lucrecia?",
                answers = listOf("Élite", "La Casa de Papel", "Vis a Vis", "El Internado"),
                correctAnswer = "Élite",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Vis a Vis' una serie ambientada en una prisión?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie presenta al personaje Macarena Ferreiro?",
                answers = listOf("Vis a Vis", "La Casa de Papel", "Élite", "El Internado"),
                correctAnswer = "Vis a Vis",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'El Internado' una serie sobre un internado en un bosque misterioso?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie incluye al personaje Iván Noiret?",
                answers = listOf("El Internado", "Élite", "Vis a Vis", "La Casa de Papel"),
                correctAnswer = "El Internado",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Did 'Breaking Bad' run for five seasons?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which actor played Walter White in 'Breaking Bad'?",
                answers = listOf("Bryan Cranston", "Aaron Paul", "Jon Hamm", "Matthew McConaughey"),
                correctAnswer = "Bryan Cranston",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Office' based on a British sitcom of the same name?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which series features the character Don Draper?",
                answers = listOf("Mad Men", "Breaking Bad", "Suits", "The West Wing"),
                correctAnswer = "Mad Men",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was 'Stranger Things' created by the Duffer Brothers?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which show features the character Claire Underwood?",
                answers = listOf("House of Cards", "The Crown", "Scandal", "Homeland"),
                correctAnswer = "House of Cards",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did 'Game of Thrones' end after eight seasons?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which show features the fictional ad agency Sterling Cooper?",
                answers = listOf("Mad Men", "Suits", "The West Wing", "The Office"),
                correctAnswer = "Mad Men",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Mandalorian' set in the Star Wars universe?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which series follows the lives of the Pearson family?",
                answers = listOf("This Is Us", "Parenthood", "Brothers & Sisters", "One Tree Hill"),
                correctAnswer = "This Is Us",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Duró 'La Casa de Papel' cinco temporadas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué actor interpretó a El Profesor en 'La Casa de Papel'?",
                answers = listOf("Álvaro Morte", "Pedro Alonso", "Miguel Herrán", "Jaime Lorente"),
                correctAnswer = "Álvaro Morte",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está 'Vis a Vis' ambientada en una prisión femenina?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie incluye al personaje Zulema Zahir?",
                answers = listOf("Vis a Vis", "La Casa de Papel", "Élite", "El Internado"),
                correctAnswer = "Vis a Vis",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'Élite' creada por Darío Madrona y Carlos Montero?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie incluye a los personajes Guzmán y Samuel?",
                answers = listOf("Élite", "La Casa de Papel", "Vis a Vis", "El Internado"),
                correctAnswer = "Élite",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está 'El Internado: Laguna Negra' ambientado en un bosque misterioso?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie tiene lugar en el internado Laguna Negra?",
                answers = listOf("El Internado", "Élite", "Vis a Vis", "La Casa de Papel"),
                correctAnswer = "El Internado",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se desarrolla 'La Veneno' en España?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie cuenta la vida de Cristina Ortiz?",
                answers = listOf("Veneno", "Élite", "Vis a Vis", "La Casa de Papel"),
                correctAnswer = "Veneno",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Was 'The Sopranos' the first cable TV show to win the Emmy for Outstanding Drama Series?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which actor played Tony Soprano in 'The Sopranos'?",
                answers = listOf("James Gandolfini", "Bryan Cranston", "Jon Hamm", "Al Pacino"),
                correctAnswer = "James Gandolfini",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Twin Peaks' directed by David Lynch?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which series features the town of Stars Hollow?",
                answers = listOf("Gilmore Girls", "Everwood", "Dawson's Creek", "Hart of Dixie"),
                correctAnswer = "Gilmore Girls",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did 'Breaking Bad' win the Emmy for Outstanding Drama Series four times?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which TV show is set in the fictional law firm Pearson Hardman?",
                answers = listOf("Suits", "The Good Wife", "Boston Legal", "Better Call Saul"),
                correctAnswer = "Suits",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Crown' a historical drama about the British monarchy?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which series features the character Dr. Gregory House?",
                answers = listOf("House", "Grey's Anatomy", "ER", "Scrubs"),
                correctAnswer = "House",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was 'The Wire' created by David Simon?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which TV show follows the Baltimore drug scene through the eyes of both law enforcement and criminals?",
                answers = listOf("The Wire", "Breaking Bad", "The Shield", "True Detective"),
                correctAnswer = "The Wire",
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Ganó 'La Casa de Papel' un premio Emmy Internacional?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién interpreta a Berlín en 'La Casa de Papel'?",
                answers = listOf("Pedro Alonso", "Álvaro Morte", "Miguel Herrán", "Jaime Lorente"),
                correctAnswer = "Pedro Alonso",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está 'Vis a Vis' basada en una prisión femenina ficticia?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie incluye a los personajes Zulema Zahir y Macarena Ferreiro?",
                answers = listOf("Vis a Vis", "Élite", "La Casa de Papel", "El Internado"),
                correctAnswer = "Vis a Vis",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'El Internado' un éxito en España durante los años 2000?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie española está ambientada en un internado en un bosque misterioso?",
                answers = listOf("El Internado", "La Casa de Papel", "Vis a Vis", "Élite"),
                correctAnswer = "El Internado",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Ganó 'Veneno' varios premios internacionales por su representación de Cristina Ortiz?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie cuenta la vida de Cristina Ortiz, conocida como 'La Veneno'?",
                answers = listOf("Veneno", "Élite", "Vis a Vis", "La Casa de Papel"),
                correctAnswer = "Veneno",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'Física o Química' una serie popular entre adolescentes en España?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie aborda la vida de estudiantes en un instituto español?",
                answers = listOf("Física o Química", "Élite", "El Internado", "Vis a Vis"),
                correctAnswer = "Física o Química",
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun videoGames() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Super Mario Bros.' a game developed by Nintendo?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which video game series features the character Link?",
                answers = listOf(
                    "The Legend of Zelda",
                    "Final Fantasy",
                    "Dark Souls",
                    "Elder Scrolls"
                ),
                correctAnswer = "The Legend of Zelda",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Minecraft' a sandbox video game?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game features the phrase 'Finish Him!'?",
                answers = listOf("Mortal Kombat", "Street Fighter", "Tekken", "Soul Calibur"),
                correctAnswer = "Mortal Kombat",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Call of Duty' a first-person shooter game series?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which video game series features the character Master Chief?",
                answers = listOf("Halo", "Gears of War", "Destiny", "Mass Effect"),
                correctAnswer = "Halo",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Sims' a life simulation game?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game includes the character Sonic the Hedgehog?",
                answers = listOf("Sonic", "Mario", "Crash Bandicoot", "Spyro"),
                correctAnswer = "Sonic",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'League of Legends' a multiplayer online battle arena (MOBA) game?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which video game series is set in the world of Azeroth?",
                answers = listOf("World of Warcraft", "Elder Scrolls", "Diablo", "Path of Exile"),
                correctAnswer = "World of Warcraft",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es 'Super Mario Bros.' un juego desarrollado por Nintendo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie de videojuegos tiene al personaje Link?",
                answers = listOf(
                    "The Legend of Zelda",
                    "Final Fantasy",
                    "Dark Souls",
                    "Elder Scrolls"
                ),
                correctAnswer = "The Legend of Zelda",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Minecraft' un videojuego de tipo sandbox?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego incluye la frase 'Finish Him!'?",
                answers = listOf("Mortal Kombat", "Street Fighter", "Tekken", "Soul Calibur"),
                correctAnswer = "Mortal Kombat",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Call of Duty' una serie de videojuegos de disparos en primera persona?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie de videojuegos incluye al personaje Master Chief?",
                answers = listOf("Halo", "Gears of War", "Destiny", "Mass Effect"),
                correctAnswer = "Halo",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Los Sims' un videojuego de simulación de vida?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego tiene al personaje Sonic the Hedgehog?",
                answers = listOf("Sonic", "Mario", "Crash Bandicoot", "Spyro"),
                correctAnswer = "Sonic",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'League of Legends' un juego de arena de batalla multijugador en línea (MOBA)?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿En qué serie de videojuegos se desarrolla en el mundo de Azeroth?",
                answers = listOf("World of Warcraft", "Elder Scrolls", "Diablo", "Path of Exile"),
                correctAnswer = "World of Warcraft",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Was 'The Legend of Zelda: Ocarina of Time' released for the Nintendo 64?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which company developed the game 'The Witcher 3: Wild Hunt'?",
                answers = listOf("CD Projekt Red", "Ubisoft", "Bethesda", "BioWare"),
                correctAnswer = "CD Projekt Red",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Overwatch' a team-based first-person shooter?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which video game series includes the region of Sinnoh?",
                answers = listOf("Pokemon", "Final Fantasy", "Monster Hunter", "Fire Emblem"),
                correctAnswer = "Pokemon",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does 'Fortnite' feature a Battle Royale mode?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game features the protagonist Arthur Morgan?",
                answers = listOf(
                    "Red Dead Redemption 2",
                    "Grand Theft Auto V",
                    "Assassin's Creed Valhalla",
                    "Far Cry 5"
                ),
                correctAnswer = "Red Dead Redemption 2",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Dark Souls' known for its challenging gameplay?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which company developed 'Half-Life'?",
                answers = listOf("Valve", "Epic Games", "id Software", "Crytek"),
                correctAnswer = "Valve",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was 'Animal Crossing: New Horizons' released in 2020?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game series features the character Kratos?",
                answers = listOf("God of War", "Devil May Cry", "Bayonetta", "Nioh"),
                correctAnswer = "God of War",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Fue 'The Legend of Zelda: Ocarina of Time' lanzado para Nintendo 64?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué compañía desarrolló el juego 'The Witcher 3: Wild Hunt'?",
                answers = listOf("CD Projekt Red", "Ubisoft", "Bethesda", "BioWare"),
                correctAnswer = "CD Projekt Red",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Overwatch' un juego de disparos en primera persona basado en equipos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie de videojuegos incluye la región de Sinnoh?",
                answers = listOf("Pokemon", "Final Fantasy", "Monster Hunter", "Fire Emblem"),
                correctAnswer = "Pokemon",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Tiene 'Fortnite' un modo Battle Royale?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego tiene como protagonista a Arthur Morgan?",
                answers = listOf(
                    "Red Dead Redemption 2",
                    "Grand Theft Auto V",
                    "Assassin's Creed Valhalla",
                    "Far Cry 5"
                ),
                correctAnswer = "Red Dead Redemption 2",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Dark Souls' conocido por su jugabilidad desafiante?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué compañía desarrolló 'Half-Life'?",
                answers = listOf("Valve", "Epic Games", "id Software", "Crytek"),
                correctAnswer = "Valve",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'Animal Crossing: New Horizons' lanzado en 2020?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie de videojuegos incluye al personaje Kratos?",
                answers = listOf("God of War", "Devil May Cry", "Bayonetta", "Nioh"),
                correctAnswer = "God of War",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Was 'Bloodborne' developed by FromSoftware?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game is known for the character Solid Snake?",
                answers = listOf("Metal Gear Solid", "Splinter Cell", "Hitman", "Resident Evil"),
                correctAnswer = "Metal Gear Solid",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does 'No Man's Sky' feature procedurally generated planets?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game studio created 'The Last of Us'?",
                answers = listOf("Naughty Dog", "Rockstar Games", "CD Projekt Red", "Ubisoft"),
                correctAnswer = "Naughty Dog",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Celeste' a platformer game about climbing a mountain?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game features the 'Citadel' as a key location?",
                answers = listOf(
                    "Mass Effect",
                    "Halo",
                    "Destiny",
                    "Star Wars: Knights of the Old Republic"
                ),
                correctAnswer = "Mass Effect",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was 'Hollow Knight' developed by Team Cherry?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game series features the 'Blades of Chaos'?",
                answers = listOf("God of War", "Diablo", "Dark Souls", "Darksiders"),
                correctAnswer = "God of War",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Outer Wilds' a game about exploring a solar system stuck in a time loop?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game series features the 'Brotherhood of Steel'?",
                answers = listOf("Fallout", "Bioshock", "The Elder Scrolls", "Metro"),
                correctAnswer = "Fallout",
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Fue 'Bloodborne' desarrollado por FromSoftware?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego es conocido por el personaje Solid Snake?",
                answers = listOf("Metal Gear Solid", "Splinter Cell", "Hitman", "Resident Evil"),
                correctAnswer = "Metal Gear Solid",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Incluye 'No Man's Sky' planetas generados proceduralmente?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué estudio creó 'The Last of Us'?",
                answers = listOf("Naughty Dog", "Rockstar Games", "CD Projekt Red", "Ubisoft"),
                correctAnswer = "Naughty Dog",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Celeste' un videojuego de plataformas sobre escalar una montaña?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego tiene a la 'Ciudadela' como ubicación clave?",
                answers = listOf(
                    "Mass Effect",
                    "Halo",
                    "Destiny",
                    "Star Wars: Caballeros de la Antigua República"
                ),
                correctAnswer = "Mass Effect",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue 'Hollow Knight' desarrollado por Team Cherry?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie de juegos incluye las 'Espadas del Caos'?",
                answers = listOf("God of War", "Diablo", "Dark Souls", "Darksiders"),
                correctAnswer = "God of War",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Outer Wilds' un juego sobre explorar un sistema solar atrapado en un bucle temporal?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie de videojuegos incluye la 'Hermandad del Acero'?",
                answers = listOf("Fallout", "Bioshock", "The Elder Scrolls", "Metro"),
                correctAnswer = "Fallout",
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun boardGames() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Monopoly' a board game about buying and trading properties?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game involves sinking your opponent's ships?",
                answers = listOf("Battleship", "Risk", "Stratego", "Clue"),
                correctAnswer = "Battleship",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Scrabble' a word-based board game?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which board game features Professor Plum and Colonel Mustard?",
                answers = listOf("Clue", "Monopoly", "Risk", "Sorry!"),
                correctAnswer = "Clue",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Chess' a two-player strategy game?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game involves capturing your opponent's king?",
                answers = listOf("Chess", "Checkers", "Backgammon", "Go"),
                correctAnswer = "Chess",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Risk' a game about world domination?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game requires you to spell words using letter tiles?",
                answers = listOf("Scrabble", "Boggle", "Yahtzee", "Taboo"),
                correctAnswer = "Scrabble",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Catan' a game about building settlements and trading resources?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game features roads, settlements, and cities?",
                answers = listOf("Catan", "Monopoly", "Carcassonne", "Risk"),
                correctAnswer = "Catan",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es 'Monopoly' un juego de mesa sobre comprar y negociar propiedades?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego consiste en hundir los barcos de tu oponente?",
                answers = listOf("Batalla Naval", "Risk", "Stratego", "Cluedo"),
                correctAnswer = "Batalla Naval",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Scrabble' un juego de mesa basado en palabras?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego de mesa presenta al Profesor Ciruela y al Coronel Mostaza?",
                answers = listOf("Cluedo", "Monopoly", "Risk", "Sorry!"),
                correctAnswer = "Cluedo",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Ajedrez' un juego de estrategia para dos jugadores?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego consiste en capturar al rey del oponente?",
                answers = listOf("Ajedrez", "Damas", "Backgammon", "Go"),
                correctAnswer = "Ajedrez",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Risk' un juego sobre dominar el mundo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego requiere formar palabras con fichas de letras?",
                answers = listOf("Scrabble", "Boggle", "Yahtzee", "Taboo"),
                correctAnswer = "Scrabble",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Catan' un juego sobre construir asentamientos y comerciar recursos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego incluye carreteras, asentamientos y ciudades?",
                answers = listOf("Catan", "Monopoly", "Carcassonne", "Risk"),
                correctAnswer = "Catan",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Catan' also known as 'The Settlers of Catan'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which board game involves placing tiles to build a medieval landscape?",
                answers = listOf("Carcassonne", "Catan", "Risk", "Pandemic"),
                correctAnswer = "Carcassonne",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Risk' a game where players compete to conquer territories on a world map?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which board game features diseases spreading across the globe?",
                answers = listOf("Pandemic", "Risk", "Catan", "Ticket to Ride"),
                correctAnswer = "Pandemic",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does 'Ticket to Ride' involve building train routes across a map?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which board game is centered around railroad building?",
                answers = listOf("Ticket to Ride", "Catan", "Risk", "Clue"),
                correctAnswer = "Ticket to Ride",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Axis & Allies' a strategy game set during World War II?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game is often referred to as a classic war strategy game?",
                answers = listOf("Risk", "Axis & Allies", "Catan", "Pandemic"),
                correctAnswer = "Risk",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does 'Clue' feature multiple potential murder weapons?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game involves solving a murder mystery?",
                answers = listOf("Clue", "Monopoly", "Pandemic", "Carcassonne"),
                correctAnswer = "Clue",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Se conoce 'Catan' también como 'Los Colonos de Catan'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego de mesa consiste en colocar fichas para construir un paisaje medieval?",
                answers = listOf("Carcassonne", "Catan", "Risk", "Pandemic"),
                correctAnswer = "Carcassonne",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Risk' un juego donde los jugadores compiten por conquistar territorios en un mapa mundial?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego de mesa presenta enfermedades que se propagan por todo el mundo?",
                answers = listOf("Pandemic", "Risk", "Catan", "Aventureros al Tren"),
                correctAnswer = "Pandemic",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Incluye 'Aventureros al Tren' la construcción de rutas de tren en un mapa?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego de mesa se centra en la construcción de ferrocarriles?",
                answers = listOf("Aventureros al Tren", "Catan", "Risk", "Cluedo"),
                correctAnswer = "Aventureros al Tren",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Axis & Allies' un juego de estrategia ambientado durante la Segunda Guerra Mundial?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego es conocido como un clásico de estrategia bélica?",
                answers = listOf("Risk", "Axis & Allies", "Catan", "Pandemic"),
                correctAnswer = "Risk",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Incluye 'Cluedo' múltiples posibles armas de asesinato?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego consiste en resolver un misterio de asesinato?",
                answers = listOf("Cluedo", "Monopoly", "Pandemic", "Carcassonne"),
                correctAnswer = "Cluedo",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Gloomhaven' a cooperative board game?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which board game involves building a civilization from the Stone Age to the Modern Age?",
                answers = listOf(
                    "Through the Ages",
                    "Civ: The Board Game",
                    "Twilight Struggle",
                    "Terraforming Mars"
                ),
                correctAnswer = "Through the Ages",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does 'Twilight Struggle' simulate the Cold War?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game features factions such as the Eyrie Dynasties and the Woodland Alliance?",
                answers = listOf("Root", "Scythe", "Terraforming Mars", "Catan"),
                correctAnswer = "Root",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Pandemic Legacy' a legacy version of 'Pandemic'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game is set in the post-apocalyptic 1920+ alternate history universe?",
                answers = listOf("Scythe", "Terraforming Mars", "Twilight Struggle", "Root"),
                correctAnswer = "Scythe",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Betrayal at House on the Hill' a cooperative and traitor mechanic game?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game features a modular board and alien species factions?",
                answers = listOf("Twilight Imperium", "Terraforming Mars", "Root", "Eclipse"),
                correctAnswer = "Twilight Imperium",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Terraforming Mars' about making Mars habitable?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which game requires players to complete secret objectives and public milestones?",
                answers = listOf("Terraforming Mars", "Catan", "Pandemic", "Risk"),
                correctAnswer = "Terraforming Mars",
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es 'Gloomhaven' un juego de mesa cooperativo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego de mesa implica construir una civilización desde la Edad de Piedra hasta la Edad Moderna?",
                answers = listOf(
                    "Through the Ages",
                    "Civ: The Board Game",
                    "Twilight Struggle",
                    "Terraforming Mars"
                ),
                correctAnswer = "Through the Ages",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Simula 'Twilight Struggle' la Guerra Fría?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego incluye facciones como las Dinastías Eyrie y la Alianza del Bosque?",
                answers = listOf("Root", "Scythe", "Terraforming Mars", "Catan"),
                correctAnswer = "Root",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Pandemic Legacy' una versión legacy de 'Pandemic'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego está ambientado en un universo alternativo postapocalíptico 1920+?",
                answers = listOf("Scythe", "Terraforming Mars", "Twilight Struggle", "Root"),
                correctAnswer = "Scythe",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Betrayal at House on the Hill' un juego cooperativo con mecánica de traidor?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego incluye un tablero modular y facciones de especies alienígenas?",
                answers = listOf("Twilight Imperium", "Terraforming Mars", "Root", "Eclipse"),
                correctAnswer = "Twilight Imperium",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Terraforming Mars' un juego sobre hacer habitable Marte?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué juego requiere que los jugadores completen objetivos secretos y hitos públicos?",
                answers = listOf("Terraforming Mars", "Catan", "Pandemic", "Risk"),
                correctAnswer = "Terraforming Mars",
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun scienceAndNature() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is water composed of hydrogen and oxygen?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What planet is known as the Red Planet?",
                answers = listOf("Mars", "Venus", "Jupiter", "Saturn"),
                correctAnswer = "Mars",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the sun a star?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which gas do plants primarily use for photosynthesis?",
                answers = listOf("Carbon Dioxide", "Oxygen", "Nitrogen", "Hydrogen"),
                correctAnswer = "Carbon Dioxide",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the chemical symbol for water H2O?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the center of our solar system?",
                answers = listOf("The Sun", "The Earth", "The Moon", "Mars"),
                correctAnswer = "The Sun",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Do humans have 206 bones in their bodies?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which organ pumps blood throughout the body?",
                answers = listOf("The Heart", "The Brain", "The Liver", "The Lungs"),
                correctAnswer = "The Heart",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the boiling point of water 100°C (at sea level)?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the largest planet in our solar system?",
                answers = listOf("Jupiter", "Saturn", "Neptune", "Earth"),
                correctAnswer = "Jupiter",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Está compuesta el agua de hidrógeno y oxígeno?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué planeta es conocido como el Planeta Rojo?",
                answers = listOf("Marte", "Venus", "Júpiter", "Saturno"),
                correctAnswer = "Marte",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el sol una estrella?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué gas usan las plantas principalmente para la fotosíntesis?",
                answers = listOf("Dióxido de Carbono", "Oxígeno", "Nitrógeno", "Hidrógeno"),
                correctAnswer = "Dióxido de Carbono",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es H2O el símbolo químico del agua?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué está en el centro de nuestro sistema solar?",
                answers = listOf("El Sol", "La Tierra", "La Luna", "Marte"),
                correctAnswer = "El Sol",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Tienen los humanos 206 huesos en sus cuerpos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué órgano bombea la sangre por todo el cuerpo?",
                answers = listOf("El Corazón", "El Cerebro", "El Hígado", "Los Pulmones"),
                correctAnswer = "El Corazón",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el punto de ebullición del agua 100°C (a nivel del mar)?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el planeta más grande de nuestro sistema solar?",
                answers = listOf("Júpiter", "Saturno", "Neptuno", "Tierra"),
                correctAnswer = "Júpiter",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the speed of light approximately 299,792 kilometers per second?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which element has the chemical symbol 'Au'?",
                answers = listOf("Gold", "Silver", "Aluminum", "Argon"),
                correctAnswer = "Gold",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does the moon have an atmosphere?",
                answers = listOf("True", "False"),
                correctAnswer = "False",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the hardest natural substance on Earth?",
                answers = listOf("Diamond", "Quartz", "Graphite", "Iron"),
                correctAnswer = "Diamond",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does the human body contain more than 60% water?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which planet has the strongest gravity in our solar system?",
                answers = listOf("Jupiter", "Earth", "Neptune", "Saturn"),
                correctAnswer = "Jupiter",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the chemical symbol for potassium 'K'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which organ is responsible for producing insulin?",
                answers = listOf("Pancreas", "Liver", "Kidney", "Stomach"),
                correctAnswer = "Pancreas",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Do plants produce oxygen during photosynthesis?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the smallest unit of life?",
                answers = listOf("Cell", "Molecule", "Atom", "Organ"),
                correctAnswer = "Cell",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es la velocidad de la luz aproximadamente 299,792 kilómetros por segundo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué elemento tiene el símbolo químico 'Au'?",
                answers = listOf("Oro", "Plata", "Aluminio", "Argón"),
                correctAnswer = "Oro",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Tiene la luna atmósfera?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Falso",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la sustancia natural más dura de la Tierra?",
                answers = listOf("Diamante", "Cuarzo", "Grafito", "Hierro"),
                correctAnswer = "Diamante",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿El cuerpo humano contiene más del 60% de agua?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué planeta tiene la gravedad más fuerte de nuestro sistema solar?",
                answers = listOf("Júpiter", "Tierra", "Neptuno", "Saturno"),
                correctAnswer = "Júpiter",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'K' el símbolo químico del potasio?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué órgano es responsable de producir insulina?",
                answers = listOf("Páncreas", "Hígado", "Riñón", "Estómago"),
                correctAnswer = "Páncreas",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Producen oxígeno las plantas durante la fotosíntesis?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la unidad más pequeña de la vida?",
                answers = listOf("Célula", "Molécula", "Átomo", "Órgano"),
                correctAnswer = "Célula",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Does the speed of light change in different mediums?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which element has the atomic number 79?",
                answers = listOf("Gold", "Platinum", "Silver", "Copper"),
                correctAnswer = "Gold",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is dark matter directly observable with current technology?",
                answers = listOf("True", "False"),
                correctAnswer = "False",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the most abundant gas in Earth's atmosphere?",
                answers = listOf("Nitrogen", "Oxygen", "Carbon Dioxide", "Argon"),
                correctAnswer = "Nitrogen",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does the human body have more bacterial cells than human cells?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which planet is tilted on its side at an angle of approximately 98 degrees?",
                answers = listOf("Uranus", "Neptune", "Venus", "Mars"),
                correctAnswer = "Uranus",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the kilogram the only SI unit defined by a physical object?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which organ has the largest surface area in the human body?",
                answers = listOf("Skin", "Liver", "Lungs", "Small Intestine"),
                correctAnswer = "Skin",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Do black holes emit radiation?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the densest naturally occurring element?",
                answers = listOf("Osmium", "Iridium", "Platinum", "Uranium"),
                correctAnswer = "Osmium",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Cambia la velocidad de la luz en diferentes medios?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué elemento tiene el número atómico 79?",
                answers = listOf("Oro", "Platino", "Plata", "Cobre"),
                correctAnswer = "Oro",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la materia oscura observable directamente con la tecnología actual?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Falso",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el gas más abundante en la atmósfera terrestre?",
                answers = listOf("Nitrógeno", "Oxígeno", "Dióxido de Carbono", "Argón"),
                correctAnswer = "Nitrógeno",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Tiene el cuerpo humano más células bacterianas que células humanas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué planeta está inclinado aproximadamente 98 grados sobre su eje?",
                answers = listOf("Urano", "Neptuno", "Venus", "Marte"),
                correctAnswer = "Urano",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el kilogramo la única unidad del SI definida por un objeto físico?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué órgano tiene la mayor superficie en el cuerpo humano?",
                answers = listOf("La Piel", "El Hígado", "Los Pulmones", "El Intestino Delgado"),
                correctAnswer = "La Piel",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Emiten radiación los agujeros negros?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el elemento natural más denso que ocurre de forma natural?",
                answers = listOf("Osmio", "Iridio", "Platino", "Uranio"),
                correctAnswer = "Osmio",
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )

        )

        private fun computers() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the CPU often referred to as the brain of the computer?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which part of the computer is responsible for processing instructions?",
                answers = listOf("CPU", "RAM", "Hard Drive", "GPU"),
                correctAnswer = "CPU",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is RAM used for temporary storage of data?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which component stores data permanently?",
                answers = listOf("Hard Drive", "RAM", "CPU", "Power Supply"),
                correctAnswer = "Hard Drive",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does a computer use binary code to process information?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which device is used to input data into a computer?",
                answers = listOf("Keyboard", "Monitor", "Speaker", "Printer"),
                correctAnswer = "Keyboard",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is an SSD faster than a traditional hard drive?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which component handles graphics processing?",
                answers = listOf("GPU", "CPU", "RAM", "Hard Drive"),
                correctAnswer = "GPU",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does the motherboard connect all the components of a computer?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which port is commonly used to connect external displays?",
                answers = listOf("HDMI", "USB", "Ethernet", "Audio Jack"),
                correctAnswer = "HDMI",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Se considera la CPU el cerebro de la computadora?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué parte de la computadora es responsable de procesar instrucciones?",
                answers = listOf("CPU", "RAM", "Disco Duro", "GPU"),
                correctAnswer = "CPU",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se utiliza la RAM para el almacenamiento temporal de datos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué componente almacena datos de forma permanente?",
                answers = listOf("Disco Duro", "RAM", "CPU", "Fuente de Alimentación"),
                correctAnswer = "Disco Duro",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Usa una computadora código binario para procesar información?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué dispositivo se utiliza para introducir datos en una computadora?",
                answers = listOf("Teclado", "Monitor", "Altavoz", "Impresora"),
                correctAnswer = "Teclado",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es un SSD más rápido que un disco duro tradicional?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué componente se encarga del procesamiento de gráficos?",
                answers = listOf("GPU", "CPU", "RAM", "Disco Duro"),
                correctAnswer = "GPU",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Conecta la placa base todos los componentes de una computadora?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué puerto se utiliza comúnmente para conectar pantallas externas?",
                answers = listOf("HDMI", "USB", "Ethernet", "Entrada de Audio"),
                correctAnswer = "HDMI",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Python considered a high-level programming language?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which company developed the Windows operating system?",
                answers = listOf("Microsoft", "Apple", "Google", "IBM"),
                correctAnswer = "Microsoft",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does an IP address uniquely identify a device on a network?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What does the acronym 'HTML' stand for?",
                answers = listOf(
                    "HyperText Markup Language",
                    "HyperText Machine Language",
                    "HyperTool Markup Language",
                    "Hyper Transfer Markup Language"
                ),
                correctAnswer = "HyperText Markup Language",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is SSD storage faster than HDD storage?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which protocol is used to securely transfer data over the internet?",
                answers = listOf("HTTPS", "HTTP", "FTP", "SMTP"),
                correctAnswer = "HTTPS",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Do firewalls protect a network from unauthorized access?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which operating system is based on Linux and developed by Google?",
                answers = listOf("Android", "Windows", "iOS", "macOS"),
                correctAnswer = "Android",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is an algorithm a step-by-step procedure for solving a problem?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which programming language is primarily used for iOS app development?",
                answers = listOf("Swift", "Java", "Python", "C++"),
                correctAnswer = "Swift",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Python considerado un lenguaje de programación de alto nivel?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué empresa desarrolló el sistema operativo Windows?",
                answers = listOf("Microsoft", "Apple", "Google", "IBM"),
                correctAnswer = "Microsoft",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Identifica una dirección IP de manera única a un dispositivo en una red?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué significa el acrónimo 'HTML'?",
                answers = listOf(
                    "Lenguaje de Marcado de Hipertexto",
                    "Lenguaje de Máquina de Hipertexto",
                    "Lenguaje de Herramientas Hipertexto",
                    "Lenguaje de Transferencia Hipertexto"
                ),
                correctAnswer = "Lenguaje de Marcado de Hipertexto",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el almacenamiento SSD más rápido que el almacenamiento HDD?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué protocolo se utiliza para transferir datos de manera segura en internet?",
                answers = listOf("HTTPS", "HTTP", "FTP", "SMTP"),
                correctAnswer = "HTTPS",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Protegen los firewalls una red del acceso no autorizado?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué sistema operativo basado en Linux fue desarrollado por Google?",
                answers = listOf("Android", "Windows", "iOS", "macOS"),
                correctAnswer = "Android",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es un algoritmo un procedimiento paso a paso para resolver un problema?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué lenguaje de programación se utiliza principalmente para desarrollar aplicaciones iOS?",
                answers = listOf("Swift", "Java", "Python", "C++"),
                correctAnswer = "Swift",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Does the Turing Test assess a machine's ability to exhibit intelligent behavior indistinguishable from a human?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which algorithm is commonly used for pathfinding and graph traversal?",
                answers = listOf("Dijkstra's Algorithm", "Bubble Sort", "Merge Sort", "Quicksort"),
                correctAnswer = "Dijkstra's Algorithm",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Big-O notation used to describe the performance of an algorithm?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What does the acronym 'DNS' stand for?",
                answers = listOf(
                    "Domain Name System",
                    "Data Network System",
                    "Distributed Name Service",
                    "Digital Network Service"
                ),
                correctAnswer = "Domain Name System",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does Moore's Law predict that the number of transistors on a microchip doubles approximately every two years?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which protocol is used to retrieve email from a server?",
                answers = listOf("IMAP", "SMTP", "FTP", "HTTP"),
                correctAnswer = "IMAP",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a quantum computer based on the principles of quantum mechanics?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which data structure uses a FIFO (First In, First Out) principle?",
                answers = listOf("Queue", "Stack", "Linked List", "Binary Tree"),
                correctAnswer = "Queue",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the RSA algorithm widely used for public-key cryptography?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which programming paradigm is based on objects and data rather than actions?",
                answers = listOf(
                    "Object-Oriented Programming",
                    "Functional Programming",
                    "Procedural Programming",
                    "Event-Driven Programming"
                ),
                correctAnswer = "Object-Oriented Programming",
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Evalúa la Prueba de Turing la capacidad de una máquina para exhibir un comportamiento inteligente indistinguible del de un humano?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué algoritmo se usa comúnmente para encontrar rutas y recorrer grafos?",
                answers = listOf(
                    "Algoritmo de Dijkstra",
                    "Ordenamiento Burbuja",
                    "Ordenamiento por Mezcla",
                    "Quicksort"
                ),
                correctAnswer = "Algoritmo de Dijkstra",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se utiliza la notación Big-O para describir el rendimiento de un algoritmo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué significa el acrónimo 'DNS'?",
                answers = listOf(
                    "Sistema de Nombres de Dominio",
                    "Sistema de Red de Datos",
                    "Servicio de Nombres Distribuidos",
                    "Servicio de Red Digital"
                ),
                correctAnswer = "Sistema de Nombres de Dominio",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Predice la Ley de Moore que el número de transistores en un microchip se duplica aproximadamente cada dos años?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué protocolo se usa para recuperar correos electrónicos de un servidor?",
                answers = listOf("IMAP", "SMTP", "FTP", "HTTP"),
                correctAnswer = "IMAP",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se basa una computadora cuántica en los principios de la mecánica cuántica?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué estructura de datos utiliza el principio FIFO (Primero en Entrar, Primero en Salir)?",
                answers = listOf("Cola", "Pila", "Lista Enlazada", "Árbol Binario"),
                correctAnswer = "Cola",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el algoritmo RSA ampliamente utilizado para la criptografía de clave pública?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué paradigma de programación se basa en objetos y datos en lugar de acciones?",
                answers = listOf(
                    "Programación Orientada a Objetos",
                    "Programación Funcional",
                    "Programación Procedural",
                    "Programación Controlada por Eventos"
                ),
                correctAnswer = "Programación Orientada a Objetos",
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun mathematics() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 2+2 equal to 4?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the square root of 16?",
                answers = listOf("4", "8", "2", "6"),
                correctAnswer = "4",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 0 an even number?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is 5 multiplied by 6?",
                answers = listOf("30", "25", "36", "20"),
                correctAnswer = "30",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the value of π approximately 3.14?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is 100 divided by 10?",
                answers = listOf("10", "5", "20", "50"),
                correctAnswer = "10",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 7 a prime number?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the sum of angles in a triangle?",
                answers = listOf("180 degrees", "90 degrees", "360 degrees", "270 degrees"),
                correctAnswer = "180 degrees",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the product of any number and 0 always 0?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the value of 9 squared?",
                answers = listOf("81", "18", "27", "72"),
                correctAnswer = "81",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es 2+2 igual a 4?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la raíz cuadrada de 16?",
                answers = listOf("4", "8", "2", "6"),
                correctAnswer = "4",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 0 un número par?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuánto es 5 multiplicado por 6?",
                answers = listOf("30", "25", "36", "20"),
                correctAnswer = "30",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el valor de π aproximadamente 3.14?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuánto es 100 dividido por 10?",
                answers = listOf("10", "5", "20", "50"),
                correctAnswer = "10",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 7 un número primo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la suma de los ángulos de un triángulo?",
                answers = listOf("180 grados", "90 grados", "360 grados", "270 grados"),
                correctAnswer = "180 grados",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el producto de cualquier número y 0 siempre 0?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el valor de 9 al cuadrado?",
                answers = listOf("81", "18", "27", "72"),
                correctAnswer = "81",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the value of √2 an irrational number?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the value of 12 squared?",
                answers = listOf("144", "122", "132", "121"),
                correctAnswer = "144",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the sum of interior angles in a pentagon 540 degrees?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the cube root of 27?",
                answers = listOf("3", "9", "2", "6"),
                correctAnswer = "3",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 121 a perfect square?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which property of addition states that a + b = b + a?",
                answers = listOf(
                    "Commutative Property",
                    "Associative Property",
                    "Distributive Property",
                    "Identity Property"
                ),
                correctAnswer = "Commutative Property",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the factorial of 0 equal to 1?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the value of log₁₀(100)?",
                answers = listOf("2", "10", "100", "1"),
                correctAnswer = "2",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 1 considered a prime number?",
                answers = listOf("True", "False"),
                correctAnswer = "False",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the derivative of x²?",
                answers = listOf("2x", "x", "x²", "2"),
                correctAnswer = "2x",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es el valor de √2 un número irracional?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el valor de 12 al cuadrado?",
                answers = listOf("144", "122", "132", "121"),
                correctAnswer = "144",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la suma de los ángulos interiores de un pentágono 540 grados?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la raíz cúbica de 27?",
                answers = listOf("3", "9", "2", "6"),
                correctAnswer = "3",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 121 un cuadrado perfecto?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué propiedad de la suma establece que a + b = b + a?",
                answers = listOf(
                    "Propiedad Conmutativa",
                    "Propiedad Asociativa",
                    "Propiedad Distributiva",
                    "Propiedad Identidad"
                ),
                correctAnswer = "Propiedad Conmutativa",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el factorial de 0 igual a 1?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el valor de log₁₀(100)?",
                answers = listOf("2", "10", "100", "1"),
                correctAnswer = "2",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se considera el 1 un número primo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Falso",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la derivada de x²?",
                answers = listOf("2x", "x", "x²", "2"),
                correctAnswer = "2x",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the Riemann Hypothesis one of the unsolved problems in mathematics?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the value of the integral of e^x?",
                answers = listOf("e^x + C", "e^(x+1)", "e^x", "1/e^x"),
                correctAnswer = "e^x + C",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is infinity a real number?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the derivative of ln(x)?",
                answers = listOf("1/x", "ln(x)", "x", "e^x"),
                correctAnswer = "1/x",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Euler's number approximately 2.718?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the sum of the infinite geometric series 1 + 1/2 + 1/4 + 1/8 + ...?",
                answers = listOf("2", "1", "Infinity", "1.5"),
                correctAnswer = "2",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a matrix with determinant 0 invertible?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which number is known as the golden ratio?",
                answers = listOf("1.618", "3.141", "2.718", "1.414"),
                correctAnswer = "1.618",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Fibonacci sequence related to the golden ratio?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the solution to the equation x² - 4 = 0?",
                answers = listOf("x = ±2", "x = 4", "x = ±4", "x = 2"),
                correctAnswer = "x = ±2",
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es la Hipótesis de Riemann uno de los problemas no resueltos en matemáticas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el valor de la integral de e^x?",
                answers = listOf("e^x + C", "e^(x+1)", "e^x", "1/e^x"),
                correctAnswer = "e^x + C",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el infinito un número real?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la derivada de ln(x)?",
                answers = listOf("1/x", "ln(x)", "x", "e^x"),
                correctAnswer = "1/x",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el número de Euler aproximadamente 2.718?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la suma de la serie geométrica infinita 1 + 1/2 + 1/4 + 1/8 + ...?",
                answers = listOf("2", "1", "Infinito", "1.5"),
                correctAnswer = "2",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es una matriz con determinante 0 invertible?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué número es conocido como el número áureo?",
                answers = listOf("1.618", "3.141", "2.718", "1.414"),
                correctAnswer = "1.618",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está la secuencia de Fibonacci relacionada con el número áureo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la solución de la ecuación x² - 4 = 0?",
                answers = listOf("x = ±2", "x = 4", "x = ±4", "x = 2"),
                correctAnswer = "x = ±2",
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )

        )

        private fun mythology() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Zeus the king of the Greek gods?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which god is associated with the sea in Greek mythology?",
                answers = listOf("Poseidon", "Hades", "Apollo", "Hermes"),
                correctAnswer = "Poseidon",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Thor a god in Norse mythology?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which goddess is known as the goddess of love in Roman mythology?",
                answers = listOf("Venus", "Minerva", "Juno", "Diana"),
                correctAnswer = "Venus",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Hades the god of the underworld in Greek mythology?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which creature has the body of a lion, wings, and the head of a man?",
                answers = listOf("Sphinx", "Chimera", "Centaur", "Minotaur"),
                correctAnswer = "Sphinx",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Athena the goddess of wisdom in Greek mythology?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which Norse god wields Mjölnir, a powerful hammer?",
                answers = listOf("Thor", "Loki", "Odin", "Balder"),
                correctAnswer = "Thor",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Minotaur part man and part bull?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which Roman god is the equivalent of Zeus in Greek mythology?",
                answers = listOf("Jupiter", "Mars", "Apollo", "Neptune"),
                correctAnswer = "Jupiter",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Zeus el rey de los dioses griegos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué dios está asociado con el mar en la mitología griega?",
                answers = listOf("Poseidón", "Hades", "Apolo", "Hermes"),
                correctAnswer = "Poseidón",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Thor un dios de la mitología nórdica?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué diosa es conocida como la diosa del amor en la mitología romana?",
                answers = listOf("Venus", "Minerva", "Juno", "Diana"),
                correctAnswer = "Venus",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Hades el dios del inframundo en la mitología griega?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué criatura tiene cuerpo de león, alas y cabeza de hombre?",
                answers = listOf("Esfinge", "Quimera", "Centauro", "Minotauro"),
                correctAnswer = "Esfinge",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Atenea la diosa de la sabiduría en la mitología griega?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué dios nórdico empuña Mjölnir, un martillo poderoso?",
                answers = listOf("Thor", "Loki", "Odín", "Balder"),
                correctAnswer = "Thor",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el Minotauro mitad hombre y mitad toro?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué dios romano es el equivalente de Zeus en la mitología griega?",
                answers = listOf("Júpiter", "Marte", "Apolo", "Neptuno"),
                correctAnswer = "Júpiter",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Loki considered a trickster god in Norse mythology?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who is the Egyptian god of the underworld?",
                answers = listOf("Osiris", "Anubis", "Ra", "Horus"),
                correctAnswer = "Osiris",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Ragnarok the prophesied end of the world in Norse mythology?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which Greek hero is known for his twelve labors?",
                answers = listOf("Heracles", "Perseus", "Achilles", "Odysseus"),
                correctAnswer = "Heracles",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does the Roman god Neptune correspond to the Greek god Poseidon?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which goddess is considered the huntress in Greek mythology?",
                answers = listOf("Artemis", "Athena", "Aphrodite", "Hera"),
                correctAnswer = "Artemis",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Hel the ruler of the realm of the dead in Norse mythology?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which creature in Greek mythology is known for turning people to stone?",
                answers = listOf("Medusa", "Cerberus", "Sphinx", "Hydra"),
                correctAnswer = "Medusa",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Yggdrasil the world tree in Norse mythology?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who is the messenger of the gods in Greek mythology?",
                answers = listOf("Hermes", "Apollo", "Zeus", "Ares"),
                correctAnswer = "Hermes",
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Loki considerado un dios embaucador en la mitología nórdica?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién es el dios egipcio del inframundo?",
                answers = listOf("Osiris", "Anubis", "Ra", "Horus"),
                correctAnswer = "Osiris",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Ragnarok el fin del mundo profetizado en la mitología nórdica?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué héroe griego es conocido por sus doce trabajos?",
                answers = listOf("Heracles", "Perseo", "Aquiles", "Ulises"),
                correctAnswer = "Heracles",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Corresponde el dios romano Neptuno al dios griego Poseidón?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué diosa es considerada la cazadora en la mitología griega?",
                answers = listOf("Artemisa", "Atenea", "Afrodita", "Hera"),
                correctAnswer = "Artemisa",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Hel la gobernante del reino de los muertos en la mitología nórdica?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué criatura de la mitología griega es conocida por convertir en piedra a las personas?",
                answers = listOf("Medusa", "Cerbero", "Esfinge", "Hidra"),
                correctAnswer = "Medusa",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Yggdrasil el árbol del mundo en la mitología nórdica?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién es el mensajero de los dioses en la mitología griega?",
                answers = listOf("Hermes", "Apolo", "Zeus", "Ares"),
                correctAnswer = "Hermes",
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun sports() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is soccer also known as football in most countries?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "How many players are on the field for one soccer team during a match?",
                answers = listOf("11", "10", "12", "9"),
                correctAnswer = "11",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is basketball played with five players on each team on the court?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which sport is known as 'America's pastime'?",
                answers = listOf("Baseball", "Basketball", "Soccer", "Tennis"),
                correctAnswer = "Baseball",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Tour de France a cycling competition?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "How many points is a touchdown worth in American football?",
                answers = listOf("6", "3", "7", "1"),
                correctAnswer = "6",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Serena Williams known for playing tennis?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which sport uses a puck instead of a ball?",
                answers = listOf("Ice Hockey", "Soccer", "Basketball", "Rugby"),
                correctAnswer = "Ice Hockey",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Olympics held every four years?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which sport is played at Wimbledon?",
                answers = listOf("Tennis", "Cricket", "Soccer", "Golf"),
                correctAnswer = "Tennis",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Se conoce el fútbol como soccer en Estados Unidos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuántos jugadores están en el campo por un equipo de fútbol durante un partido?",
                answers = listOf("11", "10", "12", "9"),
                correctAnswer = "11",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se juega el baloncesto con cinco jugadores por equipo en la cancha?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué deporte es conocido como 'el pasatiempo de América'?",
                answers = listOf("Béisbol", "Baloncesto", "Fútbol", "Tenis"),
                correctAnswer = "Béisbol",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el Tour de Francia una competición de ciclismo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuántos puntos vale un touchdown en el fútbol americano?",
                answers = listOf("6", "3", "7", "1"),
                correctAnswer = "6",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Serena Williams conocida por jugar tenis?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué deporte usa un disco en lugar de una pelota?",
                answers = listOf("Hockey sobre Hielo", "Fútbol", "Baloncesto", "Rugby"),
                correctAnswer = "Hockey sobre Hielo",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se celebran los Juegos Olímpicos cada cuatro años?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué deporte se juega en Wimbledon?",
                answers = listOf("Tenis", "Críquet", "Fútbol", "Golf"),
                correctAnswer = "Tenis",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the FIFA World Cup held every four years?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country has won the most FIFA World Cups?",
                answers = listOf("Brazil", "Germany", "Italy", "Argentina"),
                correctAnswer = "Brazil",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the NBA the top professional basketball league in the United States?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which tennis tournament is played on a clay surface?",
                answers = listOf("French Open", "Wimbledon", "US Open", "Australian Open"),
                correctAnswer = "French Open",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Formula 1 a motorsport competition?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country hosts the Tour de France?",
                answers = listOf("France", "Italy", "Spain", "Belgium"),
                correctAnswer = "France",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does cricket use a bat and ball?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which sport awards the Stanley Cup?",
                answers = listOf("Ice Hockey", "Basketball", "Baseball", "Football"),
                correctAnswer = "Ice Hockey",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a marathon exactly 42.195 kilometers?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which team sport is played on ice with skates and a puck?",
                answers = listOf("Ice Hockey", "Curling", "Speed Skating", "Field Hockey"),
                correctAnswer = "Ice Hockey",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Se celebra la Copa Mundial de la FIFA cada cuatro años?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país ha ganado más Copas Mundiales de la FIFA?",
                answers = listOf("Brasil", "Alemania", "Italia", "Argentina"),
                correctAnswer = "Brasil",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la NBA la principal liga profesional de baloncesto en los Estados Unidos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué torneo de tenis se juega sobre una superficie de arcilla?",
                answers = listOf("Roland Garros", "Wimbledon", "US Open", "Abierto de Australia"),
                correctAnswer = "Roland Garros",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la Fórmula 1 una competición de automovilismo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país organiza el Tour de Francia?",
                answers = listOf("Francia", "Italia", "España", "Bélgica"),
                correctAnswer = "Francia",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se utiliza un bate y una pelota en el cricket?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué deporte otorga la Copa Stanley?",
                answers = listOf("Hockey sobre Hielo", "Baloncesto", "Béisbol", "Fútbol"),
                correctAnswer = "Hockey sobre Hielo",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es un maratón exactamente de 42.195 kilómetros?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué deporte de equipo se juega sobre hielo con patines y un disco?",
                answers = listOf(
                    "Hockey sobre Hielo",
                    "Curling",
                    "Patinaje de Velocidad",
                    "Hockey sobre Césped"
                ),
                correctAnswer = "Hockey sobre Hielo",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Sachin Tendulkar known as the 'God of Cricket'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country won the first FIFA World Cup in 1930?",
                answers = listOf("Uruguay", "Brazil", "Germany", "Argentina"),
                correctAnswer = "Uruguay",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a triple-double a basketball achievement involving points, rebounds, and assists?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which cyclist won the most Tour de France titles before being stripped of them?",
                answers = listOf(
                    "Lance Armstrong",
                    "Eddy Merckx",
                    "Chris Froome",
                    "Bernard Hinault"
                ),
                correctAnswer = "Lance Armstrong",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Ryder Cup a golf competition between the USA and Europe?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country has the most Olympic gold medals in ice hockey?",
                answers = listOf("Canada", "Russia", "USA", "Sweden"),
                correctAnswer = "Canada",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the term 'hat trick' used in soccer to refer to scoring three goals in a match?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which boxer was known as 'The Greatest' and 'The People's Champion'?",
                answers = listOf("Muhammad Ali", "Mike Tyson", "Floyd Mayweather", "Joe Frazier"),
                correctAnswer = "Muhammad Ali",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was the first modern Olympic Games held in Athens in 1896?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country has hosted the FIFA World Cup the most times?",
                answers = listOf("Brazil", "Germany", "Italy", "Mexico"),
                correctAnswer = "Mexico",
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es conocido Sachin Tendulkar como el 'Dios del Cricket'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país ganó la primera Copa Mundial de la FIFA en 1930?",
                answers = listOf("Uruguay", "Brasil", "Alemania", "Argentina"),
                correctAnswer = "Uruguay",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es un triple-doble un logro en baloncesto que involucra puntos, rebotes y asistencias?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué ciclista ganó más títulos del Tour de Francia antes de ser despojado de ellos?",
                answers = listOf(
                    "Lance Armstrong",
                    "Eddy Merckx",
                    "Chris Froome",
                    "Bernard Hinault"
                ),
                correctAnswer = "Lance Armstrong",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la Ryder Cup una competición de golf entre EE.UU. y Europa?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país tiene más medallas de oro olímpicas en hockey sobre hielo?",
                answers = listOf("Canadá", "Rusia", "EE.UU.", "Suecia"),
                correctAnswer = "Canadá",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se usa el término 'hat trick' en el fútbol para referirse a marcar tres goles en un partido?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué boxeador era conocido como 'El Más Grande' y 'El Campeón del Pueblo'?",
                answers = listOf("Muhammad Ali", "Mike Tyson", "Floyd Mayweather", "Joe Frazier"),
                correctAnswer = "Muhammad Ali",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se celebraron los primeros Juegos Olímpicos modernos en Atenas en 1896?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país ha organizado la Copa Mundial de la FIFA más veces?",
                answers = listOf("México", "Brasil", "Alemania", "Italia"),
                correctAnswer = "México",
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
        )

        private fun geography() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Mount Everest the tallest mountain in the world?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the capital city of France?",
                answers = listOf("Paris", "Rome", "London", "Berlin"),
                correctAnswer = "Paris",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Africa the second-largest continent by land area?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which ocean is the largest?",
                answers = listOf("Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"),
                correctAnswer = "Pacific Ocean",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Australia both a country and a continent?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the capital city of Japan?",
                answers = listOf("Tokyo", "Kyoto", "Osaka", "Nagoya"),
                correctAnswer = "Tokyo",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Amazon River the longest river in the world?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country has the most people?",
                answers = listOf("China", "India", "United States", "Indonesia"),
                correctAnswer = "China",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Sahara Desert located in Africa?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country is known as the 'Land of the Rising Sun'?",
                answers = listOf("Japan", "China", "South Korea", "Thailand"),
                correctAnswer = "Japan",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es el Monte Everest la montaña más alta del mundo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la capital de Francia?",
                answers = listOf("París", "Roma", "Londres", "Berlín"),
                correctAnswer = "París",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es África el segundo continente más grande por área?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el océano más grande?",
                answers = listOf(
                    "Océano Pacífico",
                    "Océano Atlántico",
                    "Océano Índico",
                    "Océano Ártico"
                ),
                correctAnswer = "Océano Pacífico",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Australia tanto un país como un continente?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la capital de Japón?",
                answers = listOf("Tokio", "Kioto", "Osaka", "Nagoya"),
                correctAnswer = "Tokio",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el río Amazonas el río más largo del mundo?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país tiene la mayor población?",
                answers = listOf("China", "India", "Estados Unidos", "Indonesia"),
                correctAnswer = "China",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está el desierto del Sahara ubicado en África?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país es conocido como la 'Tierra del Sol Naciente'?",
                answers = listOf("Japón", "China", "Corea del Sur", "Tailandia"),
                correctAnswer = "Japón",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the Nile River longer than the Amazon River?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country is the largest by land area?",
                answers = listOf("Russia", "Canada", "China", "United States"),
                correctAnswer = "Russia",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the capital city of Australia Sydney?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country has the most volcanoes?",
                answers = listOf("Indonesia", "Japan", "United States", "Philippines"),
                correctAnswer = "Indonesia",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does the equator pass through Brazil?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the largest desert in the world?",
                answers = listOf(
                    "Sahara Desert",
                    "Antarctic Desert",
                    "Arctic Desert",
                    "Gobi Desert"
                ),
                correctAnswer = "Antarctic Desert",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Caspian Sea the largest enclosed inland body of water on Earth?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which mountain range separates Europe from Asia?",
                answers = listOf("Ural Mountains", "Alps", "Andes", "Rocky Mountains"),
                correctAnswer = "Ural Mountains",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Iceland located on the Mid-Atlantic Ridge?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which river flows through Paris?",
                answers = listOf("Seine", "Rhine", "Danube", "Thames"),
                correctAnswer = "Seine",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es el río Nilo más largo que el río Amazonas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país es el más grande por área terrestre?",
                answers = listOf("Rusia", "Canadá", "China", "Estados Unidos"),
                correctAnswer = "Rusia",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Sídney la capital de Australia?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país tiene más volcanes?",
                answers = listOf("Indonesia", "Japón", "Estados Unidos", "Filipinas"),
                correctAnswer = "Indonesia",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Pasa el ecuador por Brasil?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el desierto más grande del mundo?",
                answers = listOf(
                    "Desierto del Sahara",
                    "Desierto de la Antártida",
                    "Desierto Ártico",
                    "Desierto del Gobi"
                ),
                correctAnswer = "Desierto de la Antártida",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el Mar Caspio el cuerpo de agua interior cerrado más grande de la Tierra?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué cordillera separa Europa de Asia?",
                answers = listOf("Montes Urales", "Alpes", "Andes", "Montañas Rocosas"),
                correctAnswer = "Montes Urales",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está Islandia ubicada en la dorsal mesoatlántica?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué río pasa por París?",
                answers = listOf("Sena", "Rin", "Danubio", "Támesis"),
                correctAnswer = "Sena",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the Mariana Trench the deepest oceanic trench in the world?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country has the longest coastline in the world?",
                answers = listOf("Canada", "Russia", "Australia", "United States"),
                correctAnswer = "Canada",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Lake Baikal the deepest freshwater lake in the world?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which is the smallest country by land area?",
                answers = listOf("Vatican City", "Monaco", "San Marino", "Liechtenstein"),
                correctAnswer = "Vatican City",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does the equator pass through Kenya?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which river forms part of the border between the United States and Mexico?",
                answers = listOf(
                    "Rio Grande",
                    "Mississippi River",
                    "Colorado River",
                    "Hudson River"
                ),
                correctAnswer = "Rio Grande",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Atacama Desert the driest desert in the world?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which is the tallest waterfall in the world?",
                answers = listOf("Angel Falls", "Niagara Falls", "Victoria Falls", "Iguazu Falls"),
                correctAnswer = "Angel Falls",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Does the Tropic of Capricorn pass through Australia?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which island is the largest by land area?",
                answers = listOf("Greenland", "New Guinea", "Borneo", "Madagascar"),
                correctAnswer = "Greenland",
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es la Fosa de las Marianas la fosa oceánica más profunda del mundo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país tiene la costa más larga del mundo?",
                answers = listOf("Canadá", "Rusia", "Australia", "Estados Unidos"),
                correctAnswer = "Canadá",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el lago Baikal el lago de agua dulce más profundo del mundo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el país más pequeño por área terrestre?",
                answers = listOf("Ciudad del Vaticano", "Mónaco", "San Marino", "Liechtenstein"),
                correctAnswer = "Ciudad del Vaticano",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Pasa el ecuador por Kenia?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué río forma parte de la frontera entre Estados Unidos y México?",
                answers = listOf("Río Grande", "Río Mississippi", "Río Colorado", "Río Hudson"),
                correctAnswer = "Río Grande",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el desierto de Atacama el desierto más seco del mundo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la cascada más alta del mundo?",
                answers = listOf(
                    "Salto Ángel",
                    "Cataratas del Niágara",
                    "Cataratas Victoria",
                    "Cataratas del Iguazú"
                ),
                correctAnswer = "Salto Ángel",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Pasa el Trópico de Capricornio por Australia?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué isla es la más grande por área terrestre?",
                answers = listOf("Groenlandia", "Nueva Guinea", "Borneo", "Madagascar"),
                correctAnswer = "Groenlandia",
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun history() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Was the Great Wall of China built to protect against invasions?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who was the first President of the United States?",
                answers = listOf(
                    "George Washington",
                    "Thomas Jefferson",
                    "Abraham Lincoln",
                    "John Adams"
                ),
                correctAnswer = "George Washington",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did the Titanic sink in 1912?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which empire was known for building the Colosseum?",
                answers = listOf(
                    "Roman Empire",
                    "Greek Empire",
                    "Byzantine Empire",
                    "Ottoman Empire"
                ),
                correctAnswer = "Roman Empire",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was World War II fought between 1939 and 1945?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who discovered America in 1492?",
                answers = listOf(
                    "Christopher Columbus",
                    "Ferdinand Magellan",
                    "Vasco da Gama",
                    "Hernán Cortés"
                ),
                correctAnswer = "Christopher Columbus",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was the Declaration of Independence signed in 1776?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who was known as the 'Iron Lady'?",
                answers = listOf(
                    "Margaret Thatcher",
                    "Angela Merkel",
                    "Queen Elizabeth II",
                    "Golda Meir"
                ),
                correctAnswer = "Margaret Thatcher",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did Neil Armstrong walk on the moon in 1969?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who was the first emperor of Rome?",
                answers = listOf("Augustus", "Julius Caesar", "Nero", "Caligula"),
                correctAnswer = "Augustus",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Se construyó la Gran Muralla China para protegerse contra invasiones?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién fue el primer presidente de los Estados Unidos?",
                answers = listOf(
                    "George Washington",
                    "Thomas Jefferson",
                    "Abraham Lincoln",
                    "John Adams"
                ),
                correctAnswer = "George Washington",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se hundió el Titanic en 1912?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué imperio fue conocido por construir el Coliseo?",
                answers = listOf(
                    "Imperio Romano",
                    "Imperio Griego",
                    "Imperio Bizantino",
                    "Imperio Otomano"
                ),
                correctAnswer = "Imperio Romano",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se luchó la Segunda Guerra Mundial entre 1939 y 1945?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién descubrió América en 1492?",
                answers = listOf(
                    "Cristóbal Colón",
                    "Fernando de Magallanes",
                    "Vasco da Gama",
                    "Hernán Cortés"
                ),
                correctAnswer = "Cristóbal Colón",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se firmó la Declaración de Independencia en 1776?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién fue conocida como la 'Dama de Hierro'?",
                answers = listOf(
                    "Margaret Thatcher",
                    "Angela Merkel",
                    "Reina Isabel II",
                    "Golda Meir"
                ),
                correctAnswer = "Margaret Thatcher",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Caminó Neil Armstrong en la luna en 1969?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién fue el primer emperador de Roma?",
                answers = listOf("Augusto", "Julio César", "Nerón", "Calígula"),
                correctAnswer = "Augusto",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Was the American Civil War fought between 1861 and 1865?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which treaty ended World War I?",
                answers = listOf(
                    "Treaty of Versailles",
                    "Treaty of Paris",
                    "Treaty of Tordesillas",
                    "Treaty of Utrecht"
                ),
                correctAnswer = "Treaty of Versailles",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did the Cold War end in 1991?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who was the leader of the Soviet Union during World War II?",
                answers = listOf(
                    "Joseph Stalin",
                    "Vladimir Lenin",
                    "Mikhail Gorbachev",
                    "Nikita Khrushchev"
                ),
                correctAnswer = "Joseph Stalin",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was the Berlin Wall built in 1961?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which war was fought between Athens and Sparta in Ancient Greece?",
                answers = listOf("Peloponnesian War", "Persian Wars", "Trojan War", "Punic Wars"),
                correctAnswer = "Peloponnesian War",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did the French Revolution begin in 1789?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who was the British Prime Minister during most of World War II?",
                answers = listOf(
                    "Winston Churchill",
                    "Neville Chamberlain",
                    "Clement Attlee",
                    "Anthony Eden"
                ),
                correctAnswer = "Winston Churchill",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was the United Nations founded in 1945?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which empire was known for its road network and engineering feats?",
                answers = listOf(
                    "Roman Empire",
                    "Persian Empire",
                    "Mongol Empire",
                    "Ottoman Empire"
                ),
                correctAnswer = "Roman Empire",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Se luchó la Guerra Civil Estadounidense entre 1861 y 1865?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué tratado puso fin a la Primera Guerra Mundial?",
                answers = listOf(
                    "Tratado de Versalles",
                    "Tratado de París",
                    "Tratado de Tordesillas",
                    "Tratado de Utrecht"
                ),
                correctAnswer = "Tratado de Versalles",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Terminó la Guerra Fría en 1991?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién fue el líder de la Unión Soviética durante la Segunda Guerra Mundial?",
                answers = listOf(
                    "Joseph Stalin",
                    "Vladimir Lenin",
                    "Mijaíl Gorbachov",
                    "Nikita Jrushchov"
                ),
                correctAnswer = "Joseph Stalin",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se construyó el Muro de Berlín en 1961?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué guerra se libró entre Atenas y Esparta en la Antigua Grecia?",
                answers = listOf(
                    "Guerra del Peloponeso",
                    "Guerras Persas",
                    "Guerra de Troya",
                    "Guerras Púnicas"
                ),
                correctAnswer = "Guerra del Peloponeso",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Comenzó la Revolución Francesa en 1789?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién fue el primer ministro británico durante la mayor parte de la Segunda Guerra Mundial?",
                answers = listOf(
                    "Winston Churchill",
                    "Neville Chamberlain",
                    "Clement Attlee",
                    "Anthony Eden"
                ),
                correctAnswer = "Winston Churchill",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se fundó la Organización de las Naciones Unidas en 1945?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué imperio fue conocido por su red de caminos y logros de ingeniería?",
                answers = listOf(
                    "Imperio Romano",
                    "Imperio Persa",
                    "Imperio Mongol",
                    "Imperio Otomano"
                ),
                correctAnswer = "Imperio Romano",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Was the Byzantine Empire also known as the Eastern Roman Empire?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which war was ended by the Treaty of Westphalia in 1648?",
                answers = listOf(
                    "Thirty Years' War",
                    "Hundred Years' War",
                    "Seven Years' War",
                    "War of the Roses"
                ),
                correctAnswer = "Thirty Years' War",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did the Mongol Empire span from Europe to Asia?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who was the ruler of France during the Napoleonic Wars?",
                answers = listOf(
                    "Napoleon Bonaparte",
                    "Louis XIV",
                    "Charles de Gaulle",
                    "Louis XVI"
                ),
                correctAnswer = "Napoleon Bonaparte",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was the Berlin Conference held to divide Africa among European powers?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which empire was founded by Genghis Khan?",
                answers = listOf(
                    "Mongol Empire",
                    "Ottoman Empire",
                    "Byzantine Empire",
                    "Roman Empire"
                ),
                correctAnswer = "Mongol Empire",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did the Industrial Revolution begin in Great Britain?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who signed the Edict of Milan granting religious tolerance to Christians?",
                answers = listOf("Constantine the Great", "Julius Caesar", "Augustus", "Nero"),
                correctAnswer = "Constantine the Great",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was the Ottoman Empire dissolved after World War I?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which ancient civilization built the city of Machu Picchu?",
                answers = listOf("Inca", "Aztec", "Maya", "Olmec"),
                correctAnswer = "Inca",
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Fue el Imperio Bizantino también conocido como el Imperio Romano de Oriente?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué guerra terminó con el Tratado de Westfalia en 1648?",
                answers = listOf(
                    "Guerra de los Treinta Años",
                    "Guerra de los Cien Años",
                    "Guerra de los Siete Años",
                    "Guerra de las Rosas"
                ),
                correctAnswer = "Guerra de los Treinta Años",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se extendió el Imperio Mongol desde Europa hasta Asia?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién fue el gobernante de Francia durante las Guerras Napoleónicas?",
                answers = listOf("Napoleón Bonaparte", "Luis XIV", "Charles de Gaulle", "Luis XVI"),
                correctAnswer = "Napoleón Bonaparte",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se celebró la Conferencia de Berlín para dividir África entre las potencias europeas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué imperio fue fundado por Gengis Kan?",
                answers = listOf(
                    "Imperio Mongol",
                    "Imperio Otomano",
                    "Imperio Bizantino",
                    "Imperio Romano"
                ),
                correctAnswer = "Imperio Mongol",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Comenzó la Revolución Industrial en Gran Bretaña?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién firmó el Edicto de Milán que concedía tolerancia religiosa a los cristianos?",
                answers = listOf("Constantino el Grande", "Julio César", "Augusto", "Nerón"),
                correctAnswer = "Constantino el Grande",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se disolvió el Imperio Otomano después de la Primera Guerra Mundial?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué civilización antigua construyó la ciudad de Machu Picchu?",
                answers = listOf("Inca", "Azteca", "Maya", "Olmeca"),
                correctAnswer = "Inca",
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun politics() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the United States a federal republic?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who is known as the 'Father of the United States Constitution'?",
                answers = listOf(
                    "James Madison",
                    "George Washington",
                    "Thomas Jefferson",
                    "John Adams"
                ),
                correctAnswer = "James Madison",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the United Nations headquartered in New York City?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who was the first President of South Africa after apartheid?",
                answers = listOf("Nelson Mandela", "Jacob Zuma", "Thabo Mbeki", "F. W. de Klerk"),
                correctAnswer = "Nelson Mandela",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the European Union a political and economic union?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who was the first female Prime Minister of the United Kingdom?",
                answers = listOf(
                    "Margaret Thatcher",
                    "Theresa May",
                    "Angela Merkel",
                    "Jacinda Ardern"
                ),
                correctAnswer = "Margaret Thatcher",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the World Trade Organization (WTO) responsible for regulating international trade?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country has a President and a Prime Minister?",
                answers = listOf("France", "United States", "United Kingdom", "Canada"),
                correctAnswer = "France",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is voting a fundamental right in democratic societies?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country is known for the 'Westminster System' of government?",
                answers = listOf("United Kingdom", "United States", "France", "Germany"),
                correctAnswer = "United Kingdom",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Estados Unidos una república federal?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién es conocido como el 'Padre de la Constitución de los Estados Unidos'?",
                answers = listOf(
                    "James Madison",
                    "George Washington",
                    "Thomas Jefferson",
                    "John Adams"
                ),
                correctAnswer = "James Madison",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Tiene su sede la Organización de las Naciones Unidas en la ciudad de Nueva York?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién fue el primer presidente de Sudáfrica después del apartheid?",
                answers = listOf("Nelson Mandela", "Jacob Zuma", "Thabo Mbeki", "F. W. de Klerk"),
                correctAnswer = "Nelson Mandela",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la Unión Europea una unión política y económica?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién fue la primera mujer en ser primera ministra del Reino Unido?",
                answers = listOf(
                    "Margaret Thatcher",
                    "Theresa May",
                    "Angela Merkel",
                    "Jacinda Ardern"
                ),
                correctAnswer = "Margaret Thatcher",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la Organización Mundial del Comercio (OMC) responsable de regular el comercio internacional?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país tiene un Presidente y un Primer Ministro?",
                answers = listOf("Francia", "Estados Unidos", "Reino Unido", "Canadá"),
                correctAnswer = "Francia",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el voto un derecho fundamental en las sociedades democráticas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país es conocido por el 'Sistema de Westminster' de gobierno?",
                answers = listOf("Reino Unido", "Estados Unidos", "Francia", "Alemania"),
                correctAnswer = "Reino Unido",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the House of Lords part of the UK Parliament?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country has a chancellor as its head of government?",
                answers = listOf("Germany", "Austria", "Switzerland", "Belgium"),
                correctAnswer = "Germany",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the International Monetary Fund (IMF) focused on global financial stability?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who was the first female Prime Minister of India?",
                answers = listOf(
                    "Indira Gandhi",
                    "Sonia Gandhi",
                    "Pratibha Patil",
                    "Sarojini Naidu"
                ),
                correctAnswer = "Indira Gandhi",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is NATO a military alliance founded in 1949?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country is led by a Taoiseach as its head of government?",
                answers = listOf("Ireland", "Iceland", "Denmark", "Norway"),
                correctAnswer = "Ireland",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Magna Carta a key document in British constitutional history?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country uses the term 'Duma' for its legislative assembly?",
                answers = listOf("Russia", "Ukraine", "Belarus", "Kazakhstan"),
                correctAnswer = "Russia",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the term 'checks and balances' used to describe a system of separation of powers?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country has a Prime Minister and a ceremonial Emperor?",
                answers = listOf("Japan", "United Kingdom", "Thailand", "Spain"),
                correctAnswer = "Japan",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es la Cámara de los Lores parte del Parlamento del Reino Unido?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país tiene un canciller como jefe de gobierno?",
                answers = listOf("Alemania", "Austria", "Suiza", "Bélgica"),
                correctAnswer = "Alemania",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se enfoca el Fondo Monetario Internacional (FMI) en la estabilidad financiera global?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién fue la primera mujer en ser Primera Ministra de la India?",
                answers = listOf(
                    "Indira Gandhi",
                    "Sonia Gandhi",
                    "Pratibha Patil",
                    "Sarojini Naidu"
                ),
                correctAnswer = "Indira Gandhi",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la OTAN una alianza militar fundada en 1949?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país está dirigido por un Taoiseach como jefe de gobierno?",
                answers = listOf("Irlanda", "Islandia", "Dinamarca", "Noruega"),
                correctAnswer = "Irlanda",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la Carta Magna un documento clave en la historia constitucional británica?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país usa el término 'Duma' para su asamblea legislativa?",
                answers = listOf("Rusia", "Ucrania", "Bielorrusia", "Kazajistán"),
                correctAnswer = "Rusia",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se utiliza el término 'pesos y contrapesos' para describir un sistema de separación de poderes?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país tiene un Primer Ministro y un Emperador ceremonial?",
                answers = listOf("Japón", "Reino Unido", "Tailandia", "España"),
                correctAnswer = "Japón",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Was the Treaty of Westphalia signed in 1648?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which political philosopher wrote 'The Social Contract'?",
                answers = listOf(
                    "Jean-Jacques Rousseau",
                    "John Locke",
                    "Thomas Hobbes",
                    "Karl Marx"
                ),
                correctAnswer = "Jean-Jacques Rousseau",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the International Criminal Court (ICC) located in The Hague?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country was led by Otto von Bismarck as its first Chancellor?",
                answers = listOf("Germany", "Austria", "France", "Prussia"),
                correctAnswer = "Germany",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was the Congress of Vienna held to redraw Europe's political boundaries after Napoleon's defeat?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which country adopted the Meiji Constitution in 1889?",
                answers = listOf("Japan", "China", "Korea", "Vietnam"),
                correctAnswer = "Japan",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the term 'glasnost' associated with political openness in the Soviet Union?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which treaty established the European Union?",
                answers = listOf(
                    "Maastricht Treaty",
                    "Treaty of Lisbon",
                    "Treaty of Rome",
                    "Treaty of Paris"
                ),
                correctAnswer = "Maastricht Treaty",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Did the Warsaw Pact serve as a military alliance for the Eastern Bloc during the Cold War?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who wrote 'The Communist Manifesto'?",
                answers = listOf(
                    "Karl Marx and Friedrich Engels",
                    "Vladimir Lenin and Joseph Stalin",
                    "Mao Zedong and Zhou Enlai",
                    "Rosa Luxemburg and Leon Trotsky"
                ),
                correctAnswer = "Karl Marx and Friedrich Engels",
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Se firmó el Tratado de Westfalia en 1648?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué filósofo político escribió 'El Contrato Social'?",
                answers = listOf(
                    "Jean-Jacques Rousseau",
                    "John Locke",
                    "Thomas Hobbes",
                    "Karl Marx"
                ),
                correctAnswer = "Jean-Jacques Rousseau",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está ubicada la Corte Penal Internacional (CPI) en La Haya?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país fue liderado por Otto von Bismarck como su primer canciller?",
                answers = listOf("Alemania", "Austria", "Francia", "Prusia"),
                correctAnswer = "Alemania",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se celebró el Congreso de Viena para redibujar las fronteras políticas de Europa tras la derrota de Napoleón?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué país adoptó la Constitución Meiji en 1889?",
                answers = listOf("Japón", "China", "Corea", "Vietnam"),
                correctAnswer = "Japón",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está asociado el término 'glasnost' con la apertura política en la Unión Soviética?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué tratado estableció la Unión Europea?",
                answers = listOf(
                    "Tratado de Maastricht",
                    "Tratado de Lisboa",
                    "Tratado de Roma",
                    "Tratado de París"
                ),
                correctAnswer = "Tratado de Maastricht",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Sirvió el Pacto de Varsovia como una alianza militar del Bloque del Este durante la Guerra Fría?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién escribió 'El Manifiesto Comunista'?",
                answers = listOf(
                    "Karl Marx y Friedrich Engels",
                    "Vladimir Lenin y Joseph Stalin",
                    "Mao Zedong y Zhou Enlai",
                    "Rosa Luxemburg y León Trotsky"
                ),
                correctAnswer = "Karl Marx y Friedrich Engels",
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun art() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Leonardo da Vinci famous for painting the Mona Lisa?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who painted the Sistine Chapel ceiling?",
                answers = listOf(
                    "Michelangelo",
                    "Leonardo da Vinci",
                    "Raphael",
                    "Vincent van Gogh"
                ),
                correctAnswer = "Michelangelo",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Statue of Liberty located in New York?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which artist is famous for his work 'The Persistence of Memory'?",
                answers = listOf("Salvador Dalí", "Pablo Picasso", "Claude Monet", "Edvard Munch"),
                correctAnswer = "Salvador Dalí",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Guernica' a famous painting by Pablo Picasso?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What type of art is Pablo Picasso most associated with?",
                answers = listOf("Cubism", "Surrealism", "Impressionism", "Realism"),
                correctAnswer = "Cubism",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the 'Mona Lisa' housed in the Louvre Museum in Paris?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which famous artist is known for painting 'Starry Night'?",
                answers = listOf(
                    "Vincent van Gogh",
                    "Claude Monet",
                    "Leonardo da Vinci",
                    "Edvard Munch"
                ),
                correctAnswer = "Vincent van Gogh",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Louvre Museum located in London?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which painter is famous for the artwork 'The Birth of Venus'?",
                answers = listOf(
                    "Sandro Botticelli",
                    "Michelangelo",
                    "Leonardo da Vinci",
                    "Raphael"
                ),
                correctAnswer = "Sandro Botticelli",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Leonardo da Vinci famoso por pintar la Mona Lisa?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién pintó el techo de la Capilla Sixtina?",
                answers = listOf("Miguel Ángel", "Leonardo da Vinci", "Rafael", "Vincent van Gogh"),
                correctAnswer = "Miguel Ángel",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está la Estatua de la Libertad en Nueva York?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué artista es famoso por su obra 'La persistencia de la memoria'?",
                answers = listOf("Salvador Dalí", "Pablo Picasso", "Claude Monet", "Edvard Munch"),
                correctAnswer = "Salvador Dalí",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Guernica' una pintura famosa de Pablo Picasso?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Con qué tipo de arte está más asociado Pablo Picasso?",
                answers = listOf("Cubismo", "Surrealismo", "Impresionismo", "Realismo"),
                correctAnswer = "Cubismo",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está la 'Mona Lisa' en el Museo del Louvre en París?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué famoso pintor es conocido por la obra 'La Noche Estrellada'?",
                answers = listOf(
                    "Vincent van Gogh",
                    "Claude Monet",
                    "Leonardo da Vinci",
                    "Edvard Munch"
                ),
                correctAnswer = "Vincent van Gogh",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está el Museo del Louvre ubicado en Londres?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué pintor es famoso por la obra 'El nacimiento de Venus'?",
                answers = listOf(
                    "Sandro Botticelli",
                    "Miguel Ángel",
                    "Leonardo da Vinci",
                    "Rafael"
                ),
                correctAnswer = "Sandro Botticelli",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Was Vincent van Gogh born in the Netherlands?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which artist is famous for the painting 'The Starry Night'?",
                answers = listOf(
                    "Vincent van Gogh",
                    "Pablo Picasso",
                    "Claude Monet",
                    "Salvador Dalí"
                ),
                correctAnswer = "Vincent van Gogh",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Louvre Museum located in Paris?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who painted 'The Persistence of Memory'?",
                answers = listOf("Salvador Dalí", "Pablo Picasso", "Claude Monet", "Edvard Munch"),
                correctAnswer = "Salvador Dalí",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Mona Lisa displayed in the Uffizi Gallery?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which artist is famous for the creation of the Sistine Chapel ceiling?",
                answers = listOf("Michelangelo", "Leonardo da Vinci", "Raphael", "Donatello"),
                correctAnswer = "Michelangelo",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was the 'David' sculpture created by Michelangelo?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which famous painting is associated with the Italian artist Leonardo da Vinci?",
                answers = listOf(
                    "Mona Lisa",
                    "The Scream",
                    "The Birth of Venus",
                    "The Night Watch"
                ),
                correctAnswer = "Mona Lisa",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Guernica' by Pablo Picasso a painting that symbolizes the horrors of war?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which style of art is Pablo Picasso associated with?",
                answers = listOf("Cubism", "Impressionism", "Surrealism", "Expressionism"),
                correctAnswer = "Cubism",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Nació Vincent van Gogh en los Países Bajos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué artista es famoso por la pintura 'La Noche Estrellada'?",
                answers = listOf(
                    "Vincent van Gogh",
                    "Pablo Picasso",
                    "Claude Monet",
                    "Salvador Dalí"
                ),
                correctAnswer = "Vincent van Gogh",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está el Museo del Louvre ubicado en París?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién pintó 'La Persistencia de la Memoria'?",
                answers = listOf("Salvador Dalí", "Pablo Picasso", "Claude Monet", "Edvard Munch"),
                correctAnswer = "Salvador Dalí",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está la Mona Lisa expuesta en la Galería Uffizi?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién es el artista famoso por la creación del techo de la Capilla Sixtina?",
                answers = listOf("Miguel Ángel", "Leonardo da Vinci", "Rafael", "Donatello"),
                correctAnswer = "Miguel Ángel",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue la escultura 'David' creada por Miguel Ángel?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué famosa pintura está asociada con el artista italiano Leonardo da Vinci?",
                answers = listOf(
                    "La Mona Lisa",
                    "El Grito",
                    "El Nacimiento de Venus",
                    "La Ronda de Noche"
                ),
                correctAnswer = "La Mona Lisa",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Guernica' de Pablo Picasso una pintura que simboliza los horrores de la guerra?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Con qué estilo artístico está asociado Pablo Picasso?",
                answers = listOf("Cubismo", "Impresionismo", "Surrealismo", "Expresionismo"),
                correctAnswer = "Cubismo",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the painting 'The Night Watch' by Rembrandt housed in the Rijksmuseum?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which artist is known for painting 'The Birth of Venus'?",
                answers = listOf(
                    "Sandro Botticelli",
                    "Leonardo da Vinci",
                    "Raphael",
                    "Michelangelo"
                ),
                correctAnswer = "Sandro Botticelli",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Salvador Dalí's work considered part of the Surrealist movement?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which artist painted the famous 'School of Athens' fresco?",
                answers = listOf("Raphael", "Michelangelo", "Leonardo da Vinci", "Caravaggio"),
                correctAnswer = "Raphael",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was the Mona Lisa painted by Pablo Picasso?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which style of art is associated with abstract expressionism?",
                answers = listOf(
                    "Abstract Expressionism",
                    "Impressionism",
                    "Baroque",
                    "Surrealism"
                ),
                correctAnswer = "Abstract Expressionism",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Persistence of Memory' by Salvador Dalí a surrealist painting?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who painted the 'Sistine Chapel Ceiling'?",
                answers = listOf("Michelangelo", "Leonardo da Vinci", "Raphael", "Donatello"),
                correctAnswer = "Michelangelo",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Last Supper' an artwork by Leonardo da Vinci?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which famous Spanish artist is known for 'Guernica'?",
                answers = listOf("Pablo Picasso", "Salvador Dalí", "Frida Kahlo", "Diego Rivera"),
                correctAnswer = "Pablo Picasso",
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Está la pintura 'La Ronda de Noche' de Rembrandt en el Rijksmuseum?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué artista es conocido por pintar 'El Nacimiento de Venus'?",
                answers = listOf(
                    "Sandro Botticelli",
                    "Leonardo da Vinci",
                    "Rafael",
                    "Miguel Ángel"
                ),
                correctAnswer = "Sandro Botticelli",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se considera la obra de Salvador Dalí parte del movimiento surrealista?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué artista pintó el famoso fresco 'La Escuela de Atenas'?",
                answers = listOf("Rafael", "Miguel Ángel", "Leonardo da Vinci", "Caravaggio"),
                correctAnswer = "Rafael",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Pintó Pablo Picasso la Mona Lisa?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Con qué estilo artístico se asocia el expresionismo abstracto?",
                answers = listOf(
                    "Expresionismo abstracto",
                    "Impresionismo",
                    "Barroco",
                    "Surrealismo"
                ),
                correctAnswer = "Expresionismo abstracto",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'La Persistencia de la Memoria' de Salvador Dalí una pintura surrealista?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién pintó el 'Techo de la Capilla Sixtina'?",
                answers = listOf("Miguel Ángel", "Leonardo da Vinci", "Rafael", "Donatello"),
                correctAnswer = "Miguel Ángel",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'La Última Cena' una obra de arte de Leonardo da Vinci?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué famoso artista español es conocido por 'Guernica'?",
                answers = listOf("Pablo Picasso", "Salvador Dalí", "Frida Kahlo", "Diego Rivera"),
                correctAnswer = "Pablo Picasso",
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )

        )

        private fun celebrities() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Beyoncé known for her solo career in music?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which actor starred in the movie 'Titanic'?",
                answers = listOf("Leonardo DiCaprio", "Johnny Depp", "Brad Pitt", "Tom Hanks"),
                correctAnswer = "Leonardo DiCaprio",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Angelina Jolie known for her humanitarian work?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which famous athlete is known as 'The Greatest of All Time' (GOAT) in boxing?",
                answers = listOf(
                    "Muhammad Ali",
                    "Mike Tyson",
                    "Floyd Mayweather",
                    "Manny Pacquiao"
                ),
                correctAnswer = "Muhammad Ali",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Brad Pitt known for his role in the movie 'Fight Club'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which actress is known for playing 'Hermione Granger' in the Harry Potter series?",
                answers = listOf(
                    "Emma Watson",
                    "Natalie Portman",
                    "Keira Knightley",
                    "Anne Hathaway"
                ),
                correctAnswer = "Emma Watson",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Taylor Swift primarily known as a country singer?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which actor played 'Iron Man' in the Marvel Cinematic Universe?",
                answers = listOf(
                    "Robert Downey Jr.",
                    "Chris Hemsworth",
                    "Chris Evans",
                    "Mark Ruffalo"
                ),
                correctAnswer = "Robert Downey Jr.",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Jennifer Lopez known as 'J-Lo'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which famous celebrity is married to Victoria Beckham?",
                answers = listOf("David Beckham", "Tom Cruise", "Brad Pitt", "Johnny Depp"),
                correctAnswer = "David Beckham",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Beyoncé conocida por su carrera en solitario en la música?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué actor protagonizó la película 'Titanic'?",
                answers = listOf("Leonardo DiCaprio", "Johnny Depp", "Brad Pitt", "Tom Hanks"),
                correctAnswer = "Leonardo DiCaprio",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Angelina Jolie conocida por su trabajo humanitario?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué atleta famoso es conocido como 'El Mejor de Todos los Tiempos' (GOAT) en boxeo?",
                answers = listOf(
                    "Muhammad Ali",
                    "Mike Tyson",
                    "Floyd Mayweather",
                    "Manny Pacquiao"
                ),
                correctAnswer = "Muhammad Ali",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Brad Pitt conocido por su papel en la película 'El Club de la Pelea'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué actriz es conocida por interpretar a 'Hermione Granger' en la serie de Harry Potter?",
                answers = listOf(
                    "Emma Watson",
                    "Natalie Portman",
                    "Keira Knightley",
                    "Anne Hathaway"
                ),
                correctAnswer = "Emma Watson",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Taylor Swift principalmente conocida como cantante de música country?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué actor interpretó a 'Iron Man' en el Universo Cinematográfico de Marvel?",
                answers = listOf(
                    "Robert Downey Jr.",
                    "Chris Hemsworth",
                    "Chris Evans",
                    "Mark Ruffalo"
                ),
                correctAnswer = "Robert Downey Jr.",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Jennifer Lopez conocida como 'J-Lo'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué famoso celebrity está casado con Victoria Beckham?",
                answers = listOf("David Beckham", "Tom Cruise", "Brad Pitt", "Johnny Depp"),
                correctAnswer = "David Beckham",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Taylor Swift known for her music career in pop and country?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which actor starred in the 'Pirates of the Caribbean' movie series?",
                answers = listOf("Johnny Depp", "Brad Pitt", "Leonardo DiCaprio", "Will Smith"),
                correctAnswer = "Johnny Depp",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Emma Watson best known for her role as Hermione Granger in the Harry Potter films?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which musician is known as the 'King of Pop'?",
                answers = listOf("Michael Jackson", "Elvis Presley", "Prince", "Freddie Mercury"),
                correctAnswer = "Michael Jackson",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Leonardo DiCaprio known for his roles in dramas like 'Titanic' and 'The Revenant'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which celebrity was known for the iconic role of 'The Terminator'?",
                answers = listOf(
                    "Arnold Schwarzenegger",
                    "Sylvester Stallone",
                    "Bruce Willis",
                    "Tom Cruise"
                ),
                correctAnswer = "Arnold Schwarzenegger",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Rihanna known for her music as well as her fashion and business ventures?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which celebrity is often referred to as 'The Rock'?",
                answers = listOf(
                    "Dwayne Johnson",
                    "Chris Hemsworth",
                    "Tom Hiddleston",
                    "Jason Statham"
                ),
                correctAnswer = "Dwayne Johnson",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Oprah Winfrey known for being a talk show host and philanthropist?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which singer is famous for the song 'Like a Prayer'?",
                answers = listOf("Madonna", "Beyoncé", "Lady Gaga", "Ariana Grande"),
                correctAnswer = "Madonna",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Taylor Swift conocida por su carrera musical en pop y country?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué actor protagonizó la serie de películas 'Piratas del Caribe'?",
                answers = listOf("Johnny Depp", "Brad Pitt", "Leonardo DiCaprio", "Will Smith"),
                correctAnswer = "Johnny Depp",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Emma Watson más conocida por su papel como Hermione Granger en las películas de Harry Potter?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué músico es conocido como el 'Rey del Pop'?",
                answers = listOf("Michael Jackson", "Elvis Presley", "Prince", "Freddie Mercury"),
                correctAnswer = "Michael Jackson",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Leonardo DiCaprio conocido por sus papeles en dramas como 'Titanic' y 'El Renacido'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué celebridad fue conocida por el papel icónico de 'El Exterminador'?",
                answers = listOf(
                    "Arnold Schwarzenegger",
                    "Sylvester Stallone",
                    "Bruce Willis",
                    "Tom Cruise"
                ),
                correctAnswer = "Arnold Schwarzenegger",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Rihanna conocida por su música, así como por sus negocios de moda y productos de belleza?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué celebridad es conocida como 'La Roca'?",
                answers = listOf(
                    "Dwayne Johnson",
                    "Chris Hemsworth",
                    "Tom Hiddleston",
                    "Jason Statham"
                ),
                correctAnswer = "Dwayne Johnson",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Oprah Winfrey conocida por ser presentadora de televisión y filántropa?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué cantante es famosa por la canción 'Like a Prayer'?",
                answers = listOf("Madonna", "Beyoncé", "Lady Gaga", "Ariana Grande"),
                correctAnswer = "Madonna",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Was Marilyn Monroe married to Arthur Miller?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which actor played the character 'Jack Dawson' in 'Titanic'?",
                answers = listOf(
                    "Leonardo DiCaprio",
                    "Brad Pitt",
                    "Johnny Depp",
                    "Matthew McConaughey"
                ),
                correctAnswer = "Leonardo DiCaprio",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Leonardo DiCaprio known for receiving his first Oscar nomination for 'What's Eating Gilbert Grape'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who was the first woman to win a Nobel Prize in two different fields?",
                answers = listOf(
                    "Marie Curie",
                    "Rosalind Franklin",
                    "Ada Lovelace",
                    "Lise Meitner"
                ),
                correctAnswer = "Marie Curie",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Audrey Hepburn considered one of the greatest actresses in film history?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which famous artist was known for creating the sculpture 'David'?",
                answers = listOf("Michelangelo", "Pablo Picasso", "Leonardo da Vinci", "Raphael"),
                correctAnswer = "Michelangelo",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Was Charlie Chaplin a famous silent film actor?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which actress won the Academy Award for Best Actress in 2017 for the movie 'La La Land'?",
                answers = listOf(
                    "Emma Stone",
                    "Jennifer Lawrence",
                    "Meryl Streep",
                    "Cate Blanchett"
                ),
                correctAnswer = "Emma Stone",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the famous actor 'The Rock' real name Dwayne Johnson?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which actor played the role of 'Wolverine' in the X-Men movie series?",
                answers = listOf(
                    "Hugh Jackman",
                    "Chris Hemsworth",
                    "Robert Downey Jr.",
                    "Tom Hiddleston"
                ),
                correctAnswer = "Hugh Jackman",
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Estaba Marilyn Monroe casada con Arthur Miller?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué actor interpretó al personaje 'Jack Dawson' en 'Titanic'?",
                answers = listOf(
                    "Leonardo DiCaprio",
                    "Brad Pitt",
                    "Johnny Depp",
                    "Matthew McConaughey"
                ),
                correctAnswer = "Leonardo DiCaprio",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Leonardo DiCaprio conocido por recibir su primera nominación al Oscar por la película '¿A Quién Ama Gilbert Grape'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién fue la primera mujer en ganar un Premio Nobel en dos campos diferentes?",
                answers = listOf(
                    "Marie Curie",
                    "Rosalind Franklin",
                    "Ada Lovelace",
                    "Lise Meitner"
                ),
                correctAnswer = "Marie Curie",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Audrey Hepburn considerada una de las mejores actrices de la historia del cine?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué artista famoso fue conocido por crear la escultura 'David'?",
                answers = listOf("Miguel Ángel", "Pablo Picasso", "Leonardo da Vinci", "Rafael"),
                correctAnswer = "Miguel Ángel",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Fue Charlie Chaplin un actor famoso de películas mudas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué actriz ganó el Oscar a Mejor Actriz en 2017 por la película 'La La Land'?",
                answers = listOf(
                    "Emma Stone",
                    "Jennifer Lawrence",
                    "Meryl Streep",
                    "Cate Blanchett"
                ),
                correctAnswer = "Emma Stone",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el famoso actor 'La Roca' realmente llamado Dwayne Johnson?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué actor interpretó el papel de 'Wolverine' en la saga de películas de X-Men?",
                answers = listOf(
                    "Hugh Jackman",
                    "Chris Hemsworth",
                    "Robert Downey Jr.",
                    "Tom Hiddleston"
                ),
                correctAnswer = "Hugh Jackman",
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )

        )

        private fun animals() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the elephant the largest land animal?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animal is known for its ability to change color?",
                answers = listOf("Chameleon", "Tiger", "Elephant", "Lion"),
                correctAnswer = "Chameleon",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the cheetah the fastest land animal?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animal is known as the king of the jungle?",
                answers = listOf("Lion", "Elephant", "Giraffe", "Tiger"),
                correctAnswer = "Lion",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the giraffe the tallest land animal?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animal is famous for its ability to mimic human speech?",
                answers = listOf("Parrot", "Elephant", "Dog", "Cat"),
                correctAnswer = "Parrot",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a dolphin a mammal?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animal has a long trunk and large ears?",
                answers = listOf("Elephant", "Giraffe", "Tiger", "Bear"),
                correctAnswer = "Elephant",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the wolf closely related to the domestic dog?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animal is known for its black and white stripes?",
                answers = listOf("Zebra", "Giraffe", "Tiger", "Elephant"),
                correctAnswer = "Zebra",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es el elefante el animal terrestre más grande?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué animal es conocido por su habilidad para cambiar de color?",
                answers = listOf("Camaleón", "Tigre", "Elefante", "León"),
                correctAnswer = "Camaleón",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el guepardo el animal terrestre más rápido?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué animal es conocido como el rey de la selva?",
                answers = listOf("León", "Elefante", "Jirafa", "Tigre"),
                correctAnswer = "León",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la jirafa el animal terrestre más alto?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué animal es famoso por su habilidad para imitar el habla humana?",
                answers = listOf("Loro", "Elefante", "Perro", "Gato"),
                correctAnswer = "Loro",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el delfín un mamífero?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué animal tiene una trompa larga y orejas grandes?",
                answers = listOf("Elefante", "Jirafa", "Tigre", "Oso"),
                correctAnswer = "Elefante",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está el lobo estrechamente relacionado con el perro doméstico?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué animal es conocido por sus rayas negras y blancas?",
                answers = listOf("Cebra", "Jirafa", "Tigre", "Elefante"),
                correctAnswer = "Cebra",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the cheetah the fastest land animal?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animal is known for its black and white fur and lives in China?",
                answers = listOf("Panda", "Koala", "Sloth", "Polar Bear"),
                correctAnswer = "Panda",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Are octopuses considered mammals?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these animals is a marsupial?",
                answers = listOf("Kangaroo", "Elephant", "Lion", "Zebra"),
                correctAnswer = "Kangaroo",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the giraffe the tallest land animal?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which bird is known for its ability to mimic human speech?",
                answers = listOf("Parrot", "Eagle", "Ostrich", "Penguin"),
                correctAnswer = "Parrot",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Are giraffes herbivores?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animal has the longest lifespan?",
                answers = listOf("Blue Whale", "Elephant", "Tortoise", "Shark"),
                correctAnswer = "Tortoise",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a dolphin considered a type of whale?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the largest species of shark?",
                answers = listOf(
                    "Whale Shark",
                    "Great White Shark",
                    "Hammerhead Shark",
                    "Tiger Shark"
                ),
                correctAnswer = "Whale Shark",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es el guepardo el animal terrestre más rápido?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué animal es conocido por su pelaje blanco y negro y vive en China?",
                answers = listOf("Panda", "Koala", "Perezoso", "Oso Polar"),
                correctAnswer = "Panda",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Son los pulpos considerados mamíferos?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos animales es un marsupial?",
                answers = listOf("Canguro", "Elefante", "León", "Cebra"),
                correctAnswer = "Canguro",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la jirafa el animal terrestre más alto?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué ave es conocida por su habilidad para imitar el habla humana?",
                answers = listOf("Loro", "Águila", "Avestruz", "Pingüino"),
                correctAnswer = "Loro",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Son las jirafas herbívoras?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos animales tiene la esperanza de vida más larga?",
                answers = listOf("Ballena Azul", "Elefante", "Tortuga", "Tiburón"),
                correctAnswer = "Tortuga",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es un delfín considerado un tipo de ballena?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la especie de tiburón más grande?",
                answers = listOf(
                    "Tiburón Ballena",
                    "Tiburón Blanco",
                    "Tiburón Martillo",
                    "Tiburón Tigre"
                ),
                correctAnswer = "Tiburón Ballena",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the blue whale the largest animal on Earth?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which species is the largest bird in the world?",
                answers = listOf("Ostrich", "Emu", "Penguin", "Albatross"),
                correctAnswer = "Ostrich",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the monarch butterfly the longest-living butterfly species?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animal is known for its ability to regenerate limbs?",
                answers = listOf("Axolotl", "Frog", "Lizard", "Salmon"),
                correctAnswer = "Axolotl",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Can a jellyfish live forever in a certain condition?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animal is capable of echolocation?",
                answers = listOf("Bat", "Shark", "Elephant", "Lion"),
                correctAnswer = "Bat",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Komodo dragon the largest living lizard species?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What is the only species of bear found in South America?",
                answers = listOf("Andean Bear", "Grizzly Bear", "Polar Bear", "Black Bear"),
                correctAnswer = "Andean Bear",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Bengal tiger the largest tiger species?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animal has the longest migration?",
                answers = listOf("Arctic Tern", "Elephant", "Humpback Whale", "Caribou"),
                correctAnswer = "Arctic Tern",
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es la ballena azul el animal más grande de la Tierra?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué especie es el ave más grande del mundo?",
                answers = listOf("Avestruz", "Emú", "Pingüino", "Albatros"),
                correctAnswer = "Avestruz",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la mariposa monarca la especie de mariposa que más vive?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué animal es conocido por su capacidad para regenerar extremidades?",
                answers = listOf("Axolotl", "Rana", "Lagarto", "Salmón"),
                correctAnswer = "Axolotl",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Puede una medusa vivir para siempre bajo ciertas condiciones?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué animal es capaz de usar la ecolocalización?",
                answers = listOf("Murciélago", "Tiburón", "Elefante", "León"),
                correctAnswer = "Murciélago",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el dragón de Komodo la especie de lagarto más grande que existe?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es la única especie de oso que se encuentra en América del Sur?",
                answers = listOf("Oso Andino", "Oso Pardo", "Oso Polar", "Oso Negro"),
                correctAnswer = "Oso Andino",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el tigre de Bengala la especie de tigre más grande?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué animal tiene la migración más larga?",
                answers = listOf("Charrán Ártico", "Elefante", "Ballena Jorobada", "Caribú"),
                correctAnswer = "Charrán Ártico",
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun vehicles() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is a car considered a type of vehicle?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these is not a type of vehicle?",
                answers = listOf("Bicycle", "Bus", "Plane", "House"),
                correctAnswer = "House",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a truck used for carrying goods?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these is a type of two-wheeled vehicle?",
                answers = listOf("Motorcycle", "Car", "Truck", "Bus"),
                correctAnswer = "Motorcycle",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a bicycle considered a non-motorized vehicle?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these vehicles is used for flying?",
                answers = listOf("Helicopter", "Train", "Car", "Bicycle"),
                correctAnswer = "Helicopter",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a subway system an underground form of transportation?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which vehicle typically has four wheels?",
                answers = listOf("Car", "Motorcycle", "Bicycle", "Boat"),
                correctAnswer = "Car",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a bus a public transportation vehicle?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these vehicles is commonly used on water?",
                answers = listOf("Boat", "Bicycle", "Car", "Motorcycle"),
                correctAnswer = "Boat",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es un automóvil considerado un tipo de vehículo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos no es un tipo de vehículo?",
                answers = listOf("Bicicleta", "Autobús", "Avión", "Casa"),
                correctAnswer = "Casa",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se utiliza un camión para transportar mercancías?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos es un tipo de vehículo de dos ruedas?",
                answers = listOf("Motocicleta", "Coche", "Camión", "Autobús"),
                correctAnswer = "Motocicleta",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es una bicicleta un vehículo no motorizado?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos vehículos se utiliza para volar?",
                answers = listOf("Hélicóptero", "Tren", "Coche", "Bicicleta"),
                correctAnswer = "Hélicóptero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el metro un sistema de transporte subterráneo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué vehículo típicamente tiene cuatro ruedas?",
                answers = listOf("Coche", "Motocicleta", "Bicicleta", "Barco"),
                correctAnswer = "Coche",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es un autobús un vehículo de transporte público?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos vehículos se utiliza comúnmente en el agua?",
                answers = listOf("Barco", "Bicicleta", "Coche", "Motocicleta"),
                correctAnswer = "Barco",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is a hybrid car powered by both gasoline and electricity?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which company manufactures the Model S electric car?",
                answers = listOf("Tesla", "Nissan", "BMW", "Chevrolet"),
                correctAnswer = "Tesla",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a bicycle considered a type of vehicle?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "What type of vehicle is primarily used for water transportation?",
                answers = listOf("Boat", "Car", "Train", "Airplane"),
                correctAnswer = "Boat",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a motorcycle a type of two-wheeled vehicle?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these is a common public transport vehicle?",
                answers = listOf("Bus", "Motorcycle", "Car", "Truck"),
                correctAnswer = "Bus",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a subway system an underground form of public transportation?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which vehicle is commonly used for air transportation?",
                answers = listOf("Airplane", "Train", "Car", "Ship"),
                correctAnswer = "Airplane",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a truck used to carry heavy goods?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these vehicles is most commonly used for flying?",
                answers = listOf("Helicopter", "Submarine", "Car", "Bicycle"),
                correctAnswer = "Helicopter",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es un coche híbrido impulsado tanto por gasolina como por electricidad?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué empresa fabrica el coche eléctrico Model S?",
                answers = listOf("Tesla", "Nissan", "BMW", "Chevrolet"),
                correctAnswer = "Tesla",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es una bicicleta considerada un tipo de vehículo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué tipo de vehículo se utiliza principalmente para el transporte en el agua?",
                answers = listOf("Barco", "Coche", "Tren", "Avión"),
                correctAnswer = "Barco",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es una motocicleta un tipo de vehículo de dos ruedas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos es un vehículo común de transporte público?",
                answers = listOf("Autobús", "Motocicleta", "Coche", "Camión"),
                correctAnswer = "Autobús",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el metro un sistema subterráneo de transporte público?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué vehículo se utiliza comúnmente para el transporte aéreo?",
                answers = listOf("Avión", "Tren", "Coche", "Barco"),
                correctAnswer = "Avión",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Se utiliza un camión para transportar mercancías pesadas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos vehículos se utiliza más comúnmente para volar?",
                answers = listOf("Hélicóptero", "Submarino", "Coche", "Bicicleta"),
                correctAnswer = "Hélicóptero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is a Formula 1 car considered a type of racing vehicle?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these vehicles is most commonly used in space exploration?",
                answers = listOf("Space Shuttle", "Helicopter", "Motorcycle", "Boat"),
                correctAnswer = "Space Shuttle",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Can a hovercraft operate on land and water?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these is an example of a flying car prototype?",
                answers = listOf(
                    "Terrafugia Transition",
                    "Tesla Model X",
                    "Chevrolet Volt",
                    "Ford Mustang"
                ),
                correctAnswer = "Terrafugia Transition",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Tesla Roadster a fully electric vehicle?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which vehicle is primarily designed for exploration of deep-sea environments?",
                answers = listOf("Submarine", "Airplane", "Bicycle", "Car"),
                correctAnswer = "Submarine",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Are electric buses considered environmentally friendly transportation answers?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these is a hybrid vehicle?",
                answers = listOf("Toyota Prius", "Ford Mustang", "Chevrolet Camaro", "Honda Civic"),
                correctAnswer = "Toyota Prius",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Ford F-150 a fully electric vehicle?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which vehicle is designed for extreme off-road driving?",
                answers = listOf("Jeep Wrangler", "Toyota Corolla", "Honda Civic", "BMW 3 Series"),
                correctAnswer = "Jeep Wrangler",
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es un coche de Fórmula 1 considerado un tipo de vehículo de carreras?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos vehículos se utiliza más comúnmente en la exploración espacial?",
                answers = listOf("Transbordador Espacial", "Hélicóptero", "Motocicleta", "Barco"),
                correctAnswer = "Transbordador Espacial",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Puede un aerodeslizador operar en tierra y agua?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos es un ejemplo de prototipo de coche volador?",
                answers = listOf(
                    "Terrafugia Transition",
                    "Tesla Model X",
                    "Chevrolet Volt",
                    "Ford Mustang"
                ),
                correctAnswer = "Terrafugia Transition",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el Tesla Roadster un vehículo completamente eléctrico?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué vehículo está diseñado principalmente para la exploración de entornos submarinos?",
                answers = listOf("Submarino", "Avión", "Bicicleta", "Coche"),
                correctAnswer = "Submarino",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Son los autobuses eléctricos opciones de transporte respetuosas con el medio ambiente?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos es un vehículo híbrido?",
                answers = listOf("Toyota Prius", "Ford Mustang", "Chevrolet Camaro", "Honda Civic"),
                correctAnswer = "Toyota Prius",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el Ford F-150 un vehículo completamente eléctrico?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué vehículo está diseñado para conducir en terrenos extremadamente accidentados?",
                answers = listOf("Jeep Wrangler", "Toyota Corolla", "Honda Civic", "BMW Serie 3"),
                correctAnswer = "Jeep Wrangler",
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )

        )

        private fun comics() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Superman known as the Man of Steel?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which superhero is known for his web-slinging abilities?",
                answers = listOf("Spider-Man", "Iron Man", "Captain America", "Thor"),
                correctAnswer = "Spider-Man",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Wonder Woman a member of the Justice League?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which comic book company is responsible for characters like Batman and Superman?",
                answers = listOf("DC Comics", "Marvel Comics", "Dark Horse", "Image Comics"),
                correctAnswer = "DC Comics",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Hulk known for his green skin and superhuman strength?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which superhero has a shield made of vibranium?",
                answers = listOf("Captain America", "Iron Man", "Thor", "Black Panther"),
                correctAnswer = "Captain America",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Batman a part of the Marvel Universe?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which comic book hero is also known as the 'Merc with a Mouth'?",
                answers = listOf("Deadpool", "Spider-Man", "Iron Man", "Captain America"),
                correctAnswer = "Deadpool",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Green Lantern's power ring fueled by willpower?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which superhero is known for wielding Mjolnir, the hammer of the gods?",
                answers = listOf("Thor", "Iron Man", "Hawkeye", "Doctor Strange"),
                correctAnswer = "Thor",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Superman conocido como el Hombre de Acero?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué superhéroe es conocido por sus habilidades de lanzar telarañas?",
                answers = listOf("Spider-Man", "Iron Man", "Capitán América", "Thor"),
                correctAnswer = "Spider-Man",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Wonder Woman miembro de la Liga de la Justicia?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué editorial de cómics es responsable de personajes como Batman y Superman?",
                answers = listOf("DC Comics", "Marvel Comics", "Dark Horse", "Image Comics"),
                correctAnswer = "DC Comics",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el Hulk conocido por su piel verde y fuerza sobrehumana?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué superhéroe tiene un escudo hecho de vibranio?",
                answers = listOf("Capitán América", "Iron Man", "Thor", "Pantera Negra"),
                correctAnswer = "Capitán América",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Batman parte del Universo Marvel?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué héroe de cómic también es conocido como el 'Mercenario con Boca'?",
                answers = listOf("Deadpool", "Spider-Man", "Iron Man", "Capitán América"),
                correctAnswer = "Deadpool",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿El anillo del Green Lantern está alimentado por la fuerza de voluntad?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué superhéroe es conocido por empuñar Mjolnir, el martillo de los dioses?",
                answers = listOf("Thor", "Iron Man", "Hawkeye", "Doctor Strange"),
                correctAnswer = "Thor",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Deadpool known for breaking the fourth wall?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which superhero is known as the 'Dark Knight'?",
                answers = listOf("Batman", "Iron Man", "Spider-Man", "Captain America"),
                correctAnswer = "Batman",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Captain America known for wielding a shield made of vibranium?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which superhero has the ability to shrink to the size of an ant?",
                answers = listOf("Ant-Man", "Spider-Man", "Iron Man", "Hulk"),
                correctAnswer = "Ant-Man",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is The Flash known for his ability to run at superhuman speeds?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which Marvel superhero is also known as 'The Hulk'?",
                answers = listOf("Bruce Banner", "Tony Stark", "Steve Rogers", "Thor"),
                correctAnswer = "Bruce Banner",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Wonder Woman known for her strength and ability to fly?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which superhero is known for his iron suit and is also a genius inventor?",
                answers = listOf("Iron Man", "Thor", "Doctor Strange", "Green Lantern"),
                correctAnswer = "Iron Man",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Black Widow known for her martial arts skills?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these superheroes is known for wielding a bow and arrow?",
                answers = listOf("Hawkeye", "Black Panther", "Captain America", "Ant-Man"),
                correctAnswer = "Hawkeye",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Deadpool conocido por romper la cuarta pared?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué superhéroe es conocido como el 'Caballero Oscuro'?",
                answers = listOf("Batman", "Iron Man", "Spider-Man", "Capitán América"),
                correctAnswer = "Batman",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Capitán América conocido por empuñar un escudo hecho de vibranio?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué superhéroe tiene la habilidad de reducir su tamaño al de una hormiga?",
                answers = listOf("Ant-Man", "Spider-Man", "Iron Man", "Hulk"),
                correctAnswer = "Ant-Man",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es conocido Flash por su habilidad para correr a velocidades sobrehumanas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué superhéroe de Marvel es también conocido como 'El Hulk'?",
                answers = listOf("Bruce Banner", "Tony Stark", "Steve Rogers", "Thor"),
                correctAnswer = "Bruce Banner",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Wonder Woman conocida por su fuerza y habilidad para volar?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué superhéroe es conocido por su traje de hierro y también por ser un genio inventor?",
                answers = listOf("Iron Man", "Thor", "Doctor Strange", "Linterna Verde"),
                correctAnswer = "Iron Man",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Black Widow conocida por sus habilidades en artes marciales?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos superhéroes es conocido por usar un arco y flecha?",
                answers = listOf("Hawkeye", "Pantera Negra", "Capitán América", "Ant-Man"),
                correctAnswer = "Hawkeye",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the first appearance of Spider-Man in Amazing Fantasy #15 from 1962?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which comic book series introduced the character 'The Punisher'?",
                answers = listOf("The Amazing Spider-Man", "Daredevil", "X-Men", "Captain America"),
                correctAnswer = "Daredevil",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Thanos a character from Marvel Comics?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who created the character 'The Watchmen'?",
                answers = listOf("Alan Moore", "Frank Miller", "Stan Lee", "Grant Morrison"),
                correctAnswer = "Alan Moore",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Dark Knight Returns' a graphic novel about Batman?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these superheroes is also known as the 'Merc with a Mouth'?",
                answers = listOf("Deadpool", "Wolverine", "Iron Man", "Hawkeye"),
                correctAnswer = "Deadpool",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Watchmen' set in an alternate history where superheroes exist?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which character is known for saying 'With great power comes great responsibility'?",
                answers = listOf("Spider-Man", "Superman", "Iron Man", "The Flash"),
                correctAnswer = "Spider-Man",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Who is known as 'The Man Without Fear'?",
                answers = listOf("Daredevil", "Iron Man", "Thor", "Green Lantern"),
                correctAnswer = "Daredevil",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Infinity Gauntlet' a storyline in which Thanos tries to destroy half of the universe?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Aparece Spider-Man por primera vez en Amazing Fantasy #15 de 1962?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie de cómics presentó al personaje 'El Castigador'?",
                answers = listOf("The Amazing Spider-Man", "Daredevil", "X-Men", "Capitán América"),
                correctAnswer = "Daredevil",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Thanos un personaje de Marvel Comics?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién creó al personaje 'The Watchmen'?",
                answers = listOf("Alan Moore", "Frank Miller", "Stan Lee", "Grant Morrison"),
                correctAnswer = "Alan Moore",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Dark Knight Returns' una novela gráfica sobre Batman?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué superhéroe también es conocido como el 'Mercenario con Boca'?",
                answers = listOf("Deadpool", "Wolverine", "Iron Man", "Hawkeye"),
                correctAnswer = "Deadpool",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está 'Watchmen' ambientado en una historia alternativa donde los superhéroes existen?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué personaje es conocido por decir 'Un gran poder conlleva una gran responsabilidad'?",
                answers = listOf("Spider-Man", "Superman", "Iron Man", "The Flash"),
                correctAnswer = "Spider-Man",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Quién es conocido como 'El Hombre sin Miedo'?",
                answers = listOf("Daredevil", "Iron Man", "Thor", "Linterna Verde"),
                correctAnswer = "Daredevil",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Infinity Gauntlet' una historia en la que Thanos intenta destruir la mitad del universo?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            )
        )

        private fun gadgets() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is a smartphone considered a type of gadget?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these is a popular brand for smartwatches?",
                answers = listOf("Apple", "Nike", "Samsung", "Dell"),
                correctAnswer = "Apple",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the iPhone made by Samsung?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which gadget is commonly used for listening to music?",
                answers = listOf("Headphones", "Camera", "TV", "Washing Machine"),
                correctAnswer = "Headphones",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a tablet considered a portable device?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these is a feature commonly found in modern smartphones?",
                answers = listOf("Touchscreen", "Typewriter", "VHS", "Radio"),
                correctAnswer = "Touchscreen",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a laptop a type of portable computer?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these is a device used for taking photographs?",
                answers = listOf("Camera", "Phone", "Laptop", "Microwave"),
                correctAnswer = "Camera",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Amazon Echo used as a voice-controlled speaker?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which device is used for video calls?",
                answers = listOf("Smartphone", "Microwave", "Washing Machine", "Fridge"),
                correctAnswer = "Smartphone",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es un teléfono inteligente considerado un tipo de gadget?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estas marcas es popular en relojes inteligentes?",
                answers = listOf("Apple", "Nike", "Samsung", "Dell"),
                correctAnswer = "Apple",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está el iPhone hecho por Samsung?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué gadget se usa comúnmente para escuchar música?",
                answers = listOf("Auriculares", "Cámara", "Televisión", "Lavadora"),
                correctAnswer = "Auriculares",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es una tableta un dispositivo portátil?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estas características se encuentra comúnmente en los teléfonos inteligentes modernos?",
                answers = listOf("Pantalla táctil", "Máquina de escribir", "VHS", "Radio"),
                correctAnswer = "Pantalla táctil",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es una computadora portátil un tipo de computadora portátil?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos dispositivos se usa para tomar fotografías?",
                answers = listOf("Cámara", "Teléfono", "Computadora portátil", "Microondas"),
                correctAnswer = "Cámara",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el Amazon Echo un altavoz controlado por voz?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué dispositivo se usa para hacer videollamadas?",
                answers = listOf("Teléfono inteligente", "Microondas", "Lavadora", "Nevera"),
                correctAnswer = "Teléfono inteligente",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is a smartwatch a wearable device?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these is a commonly used gadget for reading e-books?",
                answers = listOf("Kindle", "iPad", "MacBook", "PlayStation"),
                correctAnswer = "Kindle",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Bluetooth technology commonly used for wireless communication between devices?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these devices is used for playing video games?",
                answers = listOf("PlayStation", "Microwave", "Washing Machine", "Refrigerator"),
                correctAnswer = "PlayStation",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a drone a type of flying gadget?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which company is known for manufacturing the iPhone?",
                answers = listOf("Apple", "Samsung", "Nokia", "Sony"),
                correctAnswer = "Apple",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a tablet a portable device with a touchscreen?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which gadget is commonly used to listen to music?",
                answers = listOf("Headphones", "Camera", "Washing Machine", "Refrigerator"),
                correctAnswer = "Headphones",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is a gaming console typically used for playing video games?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these is a portable device used for photography?",
                answers = listOf("Camera", "Laptop", "Smartphone", "Microwave"),
                correctAnswer = "Camera",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es un reloj inteligente un dispositivo portátil?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos dispositivos se usa comúnmente para leer libros electrónicos?",
                answers = listOf("Kindle", "iPad", "MacBook", "PlayStation"),
                correctAnswer = "Kindle",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la tecnología Bluetooth comúnmente usada para la comunicación inalámbrica entre dispositivos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos dispositivos se usa para jugar videojuegos?",
                answers = listOf("PlayStation", "Microondas", "Lavadora", "Refrigerador"),
                correctAnswer = "PlayStation",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es un dron un tipo de gadget volador?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué empresa es conocida por fabricar el iPhone?",
                answers = listOf("Apple", "Samsung", "Nokia", "Sony"),
                correctAnswer = "Apple",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es una tableta un dispositivo portátil con pantalla táctil?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué gadget se usa comúnmente para escuchar música?",
                answers = listOf("Auriculares", "Cámara", "Lavadora", "Refrigerador"),
                correctAnswer = "Auriculares",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es una consola de videojuegos un dispositivo comúnmente usado para jugar videojuegos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos es un dispositivo portátil usado para fotografía?",
                answers = listOf(
                    "Cámara",
                    "Computadora portátil",
                    "Teléfono inteligente",
                    "Microondas"
                ),
                correctAnswer = "Cámara",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the iPhone 12 5G compatible?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which company released the first smart speaker, known as the 'Echo'?",
                answers = listOf("Amazon", "Google", "Apple", "Samsung"),
                correctAnswer = "Amazon",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Apple Watch Series 6 capable of measuring your blood oxygen levels?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these gadgets was the first to offer wireless charging?",
                answers = listOf(
                    "Apple iPhone 8",
                    "Samsung Galaxy S6",
                    "Google Pixel",
                    "OnePlus 8"
                ),
                correctAnswer = "Samsung Galaxy S6",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Tesla Model 3 fully electric?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which company produces the 'Surface' tablet series?",
                answers = listOf("Microsoft", "Apple", "Google", "Samsung"),
                correctAnswer = "Microsoft",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the Oculus Rift a virtual reality headset?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which device is considered the most popular voice assistant device?",
                answers = listOf("Amazon Echo", "Google Nest", "Apple HomePod", "Sonos One"),
                correctAnswer = "Amazon Echo",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the iPad Pro capable of running MacOS applications?",
                answers = listOf("True", "False"),
                correctAnswer = "False",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which technology allows devices like smartphones and laptops to wirelessly charge?",
                answers = listOf("Qi wireless charging", "Bluetooth", "NFC", "5G"),
                correctAnswer = "Qi wireless charging",
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿El iPhone 12 es compatible con 5G?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué empresa lanzó el primer altavoz inteligente, conocido como 'Echo'?",
                answers = listOf("Amazon", "Google", "Apple", "Samsung"),
                correctAnswer = "Amazon",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el Apple Watch Series 6 capaz de medir los niveles de oxígeno en sangre?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos dispositivos fue el primero en ofrecer carga inalámbrica?",
                answers = listOf(
                    "Apple iPhone 8",
                    "Samsung Galaxy S6",
                    "Google Pixel",
                    "OnePlus 8"
                ),
                correctAnswer = "Samsung Galaxy S6",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el Tesla Model 3 completamente eléctrico?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué empresa produce la serie de tabletas 'Surface'?",
                answers = listOf("Microsoft", "Apple", "Google", "Samsung"),
                correctAnswer = "Microsoft",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el Oculus Rift un dispositivo de realidad virtual?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál es el dispositivo más popular de asistentes de voz?",
                answers = listOf("Amazon Echo", "Google Nest", "Apple HomePod", "Sonos One"),
                correctAnswer = "Amazon Echo",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el iPad Pro capaz de ejecutar aplicaciones de MacOS?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Falso",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué tecnología permite que dispositivos como smartphones y laptops se carguen de manera inalámbrica?",
                answers = listOf("Carga inalámbrica Qi", "Bluetooth", "NFC", "5G"),
                correctAnswer = "Carga inalámbrica Qi",
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun animeAndManga() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Naruto Uzumaki the main character of 'Naruto'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which anime features the characters Goku, Vegeta, and Frieza?",
                answers = listOf("Dragon Ball", "One Piece", "Naruto", "Attack on Titan"),
                correctAnswer = "Dragon Ball",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'My Hero Academia' set in a world where most people have superpowers?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which anime has the main character named Luffy?",
                answers = listOf("One Piece", "Dragon Ball", "Bleach", "Attack on Titan"),
                correctAnswer = "One Piece",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Sailor Moon' an anime focused on magical girls?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which anime character is known for shouting 'Believe it!'?",
                answers = listOf("Naruto Uzumaki", "Luffy", "Goku", "Edward Elric"),
                correctAnswer = "Naruto Uzumaki",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Attack on Titan' set in a world where humanity is under threat from giant creatures?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which anime features the characters Gon Freecss and Killua Zoldyck?",
                answers = listOf("Hunter x Hunter", "Dragon Ball", "One Piece", "Bleach"),
                correctAnswer = "Hunter x Hunter",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Fullmetal Alchemist' about two brothers searching for the Philosopher's Stone?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which character is known as the 'Pirate King' in 'One Piece'?",
                answers = listOf("Gol D. Roger", "Monkey D. Luffy", "Shanks", "Bartholomew Kuma"),
                correctAnswer = "Gol D. Roger",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Naruto Uzumaki el personaje principal de 'Naruto'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué anime presenta a los personajes Goku, Vegeta y Freezer?",
                answers = listOf("Dragon Ball", "One Piece", "Naruto", "Attack on Titan"),
                correctAnswer = "Dragon Ball",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está 'My Hero Academia' ambientado en un mundo donde la mayoría de las personas tienen superpoderes?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué anime tiene al personaje principal llamado Luffy?",
                answers = listOf("One Piece", "Dragon Ball", "Bleach", "Attack on Titan"),
                correctAnswer = "One Piece",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Sailor Moon' un anime centrado en chicas mágicas?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué personaje de anime es conocido por decir '¡Cree en ti!'?",
                answers = listOf("Naruto Uzumaki", "Luffy", "Goku", "Edward Elric"),
                correctAnswer = "Naruto Uzumaki",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está 'Attack on Titan' ambientado en un mundo donde la humanidad está amenazada por criaturas gigantes?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué anime presenta a los personajes Gon Freecss y Killua Zoldyck?",
                answers = listOf("Hunter x Hunter", "Dragon Ball", "One Piece", "Bleach"),
                correctAnswer = "Hunter x Hunter",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Fullmetal Alchemist' un anime sobre dos hermanos que buscan la Piedra Filosofal?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Goku from 'Dragon Ball' known for his ability to use the Kamehameha?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these anime characters is known for wielding a giant sword?",
                answers = listOf("Cloud Strife", "Naruto Uzumaki", "Luffy", "Goku"),
                correctAnswer = "Cloud Strife",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'My Hero Academia' set in a world where people with superpowers are called 'Quirks'?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which anime features a giant robot called 'Evangelion'?",
                answers = listOf(
                    "Neon Genesis Evangelion",
                    "Attack on Titan",
                    "Naruto",
                    "Dragon Ball"
                ),
                correctAnswer = "Neon Genesis Evangelion",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'One Punch Man' about a superhero who can defeat any opponent with a single punch?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these anime characters is known for using 'The Sharingan'?",
                answers = listOf("Sasuke Uchiha", "Naruto Uzumaki", "Luffy", "Ichigo Kurosaki"),
                correctAnswer = "Sasuke Uchiha",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the character 'Saitama' from 'One Punch Man' known for being a hero with no real struggles?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which anime features the characters Light Yagami and L, who engage in a battle of wits?",
                answers = listOf("Death Note", "Naruto", "One Piece", "Attack on Titan"),
                correctAnswer = "Death Note",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Attack on Titan' an anime about humanity fighting giant humanoid creatures?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which anime character is a half-demon who wields the sword Tessaiga?",
                answers = listOf("Inuyasha", "Naruto", "Luffy", "Ichigo"),
                correctAnswer = "Inuyasha",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Goku de 'Dragon Ball' conocido por su habilidad de usar el Kamehameha?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué personaje de anime es conocido por empuñar una espada gigante?",
                answers = listOf("Cloud Strife", "Naruto Uzumaki", "Luffy", "Goku"),
                correctAnswer = "Cloud Strife",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está 'My Hero Academia' ambientado en un mundo donde las personas con superpoderes son llamadas 'Quirks'?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué anime presenta un robot gigante llamado 'Evangelion'?",
                answers = listOf(
                    "Neon Genesis Evangelion",
                    "Attack on Titan",
                    "Naruto",
                    "Dragon Ball"
                ),
                correctAnswer = "Neon Genesis Evangelion",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'One Punch Man' sobre un superhéroe que puede derrotar a cualquier oponente con un solo golpe?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué personaje de anime es conocido por usar 'El Sharingan'?",
                answers = listOf("Sasuke Uchiha", "Naruto Uzumaki", "Luffy", "Ichigo Kurosaki"),
                correctAnswer = "Sasuke Uchiha",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Saitama de 'One Punch Man' conocido por ser un héroe sin verdaderas dificultades?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué anime presenta a los personajes Light Yagami y L, quienes participan en una batalla intelectual?",
                answers = listOf("Death Note", "Naruto", "One Piece", "Attack on Titan"),
                correctAnswer = "Death Note",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está 'Attack on Titan' ambientado en un mundo donde la humanidad lucha contra gigantes humanoides?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué personaje de anime es un medio demonio que empuña la espada Tessaiga?",
                answers = listOf("Inuyasha", "Naruto", "Luffy", "Ichigo"),
                correctAnswer = "Inuyasha",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is the anime 'Neon Genesis Evangelion' known for its complex psychological themes?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which manga series was written by Masashi Kishimoto?",
                answers = listOf("Naruto", "One Piece", "Dragon Ball", "Attack on Titan"),
                correctAnswer = "Naruto",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Akira' a movie based on a manga series?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who is the main antagonist in the manga 'One Piece'?",
                answers = listOf("Blackbeard", "Luffy", "Dracule Mihawk", "Shanks"),
                correctAnswer = "Blackbeard",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Attack on Titan' based on a manga by Hajime Isayama?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which anime series features a character known as 'The Pirate King'?",
                answers = listOf("One Piece", "Naruto", "Dragon Ball", "Hunter x Hunter"),
                correctAnswer = "One Piece",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Death Note' a psychological thriller anime series?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which character is known for using the Death Note?",
                answers = listOf("Light Yagami", "L", "Naruto Uzumaki", "Goku"),
                correctAnswer = "Light Yagami",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Fullmetal Alchemist: Brotherhood' a faithful adaptation of the original manga?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these characters is a main protagonist in the anime 'Bleach'?",
                answers = listOf("Ichigo Kurosaki", "Goku", "Luffy", "Naruto Uzumaki"),
                correctAnswer = "Ichigo Kurosaki",
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es el anime 'Neon Genesis Evangelion' conocido por sus complejos temas psicológicos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie de manga fue escrita por Masashi Kishimoto?",
                answers = listOf("Naruto", "One Piece", "Dragon Ball", "Attack on Titan"),
                correctAnswer = "Naruto",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Akira' una película basada en una serie de manga?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién es el principal antagonista en el manga 'One Piece'?",
                answers = listOf("Barbanegra", "Luffy", "Dracule Mihawk", "Shanks"),
                correctAnswer = "Barbanegra",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está 'Attack on Titan' basado en un manga de Hajime Isayama?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie de anime presenta a un personaje conocido como 'El Rey de los Piratas'?",
                answers = listOf("One Piece", "Naruto", "Dragon Ball", "Hunter x Hunter"),
                correctAnswer = "One Piece",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Death Note' una serie de anime de suspenso psicológico?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué personaje es conocido por usar el Death Note?",
                answers = listOf("Light Yagami", "L", "Naruto Uzumaki", "Goku"),
                correctAnswer = "Light Yagami",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Fullmetal Alchemist: Brotherhood' una adaptación fiel del manga original?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos personajes es el protagonista principal en el anime 'Bleach'?",
                answers = listOf("Ichigo Kurosaki", "Goku", "Luffy", "Naruto Uzumaki"),
                correctAnswer = "Ichigo Kurosaki",
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )

        private fun cartoonAndAnimations() = listOf(
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is Mickey Mouse the mascot of Disney?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which character is known for saying 'What's up, Doc'?",
                answers = listOf("Bugs Bunny", "Donald Duck", "Daffy Duck", "Mickey Mouse"),
                correctAnswer = "Bugs Bunny",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Tom from 'Tom and Jerry' a cat?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animated series features a sponge living in a pineapple under the sea?",
                answers = listOf(
                    "SpongeBob SquarePants",
                    "The Simpsons",
                    "Family Guy",
                    "Looney Tunes"
                ),
                correctAnswer = "SpongeBob SquarePants",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Scooby-Doo a dog who helps solve mysteries?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which character is the sidekick to Batman?",
                answers = listOf("Robin", "The Joker", "Catwoman", "Penguin"),
                correctAnswer = "Robin",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is Pikachu from the anime series 'Pokémon' a type of bird?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animated series features a family with characters named Homer, Marge, and Bart?",
                answers = listOf("The Simpsons", "Family Guy", "Rick and Morty", "Futurama"),
                correctAnswer = "The Simpsons",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the movie 'Toy Story' about living toys?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these characters is a famous animated dog?",
                answers = listOf("Scooby-Doo", "Snoopy", "Garfield", "Homer Simpson"),
                correctAnswer = "Scooby-Doo",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es Mickey Mouse la mascota de Disney?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué personaje es conocido por decir '¿Qué tal, Doc?'?",
                answers = listOf("Bugs Bunny", "Pato Donald", "Pato Lucas", "Mickey Mouse"),
                correctAnswer = "Bugs Bunny",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Tom de 'Tom y Jerry' un gato?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie animada presenta a una esponja que vive en una piña debajo del mar?",
                answers = listOf(
                    "SpongeBob SquarePants",
                    "Los Simpson",
                    "Padre de Familia",
                    "Looney Tunes"
                ),
                correctAnswer = "SpongeBob SquarePants",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Scooby-Doo un perro que ayuda a resolver misterios?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué personaje es el compañero de Batman?",
                answers = listOf("Robin", "El Joker", "Gata Mujer", "Pingüino"),
                correctAnswer = "Robin",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es Pikachu del anime 'Pokémon' un tipo de ave?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie animada presenta una familia con personajes llamados Homer, Marge y Bart?",
                answers = listOf("Los Simpson", "Padre de Familia", "Rick y Morty", "Futurama"),
                correctAnswer = "Los Simpson",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es la película 'Toy Story' sobre juguetes que cobran vida?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos personajes es un famoso perro animado?",
                answers = listOf("Scooby-Doo", "Snoopy", "Garfield", "Homer Simpson"),
                correctAnswer = "Scooby-Doo",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is SpongeBob SquarePants a character from a Nickelodeon animated series?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these characters is a superhero from the 'Teenage Mutant Ninja Turtles'?",
                answers = listOf("Michelangelo", "Spongebob", "Batman", "Daffy Duck"),
                correctAnswer = "Michelangelo",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Simpsons' an animated show about a family living in Springfield?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these animated shows features the character Stewie Griffin?",
                answers = listOf("Family Guy", "Rick and Morty", "The Simpsons", "American Dad"),
                correctAnswer = "Family Guy",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is the character of 'Daffy Duck' from the Looney Tunes series?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these is a famous animated superhero team?",
                answers = listOf("Justice League", "X-Men", "Teen Titans", "All of the above"),
                correctAnswer = "All of the above",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Tom and Jerry' an animated series about a cat and mouse fighting each other?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animated character is known for wearing a red cap and eating spinach?",
                answers = listOf("Popeye", "Superman", "Bugs Bunny", "Mickey Mouse"),
                correctAnswer = "Popeye",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Adventure Time' set in a post-apocalyptic world?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of these animated characters is known for his catchphrase 'What's up, Doc'?",
                answers = listOf("Bugs Bunny", "Daffy Duck", "Donald Duck", "Mickey Mouse"),
                correctAnswer = "Bugs Bunny",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Es SpongeBob SquarePants un personaje de una serie animada de Nickelodeon?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos personajes es un superhéroe de 'Las Tortugas Ninja'?",
                answers = listOf("Michelangelo", "Spongebob", "Batman", "Pato Lucas"),
                correctAnswer = "Michelangelo",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Los Simpson' una serie animada sobre una familia que vive en Springfield?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie animada presenta al personaje Stewie Griffin?",
                answers = listOf("Padre de Familia", "Rick y Morty", "Los Simpson", "American Dad"),
                correctAnswer = "Padre de Familia",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es el personaje 'Pato Lucas' de la serie Looney Tunes?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos es un equipo de superhéroes animados famoso?",
                answers = listOf(
                    "Liga de la Justicia",
                    "X-Men",
                    "Teen Titans",
                    "Todos los anteriores"
                ),
                correctAnswer = "Todos los anteriores",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Tom y Jerry' una serie animada sobre un gato y un ratón peleando entre sí?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué personaje animado es conocido por usar una gorra roja y comer espinacas?",
                answers = listOf("Popeye", "Superman", "Bugs Bunny", "Mickey Mouse"),
                correctAnswer = "Popeye",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Está 'Hora de Aventura' ambientado en un mundo post-apocalíptico?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de estos personajes animados es conocido por su frase '¿Qué tal, Doc?'?",
                answers = listOf("Bugs Bunny", "Pato Lucas", "Pato Donald", "Mickey Mouse"),
                correctAnswer = "Bugs Bunny",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// EN ////////////////////////////////////
            QuestionEntity(
                question = "Is 'Avatar: The Last Airbender' an animated series set in a world where people can control the elements?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animated character is known for his catchphrase 'Yabba Dabba Doo'?",
                answers = listOf(
                    "Fred Flintstone",
                    "Homer Simpson",
                    "George Jetson",
                    "Peter Griffin"
                ),
                correctAnswer = "Fred Flintstone",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Looney Tunes' an animated series created by Walt Disney?",
                answers = listOf("False", "True"),
                correctAnswer = "False",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Who is the main villain in the animated series 'Dragon Ball Z'?",
                answers = listOf("Frieza", "Majin Buu", "Cell", "Piccolo"),
                correctAnswer = "Frieza",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'The Simpsons' the longest-running animated television show in the U.S.?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which animated series features a character named Stewie Griffin?",
                answers = listOf("Family Guy", "The Simpsons", "American Dad", "South Park"),
                correctAnswer = "Family Guy",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Futurama' an animated series created by Matt Groening?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which character is known for the phrase 'I'm Batman'?",
                answers = listOf("Batman", "Spider-Man", "Iron Man", "Superman"),
                correctAnswer = "Batman",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "Is 'Rick and Morty' an animated series known for its dark humor and science fiction themes?",
                answers = listOf("True", "False"),
                correctAnswer = "True",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "Which of the following characters is known for being a 'mad scientist' in animated series?",
                answers = listOf(
                    "Rick Sanchez",
                    "Dr. Robotnik",
                    "Doc Brown",
                    "Frankenstein's Monster"
                ),
                correctAnswer = "Rick Sanchez",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            //////////////////////// ES ////////////////////////////////////
            QuestionEntity(
                question = "¿Está 'Avatar: La Leyenda de Aang' ambientado en un mundo donde las personas pueden controlar los elementos?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué personaje animado es conocido por su frase 'Yabba Dabba Doo'?",
                answers = listOf(
                    "Fred Flintstone",
                    "Homer Simpson",
                    "George Jetson",
                    "Peter Griffin"
                ),
                correctAnswer = "Fred Flintstone",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Looney Tunes' una serie animada creada por Walt Disney?",
                answers = listOf("Falso", "Verdadero"),
                correctAnswer = "Falso",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Quién es el principal villano de la serie animada 'Dragon Ball Z'?",
                answers = listOf("Freezer", "Majin Buu", "Cell", "Piccolo"),
                correctAnswer = "Freezer",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Los Simpson' la serie animada de televisión más longeva en los EE. UU.?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué serie animada presenta al personaje Stewie Griffin?",
                answers = listOf("Padre de Familia", "Los Simpson", "American Dad", "South Park"),
                correctAnswer = "Padre de Familia",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Futurama' una serie animada creada por Matt Groening?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Qué personaje es conocido por la frase 'Soy Batman'?",
                answers = listOf("Batman", "Spider-Man", "Iron Man", "Superman"),
                correctAnswer = "Batman",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            ),
            QuestionEntity(
                question = "¿Es 'Rick and Morty' una serie animada conocida por su humor oscuro y temas de ciencia ficción?",
                answers = listOf("Verdadero", "Falso"),
                correctAnswer = "Verdadero",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.BOOLEAN
            ),
            QuestionEntity(
                question = "¿Cuál de los siguientes personajes es conocido por ser un 'científico loco' en las series animadas?",
                answers = listOf(
                    "Rick Sanchez",
                    "Dr. Robotnik",
                    "Doc Brown",
                    "El Monstruo de Frankenstein"
                ),
                correctAnswer = "Rick Sanchez",
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD,
                quizType = QuizType.MULTIPLE
            )
        )
    }

}
