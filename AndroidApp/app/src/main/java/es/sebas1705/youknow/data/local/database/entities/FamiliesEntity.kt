package es.sebas1705.youknow.data.local.database.entities/*
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
import es.sebas1705.youknow.data.local.database.config.SettingsDB
import es.sebas1705.youknow.domain.model.games.FamiliesModel

/**
 * Data class to represent the user data and use as entity in the database
 *
 * @property answers [List]<[String]>: List of answers
 * @property correctAnswer [String]: Correct answer
 * @property category [Int]: Category of the question
 * @property language [Int]: Language of the question
 * @property difficulty [Int]: Difficulty of the question
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Entity(tableName = SettingsDB.FAMILIES_TABLE)
data class FamiliesEntity(
    @PrimaryKey val answers: List<String>,
    val correctAnswer: String,
    val category: Category,
    val language: Languages,
    val difficulty: Difficulty,
) {
    fun toFamiliesModel() = FamiliesModel(
        answers,
        correctAnswer,
        category,
        language,
        difficulty
    )

    companion object {
        fun familiesDatabase(): List<FamiliesEntity> {
            val list = mutableListOf<FamiliesEntity>()
            list.addAll(generalKnowledge())
            list.addAll(books())
            list.addAll(films())
            list.addAll(music())
            list.addAll(musicalAndTheatres())
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
            list.addAll(cartoonsAndAnimation())
            return list.toList()
        }

        private fun generalKnowledge() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Apple", "Banana", "Carrot", "Orange"),
                correctAnswer = "Carrot", // No es una fruta
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Dog", "Cat", "Fish", "Table"),
                correctAnswer = "Table", // No es un animal
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Sun", "Moon", "Earth", "Chair"),
                correctAnswer = "Chair", // No es un cuerpo celeste
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Paris", "Tokyo", "Lamp", "London"),
                correctAnswer = "Lamp", // No es una ciudad
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Football", "Basketball", "Piano", "Tennis"),
                correctAnswer = "Piano", // No es un deporte
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Blue", "Green", "Red", "Car"),
                correctAnswer = "Car", // No es un color
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Tree", "Flower", "Grass", "Television"),
                correctAnswer = "Television", // No es una planta
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Rock", "Jazz", "Classical", "Bicycle"),
                correctAnswer = "Bicycle", // No es un género musical
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Milk", "Juice", "Coffee", "Shoe"),
                correctAnswer = "Shoe", // No es una bebida
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Winter", "Summer", "Spring", "Pizza"),
                correctAnswer = "Pizza", // No es una estación del año
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Gato", "Perro", "Zanahoria", "Pez"),
                correctAnswer = "Zanahoria", // No es un animal
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Manzana", "Naranja", "Plátano", "Ordenador"),
                correctAnswer = "Ordenador", // No es una fruta
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Silla", "Mesa", "Sofá", "Tigre"),
                correctAnswer = "Tigre", // No es un mueble
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Verano", "Invierno", "Otoño", "Pizza"),
                correctAnswer = "Pizza", // No es una estación del año
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Perro", "Lobo", "Zorro", "Lámpara"),
                correctAnswer = "Lámpara", // No es un animal
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Rock", "Jazz", "Clásica", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un género musical
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("París", "Londres", "Tokio", "Coche"),
                correctAnswer = "Coche", // No es una ciudad
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Leche", "Jugo", "Café", "Lápiz"),
                correctAnswer = "Lápiz", // No es una bebida
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Fútbol", "Tenis", "Baloncesto", "Piano"),
                correctAnswer = "Piano", // No es un deporte
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Azul", "Verde", "Rojo", "Tren"),
                correctAnswer = "Tren", // No es un color
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Car", "Boat", "Plane", "Apple"),
                correctAnswer = "Apple", // No es un medio de transporte
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Mercury", "Venus", "Mars", "Tree"),
                correctAnswer = "Tree", // No es un planeta
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Eagle", "Penguin", "Hawk", "Laptop"),
                correctAnswer = "Laptop", // No es un ave
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Square", "Triangle", "Circle", "Piano"),
                correctAnswer = "Piano", // No es una figura geométrica
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Copper", "Gold", "Silver", "Table"),
                correctAnswer = "Table", // No es un metal
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Doctor", "Teacher", "Pilot", "Window"),
                correctAnswer = "Window", // No es una profesión
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Dog", "Wolf", "Fox", "Cucumber"),
                correctAnswer = "Cucumber", // No es un animal
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Pen", "Pencil", "Notebook", "Guitar"),
                correctAnswer = "Guitar", // No es un material de escritura
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Africa", "Asia", "Europe", "Fridge"),
                correctAnswer = "Fridge", // No es un continente
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Carrot", "Potato", "Tomato", "Hammer"),
                correctAnswer = "Hammer", // No es un vegetal
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Coche", "Barco", "Avión", "Manzana"),
                correctAnswer = "Manzana", // No es un medio de transporte
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Mercurio", "Venus", "Marte", "Árbol"),
                correctAnswer = "Árbol", // No es un planeta
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Águila", "Pingüino", "Halcón", "Portátil"),
                correctAnswer = "Portátil", // No es un ave
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Cuadrado", "Triángulo", "Círculo", "Piano"),
                correctAnswer = "Piano", // No es una figura geométrica
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Cobre", "Oro", "Plata", "Mesa"),
                correctAnswer = "Mesa", // No es un metal
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Doctor", "Maestro", "Piloto", "Ventana"),
                correctAnswer = "Ventana", // No es una profesión
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Perro", "Lobo", "Zorro", "Pepino"),
                correctAnswer = "Pepino", // No es un animal
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Bolígrafo", "Lápiz", "Cuaderno", "Guitarra"),
                correctAnswer = "Guitarra", // No es un material de escritura
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("África", "Asia", "Europa", "Nevera"),
                correctAnswer = "Nevera", // No es un continente
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Zanahoria", "Patata", "Tomate", "Martillo"),
                correctAnswer = "Martillo", // No es un vegetal
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            //HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Quantum Mechanics", "Relativity", "Thermodynamics", "Poetry"),
                correctAnswer = "Poetry", // No es una rama de la física
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Mozart", "Beethoven", "Shakespeare", "Chopin"),
                correctAnswer = "Shakespeare", // No es un compositor musical
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Einstein", "Newton", "Darwin", "Picasso"),
                correctAnswer = "Picasso", // No es un científico
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Mitochondria", "Nucleus", "Ribosome", "Eiffel Tower"),
                correctAnswer = "Eiffel Tower", // No es una estructura celular
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Himalayas", "Andes", "Rockies", "Amazon"),
                correctAnswer = "Amazon", // No es una cordillera
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Carbon", "Nitrogen", "Oxygen", "Triangle"),
                correctAnswer = "Triangle", // No es un elemento químico
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Kafka", "Hemingway", "Tolstoy", "Einstein"),
                correctAnswer = "Einstein", // No es un escritor
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Binary", "Octal", "Hexadecimal", "Latin"),
                correctAnswer = "Latin", // No es un sistema numérico
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Neptune", "Mars", "Jupiter", "Apollo"),
                correctAnswer = "Apollo", // No es un planeta
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "French Revolution", "World War II", "Industrial Revolution", "Photosynthesis"
                ),
                correctAnswer = "Photosynthesis", // No es un evento histórico
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Mecánica Cuántica", "Relatividad", "Termodinámica", "Poesía"),
                correctAnswer = "Poesía", // No es una rama de la física
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Mozart", "Beethoven", "Chopin", "Cervantes"),
                correctAnswer = "Cervantes", // No es un compositor
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Einstein", "Newton", "Darwin", "Velázquez"),
                correctAnswer = "Velázquez", // No es un científico
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Mitocondria", "Ribosoma", "Núcleo", "Torre Eiffel"),
                correctAnswer = "Torre Eiffel", // No es una estructura celular
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Himalayas", "Andes", "Rockies", "Amazonas"),
                correctAnswer = "Amazonas", // No es una cordillera
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Carbono", "Nitrógeno", "Oxígeno", "Triángulo"),
                correctAnswer = "Triángulo", // No es un elemento químico
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Kafka", "Hemingway", "Tolstoi", "Einstein"),
                correctAnswer = "Einstein", // No es un escritor
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Binario", "Octal", "Hexadecimal", "Latín"),
                correctAnswer = "Latín", // No es un sistema numérico
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Neptuno", "Marte", "Júpiter", "Apolo"),
                correctAnswer = "Apolo", // No es un planeta
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Revolución Francesa",
                    "Segunda Guerra Mundial",
                    "Revolución Industrial",
                    "Fotosíntesis"
                ),
                correctAnswer = "Fotosíntesis", // No es un evento histórico
                category = Category.GENERAL_KNOWLEDGE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun books() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("1984", "Animal Farm", "Brave New World", "Car"),
                correctAnswer = "Car", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("The Hobbit", "The Lord of the Rings", "Harry Potter", "Bicycle"),
                correctAnswer = "Bicycle", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf(
                    "To Kill a Mockingbird", "The Great Gatsby", "Pride and Prejudice", "Laptop"
                ),
                correctAnswer = "Laptop", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Moby Dick", "The Catcher in the Rye", "Of Mice and Men", "Chair"),
                correctAnswer = "Chair", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Hamlet", "Macbeth", "Romeo and Juliet", "Tree"),
                correctAnswer = "Tree", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("War and Peace", "Crime and Punishment", "Anna Karenina", "Pen"),
                correctAnswer = "Pen", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Dune", "Foundation", "Neuromancer", "Plane"),
                correctAnswer = "Plane", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Jane Eyre", "Wuthering Heights", "Frankenstein", "Television"),
                correctAnswer = "Television", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("The Road", "The Martian", "The Hunger Games", "Table"),
                correctAnswer = "Table", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf(
                    "Harry Potter", "Percy Jackson", "The Chronicles of Narnia", "Carrot"
                ),
                correctAnswer = "Carrot", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Don Quijote", "Cien Años de Soledad", "El Principito", "Coche"),
                correctAnswer = "Coche", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf(
                    "El Amor en los Tiempos del Cólera",
                    "Crónica de una Muerte Anunciada",
                    "Rayuela",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf(
                    "El Lazarillo de Tormes", "La Celestina", "Don Juan Tenorio", "Lámpara"
                ),
                correctAnswer = "Lámpara", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf(
                    "La Regenta", "Fortunata y Jacinta", "Los Pazos de Ulloa", "Árbol"
                ),
                correctAnswer = "Árbol", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("El Camino", "La Colmena", "Las Ratas", "Ventana"),
                correctAnswer = "Ventana", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf(
                    "El Ingenioso Hidalgo", "La Biblia", "El Nuevo Testamento", "Relatividad"
                ),
                correctAnswer = "Relatividad", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf(
                    "La Casa de los Espíritus", "Eva Luna", "De Amor y de Sombra", "Lápiz"
                ),
                correctAnswer = "Lápiz", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Marianela", "El Árbol de la Ciencia", "Pepita Jiménez", "Mesa"),
                correctAnswer = "Mesa", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Don Quijote", "La Biblia", "El Principito", "Montaña"),
                correctAnswer = "Montaña", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf(
                    "La Sombra del Viento",
                    "El Juego del Ángel",
                    "El Prisionero del Cielo",
                    "Guitarra"
                ),
                correctAnswer = "Guitarra", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("1984", "Animal Farm", "Brave New World", "Spaceship"),
                correctAnswer = "Spaceship", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "The Hobbit", "The Lord of the Rings", "The Silmarillion", "Tesla"
                ),
                correctAnswer = "Tesla", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "Pride and Prejudice", "Sense and Sensibility", "Emma", "Electricity"
                ),
                correctAnswer = "Electricity", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Hamlet", "Macbeth", "Romeo and Juliet", "Ocean"),
                correctAnswer = "Ocean", // Not a play
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "Harry Potter and the Sorcerer’s Stone",
                    "Harry Potter and the Chamber of Secrets",
                    "Harry Potter and the Goblet of Fire",
                    "Eiffel Tower"
                ),
                correctAnswer = "Eiffel Tower", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "The Catcher in the Rye",
                    "To Kill a Mockingbird",
                    "The Grapes of Wrath",
                    "Pencil"
                ),
                correctAnswer = "Pencil", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "War and Peace", "Anna Karenina", "Crime and Punishment", "Volcano"
                ),
                correctAnswer = "Volcano", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "Jane Eyre", "Wuthering Heights", "The Tenant of Wildfell Hall", "Laptop"
                ),
                correctAnswer = "Laptop", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Dune", "Foundation", "Neuromancer", "Spacesuit"),
                correctAnswer = "Spacesuit", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "The Old Man and the Sea",
                    "For Whom the Bell Tolls",
                    "A Farewell to Arms",
                    "Compass"
                ),
                correctAnswer = "Compass", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Cien Años de Soledad",
                    "El Amor en los Tiempos del Cólera",
                    "Crónica de una Muerte Anunciada",
                    "Pez"
                ),
                correctAnswer = "Pez", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Don Quijote", "La Celestina", "Don Juan Tenorio", "Árbol"),
                correctAnswer = "Árbol", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Rayuela", "El Aleph", "Ficciones", "Ventana"),
                correctAnswer = "Ventana", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Pedro Páramo", "El Llano en Llamas", "Marianela", "Luna"),
                correctAnswer = "Luna", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "La Regenta", "Fortunata y Jacinta", "Los Pazos de Ulloa", "Martillo"
                ),
                correctAnswer = "Martillo", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("El Camino", "La Colmena", "Las Ratas", "Relatividad"),
                correctAnswer = "Relatividad", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "La Sombra del Viento",
                    "El Juego del Ángel",
                    "El Prisionero del Cielo",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "El Ingenioso Hidalgo", "La Biblia", "El Nuevo Testamento", "Montaña"
                ),
                correctAnswer = "Montaña", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "La Casa de los Espíritus", "Eva Luna", "De Amor y de Sombra", "Guitarra"
                ),
                correctAnswer = "Guitarra", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "El Túnel", "Sobre Héroes y Tumbas", "Abaddón el Exterminador", "Torre Eiffel"
                ),
                correctAnswer = "Torre Eiffel", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            //HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("1984", "Brave New World", "The Great Gatsby", "E=mc²"),
                correctAnswer = "E=mc²", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Pride and Prejudice", "Moby Dick", "To Kill a Mockingbird", "Apollo 11"
                ),
                correctAnswer = "Apollo 11", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Hamlet", "Macbeth", "Othello", "The Moon"),
                correctAnswer = "The Moon", // Not a Shakespeare play
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "War and Peace", "Anna Karenina", "Crime and Punishment", "Da Vinci"
                ),
                correctAnswer = "Da Vinci", // Not a novel
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Harry Potter", "The Hobbit", "The Lord of the Rings", "Eiffel Tower"
                ),
                correctAnswer = "Eiffel Tower", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("The Catcher in the Rye", "Lolita", "Ulysses", "Taj Mahal"),
                correctAnswer = "Taj Mahal", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Don Quixote",
                    "The Brothers Karamazov",
                    "One Hundred Years of Solitude",
                    "Quantum Physics"
                ),
                correctAnswer = "Quantum Physics", // Not a literary work
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Jane Eyre", "Wuthering Heights", "Frankenstein", "The Eiffel Tower"
                ),
                correctAnswer = "The Eiffel Tower", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Dune", "The Road", "The Martian", "Mars Rover"),
                correctAnswer = "Mars Rover", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Animal Farm", "The Jungle", "Of Mice and Men", "Quantum Mechanics"
                ),
                correctAnswer = "Quantum Mechanics", // Not a literary work
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Cien Años de Soledad",
                    "Don Quijote",
                    "El Amor en los Tiempos del Cólera",
                    "Einstein"
                ),
                correctAnswer = "Einstein", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("La Casa de los Espíritus", "Rayuela", "Pedro Páramo", "Marte"),
                correctAnswer = "Marte", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Crónica de una Muerte Anunciada",
                    "El Coronel no Tiene Quien le Escriba",
                    "La Odisea",
                    "La Torre Eiffel"
                ),
                correctAnswer = "La Torre Eiffel", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("El Aleph", "Ficciones", "El Llano en Llamas", "Newton"),
                correctAnswer = "Newton", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("La Celestina", "Don Juan Tenorio", "Bodas de Sangre", "Árbol"),
                correctAnswer = "Árbol", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "El Lazarillo de Tormes", "La Regenta", "Fortunata y Jacinta", "Tesla"
                ),
                correctAnswer = "Tesla", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Marianela", "El Árbol de la Ciencia", "Los Pazos de Ulloa", "Física Cuántica"
                ),
                correctAnswer = "Física Cuántica", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("La Colmena", "El Camino", "Las Ratas", "Relatividad"),
                correctAnswer = "Relatividad", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "La Sombra del Viento",
                    "El Juego del Ángel",
                    "El Prisionero del Cielo",
                    "Matemáticas"
                ),
                correctAnswer = "Matemáticas", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Don Quijote", "La Biblia", "El Ingenioso Hidalgo", "Amazonas"),
                correctAnswer = "Amazonas", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun films() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Inception", "Interstellar", "The Matrix", "Apple"),
                correctAnswer = "Apple", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Titanic", "Avatar", "Gladiator", "Car"),
                correctAnswer = "Car", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("The Godfather", "The Dark Knight", "Pulp Fiction", "Bicycle"),
                correctAnswer = "Bicycle", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf(
                    "Forrest Gump", "The Shawshank Redemption", "The Green Mile", "Chair"
                ),
                correctAnswer = "Chair", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Frozen", "Moana", "The Lion King", "Tree"),
                correctAnswer = "Tree", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Jurassic Park", "Jaws", "King Kong", "Pen"),
                correctAnswer = "Pen", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Star Wars", "Star Trek", "Guardians of the Galaxy", "Plane"),
                correctAnswer = "Plane", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Avengers", "Spider-Man", "Iron Man", "Television"),
                correctAnswer = "Television", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("The Hunger Games", "Twilight", "Divergent", "Table"),
                correctAnswer = "Table", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Coco", "Toy Story", "Inside Out", "Carrot"),
                correctAnswer = "Carrot", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("El Laberinto del Fauno", "Mar Adentro", "Los Otros", "Coche"),
                correctAnswer = "Coche", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf(
                    "El Secreto de Sus Ojos", "Relatos Salvajes", "El Hijo de la Novia", "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Campeones", "Tesis", "Celda 211", "Lámpara"),
                correctAnswer = "Lámpara", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Amores Perros", "Biutiful", "Roma", "Árbol"),
                correctAnswer = "Árbol", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Ocho Apellidos Vascos", "La Isla Mínima", "Intacto", "Ventana"),
                correctAnswer = "Ventana", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("La Comunidad", "Átame", "Todo Sobre Mi Madre", "Relatividad"),
                correctAnswer = "Relatividad", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("El Espinazo del Diablo", "El Orfanato", "Rec", "Lápiz"),
                correctAnswer = "Lápiz", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Abre Los Ojos", "La Cara Oculta", "El Cuerpo", "Mesa"),
                correctAnswer = "Mesa", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("El Día de la Bestia", "El Bar", "Mi Gran Noche", "Montaña"),
                correctAnswer = "Montaña", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Volver", "Hable Con Ella", "Julieta", "Guitarra"),
                correctAnswer = "Guitarra", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "The Shawshank Redemption", "The Green Mile", "The Prestige", "Laptop"
                ),
                correctAnswer = "Laptop", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Inception", "Memento", "Tenet", "Newton"),
                correctAnswer = "Newton", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Avatar", "Interstellar", "Gravity", "Eiffel Tower"),
                correctAnswer = "Eiffel Tower", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("The Godfather", "Scarface", "Goodfellas", "Pencil"),
                correctAnswer = "Pencil", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("The Dark Knight", "Spider-Man", "Iron Man", "Tree"),
                correctAnswer = "Tree", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Jurassic Park", "Jaws", "Godzilla", "Volcano"),
                correctAnswer = "Volcano", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "Forrest Gump", "The Pursuit of Happyness", "Rain Man", "Spacesuit"
                ),
                correctAnswer = "Spacesuit", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("The Lion King", "Zootopia", "Finding Nemo", "Compass"),
                correctAnswer = "Compass", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Frozen", "Moana", "Tangled", "Bicycle"),
                correctAnswer = "Bicycle", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("The Matrix", "Blade Runner", "Minority Report", "Spaceship"),
                correctAnswer = "Spaceship", // Not a film
                category = Category.FILMS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "El Laberinto del Fauno", "El Orfanato", "Los Otros", "Relatividad"
                ),
                correctAnswer = "Relatividad", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "Mar Adentro", "El Secreto de Sus Ojos", "Relatos Salvajes", "Mesa"
                ),
                correctAnswer = "Mesa", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("El Espinazo del Diablo", "Abre Los Ojos", "El Bar", "Ventana"),
                correctAnswer = "Ventana", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Ocho Apellidos Vascos", "Celda 211", "La Isla Mínima", "Piano"),
                correctAnswer = "Piano", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Roma", "Amores Perros", "Biutiful", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Tesis", "El Cuerpo", "La Cara Oculta", "Árbol"),
                correctAnswer = "Árbol", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("La Comunidad", "Átame", "Todo Sobre Mi Madre", "Montaña"),
                correctAnswer = "Montaña", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf(
                    "El Día de la Bestia", "La Gran Familia Española", "Volver", "Coche"
                ),
                correctAnswer = "Coche", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Campeones", "Hable Con Ella", "Julieta", "Lámpara"),
                correctAnswer = "Lámpara", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("El Orfanato", "El Laberinto del Fauno", "Los Otros", "Guitarra"),
                correctAnswer = "Guitarra", // No es una película
                category = Category.FILMS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("1984", "Brave New World", "The Great Gatsby", "E=mc²"),
                correctAnswer = "E=mc²", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Pride and Prejudice", "Moby Dick", "To Kill a Mockingbird", "Apollo 11"
                ),
                correctAnswer = "Apollo 11", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Hamlet", "Macbeth", "Othello", "The Moon"),
                correctAnswer = "The Moon", // Not a Shakespeare play
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "War and Peace", "Anna Karenina", "Crime and Punishment", "Da Vinci"
                ),
                correctAnswer = "Da Vinci", // Not a novel
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Harry Potter", "The Hobbit", "The Lord of the Rings", "Eiffel Tower"
                ),
                correctAnswer = "Eiffel Tower", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("The Catcher in the Rye", "Lolita", "Ulysses", "Taj Mahal"),
                correctAnswer = "Taj Mahal", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Don Quixote",
                    "The Brothers Karamazov",
                    "One Hundred Years of Solitude",
                    "Quantum Physics"
                ),
                correctAnswer = "Quantum Physics", // Not a literary work
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Jane Eyre", "Wuthering Heights", "Frankenstein", "The Eiffel Tower"
                ),
                correctAnswer = "The Eiffel Tower", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Dune", "The Road", "The Martian", "Mars Rover"),
                correctAnswer = "Mars Rover", // Not a book
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Animal Farm", "The Jungle", "Of Mice and Men", "Quantum Mechanics"
                ),
                correctAnswer = "Quantum Mechanics", // Not a literary work
                category = Category.BOOKS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Cien Años de Soledad",
                    "Don Quijote",
                    "El Amor en los Tiempos del Cólera",
                    "Einstein"
                ),
                correctAnswer = "Einstein", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("La Casa de los Espíritus", "Rayuela", "Pedro Páramo", "Marte"),
                correctAnswer = "Marte", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Crónica de una Muerte Anunciada",
                    "El Coronel no Tiene Quien le Escriba",
                    "La Odisea",
                    "La Torre Eiffel"
                ),
                correctAnswer = "La Torre Eiffel", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("El Aleph", "Ficciones", "El Llano en Llamas", "Newton"),
                correctAnswer = "Newton", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("La Celestina", "Don Juan Tenorio", "Bodas de Sangre", "Árbol"),
                correctAnswer = "Árbol", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "El Lazarillo de Tormes", "La Regenta", "Fortunata y Jacinta", "Tesla"
                ),
                correctAnswer = "Tesla", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Marianela", "El Árbol de la Ciencia", "Los Pazos de Ulloa", "Física Cuántica"
                ),
                correctAnswer = "Física Cuántica", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("La Colmena", "El Camino", "Las Ratas", "Relatividad"),
                correctAnswer = "Relatividad", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "La Sombra del Viento",
                    "El Juego del Ángel",
                    "El Prisionero del Cielo",
                    "Matemáticas"
                ),
                correctAnswer = "Matemáticas", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Don Quijote", "La Biblia", "El Ingenioso Hidalgo", "Amazonas"),
                correctAnswer = "Amazonas", // No es un libro
                category = Category.BOOKS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun music() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Guitar", "Piano", "Drums", "Apple"),
                correctAnswer = "Apple", // Not a musical instrument
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Rock", "Jazz", "Pop", "Car"),
                correctAnswer = "Car", // Not a music genre
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Beethoven", "Mozart", "Chopin", "Laptop"),
                correctAnswer = "Laptop", // Not a composer
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Violin", "Cello", "Flute", "Chair"),
                correctAnswer = "Chair", // Not a musical instrument
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Drumsticks", "Guitar Pick", "Keyboard", "Tree"),
                correctAnswer = "Tree", // Not related to music
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("The Beatles", "The Rolling Stones", "Queen", "Pen"),
                correctAnswer = "Pen", // Not a band
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("C Major", "D Minor", "G Major", "Plane"),
                correctAnswer = "Plane", // Not a musical key
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Opera", "Symphony", "Sonata", "Television"),
                correctAnswer = "Television", // Not a musical form
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Do", "Re", "Mi", "Table"),
                correctAnswer = "Table", // Not a musical note
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Singing", "Dancing", "Composing", "Carrot"),
                correctAnswer = "Carrot", // Not related to music
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Guitarra", "Piano", "Tambores", "Manzana"),
                correctAnswer = "Manzana", // No es un instrumento musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Rock", "Jazz", "Pop", "Coche"),
                correctAnswer = "Coche", // No es un género musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Beethoven", "Mozart", "Chopin", "Portátil"),
                correctAnswer = "Portátil", // No es un compositor
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Violín", "Violonchelo", "Flauta", "Silla"),
                correctAnswer = "Silla", // No es un instrumento musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Baquetas", "Púa", "Teclado", "Árbol"),
                correctAnswer = "Árbol", // No está relacionado con la música
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Los Beatles", "Los Rolling Stones", "Queen", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una banda
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Do Mayor", "Re Menor", "Sol Mayor", "Avión"),
                correctAnswer = "Avión", // No es una tonalidad musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Ópera", "Sinfonía", "Sonata", "Televisión"),
                correctAnswer = "Televisión", // No es una forma musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Do", "Re", "Mi", "Mesa"),
                correctAnswer = "Mesa", // No es una nota musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ), FamiliesEntity(
                answers = listOf("Cantar", "Bailar", "Componer", "Zanahoria"),
                correctAnswer = "Zanahoria", // No está relacionado con la música
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Guitar", "Violin", "Saxophone", "Table"),
                correctAnswer = "Table", // Not a musical instrument
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Jazz", "Blues", "Reggae", "Bicycle"),
                correctAnswer = "Bicycle", // Not a music genre
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Beethoven", "Bach", "Mozart", "Newton"),
                correctAnswer = "Newton", // Not a composer
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("C Major", "D Minor", "F Sharp", "Eiffel Tower"),
                correctAnswer = "Eiffel Tower", // Not a musical key
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Symphony", "Opera", "Concerto", "Television"),
                correctAnswer = "Television", // Not a musical form
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("The Beatles", "Pink Floyd", "Led Zeppelin", "Pencil"),
                correctAnswer = "Pencil", // Not a band
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Piano", "Keyboard", "Synthesizer", "Tree"),
                correctAnswer = "Tree", // Not a musical instrument
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Do", "Re", "Mi", "Spacesuit"),
                correctAnswer = "Spacesuit", // Not a musical note
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Singing", "Composing", "Conducting", "Car"),
                correctAnswer = "Car", // Not a musical activity
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Rock", "Metal", "Classical", "Mars Rover"),
                correctAnswer = "Mars Rover", // Not a music genre
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Guitarra", "Violín", "Saxofón", "Mesa"),
                correctAnswer = "Mesa", // No es un instrumento musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Jazz", "Blues", "Reggae", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un género musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Beethoven", "Bach", "Mozart", "Einstein"),
                correctAnswer = "Einstein", // No es un compositor
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Do Mayor", "Re Menor", "Fa Sostenido", "Torre Eiffel"),
                correctAnswer = "Torre Eiffel", // No es una tonalidad musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Sinfonía", "Ópera", "Concierto", "Televisión"),
                correctAnswer = "Televisión", // No es una forma musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Los Beatles", "Pink Floyd", "Led Zeppelin", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una banda
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Piano", "Teclado", "Sintetizador", "Árbol"),
                correctAnswer = "Árbol", // No es un instrumento musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Do", "Re", "Mi", "Traje Espacial"),
                correctAnswer = "Traje Espacial", // No es una nota musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Cantar", "Componer", "Dirigir", "Coche"),
                correctAnswer = "Coche", // No es una actividad musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ), FamiliesEntity(
                answers = listOf("Rock", "Metal", "Clásica", "Rover de Marte"),
                correctAnswer = "Rover de Marte", // No es un género musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Symphony No. 5", "Eine kleine Nachtmusik", "Clair de Lune", "The Moon"
                ),
                correctAnswer = "The Moon", // Not a musical composition
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Ludwig van Beethoven",
                    "Johann Sebastian Bach",
                    "Wolfgang Amadeus Mozart",
                    "Isaac Newton"
                ),
                correctAnswer = "Isaac Newton", // Not a composer
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Jazz", "Blues", "Baroque", "Relativity"),
                correctAnswer = "Relativity", // Not a music style
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Crescendo", "Legato", "Staccato", "Volcano"),
                correctAnswer = "Volcano", // Not a musical term
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Fugue", "Canon", "Sonata", "Spaceship"),
                correctAnswer = "Spaceship", // Not a musical form
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Do", "Re", "Mi", "Einstein"),
                correctAnswer = "Einstein", // Not a musical note
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Guitar", "Harp", "Lyre", "Bicycle"),
                correctAnswer = "Bicycle", // Not a musical instrument
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Counterpoint", "Harmony", "Orchestration", "Eiffel Tower"),
                correctAnswer = "Eiffel Tower", // Not a musical technique
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Palestrina", "Monteverdi", "Vivaldi", "Tesla"),
                correctAnswer = "Tesla", // Not a composer
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Allegro", "Adagio", "Presto", "Tree"),
                correctAnswer = "Tree", // Not a tempo marking
                category = Category.MUSIC,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Sinfonía Nº5", "Pequeña Serenata Nocturna", "Claro de Luna", "La Luna"
                ),
                correctAnswer = "La Luna", // No es una composición musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf(
                    "Ludwig van Beethoven",
                    "Johann Sebastian Bach",
                    "Wolfgang Amadeus Mozart",
                    "Isaac Newton"
                ),
                correctAnswer = "Isaac Newton", // No es un compositor
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Jazz", "Blues", "Barroco", "Relatividad"),
                correctAnswer = "Relatividad", // No es un estilo musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Crescendo", "Legato", "Staccato", "Volcán"),
                correctAnswer = "Volcán", // No es un término musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Fuga", "Canon", "Sonata", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es una forma musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Do", "Re", "Mi", "Einstein"),
                correctAnswer = "Einstein", // No es una nota musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Guitarra", "Arpa", "Lira", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un instrumento musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Contrapunto", "Armonía", "Orquestación", "Torre Eiffel"),
                correctAnswer = "Torre Eiffel", // No es una técnica musical
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Palestrina", "Monteverdi", "Vivaldi", "Tesla"),
                correctAnswer = "Tesla", // No es un compositor
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ), FamiliesEntity(
                answers = listOf("Allegro", "Adagio", "Presto", "Árbol"),
                correctAnswer = "Árbol", // No es una indicación de tempo
                category = Category.MUSIC,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun musicalAndTheatres() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("The Phantom of the Opera", "Les Misérables", "Cats", "Laptop"),
                correctAnswer = "Laptop", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Hamilton", "Wicked", "Chicago", "Car"),
                correctAnswer = "Car", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Lion King", "Aladdin", "Frozen", "Apple"),
                correctAnswer = "Apple", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Grease", "Mamma Mia!", "Hairspray", "Table"),
                correctAnswer = "Table", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Sound of Music", "West Side Story", "My Fair Lady", "Chair"),
                correctAnswer = "Chair", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Rent", "Annie", "Oliver!", "Pen"),
                correctAnswer = "Pen", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cabaret", "Evita", "Billy Elliot", "Tree"),
                correctAnswer = "Tree", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Matilda", "The Book of Mormon", "School of Rock", "Television"),
                correctAnswer = "Television", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Dreamgirls", "Kinky Boots", "Once", "Bicycle"),
                correctAnswer = "Bicycle", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Producers", "Spamalot", "Avenue Q", "Spaceship"),
                correctAnswer = "Spaceship", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("El Rey León", "Los Miserables", "Cats", "Manzana"),
                correctAnswer = "Manzana", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Hamilton", "Wicked", "Chicago", "Coche"),
                correctAnswer = "Coche", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("El Fantasma de la Ópera", "Mamma Mia!", "Hairspray", "Mesa"),
                correctAnswer = "Mesa", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("La Bella y la Bestia", "Aladdín", "Frozen", "Silla"),
                correctAnswer = "Silla", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Grease", "West Side Story", "My Fair Lady", "Árbol"),
                correctAnswer = "Árbol", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("El Sonido de la Música", "Rent", "Annie", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Evita", "Matilda", "Billy Elliot", "Árbol"),
                correctAnswer = "Árbol", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cabaret", "Dreamgirls", "School of Rock", "Televisión"),
                correctAnswer = "Televisión", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Los Productores", "Spamalot", "Avenue Q", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Kinky Boots", "Once", "La La Land", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Hamilton",
                    "Les Misérables",
                    "The Phantom of the Opera",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Wicked", "Cats", "Chicago", "Einstein"),
                correctAnswer = "Einstein", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Aladdin", "Frozen", "The Lion King", "Relativity"),
                correctAnswer = "Relativity", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Rent", "Hairspray", "Grease", "Volcano"),
                correctAnswer = "Volcano", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("The Book of Mormon", "Matilda", "School of Rock", "Laptop"),
                correctAnswer = "Laptop", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Dreamgirls", "Kinky Boots", "Avenue Q", "Tree"),
                correctAnswer = "Tree", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("The Sound of Music", "West Side Story", "My Fair Lady", "Pencil"),
                correctAnswer = "Pencil", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Evita", "Cabaret", "Billy Elliot", "Television"),
                correctAnswer = "Television", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("The Producers", "Spamalot", "La La Land", "Mars Rover"),
                correctAnswer = "Mars Rover", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Mamma Mia!", "Hairspray", "Into the Woods", "Bicycle"),
                correctAnswer = "Bicycle", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "El Rey León",
                    "Los Miserables",
                    "El Fantasma de la Ópera",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hamilton", "Wicked", "Chicago", "Einstein"),
                correctAnswer = "Einstein", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Aladdín", "Frozen", "La Bella y la Bestia", "Relatividad"),
                correctAnswer = "Relatividad", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Rent", "Grease", "Hairspray", "Volcán"),
                correctAnswer = "Volcán", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("El Libro del Mormón", "Matilda", "School of Rock", "Portátil"),
                correctAnswer = "Portátil", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Sueños de Fama", "Kinky Boots", "Avenue Q", "Árbol"),
                correctAnswer = "Árbol", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "El Sonido de la Música",
                    "West Side Story",
                    "My Fair Lady",
                    "Lápiz"
                ),
                correctAnswer = "Lápiz", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Evita", "Cabaret", "Billy Elliot", "Televisión"),
                correctAnswer = "Televisión", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Los Productores", "Spamalot", "La La Land", "Rover de Marte"),
                correctAnswer = "Rover de Marte", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Mamma Mia!", "Hairspray", "Into the Woods", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "The Phantom of the Opera",
                    "Les Misérables",
                    "The Book of Mormon",
                    "Eiffel Tower"
                ),
                correctAnswer = "Eiffel Tower", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Wicked", "Hamilton", "Chicago", "Tesla"),
                correctAnswer = "Tesla", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("The Lion King", "Frozen", "Aladdin", "Quantum Mechanics"),
                correctAnswer = "Quantum Mechanics", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Cabaret", "Evita", "Billy Elliot", "Relativity"),
                correctAnswer = "Relativity", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Rent", "Hairspray", "Grease", "Volcano"),
                correctAnswer = "Volcano", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Matilda", "School of Rock", "Dreamgirls", "Spaceship"),
                correctAnswer = "Spaceship", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Sound of Music",
                    "My Fair Lady",
                    "West Side Story",
                    "Mars Rover"
                ),
                correctAnswer = "Mars Rover", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Into the Woods", "Mamma Mia!", "The Producers", "Newton"),
                correctAnswer = "Newton", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Kinky Boots", "Spamalot", "Avenue Q", "Bach"),
                correctAnswer = "Bach", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hairspray", "La La Land", "Dreamgirls", "Tree"),
                correctAnswer = "Tree", // Not a musical or theatre production
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "El Fantasma de la Ópera",
                    "Los Miserables",
                    "El Libro del Mormón",
                    "Torre Eiffel"
                ),
                correctAnswer = "Torre Eiffel", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hamilton", "Chicago", "Wicked", "Tesla"),
                correctAnswer = "Tesla", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("El Rey León", "Frozen", "Aladdín", "Mecánica Cuántica"),
                correctAnswer = "Mecánica Cuántica", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Cabaret", "Evita", "Billy Elliot", "Relatividad"),
                correctAnswer = "Relatividad", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Rent", "Hairspray", "Grease", "Volcán"),
                correctAnswer = "Volcán", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Matilda", "School of Rock", "Sueños de Fama", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "El Sonido de la Música",
                    "My Fair Lady",
                    "West Side Story",
                    "Rover de Marte"
                ),
                correctAnswer = "Rover de Marte", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Into the Woods", "Mamma Mia!", "Los Productores", "Newton"),
                correctAnswer = "Newton", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Kinky Boots", "Spamalot", "Avenue Q", "Bach"),
                correctAnswer = "Bach", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("La La Land", "Sueños de Fama", "Cabaret", "Árbol"),
                correctAnswer = "Árbol", // No es un musical o teatro
                category = Category.MUSICALS_AND_THEATRES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun television() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Friends", "Breaking Bad", "Game of Thrones", "Apple"),
                correctAnswer = "Apple", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Office", "Parks and Recreation", "Brooklyn Nine-Nine", "Car"),
                correctAnswer = "Car", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Simpsons", "Family Guy", "Rick and Morty", "Laptop"),
                correctAnswer = "Laptop", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Stranger Things", "The Witcher", "The Mandalorian", "Table"),
                correctAnswer = "Table", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Crown", "Downton Abbey", "Peaky Blinders", "Chair"),
                correctAnswer = "Chair", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Sherlock", "Luther", "Broadchurch", "Pen"),
                correctAnswer = "Pen", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Grey's Anatomy", "House", "Scrubs", "Tree"),
                correctAnswer = "Tree", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "How I Met Your Mother",
                    "Two and a Half Men",
                    "Big Bang Theory",
                    "Television"
                ),
                correctAnswer = "Television", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Boys", "The Umbrella Academy", "Daredevil", "Bicycle"),
                correctAnswer = "Bicycle", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Walking Dead",
                    "Supernatural",
                    "Buffy the Vampire Slayer",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("La Casa de Papel", "Vis a Vis", "Élite", "Manzana"),
                correctAnswer = "Manzana", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Los Serrano",
                    "Aquí No Hay Quien Viva",
                    "La Que Se Avecina",
                    "Coche"
                ),
                correctAnswer = "Coche", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cuéntame Cómo Pasó", "El Internado", "Gran Hotel", "Lámpara"),
                correctAnswer = "Lámpara", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Velvet", "Las Chicas del Cable", "Galerías Paradise", "Mesa"),
                correctAnswer = "Mesa", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Isabel",
                    "El Ministerio del Tiempo",
                    "Señor, Dame Paciencia",
                    "Silla"
                ),
                correctAnswer = "Silla", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("El Comisario", "Los Hombres de Paco", "Policías", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Hospital Central", "Centro Médico", "Doctor Mateo", "Árbol"),
                correctAnswer = "Árbol", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Al Salir de Clase", "Compañeros", "Física o Química", "Ventana"),
                correctAnswer = "Ventana", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("MasterChef", "Operación Triunfo", "First Dates", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pasapalabra", "Saber y Ganar", "Ahora Caigo", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Breaking Bad", "Mad Men", "The Sopranos", "Spaceship"),
                correctAnswer = "Spaceship", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Stranger Things", "The Witcher", "The Mandalorian", "Einstein"),
                correctAnswer = "Einstein", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("The Crown", "Downton Abbey", "Peaky Blinders", "Tesla"),
                correctAnswer = "Tesla", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("The Boys", "The Umbrella Academy", "Daredevil", "Volcano"),
                correctAnswer = "Volcano", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "How I Met Your Mother",
                    "Two and a Half Men",
                    "Big Bang Theory",
                    "Relativity"
                ),
                correctAnswer = "Relativity", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Sherlock", "Luther", "Broadchurch", "Spacesuit"),
                correctAnswer = "Spacesuit", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Walking Dead",
                    "Supernatural",
                    "Buffy the Vampire Slayer",
                    "Newton"
                ),
                correctAnswer = "Newton", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Grey's Anatomy", "House", "Scrubs", "Mars Rover"),
                correctAnswer = "Mars Rover", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Game of Thrones", "The Wire", "Fargo", "Tree"),
                correctAnswer = "Tree", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Office",
                    "Parks and Recreation",
                    "Brooklyn Nine-Nine",
                    "Bicycle"
                ),
                correctAnswer = "Bicycle", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("La Casa de Papel", "Vis a Vis", "Élite", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Los Serrano",
                    "Aquí No Hay Quien Viva",
                    "La Que Se Avecina",
                    "Einstein"
                ),
                correctAnswer = "Einstein", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Cuéntame Cómo Pasó", "El Internado", "Gran Hotel", "Tesla"),
                correctAnswer = "Tesla", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Velvet", "Las Chicas del Cable", "Galerías Paradise", "Volcán"),
                correctAnswer = "Volcán", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Isabel",
                    "El Ministerio del Tiempo",
                    "Señor, Dame Paciencia",
                    "Relatividad"
                ),
                correctAnswer = "Relatividad", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "El Comisario",
                    "Los Hombres de Paco",
                    "Policías",
                    "Traje Espacial"
                ),
                correctAnswer = "Traje Espacial", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Hospital Central",
                    "Centro Médico",
                    "Doctor Mateo",
                    "Rover de Marte"
                ),
                correctAnswer = "Rover de Marte", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Al Salir de Clase", "Compañeros", "Física o Química", "Newton"),
                correctAnswer = "Newton", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("MasterChef", "Operación Triunfo", "First Dates", "Árbol"),
                correctAnswer = "Árbol", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Pasapalabra", "Saber y Ganar", "Ahora Caigo", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Breaking Bad", "The Sopranos", "Mad Men", "Quantum Physics"),
                correctAnswer = "Quantum Physics", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("The Wire", "Fargo", "Twin Peaks", "Eiffel Tower"),
                correctAnswer = "Eiffel Tower", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Boardwalk Empire", "Ozark", "The Americans", "Relativity"),
                correctAnswer = "Relativity", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Succession", "Better Call Saul", "The Leftovers", "Spaceship"),
                correctAnswer = "Spaceship", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("The Crown", "The Handmaid’s Tale", "Big Little Lies", "Newton"),
                correctAnswer = "Newton", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Watchmen", "Lovecraft Country", "The Mandalorian", "Tree"),
                correctAnswer = "Tree", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Six Feet Under", "Hannibal", "True Detective", "Tesla"),
                correctAnswer = "Tesla", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Sherlock", "Luther", "Broadchurch", "Mars Rover"),
                correctAnswer = "Mars Rover", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Homeland", "24", "Prison Break", "Spacesuit"),
                correctAnswer = "Spacesuit", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("The Boys", "Daredevil", "The Umbrella Academy", "Volcano"),
                correctAnswer = "Volcano", // Not a TV show
                category = Category.TELEVISION,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("La Casa de Papel", "Vis a Vis", "Élite", "Física Cuántica"),
                correctAnswer = "Física Cuántica", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "El Ministerio del Tiempo",
                    "Isabel",
                    "Gran Hotel",
                    "Torre Eiffel"
                ),
                correctAnswer = "Torre Eiffel", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Velvet",
                    "Las Chicas del Cable",
                    "Galerías Paradise",
                    "Relatividad"
                ),
                correctAnswer = "Relatividad", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Física o Química",
                    "Al Salir de Clase",
                    "Compañeros",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Los Serrano",
                    "Aquí No Hay Quien Viva",
                    "La Que Se Avecina",
                    "Newton"
                ),
                correctAnswer = "Newton", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("El Comisario", "Los Hombres de Paco", "Policías", "Árbol"),
                correctAnswer = "Árbol", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hospital Central", "Centro Médico", "Doctor Mateo", "Tesla"),
                correctAnswer = "Tesla", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Pasapalabra", "Saber y Ganar", "Ahora Caigo", "Rover de Marte"),
                correctAnswer = "Rover de Marte", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("MasterChef", "Operación Triunfo", "First Dates", "Volcán"),
                correctAnswer = "Volcán", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("El Internado", "Gran Reserva", "Bajo Sospecha", "Traje Espacial"),
                correctAnswer = "Traje Espacial", // No es un programa de televisión
                category = Category.TELEVISION,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun videoGames() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Minecraft", "Fortnite", "Among Us", "Apple"),
                correctAnswer = "Apple", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Call of Duty", "Battlefield", "Halo", "Car"),
                correctAnswer = "Car", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Legend of Zelda", "Super Mario Bros", "Metroid", "Laptop"),
                correctAnswer = "Laptop", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("League of Legends", "Dota 2", "Overwatch", "Table"),
                correctAnswer = "Table", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Grand Theft Auto",
                    "Red Dead Redemption",
                    "Cyberpunk 2077",
                    "Chair"
                ),
                correctAnswer = "Chair", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Animal Crossing", "Stardew Valley", "The Sims", "Pen"),
                correctAnswer = "Pen", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("FIFA", "PES", "NBA 2K", "Tree"),
                correctAnswer = "Tree", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Dark Souls", "Elden Ring", "Bloodborne", "Television"),
                correctAnswer = "Television", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pokemon", "Digimon", "Monster Hunter", "Bicycle"),
                correctAnswer = "Bicycle", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tetris", "Pac-Man", "Space Invaders", "Spaceship"),
                correctAnswer = "Spaceship", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Minecraft", "Fortnite", "Among Us", "Manzana"),
                correctAnswer = "Manzana", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Call of Duty", "Battlefield", "Halo", "Coche"),
                correctAnswer = "Coche", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Legend of Zelda", "Super Mario Bros", "Metroid", "Portátil"),
                correctAnswer = "Portátil", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("League of Legends", "Dota 2", "Overwatch", "Mesa"),
                correctAnswer = "Mesa", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Grand Theft Auto",
                    "Red Dead Redemption",
                    "Cyberpunk 2077",
                    "Silla"
                ),
                correctAnswer = "Silla", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Animal Crossing", "Stardew Valley", "The Sims", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("FIFA", "PES", "NBA 2K", "Árbol"),
                correctAnswer = "Árbol", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Dark Souls", "Elden Ring", "Bloodborne", "Televisión"),
                correctAnswer = "Televisión", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pokemon", "Digimon", "Monster Hunter", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tetris", "Pac-Man", "Space Invaders", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("The Witcher 3", "Elden Ring", "Dark Souls", "Spaceship"),
                correctAnswer = "Spaceship", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hollow Knight", "Ori and the Blind Forest", "Celeste", "Apple"),
                correctAnswer = "Apple", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Half-Life", "Portal", "BioShock", "Eiffel Tower"),
                correctAnswer = "Eiffel Tower", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Red Dead Redemption 2",
                    "Grand Theft Auto V",
                    "Cyberpunk 2077",
                    "Relativity"
                ),
                correctAnswer = "Relativity", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Overwatch", "Team Fortress 2", "Paladins", "Tree"),
                correctAnswer = "Tree", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Stardew Valley", "Harvest Moon", "Animal Crossing", "Newton"),
                correctAnswer = "Newton", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("League of Legends", "Dota 2", "Smite", "Tesla"),
                correctAnswer = "Tesla", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Legend of Zelda: Breath of the Wild",
                    "Super Mario Odyssey",
                    "Metroid Dread",
                    "Spacesuit"
                ),
                correctAnswer = "Spacesuit", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Pokemon Red", "Pokemon Blue", "Pokemon Yellow", "Bicycle"),
                correctAnswer = "Bicycle", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("The Last of Us", "Uncharted", "Horizon Zero Dawn", "Volcano"),
                correctAnswer = "Volcano", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("The Witcher 3", "Elden Ring", "Dark Souls", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hollow Knight", "Ori and the Blind Forest", "Celeste", "Manzana"),
                correctAnswer = "Manzana", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Half-Life", "Portal", "BioShock", "Torre Eiffel"),
                correctAnswer = "Torre Eiffel", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Red Dead Redemption 2",
                    "Grand Theft Auto V",
                    "Cyberpunk 2077",
                    "Relatividad"
                ),
                correctAnswer = "Relatividad", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Overwatch", "Team Fortress 2", "Paladins", "Árbol"),
                correctAnswer = "Árbol", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Stardew Valley", "Harvest Moon", "Animal Crossing", "Newton"),
                correctAnswer = "Newton", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("League of Legends", "Dota 2", "Smite", "Tesla"),
                correctAnswer = "Tesla", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Legend of Zelda: Breath of the Wild",
                    "Super Mario Odyssey",
                    "Metroid Dread",
                    "Traje Espacial"
                ),
                correctAnswer = "Traje Espacial", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Pokemon Rojo", "Pokemon Azul", "Pokemon Amarillo", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("The Last of Us", "Uncharted", "Horizon Zero Dawn", "Volcán"),
                correctAnswer = "Volcán", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Disco Elysium",
                    "Planescape: Torment",
                    "Baldur's Gate",
                    "Relativity"
                ),
                correctAnswer = "Relativity", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("System Shock 2", "Deus Ex", "Prey", "Tesla"),
                correctAnswer = "Tesla", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Sekiro: Shadows Die Twice",
                    "Nioh",
                    "Ghost of Tsushima",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Persona 5", "Shin Megami Tensei V", "Catherine", "Newton"),
                correctAnswer = "Newton", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Divinity: Original Sin 2",
                    "Pillars of Eternity",
                    "Wasteland 3",
                    "Eiffel Tower"
                ),
                correctAnswer = "Eiffel Tower", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hollow Knight", "Cuphead", "Celeste", "Tree"),
                correctAnswer = "Tree", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("The Outer Worlds", "No Man's Sky", "Mass Effect", "Spacesuit"),
                correctAnswer = "Spacesuit", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Chrono Trigger", "EarthBound", "Final Fantasy VI", "Volcano"),
                correctAnswer = "Volcano", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Stellaris",
                    "Crusader Kings III",
                    "Europa Universalis IV",
                    "Bicycle"
                ),
                correctAnswer = "Bicycle", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("The Witness", "Myst", "Obduction", "Relativity"),
                correctAnswer = "Relativity", // Not a video game
                category = Category.VIDEO_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Disco Elysium",
                    "Planescape: Torment",
                    "Baldur's Gate",
                    "Relatividad"
                ),
                correctAnswer = "Relatividad", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("System Shock 2", "Deus Ex", "Prey", "Tesla"),
                correctAnswer = "Tesla", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Sekiro: Shadows Die Twice",
                    "Nioh",
                    "Ghost of Tsushima",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Persona 5", "Shin Megami Tensei V", "Catherine", "Newton"),
                correctAnswer = "Newton", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Divinity: Original Sin 2",
                    "Pillars of Eternity",
                    "Wasteland 3",
                    "Torre Eiffel"
                ),
                correctAnswer = "Torre Eiffel", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hollow Knight", "Cuphead", "Celeste", "Árbol"),
                correctAnswer = "Árbol", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Outer Worlds",
                    "No Man's Sky",
                    "Mass Effect",
                    "Traje Espacial"
                ),
                correctAnswer = "Traje Espacial", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Chrono Trigger", "EarthBound", "Final Fantasy VI", "Volcán"),
                correctAnswer = "Volcán", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Stellaris",
                    "Crusader Kings III",
                    "Europa Universalis IV",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("The Witness", "Myst", "Obduction", "Física Cuántica"),
                correctAnswer = "Física Cuántica", // No es un videojuego
                category = Category.VIDEO_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun boardGames() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Monopoly", "Catan", "Clue", "Apple"),
                correctAnswer = "Apple", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Scrabble", "Boggle", "BananaGrams", "Car"),
                correctAnswer = "Car", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Chess", "Checkers", "Go", "Laptop"),
                correctAnswer = "Laptop", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Risk", "Stratego", "Axis & Allies", "Table"),
                correctAnswer = "Table", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Ticket to Ride", "Carcassonne", "Pandemic", "Chair"),
                correctAnswer = "Chair", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Game of Life", "Sorry!", "Trouble", "Pen"),
                correctAnswer = "Pen", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Battleship", "Guess Who?", "Connect Four", "Tree"),
                correctAnswer = "Tree", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Candy Land", "Chutes and Ladders", "Operation", "Television"),
                correctAnswer = "Television", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Clue", "Monopoly", "Risk", "Bicycle"),
                correctAnswer = "Bicycle", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pictionary", "Taboo", "Scattergories", "Spaceship"),
                correctAnswer = "Spaceship", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Monopoly", "Catan", "Cluedo", "Manzana"),
                correctAnswer = "Manzana", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Scrabble", "Boggle", "BananaGrams", "Coche"),
                correctAnswer = "Coche", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Ajedrez", "Damas", "Go", "Portátil"),
                correctAnswer = "Portátil", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Risk", "Stratego", "Axis & Allies", "Mesa"),
                correctAnswer = "Mesa", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Aventureros al Tren", "Carcassonne", "Pandemic", "Silla"),
                correctAnswer = "Silla", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("La Isla", "Dixit", "7 Wonders", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Hundir la Flota", "¿Quién es Quién?", "Conecta 4", "Árbol"),
                correctAnswer = "Árbol", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Candy Land", "Escaleras y Serpientes", "Operación", "Televisión"),
                correctAnswer = "Televisión", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cluedo", "Monopoly", "Risk", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pictionary", "Tabú", "Scattergories", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Catan", "Carcassonne", "Ticket to Ride", "Spaceship"),
                correctAnswer = "Spaceship", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Agricola", "Viticulture", "Terra Mystica", "Einstein"),
                correctAnswer = "Einstein", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Pandemic", "Dead of Winter", "Zombicide", "Relativity"),
                correctAnswer = "Relativity", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("7 Wonders", "Dominion", "Race for the Galaxy", "Tree"),
                correctAnswer = "Tree", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Gloomhaven", "Mage Knight", "Descent", "Newton"),
                correctAnswer = "Newton", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Twilight Imperium", "Eclipse", "Scythe", "Tesla"),
                correctAnswer = "Tesla", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Codenames", "Decrypto", "Just One", "Spacesuit"),
                correctAnswer = "Spacesuit", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Patchwork", "Calico", "Azul", "Volcano"),
                correctAnswer = "Volcano", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Root", "Everdell", "Wingspan", "Bicycle"),
                correctAnswer = "Bicycle", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Terraforming Mars", "Ark Nova", "Gaia Project", "Eiffel Tower"),
                correctAnswer = "Eiffel Tower", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Catan", "Carcassonne", "Aventureros al Tren", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Agrícola", "Viticulture", "Terra Mystica", "Einstein"),
                correctAnswer = "Einstein", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Pandemic", "Dead of Winter", "Zombicide", "Relatividad"),
                correctAnswer = "Relatividad", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("7 Wonders", "Dominion", "Race for the Galaxy", "Árbol"),
                correctAnswer = "Árbol", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Gloomhaven", "Mage Knight", "Descent", "Newton"),
                correctAnswer = "Newton", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Twilight Imperium", "Eclipse", "Scythe", "Tesla"),
                correctAnswer = "Tesla", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Codenames", "Decrypto", "Just One", "Traje Espacial"),
                correctAnswer = "Traje Espacial", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Patchwork", "Calico", "Azul", "Volcán"),
                correctAnswer = "Volcán", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Root", "Everdell", "Wingspan", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Terraforming Mars", "Ark Nova", "Gaia Project", "Torre Eiffel"),
                correctAnswer = "Torre Eiffel", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Through the Ages",
                    "Twilight Imperium",
                    "Brass: Birmingham",
                    "Relativity"
                ),
                correctAnswer = "Relativity", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Pax Pamir", "Pax Renaissance", "Pax Viking", "Spaceship"),
                correctAnswer = "Spaceship", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Lisboa", "The Gallerist", "Kanban EV", "Tesla"),
                correctAnswer = "Tesla", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("On Mars", "Terraforming Mars", "Gaia Project", "Newton"),
                correctAnswer = "Newton", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Gloomhaven", "Frosthaven", "Kingdom Death: Monster", "Tree"),
                correctAnswer = "Tree", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Arkham Horror",
                    "Eldritch Horror",
                    "Mansions of Madness",
                    "Bicycle"
                ),
                correctAnswer = "Bicycle", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Mage Knight", "Spirit Island", "Robinson Crusoe", "Volcano"),
                correctAnswer = "Volcano", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Food Chain Magnate",
                    "Dominant Species",
                    "Antiquity",
                    "Spacesuit"
                ),
                correctAnswer = "Spacesuit", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Tigris & Euphrates", "Ra", "The Great Zimbabwe", "Eiffel Tower"),
                correctAnswer = "Eiffel Tower", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Root", "Oath", "Pax Pamir", "Fission"),
                correctAnswer = "Fission", // Not a board game
                category = Category.BOARD_GAMES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "A Través de los Siglos",
                    "Twilight Imperium",
                    "Brass: Birmingham",
                    "Relatividad"
                ),
                correctAnswer = "Relatividad", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Pax Pamir", "Pax Renaissance", "Pax Viking", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Lisboa", "The Gallerist", "Kanban EV", "Tesla"),
                correctAnswer = "Tesla", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("En Marte", "Terraforming Mars", "Gaia Project", "Newton"),
                correctAnswer = "Newton", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Gloomhaven", "Frosthaven", "Kingdom Death: Monster", "Árbol"),
                correctAnswer = "Árbol", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Horror en Arkham",
                    "Horror Cósmico",
                    "Mansiones de la Locura",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Mage Knight", "Spirit Island", "Robinson Crusoe", "Volcán"),
                correctAnswer = "Volcán", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Food Chain Magnate",
                    "Dominant Species",
                    "Antiquity",
                    "Traje Espacial"
                ),
                correctAnswer = "Traje Espacial", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Tigris y Éufrates", "Ra", "The Great Zimbabwe", "Torre Eiffel"),
                correctAnswer = "Torre Eiffel", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Root", "Oath", "Pax Pamir", "Fisión"),
                correctAnswer = "Fisión", // No es un juego de mesa
                category = Category.BOARD_GAMES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun scienceAndNature() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Oxygen", "Hydrogen", "Nitrogen", "Apple"),
                correctAnswer = "Apple", // Not a chemical element
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Dog", "Cat", "Elephant", "Car"),
                correctAnswer = "Car", // Not an animal
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Earth", "Mars", "Jupiter", "Table"),
                correctAnswer = "Table", // Not a planet
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Rose", "Tulip", "Sunflower", "Chair"),
                correctAnswer = "Chair", // Not a flower
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Carbon", "Oxygen", "Gold", "Pen"),
                correctAnswer = "Pen", // Not a chemical element
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Sun", "Moon", "Earth", "Tree"),
                correctAnswer = "Tree", // Not a celestial body
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Lion", "Tiger", "Bear", "Television"),
                correctAnswer = "Television", // Not an animal
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Oak", "Pine", "Maple", "Spaceship"),
                correctAnswer = "Spaceship", // Not a tree
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Water", "Air", "Fire", "Bicycle"),
                correctAnswer = "Bicycle", // Not an element
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Shark", "Whale", "Dolphin", "Lamp"),
                correctAnswer = "Lamp", // Not a marine animal
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Oxígeno", "Hidrógeno", "Nitrógeno", "Manzana"),
                correctAnswer = "Manzana", // No es un elemento químico
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Perro", "Gato", "Elefante", "Coche"),
                correctAnswer = "Coche", // No es un animal
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tierra", "Marte", "Júpiter", "Mesa"),
                correctAnswer = "Mesa", // No es un planeta
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Rosa", "Tulipán", "Girasol", "Silla"),
                correctAnswer = "Silla", // No es una flor
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Carbono", "Oxígeno", "Oro", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un elemento químico
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Sol", "Luna", "Tierra", "Árbol"),
                correctAnswer = "Árbol", // No es un cuerpo celeste
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("León", "Tigre", "Oso", "Televisión"),
                correctAnswer = "Televisión", // No es un animal
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Roble", "Pino", "Arce", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un árbol
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Agua", "Aire", "Fuego", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un elemento
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tiburón", "Ballena", "Delfín", "Lámpara"),
                correctAnswer = "Lámpara", // No es un animal marino
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Proton", "Neutron", "Electron", "Spaceship"),
                correctAnswer = "Spaceship", // Not a subatomic particle
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Mercury", "Venus", "Neptune", "Newton"),
                correctAnswer = "Newton", // Not a planet
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Photosynthesis", "Respiration", "Fermentation", "Car"),
                correctAnswer = "Car", // Not a biological process
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Volcano", "Mountain", "Glacier", "Television"),
                correctAnswer = "Television", // Not a natural formation
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hummingbird", "Penguin", "Ostrich", "Tree"),
                correctAnswer = "Tree", // Not a bird
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Carbon Dioxide", "Methane", "Nitrous Oxide", "Laptop"),
                correctAnswer = "Laptop", // Not a greenhouse gas
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Mitochondria", "Nucleus", "Ribosome", "Chair"),
                correctAnswer = "Chair", // Not a part of a cell
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Iron", "Copper", "Silver", "Spacesuit"),
                correctAnswer = "Spacesuit", // Not a metal
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Tornado", "Hurricane", "Earthquake", "Bicycle"),
                correctAnswer = "Bicycle", // Not a natural disaster
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Mars Rover", "Saturn V", "Voyager", "Oak"),
                correctAnswer = "Oak", // Not a space exploration device
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Protón", "Neutrón", "Electrón", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es una partícula subatómica
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Mercurio", "Venus", "Neptuno", "Newton"),
                correctAnswer = "Newton", // No es un planeta
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Fotosíntesis", "Respiración", "Fermentación", "Coche"),
                correctAnswer = "Coche", // No es un proceso biológico
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Volcán", "Montaña", "Glaciar", "Televisión"),
                correctAnswer = "Televisión", // No es una formación natural
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Colibrí", "Pingüino", "Avestruz", "Árbol"),
                correctAnswer = "Árbol", // No es un ave
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Dióxido de Carbono", "Metano", "Óxido Nitroso", "Portátil"),
                correctAnswer = "Portátil", // No es un gas de efecto invernadero
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Mitocondria", "Núcleo", "Ribosoma", "Silla"),
                correctAnswer = "Silla", // No es una parte de la célula
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hierro", "Cobre", "Plata", "Traje Espacial"),
                correctAnswer = "Traje Espacial", // No es un metal
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Tornado", "Huracán", "Terremoto", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un desastre natural
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Mars Rover", "Saturn V", "Voyager", "Roble"),
                correctAnswer = "Roble", // No es un dispositivo de exploración espacial
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Higgs Boson", "Photon", "Quark", "Relativity"),
                correctAnswer = "Relativity", // Not a subatomic particle
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Proxima Centauri", "Sirius", "Vega", "Eiffel Tower"),
                correctAnswer = "Eiffel Tower", // Not a star
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Igneous", "Sedimentary", "Metamorphic", "Bicycle"),
                correctAnswer = "Bicycle", // Not a rock type
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Mandelbrot Set",
                    "Fibonacci Sequence",
                    "Golden Ratio",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a mathematical concept
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Eukaryote", "Prokaryote", "Archaea", "Tree"),
                correctAnswer = "Tree", // Not a cell type
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Paleontology", "Geology", "Astronomy", "Lamp"),
                correctAnswer = "Lamp", // Not a scientific field
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Pangea", "Laurasia", "Gondwana", "Television"),
                correctAnswer = "Television", // Not a supercontinent
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Helium", "Argon", "Radon", "Newton"),
                correctAnswer = "Newton", // Not a noble gas
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Paramecium", "Euglena", "Amoeba", "Pen"),
                correctAnswer = "Pen", // Not a microorganism
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Aurora Borealis", "Aurora Australis", "Supernova", "Volcano"),
                correctAnswer = "Volcano", // Not an astronomical phenomenon
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Bosón de Higgs", "Fotón", "Quark", "Relatividad"),
                correctAnswer = "Relatividad", // No es una partícula subatómica
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Próxima Centauri", "Sirio", "Vega", "Torre Eiffel"),
                correctAnswer = "Torre Eiffel", // No es una estrella
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Ígnea", "Sedimentaria", "Metamórfica", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un tipo de roca
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Conjunto de Mandelbrot",
                    "Sucesión de Fibonacci",
                    "Razón Áurea",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un concepto matemático
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Eucariota", "Procariota", "Arquea", "Árbol"),
                correctAnswer = "Árbol", // No es un tipo de célula
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Paleontología", "Geología", "Astronomía", "Lámpara"),
                correctAnswer = "Lámpara", // No es un campo científico
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Pangea", "Laurasia", "Gondwana", "Televisión"),
                correctAnswer = "Televisión", // No es un supercontinente
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Helio", "Argón", "Radón", "Newton"),
                correctAnswer = "Newton", // No es un gas noble
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Paramecio", "Euglena", "Ameba", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un microorganismo
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Aurora Boreal", "Aurora Austral", "Supernova", "Volcán"),
                correctAnswer = "Volcán", // No es un fenómeno astronómico
                category = Category.SCIENCE_AND_NATURE,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun computers() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Mouse", "Keyboard", "Monitor", "Apple"),
                correctAnswer = "Apple", // Not a computer component
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("RAM", "CPU", "Hard Drive", "Car"),
                correctAnswer = "Car", // Not a computer component
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Windows", "Linux", "MacOS", "Table"),
                correctAnswer = "Table", // Not an operating system
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Chrome", "Firefox", "Safari", "Chair"),
                correctAnswer = "Chair", // Not a web browser
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Excel", "Word", "PowerPoint", "Pen"),
                correctAnswer = "Pen", // Not a software application
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Python", "JavaScript", "HTML", "Tree"),
                correctAnswer = "Tree", // Not a programming language
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Google", "Bing", "Yahoo", "Lamp"),
                correctAnswer = "Lamp", // Not a search engine
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("GitHub", "GitLab", "Bitbucket", "Television"),
                correctAnswer = "Television", // Not a repository hosting service
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Ethernet", "WiFi", "Bluetooth", "Spaceship"),
                correctAnswer = "Spaceship", // Not a connectivity technology
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Motherboard", "Power Supply", "Graphics Card", "Bicycle"),
                correctAnswer = "Bicycle", // Not a computer component
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Ratón", "Teclado", "Monitor", "Manzana"),
                correctAnswer = "Manzana", // No es un componente de computadora
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("RAM", "CPU", "Disco Duro", "Coche"),
                correctAnswer = "Coche", // No es un componente de computadora
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Windows", "Linux", "MacOS", "Mesa"),
                correctAnswer = "Mesa", // No es un sistema operativo
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Chrome", "Firefox", "Safari", "Silla"),
                correctAnswer = "Silla", // No es un navegador web
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Excel", "Word", "PowerPoint", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una aplicación de software
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Python", "JavaScript", "HTML", "Árbol"),
                correctAnswer = "Árbol", // No es un lenguaje de programación
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Google", "Bing", "Yahoo", "Lámpara"),
                correctAnswer = "Lámpara", // No es un motor de búsqueda
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("GitHub", "GitLab", "Bitbucket", "Televisión"),
                correctAnswer = "Televisión", // No es un servicio de repositorio
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Ethernet", "WiFi", "Bluetooth", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es una tecnología de conectividad
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Placa Base",
                    "Fuente de Alimentación",
                    "Tarjeta Gráfica",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un componente de computadora
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("BIOS", "UEFI", "Kernel", "Spaceship"),
                correctAnswer = "Spaceship", // Not related to computers
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("TCP/IP", "HTTP", "SMTP", "Chair"),
                correctAnswer = "Chair", // Not a protocol
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("SSD", "HDD", "RAID", "Table"),
                correctAnswer = "Table", // Not a storage technology
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("C++", "Java", "Assembly", "Tree"),
                correctAnswer = "Tree", // Not a programming language
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("VLAN", "VPN", "LAN", "Lamp"),
                correctAnswer = "Lamp", // Not a networking term
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Python", "Ruby", "Perl", "Newton"),
                correctAnswer = "Newton", // Not a programming language
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("IP Address", "MAC Address", "Subnet Mask", "Pen"),
                correctAnswer = "Pen", // Not a networking term
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Docker", "Kubernetes", "VMware", "Bicycle"),
                correctAnswer = "Bicycle", // Not related to containerization or virtualization
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("AES", "RSA", "SHA-256", "Car"),
                correctAnswer = "Car", // Not a cryptographic algorithm
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("HTML", "CSS", "SQL", "Volcano"),
                correctAnswer = "Volcano", // Not related to web development
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("BIOS", "UEFI", "Kernel", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No está relacionado con computadoras
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("TCP/IP", "HTTP", "SMTP", "Silla"),
                correctAnswer = "Silla", // No es un protocolo
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("SSD", "HDD", "RAID", "Mesa"),
                correctAnswer = "Mesa", // No es una tecnología de almacenamiento
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("C++", "Java", "Assembly", "Árbol"),
                correctAnswer = "Árbol", // No es un lenguaje de programación
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("VLAN", "VPN", "LAN", "Lámpara"),
                correctAnswer = "Lámpara", // No es un término de redes
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Python", "Ruby", "Perl", "Newton"),
                correctAnswer = "Newton", // No es un lenguaje de programación
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Dirección IP", "Dirección MAC", "Máscara de Subred", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un término de redes
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Docker", "Kubernetes", "VMware", "Bicicleta"),
                correctAnswer = "Bicicleta", // No está relacionado con contenedores o virtualización
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("AES", "RSA", "SHA-256", "Coche"),
                correctAnswer = "Coche", // No es un algoritmo criptográfico
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("HTML", "CSS", "SQL", "Volcán"),
                correctAnswer = "Volcán", // No está relacionado con desarrollo web
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("IPv4", "IPv6", "CIDR", "Spaceship"),
                correctAnswer = "Spaceship", // Not a networking concept
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Quantum Computing",
                    "Neural Networks",
                    "Machine Learning",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a computer science concept
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("HashMap", "Binary Tree", "Linked List", "Bicycle"),
                correctAnswer = "Bicycle", // Not a data structure
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Docker", "Kubernetes", "Ansible", "Newton"),
                correctAnswer = "Newton", // Not related to containerization or orchestration
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("RSA", "AES", "SHA-3", "Lamp"),
                correctAnswer = "Lamp", // Not a cryptographic algorithm
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("O(n)", "O(log n)", "O(n^2)", "Tree"),
                correctAnswer = "Tree", // Not a complexity notation
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("CPU Scheduling", "Disk Scheduling", "Memory Paging", "Car"),
                correctAnswer = "Car", // Not an operating system concept
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Fiber Optics", "Ethernet", "Satellite Internet", "Pen"),
                correctAnswer = "Pen", // Not an internet connection type
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Relational Database",
                    "NoSQL Database",
                    "Object-Oriented Database",
                    "Television"
                ),
                correctAnswer = "Television", // Not a database type
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Full Stack Developer",
                    "Frontend Developer",
                    "Backend Developer",
                    "Spacesuit"
                ),
                correctAnswer = "Spacesuit", // Not a developer role
                category = Category.COMPUTERS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("IPv4", "IPv6", "CIDR", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un concepto de redes
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Computación Cuántica",
                    "Redes Neuronales",
                    "Aprendizaje Automático",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un concepto de informática
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("HashMap", "Árbol Binario", "Lista Enlazada", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es una estructura de datos
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Docker", "Kubernetes", "Ansible", "Newton"),
                correctAnswer = "Newton", // No está relacionado con contenedores u orquestación
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("RSA", "AES", "SHA-3", "Lámpara"),
                correctAnswer = "Lámpara", // No es un algoritmo criptográfico
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("O(n)", "O(log n)", "O(n^2)", "Árbol"),
                correctAnswer = "Árbol", // No es una notación de complejidad
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Planificación de CPU",
                    "Planificación de Disco",
                    "Paginación de Memoria",
                    "Coche"
                ),
                correctAnswer = "Coche", // No es un concepto de sistemas operativos
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Fibra Óptica", "Ethernet", "Internet Satelital", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un tipo de conexión a internet
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Base de Datos Relacional",
                    "Base de Datos NoSQL",
                    "Base de Datos Orientada a Objetos",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un tipo de base de datos
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Desarrollador Full Stack",
                    "Desarrollador Frontend",
                    "Desarrollador Backend",
                    "Traje Espacial"
                ),
                correctAnswer = "Traje Espacial", // No es un rol de desarrollador
                category = Category.COMPUTERS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun mathematics() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Addition", "Subtraction", "Multiplication", "Apple"),
                correctAnswer = "Apple", // Not a mathematical operation
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Triangle", "Square", "Circle", "Car"),
                correctAnswer = "Car", // Not a geometric shape
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("One", "Two", "Three", "Table"),
                correctAnswer = "Table", // Not a number
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Area", "Perimeter", "Volume", "Chair"),
                correctAnswer = "Chair", // Not a mathematical concept
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("45°", "90°", "180°", "Pen"),
                correctAnswer = "Pen", // Not an angle
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("x", "y", "z", "Tree"),
                correctAnswer = "Tree", // Not a variable in algebra
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pi", "Euler's Number", "Square Root of 2", "Lamp"),
                correctAnswer = "Lamp", // Not a mathematical constant
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Line", "Ray", "Segment", "Television"),
                correctAnswer = "Television", // Not a geometric concept
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Plus", "Minus", "Equal", "Spaceship"),
                correctAnswer = "Spaceship", // Not a mathematical symbol
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Prime", "Composite", "Even", "Bicycle"),
                correctAnswer = "Bicycle", // Not a type of number
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Suma", "Resta", "Multiplicación", "Manzana"),
                correctAnswer = "Manzana", // No es una operación matemática
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Triángulo", "Cuadrado", "Círculo", "Coche"),
                correctAnswer = "Coche", // No es una figura geométrica
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Uno", "Dos", "Tres", "Mesa"),
                correctAnswer = "Mesa", // No es un número
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Área", "Perímetro", "Volumen", "Silla"),
                correctAnswer = "Silla", // No es un concepto matemático
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("45°", "90°", "180°", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un ángulo
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("x", "y", "z", "Árbol"),
                correctAnswer = "Árbol", // No es una variable en álgebra
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pi", "Número de Euler", "Raíz Cuadrada de 2", "Lámpara"),
                correctAnswer = "Lámpara", // No es una constante matemática
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Línea", "Rayo", "Segmento", "Televisión"),
                correctAnswer = "Televisión", // No es un concepto geométrico
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Más", "Menos", "Igual", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un símbolo matemático
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Primo", "Compuesto", "Par", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un tipo de número
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Quadratic Equation",
                    "Linear Equation",
                    "Cubic Equation",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a type of equation
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Sine", "Cosine", "Tangent", "Tree"),
                correctAnswer = "Tree", // Not a trigonometric function
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Prime", "Composite", "Perfect", "Bicycle"),
                correctAnswer = "Bicycle", // Not a type of number
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Factorial", "Permutation", "Combination", "Lamp"),
                correctAnswer = "Lamp", // Not a combinatorial concept
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Cartesian Plane",
                    "Polar Coordinates",
                    "Spherical Coordinates",
                    "Car"
                ),
                correctAnswer = "Car", // Not a coordinate system
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Mean", "Median", "Mode", "Pen"),
                correctAnswer = "Pen", // Not a statistical measure
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Matrix", "Determinant", "Eigenvalue", "Television"),
                correctAnswer = "Television", // Not related to linear algebra
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Integral", "Derivative", "Limit", "Chair"),
                correctAnswer = "Chair", // Not a calculus concept
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Octagon", "Hexagon", "Pentagon", "Table"),
                correctAnswer = "Table", // Not a polygon
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Probability", "Variance", "Standard Deviation", "Volcano"),
                correctAnswer = "Volcano", // Not a statistical concept
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Ecuación Cuadrática",
                    "Ecuación Lineal",
                    "Ecuación Cúbica",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un tipo de ecuación
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Seno", "Coseno", "Tangente", "Árbol"),
                correctAnswer = "Árbol", // No es una función trigonométrica
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Primo", "Compuesto", "Perfecto", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un tipo de número
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Factorial", "Permutación", "Combinación", "Lámpara"),
                correctAnswer = "Lámpara", // No es un concepto combinatorio
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Plano Cartesiano",
                    "Coordenadas Polares",
                    "Coordenadas Esféricas",
                    "Coche"
                ),
                correctAnswer = "Coche", // No es un sistema de coordenadas
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Media", "Mediana", "Moda", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una medida estadística
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Matriz", "Determinante", "Valor Propio", "Televisión"),
                correctAnswer = "Televisión", // No está relacionado con álgebra lineal
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Integral", "Derivada", "Límite", "Silla"),
                correctAnswer = "Silla", // No es un concepto de cálculo
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Octágono", "Hexágono", "Pentágono", "Mesa"),
                correctAnswer = "Mesa", // No es un polígono
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Probabilidad", "Varianza", "Desviación Estándar", "Volcán"),
                correctAnswer = "Volcán", // No es un concepto estadístico
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Riemann Hypothesis",
                    "Fermat's Last Theorem",
                    "Poincaré Conjecture",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a mathematical problem
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Group Theory", "Ring Theory", "Field Theory", "Lamp"),
                correctAnswer = "Lamp", // Not a mathematical field
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Gaussian Distribution",
                    "Binomial Distribution",
                    "Poisson Distribution",
                    "Tree"
                ),
                correctAnswer = "Tree", // Not a statistical distribution
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Partial Derivative", "Gradient", "Divergence", "Bicycle"),
                correctAnswer = "Bicycle", // Not a concept in vector calculus
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hilbert Space", "Vector Space", "Tensor Space", "Car"),
                correctAnswer = "Car", // Not a concept in advanced mathematics
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Lebesgue Integral",
                    "Riemann Integral",
                    "Stieltjes Integral",
                    "Chair"
                ),
                correctAnswer = "Chair", // Not an integral type
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Elliptic Curve",
                    "Hyperbolic Curve",
                    "Parabolic Curve",
                    "Television"
                ),
                correctAnswer = "Television", // Not a mathematical curve
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Eigenvalue", "Eigenvector", "Singular Value", "Pen"),
                correctAnswer = "Pen", // Not a concept in linear algebra
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Cryptography", "Number Theory", "Graph Theory", "Newton"),
                correctAnswer = "Newton", // Not a mathematical branch
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Taylor Series", "Fourier Series", "Maclaurin Series", "Volcano"),
                correctAnswer = "Volcano", // Not a series in mathematics
                category = Category.MATHEMATICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Hipótesis de Riemann",
                    "Último Teorema de Fermat",
                    "Conjetura de Poincaré",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un problema matemático
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Teoría de Grupos",
                    "Teoría de Anillos",
                    "Teoría de Campos",
                    "Lámpara"
                ),
                correctAnswer = "Lámpara", // No es un campo matemático
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Distribución Gaussiana",
                    "Distribución Binomial",
                    "Distribución de Poisson",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es una distribución estadística
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Derivada Parcial", "Gradiente", "Divergencia", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un concepto en cálculo vectorial
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Espacio de Hilbert",
                    "Espacio Vectorial",
                    "Espacio Tensorial",
                    "Coche"
                ),
                correctAnswer = "Coche", // No es un concepto matemático avanzado
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Integral de Lebesgue",
                    "Integral de Riemann",
                    "Integral de Stieltjes",
                    "Silla"
                ),
                correctAnswer = "Silla", // No es un tipo de integral
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Curva Elíptica",
                    "Curva Hiperbólica",
                    "Curva Parabólica",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es una curva matemática
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Valor Propio", "Vector Propio", "Valor Singular", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un concepto de álgebra lineal
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Criptografía", "Teoría de Números", "Teoría de Grafos", "Newton"),
                correctAnswer = "Newton", // No es una rama matemática
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Serie de Taylor",
                    "Serie de Fourier",
                    "Serie de Maclaurin",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es una serie matemática
                category = Category.MATHEMATICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun mythology() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Zeus", "Hera", "Poseidon", "Apple"),
                correctAnswer = "Apple", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Thor", "Odin", "Loki", "Car"),
                correctAnswer = "Car", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Achilles", "Hercules", "Perseus", "Chair"),
                correctAnswer = "Chair", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Ra", "Anubis", "Isis", "Lamp"),
                correctAnswer = "Lamp", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Athena", "Apollo", "Ares", "Table"),
                correctAnswer = "Table", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Minotaur", "Cyclops", "Chimera", "Tree"),
                correctAnswer = "Tree", // Not a mythological creature
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Hades", "Pluto", "Hermes", "Television"),
                correctAnswer = "Television", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pegasus", "Sphinx", "Cerberus", "Bicycle"),
                correctAnswer = "Bicycle", // Not a mythological creature
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cupid", "Venus", "Mars", "Spaceship"),
                correctAnswer = "Spaceship", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Kraken", "Hydra", "Phoenix", "Pen"),
                correctAnswer = "Pen", // Not a mythological creature
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Zeus", "Hera", "Poseidón", "Manzana"),
                correctAnswer = "Manzana", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Thor", "Odín", "Loki", "Coche"),
                correctAnswer = "Coche", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Aquiles", "Hércules", "Perseo", "Silla"),
                correctAnswer = "Silla", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Ra", "Anubis", "Isis", "Lámpara"),
                correctAnswer = "Lámpara", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Atenea", "Apolo", "Ares", "Mesa"),
                correctAnswer = "Mesa", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Minotauro", "Cíclope", "Quimera", "Árbol"),
                correctAnswer = "Árbol", // No es una criatura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Hades", "Plutón", "Hermes", "Televisión"),
                correctAnswer = "Televisión", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pegaso", "Esfinge", "Cerbero", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es una criatura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cupido", "Venus", "Marte", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Kraken", "Hidra", "Fénix", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una criatura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Cronus", "Uranus", "Gaia", "Spaceship"),
                correctAnswer = "Spaceship", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Fenrir", "Jörmungandr", "Hel", "Lamp"),
                correctAnswer = "Lamp", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Medusa", "Charybdis", "Scylla", "Tree"),
                correctAnswer = "Tree", // Not a mythological creature
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hestia", "Demeter", "Dionysus", "Television"),
                correctAnswer = "Television", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Eros", "Pan", "Hermaphroditus", "Pen"),
                correctAnswer = "Pen", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Osiris", "Set", "Horus", "Bicycle"),
                correctAnswer = "Bicycle", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hephaestus", "Aphrodite", "Hermes", "Volcano"),
                correctAnswer = "Volcano", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Ymir", "Aegir", "Ran", "Car"),
                correctAnswer = "Car", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Ixion", "Sisyphus", "Tantalus", "Chair"),
                correctAnswer = "Chair", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Typhon", "Echidna", "Cerberus", "Lamp"),
                correctAnswer = "Lamp", // Not a mythological creature
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Cronos", "Urano", "Gea", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Fenrir", "Jörmungandr", "Hel", "Lámpara"),
                correctAnswer = "Lámpara", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Medusa", "Caribdis", "Escila", "Árbol"),
                correctAnswer = "Árbol", // No es una criatura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hestia", "Deméter", "Dionisio", "Televisión"),
                correctAnswer = "Televisión", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Eros", "Pan", "Hermafrodito", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Osiris", "Set", "Horus", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hefesto", "Afrodita", "Hermes", "Volcán"),
                correctAnswer = "Volcán", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Ymir", "Aegir", "Ran", "Coche"),
                correctAnswer = "Coche", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Ixión", "Sísifo", "Tántalo", "Silla"),
                correctAnswer = "Silla", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Tifón", "Equidna", "Cerbero", "Lámpara"),
                correctAnswer = "Lámpara", // No es una criatura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Nyx", "Erebus", "Hemera", "Lamp"),
                correctAnswer = "Lamp", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Aegis", "Excalibur", "Gungnir", "Bicycle"),
                correctAnswer = "Bicycle", // Not a mythological artifact
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Charybdis", "Ladon", "Orthrus", "Spaceship"),
                correctAnswer = "Spaceship", // Not a mythological creature
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hecate", "Selene", "Eos", "Chair"),
                correctAnswer = "Chair", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Bragi", "Forseti", "Hodr", "Tree"),
                correctAnswer = "Tree", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Tartarus", "Elysium", "Asphodel Meadows", "Pen"),
                correctAnswer = "Pen", // Not a concept from mythology
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Amaterasu", "Susanoo", "Tsukuyomi", "Car"),
                correctAnswer = "Car", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Nidhogg", "Fafnir", "Jörmungandr", "Television"),
                correctAnswer = "Television", // Not a mythological creature
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("The Morrigan", "Cernunnos", "Danu", "Volcano"),
                correctAnswer = "Volcano", // Not a mythological figure
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Scythe", "Spear of Destiny", "Mjolnir", "Eiffel Tower"),
                correctAnswer = "Eiffel Tower", // Not a mythological artifact
                category = Category.MYTHOLOGY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Nix", "Érebo", "Hémera", "Lámpara"),
                correctAnswer = "Lámpara", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Égida", "Excalibur", "Gungnir", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un artefacto mitológico
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Caribdis", "Ladón", "Ortros", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es una criatura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hécate", "Selene", "Eos", "Silla"),
                correctAnswer = "Silla", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Bragi", "Forseti", "Höðr", "Árbol"),
                correctAnswer = "Árbol", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Tártaro", "Elíseo", "Praderas de Asfódelos", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un concepto mitológico
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Amaterasu", "Susanoo", "Tsukuyomi", "Coche"),
                correctAnswer = "Coche", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Nidhogg", "Fafnir", "Jörmungandr", "Televisión"),
                correctAnswer = "Televisión", // No es una criatura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("La Morrigan", "Cernunnos", "Danu", "Volcán"),
                correctAnswer = "Volcán", // No es una figura mitológica
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Guadaña", "Lanza del Destino", "Mjolnir", "Torre Eiffel"),
                correctAnswer = "Torre Eiffel", // No es un artefacto mitológico
                category = Category.MYTHOLOGY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun sports() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Soccer", "Basketball", "Baseball", "Apple"),
                correctAnswer = "Apple", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tennis", "Badminton", "Table Tennis", "Car"),
                correctAnswer = "Car", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Golf", "Cricket", "Hockey", "Lamp"),
                correctAnswer = "Lamp", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Football", "Rugby", "American Football", "Tree"),
                correctAnswer = "Tree", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Swimming", "Diving", "Water Polo", "Chair"),
                correctAnswer = "Chair", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cycling", "Running", "Track and Field", "Table"),
                correctAnswer = "Table", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Skiing", "Snowboarding", "Ice Skating", "Pen"),
                correctAnswer = "Pen", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Boxing", "Karate", "Judo", "Television"),
                correctAnswer = "Television", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Archery", "Shooting", "Fencing", "Spaceship"),
                correctAnswer = "Spaceship", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Skateboarding", "BMX", "Surfing", "Bicycle"),
                correctAnswer = "Bicycle", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Fútbol", "Baloncesto", "Béisbol", "Manzana"),
                correctAnswer = "Manzana", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tenis", "Bádminton", "Tenis de Mesa", "Coche"),
                correctAnswer = "Coche", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Golf", "Críquet", "Hockey", "Lámpara"),
                correctAnswer = "Lámpara", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Fútbol Americano", "Rugby", "Fútbol", "Árbol"),
                correctAnswer = "Árbol", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Natación", "Clavados", "Waterpolo", "Silla"),
                correctAnswer = "Silla", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Ciclismo", "Atletismo", "Carreras", "Mesa"),
                correctAnswer = "Mesa", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Esquí", "Snowboard", "Patinaje Sobre Hielo", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Boxeo", "Karate", "Judo", "Televisión"),
                correctAnswer = "Televisión", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tiro con Arco", "Tiro Deportivo", "Esgrima", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Monopatín", "BMX", "Surf", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Triathlon", "Decathlon", "Heptathlon", "Lamp"),
                correctAnswer = "Lamp", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Fencing", "Jousting", "Kendo", "Tree"),
                correctAnswer = "Tree", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Curling", "Luge", "Skeleton", "Car"),
                correctAnswer = "Car", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Squash", "Racquetball", "Padel", "Chair"),
                correctAnswer = "Chair", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Sailing", "Rowing", "Kayaking", "Table"),
                correctAnswer = "Table", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Equestrian", "Dressage", "Show Jumping", "Bicycle"),
                correctAnswer = "Bicycle", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Shot Put", "Discus Throw", "Javelin", "Pen"),
                correctAnswer = "Pen", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Snooker", "Pool", "Billiards", "Spaceship"),
                correctAnswer = "Spaceship", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Archery", "Skeet Shooting", "Biathlon", "Television"),
                correctAnswer = "Television", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Cricket", "Baseball", "Softball", "Lamp"),
                correctAnswer = "Lamp", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Triatlón", "Decatlón", "Heptatlón", "Lámpara"),
                correctAnswer = "Lámpara", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Esgrima", "Justas", "Kendo", "Árbol"),
                correctAnswer = "Árbol", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Curling", "Luge", "Skeleton", "Coche"),
                correctAnswer = "Coche", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Squash", "Ráquetbol", "Pádel", "Silla"),
                correctAnswer = "Silla", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Vela", "Remo", "Kayak", "Mesa"),
                correctAnswer = "Mesa", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hípica", "Doma", "Salto", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Lanzamiento de Bala",
                    "Lanzamiento de Disco",
                    "Lanzamiento de Jabalina",
                    "Bolígrafo"
                ),
                correctAnswer = "Bolígrafo", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Snooker", "Pool", "Billar", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Tiro con Arco", "Tiro al Plato", "Biatlón", "Televisión"),
                correctAnswer = "Televisión", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Críquet", "Béisbol", "Softbol", "Lámpara"),
                correctAnswer = "Lámpara", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Hurling", "Shinty", "Camogie", "Lamp"),
                correctAnswer = "Lamp", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Kabaddi", "Sepak Takraw", "Buzkashi", "Spaceship"),
                correctAnswer = "Spaceship", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Modern Pentathlon", "Steeplechase", "Hammer Throw", "Tree"),
                correctAnswer = "Tree", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Cheerleading", "Acrobatics", "Parkour", "Bicycle"),
                correctAnswer = "Bicycle", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Polo", "Elephant Polo", "Horseball", "Car"),
                correctAnswer = "Car", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Kiteboarding", "Windsurfing", "Skimboarding", "Chair"),
                correctAnswer = "Chair", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Sepak Takraw", "Teqball", "Jai Alai", "Pen"),
                correctAnswer = "Pen", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Underwater Hockey", "Underwater Rugby", "Freediving", "Lamp"),
                correctAnswer = "Lamp", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Gaelic Football",
                    "Australian Rules Football",
                    "Canadian Football",
                    "Table"
                ),
                correctAnswer = "Table", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Muay Thai", "Savate", "Capoeira", "Television"),
                correctAnswer = "Television", // Not a sport
                category = Category.SPORTS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Hurling", "Shinty", "Camogie", "Lámpara"),
                correctAnswer = "Lámpara", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Kabaddi", "Sepak Takraw", "Buzkashi", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Pentatlón Moderno",
                    "Carrera de Obstáculos",
                    "Lanzamiento de Martillo",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Porristas", "Acrobacias", "Parkour", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Polo", "Polo en Elefante", "Horseball", "Coche"),
                correctAnswer = "Coche", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Kitesurf", "Windsurf", "Skimboard", "Silla"),
                correctAnswer = "Silla", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Sepak Takraw", "Teqball", "Jai Alai", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Hockey Subacuático",
                    "Rugby Subacuático",
                    "Buceo Libre",
                    "Lámpara"
                ),
                correctAnswer = "Lámpara", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Fútbol Gaélico",
                    "Fútbol Australiano",
                    "Fútbol Canadiense",
                    "Mesa"
                ),
                correctAnswer = "Mesa", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Muay Thai", "Savate", "Capoeira", "Televisión"),
                correctAnswer = "Televisión", // No es un deporte
                category = Category.SPORTS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun geography() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Asia", "Africa", "Europe", "Apple"),
                correctAnswer = "Apple", // Not a continent
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Amazon", "Nile", "Yangtze", "Car"),
                correctAnswer = "Car", // Not a river
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Mount Everest", "K2", "Kilimanjaro", "Table"),
                correctAnswer = "Table", // Not a mountain
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Sahara", "Gobi", "Atacama", "Chair"),
                correctAnswer = "Chair", // Not a desert
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Pen"),
                correctAnswer = "Pen", // Not an ocean
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("USA", "Canada", "Mexico", "Lamp"),
                correctAnswer = "Lamp", // Not a country in North America
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Spain", "Italy", "France", "Tree"),
                correctAnswer = "Tree", // Not a European country
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tokyo", "Beijing", "Seoul", "Television"),
                correctAnswer = "Television", // Not an Asian capital
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Australia", "New Zealand", "Fiji", "Spaceship"),
                correctAnswer = "Spaceship", // Not a country in Oceania
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Greenland", "Iceland", "Madagascar", "Bicycle"),
                correctAnswer = "Bicycle", // Not an island
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Asia", "África", "Europa", "Manzana"),
                correctAnswer = "Manzana", // No es un continente
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Amazonas", "Nilo", "Yangtsé", "Coche"),
                correctAnswer = "Coche", // No es un río
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Monte Everest", "K2", "Kilimanjaro", "Mesa"),
                correctAnswer = "Mesa", // No es una montaña
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Sahara", "Gobi", "Atacama", "Silla"),
                correctAnswer = "Silla", // No es un desierto
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Océano Pacífico",
                    "Océano Atlántico",
                    "Océano Índico",
                    "Bolígrafo"
                ),
                correctAnswer = "Bolígrafo", // No es un océano
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Estados Unidos", "Canadá", "México", "Lámpara"),
                correctAnswer = "Lámpara", // No es un país de América del Norte
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("España", "Italia", "Francia", "Árbol"),
                correctAnswer = "Árbol", // No es un país de Europa
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tokio", "Pekín", "Seúl", "Televisión"),
                correctAnswer = "Televisión", // No es una capital asiática
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Australia", "Nueva Zelanda", "Fiyi", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un país de Oceanía
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Groenlandia", "Islandia", "Madagascar", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es una isla
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Andes", "Himalayas", "Rockies", "Lamp"),
                correctAnswer = "Lamp", // Not a mountain range
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Danube", "Volga", "Rhine", "Tree"),
                correctAnswer = "Tree", // Not a river
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Kyoto", "Osaka", "Sapporo", "Car"),
                correctAnswer = "Car", // Not a Japanese city
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Amazon Rainforest",
                    "Congo Rainforest",
                    "Daintree Rainforest",
                    "Table"
                ),
                correctAnswer = "Table", // Not a rainforest
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Victoria Falls", "Niagara Falls", "Iguazu Falls", "Pen"),
                correctAnswer = "Pen", // Not a waterfall
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Sicily", "Sardinia", "Corsica", "Chair"),
                correctAnswer = "Chair", // Not a Mediterranean island
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Morocco", "Algeria", "Tunisia", "Television"),
                correctAnswer = "Television", // Not a country in North Africa
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Buenos Aires", "Lima", "Bogota", "Spaceship"),
                correctAnswer = "Spaceship", // Not a South American capital
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Lake Baikal", "Lake Superior", "Lake Victoria", "Bicycle"),
                correctAnswer = "Bicycle", // Not a lake
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Arctic Circle", "Tropic of Cancer", "Equator", "Volcano"),
                correctAnswer = "Volcano", // Not a geographic line
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Andes", "Himalaya", "Rocosas", "Lámpara"),
                correctAnswer = "Lámpara", // No es una cordillera
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Danubio", "Volga", "Rin", "Árbol"),
                correctAnswer = "Árbol", // No es un río
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Kioto", "Osaka", "Sapporo", "Coche"),
                correctAnswer = "Coche", // No es una ciudad japonesa
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Selva Amazónica", "Selva del Congo", "Selva de Daintree", "Mesa"),
                correctAnswer = "Mesa", // No es una selva
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Cataratas Victoria",
                    "Cataratas del Niágara",
                    "Cataratas del Iguazú",
                    "Bolígrafo"
                ),
                correctAnswer = "Bolígrafo", // No es una catarata
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Sicilia", "Cerdeña", "Córcega", "Silla"),
                correctAnswer = "Silla", // No es una isla del Mediterráneo
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Marruecos", "Argelia", "Túnez", "Televisión"),
                correctAnswer = "Televisión", // No es un país del norte de África
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Buenos Aires", "Lima", "Bogotá", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es una capital sudamericana
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Lago Baikal", "Lago Superior", "Lago Victoria", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un lago
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Círculo Polar Ártico", "Trópico de Cáncer", "Ecuador", "Volcán"),
                correctAnswer = "Volcán", // No es una línea geográfica
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Lake Tanganyika", "Lake Malawi", "Lake Baikal", "Lamp"),
                correctAnswer = "Lamp", // Not a lake
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Carpathian Mountains", "Altai Mountains", "Tian Shan", "Bicycle"),
                correctAnswer = "Bicycle", // Not a mountain range
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Strait of Hormuz", "Strait of Malacca", "Bosporus", "Spaceship"),
                correctAnswer = "Spaceship", // Not a strait
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Gobi Desert", "Thar Desert", "Karakum Desert", "Tree"),
                correctAnswer = "Tree", // Not a desert
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Brunei", "Bhutan", "Timor-Leste", "Car"),
                correctAnswer = "Car", // Not a country
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Andaman Sea", "Sargasso Sea", "Coral Sea", "Chair"),
                correctAnswer = "Chair", // Not a sea
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Macquarie Island",
                    "South Georgia Island",
                    "Bouvet Island",
                    "Television"
                ),
                correctAnswer = "Television", // Not an island
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Mekong", "Irrawaddy", "Chao Phraya", "Table"),
                correctAnswer = "Table", // Not a river
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Caspian Sea", "Dead Sea", "Aral Sea", "Pen"),
                correctAnswer = "Pen", // Not a real sea
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Mariana Trench",
                    "Tonga Trench",
                    "Kuril-Kamchatka Trench",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not an ocean trench
                category = Category.GEOGRAPHY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Lago Tanganica", "Lago Malaui", "Lago Baikal", "Lámpara"),
                correctAnswer = "Lámpara", // No es un lago
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Montes Cárpatos", "Montes Altái", "Tian Shan", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es una cordillera
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Estrecho de Ormuz",
                    "Estrecho de Malaca",
                    "Bósforo",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un estrecho
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Desierto del Gobi",
                    "Desierto del Thar",
                    "Desierto del Karakum",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es un desierto
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Brunéi", "Bután", "Timor Oriental", "Coche"),
                correctAnswer = "Coche", // No es un país
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Mar de Andamán", "Mar de los Sargazos", "Mar del Coral", "Silla"),
                correctAnswer = "Silla", // No es un mar
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Isla Macquarie",
                    "Isla Georgia del Sur",
                    "Isla Bouvet",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es una isla
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Mekong", "Irrawaddy", "Chao Phraya", "Mesa"),
                correctAnswer = "Mesa", // No es un río
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Mar Caspio", "Mar Muerto", "Mar de Aral", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un mar real
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Fosa de las Marianas",
                    "Fosa de Tonga",
                    "Fosa Kuril-Kamchatka",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es una fosa oceánica
                category = Category.GEOGRAPHY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun history() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("World War I", "World War II", "Cold War", "Apple"),
                correctAnswer = "Apple", // Not a historical event
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("George Washington", "Abraham Lincoln", "Thomas Jefferson", "Car"),
                correctAnswer = "Car", // Not a historical figure
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "French Revolution",
                    "American Revolution",
                    "Industrial Revolution",
                    "Chair"
                ),
                correctAnswer = "Chair", // Not a historical event
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Roman Empire", "Ottoman Empire", "Mongol Empire", "Tree"),
                correctAnswer = "Tree", // Not an empire
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cleopatra", "Julius Caesar", "Alexander the Great", "Pen"),
                correctAnswer = "Pen", // Not a historical figure
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Apollo 11", "Sputnik", "Hubble Telescope", "Bicycle"),
                correctAnswer = "Bicycle", // Not a space exploration mission
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Great Depression",
                    "Roaring Twenties",
                    "Civil Rights Movement",
                    "Table"
                ),
                correctAnswer = "Table", // Not a historical period
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Christopher Columbus",
                    "Ferdinand Magellan",
                    "Vasco da Gama",
                    "Lamp"
                ),
                correctAnswer = "Lamp", // Not an explorer
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Berlin Wall",
                    "Great Wall of China",
                    "Hadrian's Wall",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a historical wall
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Renaissance", "Middle Ages", "Ancient Greece", "Television"),
                correctAnswer = "Television", // Not a historical era
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Primera Guerra Mundial",
                    "Segunda Guerra Mundial",
                    "Guerra Fría",
                    "Manzana"
                ),
                correctAnswer = "Manzana", // No es un evento histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Simón Bolívar", "Miguel Hidalgo", "José de San Martín", "Coche"),
                correctAnswer = "Coche", // No es una figura histórica
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Revolución Francesa",
                    "Revolución Americana",
                    "Revolución Industrial",
                    "Silla"
                ),
                correctAnswer = "Silla", // No es un evento histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Imperio Romano", "Imperio Otomano", "Imperio Mongol", "Árbol"),
                correctAnswer = "Árbol", // No es un imperio
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cleopatra", "Julio César", "Alejandro Magno", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una figura histórica
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Apolo 11", "Sputnik", "Telescopio Hubble", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es una misión de exploración espacial
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Gran Depresión",
                    "Felices Años Veinte",
                    "Movimiento por los Derechos Civiles",
                    "Mesa"
                ),
                correctAnswer = "Mesa", // No es un periodo histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Cristóbal Colón",
                    "Fernando de Magallanes",
                    "Vasco da Gama",
                    "Lámpara"
                ),
                correctAnswer = "Lámpara", // No es un explorador
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Muro de Berlín",
                    "Gran Muralla China",
                    "Muro de Adriano",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un muro histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Renacimiento", "Edad Media", "Grecia Antigua", "Televisión"),
                correctAnswer = "Televisión", // No es una era histórica
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Battle of Agincourt",
                    "Battle of Trafalgar",
                    "Battle of Midway",
                    "Lamp"
                ),
                correctAnswer = "Lamp", // Not a historical battle
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Age of Exploration",
                    "Age of Enlightenment",
                    "Age of Revolution",
                    "Car"
                ),
                correctAnswer = "Car", // Not a historical period
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Treaty of Ghent", "Treaty of Paris", "Treaty of Utrecht", "Tree"),
                correctAnswer = "Tree", // Not a historical treaty
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Henry VIII", "Louis XIV", "Philip II", "Chair"),
                correctAnswer = "Chair", // Not a historical monarch
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Sparta", "Thebes", "Delphi", "Pen"),
                correctAnswer = "Pen", // Not an ancient Greek city-state
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Mughal Empire", "Maurya Empire", "Gupta Empire", "Table"),
                correctAnswer = "Table", // Not an Indian empire
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Isaac Newton", "Johannes Kepler", "Tycho Brahe", "Bicycle"),
                correctAnswer = "Bicycle", // Not a historical scientist
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "American Civil War",
                    "French and Indian War",
                    "Crimean War",
                    "Television"
                ),
                correctAnswer = "Television", // Not a historical war
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Leif Erikson",
                    "Vasco Núñez de Balboa",
                    "Francis Drake",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not an explorer
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Great Fire of London",
                    "Boston Tea Party",
                    "Storming of the Bastille",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a historical event
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Batalla de Agincourt",
                    "Batalla de Trafalgar",
                    "Batalla de Midway",
                    "Lámpara"
                ),
                correctAnswer = "Lámpara", // No es una batalla histórica
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Edad de la Exploración",
                    "Ilustración",
                    "Edad de las Revoluciones",
                    "Coche"
                ),
                correctAnswer = "Coche", // No es un periodo histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Tratado de Gante",
                    "Tratado de París",
                    "Tratado de Utrecht",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es un tratado histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Enrique VIII", "Luis XIV", "Felipe II", "Silla"),
                correctAnswer = "Silla", // No es un monarca histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Esparta", "Tebas", "Delfos", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una ciudad-estado griega
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Imperio Mogol", "Imperio Maurya", "Imperio Gupta", "Mesa"),
                correctAnswer = "Mesa", // No es un imperio de la India
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Isaac Newton", "Johannes Kepler", "Tycho Brahe", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un científico histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Guerra Civil Americana",
                    "Guerra de los Siete Años",
                    "Guerra de Crimea",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es una guerra histórica
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Leif Erikson",
                    "Vasco Núñez de Balboa",
                    "Francis Drake",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un explorador
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Gran Incendio de Londres",
                    "Motín del Té de Boston",
                    "Toma de la Bastilla",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un evento histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Battle of Vienna",
                    "Battle of Tours",
                    "Battle of Lepanto",
                    "Lamp"
                ),
                correctAnswer = "Lamp", // Not a historical battle
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Neolithic Revolution",
                    "Scientific Revolution",
                    "Glorious Revolution",
                    "Car"
                ),
                correctAnswer = "Car", // Not a historical period
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Treaty of Guadalupe Hidalgo",
                    "Treaty of Brest-Litovsk",
                    "Treaty of Portsmouth",
                    "Tree"
                ),
                correctAnswer = "Tree", // Not a historical treaty
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Emperor Meiji",
                    "Suleiman the Magnificent",
                    "Charlemagne",
                    "Chair"
                ),
                correctAnswer = "Chair", // Not a historical figure
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Carthage", "Ur", "Nineveh", "Pen"),
                correctAnswer = "Pen", // Not an ancient city
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Qing Dynasty", "Ming Dynasty", "Han Dynasty", "Table"),
                correctAnswer = "Table", // Not a Chinese dynasty
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("John Locke", "Jean-Jacques Rousseau", "Thomas Hobbes", "Bicycle"),
                correctAnswer = "Bicycle", // Not a philosopher
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Taiping Rebellion",
                    "Boxer Rebellion",
                    "Sepoy Rebellion",
                    "Television"
                ),
                correctAnswer = "Television", // Not a historical rebellion
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Ferdinand Magellan",
                    "Amerigo Vespucci",
                    "Bartolomeu Dias",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not an explorer
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Defenestration of Prague",
                    "Edict of Nantes",
                    "Peace of Augsburg",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a historical event
                category = Category.HISTORY,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Batalla de Viena",
                    "Batalla de Poitiers",
                    "Batalla de Lepanto",
                    "Lámpara"
                ),
                correctAnswer = "Lámpara", // No es una batalla histórica
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Revolución Neolítica",
                    "Revolución Científica",
                    "Revolución Gloriosa",
                    "Coche"
                ),
                correctAnswer = "Coche", // No es un periodo histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Tratado de Guadalupe Hidalgo",
                    "Tratado de Brest-Litovsk",
                    "Tratado de Portsmouth",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es un tratado histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Emperador Meiji", "Solimán el Magnífico", "Carlomagno", "Silla"),
                correctAnswer = "Silla", // No es una figura histórica
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Cartago", "Ur", "Nínive", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una ciudad antigua
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Dinastía Qing", "Dinastía Ming", "Dinastía Han", "Mesa"),
                correctAnswer = "Mesa", // No es una dinastía china
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "John Locke",
                    "Jean-Jacques Rousseau",
                    "Thomas Hobbes",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un filósofo
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Rebelión Taiping",
                    "Rebelión Bóxer",
                    "Rebelión de los Cipayos",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es una rebelión histórica
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Fernando de Magallanes",
                    "Américo Vespucio",
                    "Bartolomé Díaz",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un explorador
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Defenestración de Praga",
                    "Edicto de Nantes",
                    "Paz de Augsburgo",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un evento histórico
                category = Category.HISTORY,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun politics() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Democracy", "Monarchy", "Republic", "Lamp"),
                correctAnswer = "Lamp", // Not a political system
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Barack Obama", "Donald Trump", "Joe Biden", "Car"),
                correctAnswer = "Car", // Not a political figure
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("United Nations", "NATO", "World Trade Organization", "Tree"),
                correctAnswer = "Tree", // Not an international organization
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("House of Representatives", "Senate", "Supreme Court", "Bicycle"),
                correctAnswer = "Bicycle", // Not a political institution
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Prime Minister", "President", "Chancellor", "Pen"),
                correctAnswer = "Pen", // Not a political role
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Constitution",
                    "Bill of Rights",
                    "Declaration of Independence",
                    "Chair"
                ),
                correctAnswer = "Chair", // Not a legal document
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Liberalism", "Conservatism", "Socialism", "Table"),
                correctAnswer = "Table", // Not a political ideology
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Supreme Leader",
                    "Head of State",
                    "Cabinet Minister",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a political title
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Judicial", "Executive", "Legislative", "Television"),
                correctAnswer = "Television", // Not a branch of government
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Veto", "Amendment", "Impeachment", "Volcano"),
                correctAnswer = "Volcano", // Not a political process
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Democracia", "Monarquía", "República", "Lámpara"),
                correctAnswer = "Lámpara", // No es un sistema político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Barack Obama", "Donald Trump", "Joe Biden", "Coche"),
                correctAnswer = "Coche", // No es una figura política
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Naciones Unidas",
                    "OTAN",
                    "Organización Mundial del Comercio",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es una organización internacional
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Congreso", "Senado", "Corte Suprema", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es una institución política
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Primer Ministro", "Presidente", "Canciller", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un rol político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Constitución",
                    "Declaración de Derechos",
                    "Declaración de Independencia",
                    "Silla"
                ),
                correctAnswer = "Silla", // No es un documento legal
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Liberalismo", "Conservadurismo", "Socialismo", "Mesa"),
                correctAnswer = "Mesa", // No es una ideología política
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Líder Supremo",
                    "Jefe de Estado",
                    "Ministro del Gabinete",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un título político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Judicial", "Ejecutivo", "Legislativo", "Televisión"),
                correctAnswer = "Televisión", // No es una rama de gobierno
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Veto", "Enmienda", "Juicio Político", "Volcán"),
                correctAnswer = "Volcán", // No es un proceso político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Parliamentary System",
                    "Presidential System",
                    "Federal System",
                    "Lamp"
                ),
                correctAnswer = "Lamp", // Not a political system
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Constitution of the United States",
                    "The Magna Carta",
                    "The Federalist Papers",
                    "Car"
                ),
                correctAnswer = "Car", // Not a political document
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("European Union", "African Union", "ASEAN", "Tree"),
                correctAnswer = "Tree", // Not an international organization
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Impeachment", "Filibuster", "Censure", "Table"),
                correctAnswer = "Table", // Not a political process
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Prime Minister", "Governor-General", "President", "Bicycle"),
                correctAnswer = "Bicycle", // Not a political position
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Electoral College", "Popular Vote", "Gerrymandering", "Chair"),
                correctAnswer = "Chair", // Not related to elections
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Liberal Party",
                    "Conservative Party",
                    "Social Democratic Party",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a political party
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Checks and Balances",
                    "Separation of Powers",
                    "Judicial Review",
                    "Pen"
                ),
                correctAnswer = "Pen", // Not a principle of governance
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Civil Liberties",
                    "Human Rights",
                    "State Sovereignty",
                    "Television"
                ),
                correctAnswer = "Television", // Not a political concept
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("UN Security Council", "UN General Assembly", "UNESCO", "Volcano"),
                correctAnswer = "Volcano", // Not a United Nations body
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Sistema Parlamentario",
                    "Sistema Presidencial",
                    "Sistema Federal",
                    "Lámpara"
                ),
                correctAnswer = "Lámpara", // No es un sistema político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "La Constitución de los Estados Unidos",
                    "La Carta Magna",
                    "Los Papeles Federalistas",
                    "Coche"
                ),
                correctAnswer = "Coche", // No es un documento político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Unión Europea", "Unión Africana", "ASEAN", "Árbol"),
                correctAnswer = "Árbol", // No es una organización internacional
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Juicio Político", "Filibusterismo", "Censura", "Mesa"),
                correctAnswer = "Mesa", // No es un proceso político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Primer Ministro",
                    "Gobernador General",
                    "Presidente",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un cargo político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Colegio Electoral",
                    "Voto Popular",
                    "Manipulación de Distritos",
                    "Silla"
                ),
                correctAnswer = "Silla", // No está relacionado con elecciones
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Partido Liberal",
                    "Partido Conservador",
                    "Partido Social Demócrata",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un partido político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Equilibrio de Poderes",
                    "Separación de Poderes",
                    "Revisión Judicial",
                    "Bolígrafo"
                ),
                correctAnswer = "Bolígrafo", // No es un principio de gobernanza
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Libertades Civiles",
                    "Derechos Humanos",
                    "Soberanía del Estado",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un concepto político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Consejo de Seguridad de la ONU",
                    "Asamblea General de la ONU",
                    "UNESCO",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un organismo de la ONU
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Realpolitik", "Balance of Power", "Manifest Destiny", "Lamp"),
                correctAnswer = "Lamp", // Not a political concept
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Jean Monnet", "Robert Schuman", "Konrad Adenauer", "Car"),
                correctAnswer = "Car", // Not a historical figure in European integration
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Treaty of Maastricht",
                    "Treaty of Lisbon",
                    "Treaty of Nice",
                    "Tree"
                ),
                correctAnswer = "Tree", // Not a European Union treaty
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "First Past the Post",
                    "Proportional Representation",
                    "Ranked Voting",
                    "Chair"
                ),
                correctAnswer = "Chair", // Not an electoral system
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Thomas Hobbes",
                    "Niccolò Machiavelli",
                    "John Stuart Mill",
                    "Bicycle"
                ),
                correctAnswer = "Bicycle", // Not a political philosopher
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("House of Commons", "Bundestag", "National Assembly", "Table"),
                correctAnswer = "Table", // Not a legislative body
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Gerrymandering", "Filibustering", "Whipping", "Pen"),
                correctAnswer = "Pen", // Not a political practice
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "United Nations Charter",
                    "Vienna Convention",
                    "Treaty of Westphalia",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a political or diplomatic document
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Angela Merkel", "Margaret Thatcher", "Golda Meir", "Television"),
                correctAnswer = "Television", // Not a political leader
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Cold War",
                    "Non-Aligned Movement",
                    "Containment Policy",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a political strategy or era
                category = Category.POLITICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Realpolitik",
                    "Equilibrio de Poder",
                    "Destino Manifiesto",
                    "Lámpara"
                ),
                correctAnswer = "Lámpara", // No es un concepto político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Jean Monnet", "Robert Schuman", "Konrad Adenauer", "Coche"),
                correctAnswer = "Coche", // No es una figura histórica en la integración europea
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Tratado de Maastricht",
                    "Tratado de Lisboa",
                    "Tratado de Niza",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es un tratado de la Unión Europea
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Mayoría Simple",
                    "Representación Proporcional",
                    "Votación Clasificada",
                    "Silla"
                ),
                correctAnswer = "Silla", // No es un sistema electoral
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Thomas Hobbes",
                    "Nicolás Maquiavelo",
                    "John Stuart Mill",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un filósofo político
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Cámara de los Comunes", "Bundestag", "Asamblea Nacional", "Mesa"),
                correctAnswer = "Mesa", // No es un cuerpo legislativo
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Manipulación de Distritos",
                    "Obstrucción Parlamentaria",
                    "Control de Votantes",
                    "Bolígrafo"
                ),
                correctAnswer = "Bolígrafo", // No es una práctica política
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Carta de las Naciones Unidas",
                    "Convención de Viena",
                    "Tratado de Westfalia",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un documento político o diplomático
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Angela Merkel", "Margaret Thatcher", "Golda Meir", "Televisión"),
                correctAnswer = "Televisión", // No es una líder política
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Guerra Fría",
                    "Movimiento de Países No Alineados",
                    "Política de Contención",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es una estrategia o época política
                category = Category.POLITICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun art() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Mona Lisa", "Starry Night", "The Persistence of Memory", "Lamp"),
                correctAnswer = "Lamp", // Not a famous painting
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Leonardo da Vinci", "Vincent van Gogh", "Salvador Dalí", "Car"),
                correctAnswer = "Car", // Not an artist
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Louvre", "The Prado", "The Uffizi", "Tree"),
                correctAnswer = "Tree", // Not an art museum
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cubism", "Impressionism", "Surrealism", "Bicycle"),
                correctAnswer = "Bicycle", // Not an art movement
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Michelangelo", "Donatello", "Raphael", "Pen"),
                correctAnswer = "Pen", // Not an artist
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Sculpture", "Painting", "Drawing", "Chair"),
                correctAnswer = "Chair", // Not a type of art
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Thinker", "David", "Venus de Milo", "Spaceship"),
                correctAnswer = "Spaceship", // Not a famous sculpture
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Canvas", "Brush", "Palette", "Table"),
                correctAnswer = "Table", // Not a tool for painting
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Fresco", "Oil Painting", "Watercolor", "Television"),
                correctAnswer = "Television", // Not a painting technique
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Last Supper", "The Birth of Venus", "The Scream", "Volcano"),
                correctAnswer = "Volcano", // Not a famous painting
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "La Gioconda",
                    "La Noche Estrellada",
                    "La Persistencia de la Memoria",
                    "Lámpara"
                ),
                correctAnswer = "Lámpara", // No es una pintura famosa
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Leonardo da Vinci", "Vincent van Gogh", "Salvador Dalí", "Coche"),
                correctAnswer = "Coche", // No es un artista
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("El Louvre", "El Prado", "Los Uffizi", "Árbol"),
                correctAnswer = "Árbol", // No es un museo de arte
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cubismo", "Impresionismo", "Surrealismo", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un movimiento artístico
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Miguel Ángel", "Donatello", "Rafael", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un artista
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Escultura", "Pintura", "Dibujo", "Silla"),
                correctAnswer = "Silla", // No es un tipo de arte
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("El Pensador", "David", "Venus de Milo", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es una escultura famosa
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Lienzo", "Pincel", "Paleta", "Mesa"),
                correctAnswer = "Mesa", // No es una herramienta para pintar
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Fresco", "Óleo", "Acuarela", "Televisión"),
                correctAnswer = "Televisión", // No es una técnica de pintura
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("La Última Cena", "El Nacimiento de Venus", "El Grito", "Volcán"),
                correctAnswer = "Volcán", // No es una pintura famosa
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Baroque", "Rococo", "Romanticism", "Lamp"),
                correctAnswer = "Lamp", // Not an art movement
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Francisco Goya", "Eugène Delacroix", "J.M.W. Turner", "Car"),
                correctAnswer = "Car", // Not an artist
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Garden of Earthly Delights",
                    "The School of Athens",
                    "The Hay Wain",
                    "Tree"
                ),
                correctAnswer = "Tree", // Not a famous painting
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Impressionism", "Expressionism", "Fauvism", "Table"),
                correctAnswer = "Table", // Not an art movement
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Gothic Architecture",
                    "Renaissance Architecture",
                    "Neoclassical Architecture",
                    "Pen"
                ),
                correctAnswer = "Pen", // Not an architectural style
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Claude Monet", "Camille Pissarro", "Édouard Manet", "Bicycle"),
                correctAnswer = "Bicycle", // Not an artist
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Bas-relief", "High Relief", "Sgraffito", "Chair"),
                correctAnswer = "Chair", // Not a sculptural technique
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Abstract Art",
                    "Figurative Art",
                    "Geometric Abstraction",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not an art style
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Cave Paintings of Lascaux",
                    "Terracotta Army",
                    "Stonehenge",
                    "Television"
                ),
                correctAnswer = "Television", // Not an ancient artwork
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Blue Period", "Rose Period", "Surrealist Period", "Volcano"),
                correctAnswer = "Volcano", // Not an art period
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Barroco", "Rococó", "Romanticismo", "Lámpara"),
                correctAnswer = "Lámpara", // No es un movimiento artístico
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Francisco Goya", "Eugène Delacroix", "J.M.W. Turner", "Coche"),
                correctAnswer = "Coche", // No es un artista
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "El Jardín de las Delicias",
                    "La Escuela de Atenas",
                    "El Carro de Heno",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es una pintura famosa
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Impresionismo", "Expresionismo", "Fauvismo", "Mesa"),
                correctAnswer = "Mesa", // No es un movimiento artístico
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Arquitectura Gótica",
                    "Arquitectura Renacentista",
                    "Arquitectura Neoclásica",
                    "Bolígrafo"
                ),
                correctAnswer = "Bolígrafo", // No es un estilo arquitectónico
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Claude Monet", "Camille Pissarro", "Édouard Manet", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un artista
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Bajorrelieve", "Altorrelieve", "Sgrafiado", "Silla"),
                correctAnswer = "Silla", // No es una técnica escultórica
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Arte Abstracto",
                    "Arte Figurativo",
                    "Abstracción Geométrica",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un estilo de arte
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Pinturas Rupestres de Lascaux",
                    "Ejército de Terracota",
                    "Stonehenge",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es una obra antigua
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Periodo Azul", "Periodo Rosa", "Periodo Surrealista", "Volcán"),
                correctAnswer = "Volcán", // No es un periodo artístico
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Suprematism", "Constructivism", "De Stijl", "Lamp"),
                correctAnswer = "Lamp", // Not an art movement
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Kazimir Malevich", "Piet Mondrian", "Wassily Kandinsky", "Car"),
                correctAnswer = "Car", // Not an artist
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Black Square",
                    "Broadway Boogie Woogie",
                    "Composition VIII",
                    "Tree"
                ),
                correctAnswer = "Tree", // Not a famous painting
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Dadaism", "Futurism", "Vorticism", "Table"),
                correctAnswer = "Table", // Not an art movement
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Flying Buttress", "Caryatid", "Pilaster", "Pen"),
                correctAnswer = "Pen", // Not an architectural element
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hans Holbein", "Albrecht Dürer", "Lucas Cranach", "Bicycle"),
                correctAnswer = "Bicycle", // Not an artist
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Triptych", "Polyptych", "Diptych", "Chair"),
                correctAnswer = "Chair", // Not a type of artwork
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Action Painting",
                    "Color Field",
                    "Hard-edge Painting",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a painting style
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Judith Slaying Holofernes",
                    "The Night Watch",
                    "Las Meninas",
                    "Television"
                ),
                correctAnswer = "Television", // Not a famous painting
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Isenheim Altarpiece",
                    "The Ghent Altarpiece",
                    "The Merode Altarpiece",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a famous altarpiece
                category = Category.ART,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Suprematismo", "Constructivismo", "De Stijl", "Lámpara"),
                correctAnswer = "Lámpara", // No es un movimiento artístico
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Kazimir Malevich", "Piet Mondrian", "Wassily Kandinsky", "Coche"),
                correctAnswer = "Coche", // No es un artista
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Cuadrado Negro",
                    "Broadway Boogie Woogie",
                    "Composición VIII",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es una pintura famosa
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Dadaísmo", "Futurismo", "Vorticismo", "Mesa"),
                correctAnswer = "Mesa", // No es un movimiento artístico
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Arbotante", "Cariatide", "Pilastra", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un elemento arquitectónico
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hans Holbein", "Alberto Durero", "Lucas Cranach", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un artista
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Tríptico", "Políptico", "Díptico", "Silla"),
                correctAnswer = "Silla", // No es un tipo de obra de arte
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Pintura de Acción",
                    "Campo de Color",
                    "Pintura de Bordes Duros",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un estilo de pintura
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Judith decapitando a Holofernes",
                    "La Ronda de Noche",
                    "Las Meninas",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es una pintura famosa
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "El Retablo de Isenheim",
                    "El Retablo de Gante",
                    "El Retablo de Merode",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un retablo famoso
                category = Category.ART,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun celebrities() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Tom Cruise", "Brad Pitt", "Leonardo DiCaprio", "Lamp"),
                correctAnswer = "Lamp", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Taylor Swift", "Ariana Grande", "Beyoncé", "Car"),
                correctAnswer = "Car", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Oprah Winfrey", "Ellen DeGeneres", "Jimmy Fallon", "Tree"),
                correctAnswer = "Tree", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cristiano Ronaldo", "Lionel Messi", "Neymar", "Bicycle"),
                correctAnswer = "Bicycle", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Angelina Jolie",
                    "Scarlett Johansson",
                    "Jennifer Lawrence",
                    "Pen"
                ),
                correctAnswer = "Pen", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Drake", "Kanye West", "Post Malone", "Chair"),
                correctAnswer = "Chair", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("LeBron James", "Michael Jordan", "Kobe Bryant", "Spaceship"),
                correctAnswer = "Spaceship", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Bill Gates", "Elon Musk", "Jeff Bezos", "Table"),
                correctAnswer = "Table", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Dwayne Johnson", "Chris Hemsworth", "Jason Momoa", "Television"),
                correctAnswer = "Television", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Serena Williams", "Roger Federer", "Rafael Nadal", "Volcano"),
                correctAnswer = "Volcano", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Antonio Banderas", "Penélope Cruz", "Javier Bardem", "Lámpara"),
                correctAnswer = "Lámpara", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Shakira", "Ricky Martin", "Enrique Iglesias", "Coche"),
                correctAnswer = "Coche", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pablo Alborán", "Alejandro Sanz", "Luis Fonsi", "Árbol"),
                correctAnswer = "Árbol", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Lionel Messi", "Cristiano Ronaldo", "Rafa Nadal", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Salma Hayek", "Gael García Bernal", "Diego Luna", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Piqué", "Iniesta", "Xavi Hernández", "Silla"),
                correctAnswer = "Silla", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Rosalía", "Bad Bunny", "J Balvin", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pau Gasol", "Marc Gasol", "Ricky Rubio", "Mesa"),
                correctAnswer = "Mesa", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("David Bisbal", "Manuel Carrasco", "Luis Miguel", "Televisión"),
                correctAnswer = "Televisión", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Carlos Alcaraz", "Garbiñe Muguruza", "Paula Badosa", "Volcán"),
                correctAnswer = "Volcán", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Meryl Streep", "Cate Blanchett", "Tilda Swinton", "Lamp"),
                correctAnswer = "Lamp", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Ed Sheeran", "Sam Smith", "Shawn Mendes", "Car"),
                correctAnswer = "Car", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Trevor Noah", "John Oliver", "Stephen Colbert", "Tree"),
                correctAnswer = "Tree", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Novak Djokovic", "Andy Murray", "Dominic Thiem", "Bicycle"),
                correctAnswer = "Bicycle", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Anne Hathaway", "Emily Blunt", "Jessica Chastain", "Pen"),
                correctAnswer = "Pen", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Jay-Z", "Kendrick Lamar", "Travis Scott", "Chair"),
                correctAnswer = "Chair", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Naomi Osaka", "Simona Halep", "Ashleigh Barty", "Spaceship"),
                correctAnswer = "Spaceship", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Elon Musk", "Mark Zuckerberg", "Tim Cook", "Table"),
                correctAnswer = "Table", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Tom Hiddleston",
                    "Benedict Cumberbatch",
                    "Andrew Garfield",
                    "Television"
                ),
                correctAnswer = "Television", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Serena Williams", "Naomi Osaka", "Coco Gauff", "Volcano"),
                correctAnswer = "Volcano", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Penélope Cruz", "Maribel Verdú", "Blanca Portillo", "Lámpara"),
                correctAnswer = "Lámpara", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Pablo Alborán", "David Bustamante", "Manuel Carrasco", "Coche"),
                correctAnswer = "Coche", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Andrés Iniesta", "Iker Casillas", "Raúl González", "Árbol"),
                correctAnswer = "Árbol", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Rafa Nadal", "David Ferrer", "Feliciano López", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Natalia Lafourcade",
                    "Julieta Venegas",
                    "Carla Morrison",
                    "Bolígrafo"
                ),
                correctAnswer = "Bolígrafo", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Rosalía", "C. Tangana", "Bad Gyal", "Silla"),
                correctAnswer = "Silla", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Alberto Contador",
                    "Alejandro Valverde",
                    "Mikel Landa",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Carlos Alcaraz", "Paula Badosa", "Garbiñe Muguruza", "Mesa"),
                correctAnswer = "Mesa", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Luis Fonsi", "Ricky Martin", "Chayanne", "Televisión"),
                correctAnswer = "Televisión", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Jorge Drexler", "Luis Eduardo Aute", "Joaquín Sabina", "Volcán"),
                correctAnswer = "Volcán", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Tilda Swinton", "Frances McDormand", "Saoirse Ronan", "Lamp"),
                correctAnswer = "Lamp", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Bon Iver", "Fleet Foxes", "The National", "Car"),
                correctAnswer = "Car", // Not a celebrity or music group
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Trevor Noah", "Hasan Minhaj", "Amber Ruffin", "Tree"),
                correctAnswer = "Tree", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Simone Biles", "Allyson Felix", "Katie Ledecky", "Bicycle"),
                correctAnswer = "Bicycle", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Viola Davis", "Regina King", "Angela Bassett", "Pen"),
                correctAnswer = "Pen", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Chance the Rapper",
                    "Childish Gambino",
                    "Anderson .Paak",
                    "Chair"
                ),
                correctAnswer = "Chair", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Iga Swiatek",
                    "Barbora Krejcikova",
                    "Aryna Sabalenka",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Satya Nadella", "Sundar Pichai", "Arvind Krishna", "Table"),
                correctAnswer = "Table", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Mahershala Ali",
                    "Chiwetel Ejiofor",
                    "Lakeith Stanfield",
                    "Television"
                ),
                correctAnswer = "Television", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Allyson Felix",
                    "Florence Griffith Joyner",
                    "Wilma Rudolph",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a celebrity
                category = Category.CELEBRITIES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("María Valverde", "Adriana Ugarte", "Inma Cuesta", "Lámpara"),
                correctAnswer = "Lámpara", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Vetusta Morla", "Love of Lesbian", "Izal", "Coche"),
                correctAnswer = "Coche", // No es un grupo musical
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Dani Rovira", "Joaquín Reyes", "Ernesto Sevilla", "Árbol"),
                correctAnswer = "Árbol", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Mireia Belmonte",
                    "Carolina Marín",
                    "Lydia Valentín",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Paz Vega", "Candela Peña", "Elena Anaya", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("C. Tangana", "Rels B", "Kase.O", "Silla"),
                correctAnswer = "Silla", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Alberto Ginés",
                    "Nicolás Massú",
                    "Saúl Craviotto",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Rafael Moneo", "Santiago Calatrava", "Norman Foster", "Mesa"),
                correctAnswer = "Mesa", // No es un arquitecto
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Javier Cámara", "Luis Tosar", "Eduard Fernández", "Televisión"),
                correctAnswer = "Televisión", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Anabel Medina", "Conchita Martínez", "Virginia Ruano", "Volcán"),
                correctAnswer = "Volcán", // No es una celebridad
                category = Category.CELEBRITIES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun animals() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Dog", "Cat", "Elephant", "Lamp"),
                correctAnswer = "Lamp", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tiger", "Lion", "Leopard", "Car"),
                correctAnswer = "Car", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Shark", "Whale", "Dolphin", "Tree"),
                correctAnswer = "Tree", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Penguin", "Ostrich", "Flamingo", "Bicycle"),
                correctAnswer = "Bicycle", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Frog", "Toad", "Salamander", "Pen"),
                correctAnswer = "Pen", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Wolf", "Fox", "Coyote", "Chair"),
                correctAnswer = "Chair", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Parrot", "Peacock", "Eagle", "Spaceship"),
                correctAnswer = "Spaceship", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Horse", "Donkey", "Zebra", "Table"),
                correctAnswer = "Table", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Snake", "Lizard", "Turtle", "Television"),
                correctAnswer = "Television", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Bee", "Ant", "Butterfly", "Volcano"),
                correctAnswer = "Volcano", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Perro", "Gato", "Elefante", "Lámpara"),
                correctAnswer = "Lámpara", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tigre", "León", "Leopardo", "Coche"),
                correctAnswer = "Coche", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tiburón", "Ballena", "Delfín", "Árbol"),
                correctAnswer = "Árbol", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pingüino", "Avestruz", "Flamenco", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Rana", "Sapo", "Salamandra", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Lobo", "Zorro", "Coyote", "Silla"),
                correctAnswer = "Silla", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Loro", "Pavo Real", "Águila", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Caballo", "Burro", "Cebra", "Mesa"),
                correctAnswer = "Mesa", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Serpiente", "Lagarto", "Tortuga", "Televisión"),
                correctAnswer = "Televisión", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Abeja", "Hormiga", "Mariposa", "Volcán"),
                correctAnswer = "Volcán", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Koala", "Wombat", "Kangaroo", "Lamp"),
                correctAnswer = "Lamp", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Platypus", "Echidna", "Axolotl", "Car"),
                correctAnswer = "Car", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Polar Bear", "Grizzly Bear", "Panda Bear", "Tree"),
                correctAnswer = "Tree", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Anaconda", "Boa Constrictor", "Python", "Bicycle"),
                correctAnswer = "Bicycle", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hawk", "Falcon", "Eagle", "Pen"),
                correctAnswer = "Pen", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Manatee", "Dugong", "Beluga", "Chair"),
                correctAnswer = "Chair", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Chameleon", "Gecko", "Iguana", "Spaceship"),
                correctAnswer = "Spaceship", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Arctic Fox", "Red Fox", "Fennec Fox", "Table"),
                correctAnswer = "Table", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Emperor Penguin",
                    "King Penguin",
                    "Macaroni Penguin",
                    "Television"
                ),
                correctAnswer = "Television", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Walrus", "Sea Lion", "Seal", "Volcano"),
                correctAnswer = "Volcano", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Koala", "Wombat", "Canguro", "Lámpara"),
                correctAnswer = "Lámpara", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Ornitorrinco", "Equidna", "Ajolote", "Coche"),
                correctAnswer = "Coche", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Oso Polar", "Oso Grizzly", "Oso Panda", "Árbol"),
                correctAnswer = "Árbol", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Anaconda", "Boa Constrictora", "Pitón", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Halcón", "Águila", "Milano", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Manatí", "Dugongo", "Beluga", "Silla"),
                correctAnswer = "Silla", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Camaleón", "Geco", "Iguana", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Zorro Ártico", "Zorro Rojo", "Zorro del Desierto", "Mesa"),
                correctAnswer = "Mesa", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Pingüino Emperador",
                    "Pingüino Rey",
                    "Pingüino Macaroni",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Morsa", "León Marino", "Foca", "Volcán"),
                correctAnswer = "Volcán", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Aye-Aye", "Tarsier", "Colugo", "Lamp"),
                correctAnswer = "Lamp", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Saiga Antelope", "Markhor", "Bongo", "Car"),
                correctAnswer = "Car", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Axolotl", "Olm", "Hellbender", "Tree"),
                correctAnswer = "Tree", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Pangolin", "Armadillo", "Echidna", "Bicycle"),
                correctAnswer = "Bicycle", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Cassowary", "Kakapo", "Shoebill", "Pen"),
                correctAnswer = "Pen", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Blobfish", "Goblin Shark", "Viperfish", "Chair"),
                correctAnswer = "Chair", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Okapi", "Quokka", "Quagga", "Spaceship"),
                correctAnswer = "Spaceship", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Frigatebird", "Albatross", "Petrel", "Table"),
                correctAnswer = "Table", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Japanese Spider Crab",
                    "Horseshoe Crab",
                    "King Crab",
                    "Television"
                ),
                correctAnswer = "Television", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Tree Kangaroo", "Sugar Glider", "Flying Lemur", "Volcano"),
                correctAnswer = "Volcano", // Not an animal
                category = Category.ANIMALS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Aye-Aye", "Tarsero", "Colugo", "Lámpara"),
                correctAnswer = "Lámpara", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Antílope Saiga", "Markhor", "Bongo", "Coche"),
                correctAnswer = "Coche", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Ajolote", "Olm", "Hellbender", "Árbol"),
                correctAnswer = "Árbol", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Pangolín", "Armadillo", "Equidna", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Casuario", "Kakapo", "Picozapato", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Pez Gota", "Tiburón Duende", "Pez Vípera", "Silla"),
                correctAnswer = "Silla", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Okapi", "Quokka", "Quagga", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Ave Fragata", "Albatros", "Petrel", "Mesa"),
                correctAnswer = "Mesa", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Cangrejo Gigante Japonés",
                    "Cangrejo Herradura",
                    "Cangrejo Real",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Canguro Arbóreo",
                    "Petauro del Azúcar",
                    "Lémur Volador",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un animal
                category = Category.ANIMALS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
        )

        private fun vehicles() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Car", "Truck", "Motorcycle", "Lamp"),
                correctAnswer = "Lamp", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Bicycle", "Scooter", "Skateboard", "Tree"),
                correctAnswer = "Tree", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Airplane", "Helicopter", "Glider", "Chair"),
                correctAnswer = "Chair", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Train", "Tram", "Subway", "Pen"),
                correctAnswer = "Pen", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Ship", "Boat", "Yacht", "Table"),
                correctAnswer = "Table", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Bus", "Minivan", "Coach", "Spaceship"),
                correctAnswer = "Spaceship", // Not a land vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("SUV", "Convertible", "Hatchback", "Television"),
                correctAnswer = "Television", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Kayak", "Canoe", "Raft", "Volcano"),
                correctAnswer = "Volcano", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Ferry", "Cargo Ship", "Cruise Ship", "Bicycle"),
                correctAnswer = "Bicycle", // Not a water vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Rocket", "Space Shuttle", "Satellite", "Tree"),
                correctAnswer = "Tree", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Coche", "Camión", "Motocicleta", "Lámpara"),
                correctAnswer = "Lámpara", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Bicicleta", "Patinete", "Monopatín", "Árbol"),
                correctAnswer = "Árbol", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Avión", "Helicóptero", "Planeador", "Silla"),
                correctAnswer = "Silla", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Tren", "Tranvía", "Metro", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Barco", "Lancha", "Yate", "Mesa"),
                correctAnswer = "Mesa", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Autobús", "Furgoneta", "Autocar", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un vehículo terrestre
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Todoterreno", "Convertible", "Hatchback", "Televisión"),
                correctAnswer = "Televisión", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Kayak", "Canoa", "Balsa", "Volcán"),
                correctAnswer = "Volcán", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Ferry", "Barco de Carga", "Crucero", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un vehículo acuático
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cohete", "Transbordador Espacial", "Satélite", "Árbol"),
                correctAnswer = "Árbol", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Electric Car", "Hybrid Car", "Diesel Car", "Lamp"),
                correctAnswer = "Lamp", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hot Air Balloon", "Zeppelin", "Blimp", "Tree"),
                correctAnswer = "Tree", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Bullet Train", "Maglev Train", "Commuter Train", "Chair"),
                correctAnswer = "Chair", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Sailing Boat", "Catamaran", "Trimaran", "Pen"),
                correctAnswer = "Pen", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Snowmobile", "Jet Ski", "ATV", "Spaceship"),
                correctAnswer = "Spaceship", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Ambulance", "Fire Truck", "Police Car", "Television"),
                correctAnswer = "Television", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Hovercraft", "Hydrofoil", "Submarine", "Bicycle"),
                correctAnswer = "Bicycle", // Not a water vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Cargo Plane", "Fighter Jet", "Passenger Plane", "Table"),
                correctAnswer = "Table", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Electric Scooter", "Kick Scooter", "Foldable Scooter", "Volcano"),
                correctAnswer = "Volcano", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("SpaceX Rocket", "Blue Origin Rocket", "NASA Shuttle", "Tree"),
                correctAnswer = "Tree", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Coche Eléctrico", "Coche Híbrido", "Coche Diésel", "Lámpara"),
                correctAnswer = "Lámpara", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Globo Aerostático", "Zepelín", "Dirigible", "Árbol"),
                correctAnswer = "Árbol", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Tren Bala", "Tren Maglev", "Tren de Cercanías", "Silla"),
                correctAnswer = "Silla", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Velero", "Catamarán", "Trimarán", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Moto de Nieve", "Jet Ski", "Quad", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Ambulancia",
                    "Camión de Bomberos",
                    "Coche de Policía",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Aerodeslizador", "Hidroala", "Submarino", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un vehículo acuático
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Avión de Carga", "Avión de Combate", "Avión Comercial", "Mesa"),
                correctAnswer = "Mesa", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Patinete Eléctrico",
                    "Patinete Manual",
                    "Patinete Plegable",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Cohete de SpaceX",
                    "Cohete de Blue Origin",
                    "Transbordador de la NASA",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Tuk-Tuk", "Rickshaw", "Velomobile", "Lamp"),
                correctAnswer = "Lamp", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Amphibious Car", "Hovercraft", "Hydrofoil", "Tree"),
                correctAnswer = "Tree", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Sidecar Motorcycle",
                    "Cruiser Motorcycle",
                    "Touring Motorcycle",
                    "Chair"
                ),
                correctAnswer = "Chair", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Airship", "Gyrocopter", "Autogyro", "Pen"),
                correctAnswer = "Pen", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Tandem Bicycle",
                    "Recumbent Bicycle",
                    "Penny-Farthing",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a bicycle type
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Dinghy", "Skiff", "Coracle", "Television"),
                correctAnswer = "Television", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Maglev Train", "Monorail", "Funicular", "Bicycle"),
                correctAnswer = "Bicycle", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Fighter Jet", "Stealth Bomber", "Attack Helicopter", "Table"),
                correctAnswer = "Table", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Electric Unicycle", "Hoverboard", "Segway", "Volcano"),
                correctAnswer = "Volcano", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Suborbital Spaceplane",
                    "Reusable Rocket",
                    "Interplanetary Probe",
                    "Tree"
                ),
                correctAnswer = "Tree", // Not a vehicle
                category = Category.VEHICLES,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Tuk-Tuk", "Rickshaw", "Velomóvil", "Lámpara"),
                correctAnswer = "Lámpara", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Coche Anfibio", "Aerodeslizador", "Hidroala", "Árbol"),
                correctAnswer = "Árbol", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Moto con Sidecar", "Moto Cruiser", "Moto Touring", "Silla"),
                correctAnswer = "Silla", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Dirigible", "Girocóptero", "Autogiro", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Bicicleta Tándem",
                    "Bicicleta Reclinada",
                    "Bicicleta Penny-Farthing",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un tipo de bicicleta
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Bote Inflable", "Bote Skiff", "Coracle", "Televisión"),
                correctAnswer = "Televisión", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Tren Maglev", "Monorraíl", "Funicular", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Avión de Combate",
                    "Bombardero Stealth",
                    "Helicóptero de Ataque",
                    "Mesa"
                ),
                correctAnswer = "Mesa", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Monociclo Eléctrico", "Hoverboard", "Segway", "Volcán"),
                correctAnswer = "Volcán", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Avión Suborbital",
                    "Cohete Reutilizable",
                    "Sonda Interplanetaria",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es un vehículo
                category = Category.VEHICLES,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun comics() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Spider-Man", "Batman", "Superman", "Lamp"),
                correctAnswer = "Lamp", // Not a comic book character
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Marvel", "DC", "Dark Horse", "Car"),
                correctAnswer = "Car", // Not a comic book publisher
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Gotham City", "Metropolis", "Asgard", "Tree"),
                correctAnswer = "Tree", // Not a comic book setting
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Iron Man", "Thor", "Hulk", "Bicycle"),
                correctAnswer = "Bicycle", // Not a comic book character
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Wonder Woman", "Black Widow", "Harley Quinn", "Pen"),
                correctAnswer = "Pen", // Not a comic book character
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Avengers", "The Justice League", "The X-Men", "Chair"),
                correctAnswer = "Chair", // Not a superhero team
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Joker", "Lex Luthor", "Green Goblin", "Spaceship"),
                correctAnswer = "Spaceship", // Not a comic book villain
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Stan Lee", "Jack Kirby", "Bob Kane", "Table"),
                correctAnswer = "Table", // Not a comic book creator
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Wolverine", "Deadpool", "Cyclops", "Television"),
                correctAnswer = "Television", // Not a comic book character
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Daily Bugle",
                    "The Daily Planet",
                    "The Gotham Gazette",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a fictional newspaper
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Spider-Man", "Batman", "Superman", "Lámpara"),
                correctAnswer = "Lámpara", // No es un personaje de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Marvel", "DC", "Dark Horse", "Coche"),
                correctAnswer = "Coche", // No es una editorial de cómics
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Ciudad Gótica", "Metrópolis", "Asgard", "Árbol"),
                correctAnswer = "Árbol", // No es un lugar ficticio de cómics
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Iron Man", "Thor", "Hulk", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un personaje de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Mujer Maravilla", "Viuda Negra", "Harley Quinn", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un personaje de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Los Vengadores", "La Liga de la Justicia", "Los X-Men", "Silla"),
                correctAnswer = "Silla", // No es un equipo de superhéroes
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("El Guasón", "Lex Luthor", "Duende Verde", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un villano de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Stan Lee", "Jack Kirby", "Bob Kane", "Mesa"),
                correctAnswer = "Mesa", // No es un creador de cómics
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Wolverine", "Deadpool", "Cíclope", "Televisión"),
                correctAnswer = "Televisión", // No es un personaje de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "El Clarín",
                    "El Planeta Diario",
                    "La Gaceta de Ciudad Gótica",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un periódico ficticio
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Green Lantern", "The Flash", "Aquaman", "Lamp"),
                correctAnswer = "Lamp", // Not a comic book character
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Image Comics", "Valiant Comics", "IDW Publishing", "Car"),
                correctAnswer = "Car", // Not a comic book publisher
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Krypton", "Themyscira", "Atlantis", "Tree"),
                correctAnswer = "Tree", // Not a fictional comic book location
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Doctor Strange", "Scarlet Witch", "Black Panther", "Chair"),
                correctAnswer = "Chair", // Not a comic book character
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Sinestro", "Reverse Flash", "Black Manta", "Pen"),
                correctAnswer = "Pen", // Not a comic book villain
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Fantastic Four",
                    "The Teen Titans",
                    "The Watchmen",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a superhero team
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Alan Moore", "Frank Miller", "Neil Gaiman", "Table"),
                correctAnswer = "Table", // Not a comic book creator
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Infinity Gauntlet",
                    "Crisis on Infinite Earths",
                    "Secret Wars",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a comic book storyline
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Venom", "Carnage", "Morbius", "Television"),
                correctAnswer = "Television", // Not a comic book character
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Daily Planet", "Bugle", "Gotham Gazette", "Bicycle"),
                correctAnswer = "Bicycle", // Not a comic book fictional newspaper
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Linterna Verde", "Flash", "Acuaman", "Lámpara"),
                correctAnswer = "Lámpara", // No es un personaje de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Image Comics", "Valiant Comics", "IDW Publishing", "Coche"),
                correctAnswer = "Coche", // No es una editorial de cómics
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Kripton", "Themyscira", "Atlantis", "Árbol"),
                correctAnswer = "Árbol", // No es un lugar ficticio de cómics
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Doctor Strange", "Bruja Escarlata", "Pantera Negra", "Silla"),
                correctAnswer = "Silla", // No es un personaje de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Siniestro", "Flash Reverso", "Manta Negra", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un villano de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Los Cuatro Fantásticos",
                    "Los Jóvenes Titanes",
                    "Los Vigilantes",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un equipo de superhéroes
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Alan Moore", "Frank Miller", "Neil Gaiman", "Mesa"),
                correctAnswer = "Mesa", // No es un creador de cómics
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "El Guantelete del Infinito",
                    "Crisis en Tierras Infinitas",
                    "Secret Wars",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es una historia de cómics
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Venom", "Carnage", "Morbius", "Televisión"),
                correctAnswer = "Televisión", // No es un personaje de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Planeta Diario",
                    "Clarín",
                    "Gaceta de Ciudad Gótica",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un periódico ficticio de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Swamp Thing", "Animal Man", "Constantine", "Lamp"),
                correctAnswer = "Lamp", // Not a comic book character
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Vertigo", "WildStorm", "Milestone", "Car"),
                correctAnswer = "Car", // Not a comic book imprint
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Apokolips", "Oa", "New Genesis", "Tree"),
                correctAnswer = "Tree", // Not a fictional location
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Doctor Doom", "Darkseid", "Galactus", "Chair"),
                correctAnswer = "Chair", // Not a comic book villain
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Saga", "Y: The Last Man", "Paper Girls", "Pen"),
                correctAnswer = "Pen", // Not a comic book series
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Jack Kirby", "Steve Ditko", "Carmine Infantino", "Spaceship"),
                correctAnswer = "Spaceship", // Not a comic book creator
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Elektra", "Black Canary", "Zatanna", "Table"),
                correctAnswer = "Table", // Not a comic book character
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Dark Knight Returns",
                    "Kingdom Come",
                    "All-Star Superman",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a comic book storyline
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Silver Surfer", "Adam Warlock", "Beta Ray Bill", "Television"),
                correctAnswer = "Television", // Not a comic book character
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Baxter Building",
                    "The Hall of Justice",
                    "The Tower of Fate",
                    "Bicycle"
                ),
                correctAnswer = "Bicycle", // Not a fictional location
                category = Category.COMICS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("La Cosa del Pantano", "Hombre Animal", "Constantine", "Lámpara"),
                correctAnswer = "Lámpara", // No es un personaje de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Vértigo", "WildStorm", "Milestone", "Coche"),
                correctAnswer = "Coche", // No es un sello de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Apokolips", "Oa", "Nueva Génesis", "Árbol"),
                correctAnswer = "Árbol", // No es un lugar ficticio
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Doctor Doom", "Darkseid", "Galactus", "Silla"),
                correctAnswer = "Silla", // No es un villano de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Saga", "Y: El Último Hombre", "Paper Girls", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es una serie de cómics
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Jack Kirby", "Steve Ditko", "Carmine Infantino", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un creador de cómics
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Elektra", "Canario Negro", "Zatanna", "Mesa"),
                correctAnswer = "Mesa", // No es un personaje de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "El Regreso del Caballero Oscuro",
                    "Kingdom Come",
                    "All-Star Superman",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es una historia de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Silver Surfer", "Adam Warlock", "Beta Ray Bill", "Televisión"),
                correctAnswer = "Televisión", // No es un personaje de cómic
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "El Edificio Baxter",
                    "El Salón de la Justicia",
                    "La Torre del Destino",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un lugar ficticio
                category = Category.COMICS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun gadgets() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Smartphone", "Tablet", "Laptop", "Lamp"),
                correctAnswer = "Lamp", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Smartwatch", "Fitness Tracker", "Bluetooth Earbuds", "Car"),
                correctAnswer = "Car", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Digital Camera", "Drone", "GoPro", "Tree"),
                correctAnswer = "Tree", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Wireless Charger", "Power Bank", "Portable Speaker", "Bicycle"),
                correctAnswer = "Bicycle", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Gaming Console", "VR Headset", "Gaming Mouse", "Pen"),
                correctAnswer = "Pen", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Smart TV", "Streaming Stick", "Home Projector", "Chair"),
                correctAnswer = "Chair", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "E-Reader",
                    "Noise-Canceling Headphones",
                    "Smart Glasses",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "USB Drive",
                    "External Hard Drive",
                    "Cloud Storage Device",
                    "Table"
                ),
                correctAnswer = "Table", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Home Assistant",
                    "Smart Thermostat",
                    "Robot Vacuum",
                    "Television"
                ),
                correctAnswer = "Television", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Smart Light Bulb", "Smart Plug", "Wi-Fi Extender", "Volcano"),
                correctAnswer = "Volcano", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Teléfono Inteligente", "Tableta", "Portátil", "Lámpara"),
                correctAnswer = "Lámpara", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Reloj Inteligente",
                    "Monitor de Actividad",
                    "Auriculares Bluetooth",
                    "Coche"
                ),
                correctAnswer = "Coche", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Cámara Digital", "Drone", "GoPro", "Árbol"),
                correctAnswer = "Árbol", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Cargador Inalámbrico",
                    "Power Bank",
                    "Altavoz Portátil",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Consola de Videojuegos",
                    "Casco de VR",
                    "Ratón Gaming",
                    "Bolígrafo"
                ),
                correctAnswer = "Bolígrafo", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Televisión Inteligente",
                    "Dispositivo de Streaming",
                    "Proyector Casero",
                    "Silla"
                ),
                correctAnswer = "Silla", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "E-Reader",
                    "Auriculares con Cancelación de Ruido",
                    "Gafas Inteligentes",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Unidad USB",
                    "Disco Duro Externo",
                    "Dispositivo de Almacenamiento en la Nube",
                    "Mesa"
                ),
                correctAnswer = "Mesa", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Asistente Doméstico",
                    "Termostato Inteligente",
                    "Aspiradora Robot",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Bombilla Inteligente",
                    "Enchufe Inteligente",
                    "Extensor de Wi-Fi",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Smart Lock", "Video Doorbell", "Home Security Camera", "Lamp"),
                correctAnswer = "Lamp", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("3D Printer", "Laser Cutter", "CNC Router", "Tree"),
                correctAnswer = "Tree", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Portable Monitor", "Docking Station", "Ergonomic Mouse", "Chair"),
                correctAnswer = "Chair", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Smart Ring", "Smart Glasses", "Fitness Ring", "Pen"),
                correctAnswer = "Pen", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Wi-Fi Mesh System",
                    "Range Extender",
                    "Powerline Adapter",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Thermal Printer", "Photo Scanner", "Laminator", "Table"),
                correctAnswer = "Table", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Streaming Webcam",
                    "Conference Microphone",
                    "Ring Light",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Smart Garage Door Opener",
                    "Outdoor Smart Plug",
                    "Smart Irrigation Controller",
                    "Television"
                ),
                correctAnswer = "Television", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Electric Skateboard",
                    "Hoverboard",
                    "Electric Scooter",
                    "Bicycle"
                ),
                correctAnswer = "Bicycle", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Noise Sensor", "Air Quality Monitor", "Smart Thermostat", "Tree"),
                correctAnswer = "Tree", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Cerradura Inteligente",
                    "Timbre con Video",
                    "Cámara de Seguridad",
                    "Lámpara"
                ),
                correctAnswer = "Lámpara", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Impresora 3D", "Cortadora Láser", "Router CNC", "Árbol"),
                correctAnswer = "Árbol", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Monitor Portátil", "Base Docking", "Ratón Ergonómico", "Silla"),
                correctAnswer = "Silla", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Anillo Inteligente",
                    "Gafas Inteligentes",
                    "Anillo Fitness",
                    "Bolígrafo"
                ),
                correctAnswer = "Bolígrafo", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Sistema de Malla Wi-Fi",
                    "Extensor de Rango",
                    "Adaptador Powerline",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Impresora Térmica", "Escáner de Fotos", "Plastificadora", "Mesa"),
                correctAnswer = "Mesa", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Webcam para Streaming",
                    "Micrófono de Conferencia",
                    "Aro de Luz",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Abridor de Garaje Inteligente",
                    "Enchufe Exterior Inteligente",
                    "Controlador de Riego Inteligente",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Monopatín Eléctrico",
                    "Hoverboard",
                    "Patinete Eléctrico",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Sensor de Ruido",
                    "Monitor de Calidad del Aire",
                    "Termostato Inteligente",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Lidar Scanner", "Infrared Thermometer", "Spectrometer", "Lamp"),
                correctAnswer = "Lamp", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Mechanical Keyboard",
                    "Ergonomic Trackball",
                    "Programmable Mouse",
                    "Tree"
                ),
                correctAnswer = "Tree", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Smart Blood Pressure Monitor",
                    "Smart ECG Monitor",
                    "Pulse Oximeter",
                    "Chair"
                ),
                correctAnswer = "Chair", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Raspberry Pi", "Arduino", "BeagleBone", "Pen"),
                correctAnswer = "Pen", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Smart Sprinkler Controller",
                    "Soil Moisture Sensor",
                    "Weather Station",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Portable Solar Charger",
                    "Foldable Solar Panel",
                    "Power Inverter",
                    "Table"
                ),
                correctAnswer = "Table", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Thermal Imaging Camera",
                    "UV Flashlight",
                    "Multispectral Camera",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Smart Coffee Machine",
                    "Automated Cocktail Maker",
                    "Self-Cleaning Oven",
                    "Television"
                ),
                correctAnswer = "Television", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Smart Air Purifier", "Dehumidifier", "Humidifier", "Bicycle"),
                correctAnswer = "Bicycle", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Electric Winch",
                    "Car Diagnostic Scanner",
                    "Digital Torque Wrench",
                    "Tree"
                ),
                correctAnswer = "Tree", // Not a gadget
                category = Category.GADGETS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf(
                    "Escáner Lidar",
                    "Termómetro Infrarrojo",
                    "Espectrómetro",
                    "Lámpara"
                ),
                correctAnswer = "Lámpara", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Teclado Mecánico",
                    "Trackball Ergonómico",
                    "Ratón Programable",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Monitor de Presión Arterial Inteligente",
                    "Monitor de ECG Inteligente",
                    "Oxímetro de Pulso",
                    "Silla"
                ),
                correctAnswer = "Silla", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Raspberry Pi", "Arduino", "BeagleBone", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Controlador de Aspersores Inteligente",
                    "Sensor de Humedad del Suelo",
                    "Estación Meteorológica",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Cargador Solar Portátil",
                    "Panel Solar Plegable",
                    "Inversor de Potencia",
                    "Mesa"
                ),
                correctAnswer = "Mesa", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Cámara de Imagen Térmica",
                    "Linterna UV",
                    "Cámara Multiespectral",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Máquina de Café Inteligente",
                    "Fabricante de Cócteles Automatizado",
                    "Horno Autolimpiante",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Purificador de Aire Inteligente",
                    "Deshumidificador",
                    "Humidificador",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Winche Eléctrico",
                    "Escáner de Diagnóstico para Autos",
                    "Llave Dinamométrica Digital",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es un gadget
                category = Category.GADGETS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun animeAndManga() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Naruto", "Dragon Ball", "One Piece", "Lamp"),
                correctAnswer = "Lamp", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Attack on Titan", "My Hero Academia", "Demon Slayer", "Car"),
                correctAnswer = "Car", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Sailor Moon", "Tokyo Ghoul", "Death Note", "Tree"),
                correctAnswer = "Tree", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pokemon", "Digimon", "Yu-Gi-Oh!", "Bicycle"),
                correctAnswer = "Bicycle", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Bleach", "Fairy Tail", "Black Clover", "Pen"),
                correctAnswer = "Pen", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Hunter x Hunter", "Fullmetal Alchemist", "Soul Eater", "Chair"),
                correctAnswer = "Chair", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("One Punch Man", "Sword Art Online", "Re:Zero", "Spaceship"),
                correctAnswer = "Spaceship", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Haikyuu!!", "Kuroko no Basket", "Free!", "Table"),
                correctAnswer = "Table", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "JoJo's Bizarre Adventure",
                    "Blue Exorcist",
                    "The Seven Deadly Sins",
                    "Television"
                ),
                correctAnswer = "Television", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Neon Genesis Evangelion", "Cowboy Bebop", "Trigun", "Volcano"),
                correctAnswer = "Volcano", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Naruto", "Dragon Ball", "One Piece", "Lámpara"),
                correctAnswer = "Lámpara", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Ataque a los Titanes",
                    "My Hero Academia",
                    "Demon Slayer",
                    "Coche"
                ),
                correctAnswer = "Coche", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Sailor Moon", "Tokyo Ghoul", "Death Note", "Árbol"),
                correctAnswer = "Árbol", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Pokemon", "Digimon", "Yu-Gi-Oh!", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Bleach", "Fairy Tail", "Black Clover", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Hunter x Hunter", "Fullmetal Alchemist", "Soul Eater", "Silla"),
                correctAnswer = "Silla", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("One Punch Man", "Sword Art Online", "Re:Zero", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Haikyuu!!", "Kuroko no Basket", "Free!", "Mesa"),
                correctAnswer = "Mesa", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "JoJo's Bizarre Adventure",
                    "Blue Exorcist",
                    "Los Siete Pecados Capitales",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Neon Genesis Evangelion", "Cowboy Bebop", "Trigun", "Volcán"),
                correctAnswer = "Volcán", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Code Geass", "Steins;Gate", "Erased", "Lamp"),
                correctAnswer = "Lamp", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Berserk", "Vinland Saga", "Claymore", "Car"),
                correctAnswer = "Car", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Parasyte", "Another", "Hellsing", "Tree"),
                correctAnswer = "Tree", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Akame ga Kill!", "Toradora!", "Angel Beats!", "Bicycle"),
                correctAnswer = "Bicycle", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Psycho-Pass", "Ergo Proxy", "Terror in Resonance", "Pen"),
                correctAnswer = "Pen", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Fate/Stay Night", "Fate/Zero", "Fate/Apocrypha", "Chair"),
                correctAnswer = "Chair", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "No Game No Life",
                    "The Rising of the Shield Hero",
                    "Log Horizon",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Monster", "Death Parade", "Paranoia Agent", "Table"),
                correctAnswer = "Table", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Golden Kamuy",
                    "Samurai Champloo",
                    "Rurouni Kenshin",
                    "Television"
                ),
                correctAnswer = "Television", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Promised Neverland",
                    "Made in Abyss",
                    "March Comes in Like a Lion",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Code Geass", "Steins;Gate", "Erased", "Lámpara"),
                correctAnswer = "Lámpara", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Berserk", "Vinland Saga", "Claymore", "Coche"),
                correctAnswer = "Coche", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Parasyte", "Another", "Hellsing", "Árbol"),
                correctAnswer = "Árbol", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Akame ga Kill!", "Toradora!", "Angel Beats!", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Psycho-Pass", "Ergo Proxy", "Terror in Resonance", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Fate/Stay Night", "Fate/Zero", "Fate/Apocrypha", "Silla"),
                correctAnswer = "Silla", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "No Game No Life",
                    "The Rising of the Shield Hero",
                    "Log Horizon",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Monster", "Death Parade", "Paranoia Agent", "Mesa"),
                correctAnswer = "Mesa", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Golden Kamuy",
                    "Samurai Champloo",
                    "Rurouni Kenshin",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Promised Neverland",
                    "Made in Abyss",
                    "March Comes in Like a Lion",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Ping Pong the Animation", "Tatami Galaxy", "Kaiba", "Lamp"),
                correctAnswer = "Lamp", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Gankutsuou: The Count of Monte Cristo",
                    "Texhnolyze",
                    "Serial Experiments Lain",
                    "Car"
                ),
                correctAnswer = "Car", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Nana", "Paradise Kiss", "Honey and Clover", "Tree"),
                correctAnswer = "Tree", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Houseki no Kuni", "Shiki", "Monster Musume", "Bicycle"),
                correctAnswer = "Bicycle", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Planetes", "Eureka Seven", "Pale Cocoon", "Pen"),
                correctAnswer = "Pen", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Flowers of Evil",
                    "March Comes in Like a Lion",
                    "Barakamon",
                    "Chair"
                ),
                correctAnswer = "Chair", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hyouka", "Erased", "Shinsekai Yori", "Spaceship"),
                correctAnswer = "Spaceship", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Revolutionary Girl Utena",
                    "Princess Tutu",
                    "Red Garden",
                    "Table"
                ),
                correctAnswer = "Table", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Dorohedoro", "Deadman Wonderland", "Ajin", "Television"),
                correctAnswer = "Television", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Mushishi", "Ergo Proxy", "Kino's Journey", "Volcano"),
                correctAnswer = "Volcano", // Not an anime or manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Ping Pong the Animation", "Tatami Galaxy", "Kaiba", "Lámpara"),
                correctAnswer = "Lámpara", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Gankutsuou: El Conde de Montecristo",
                    "Texhnolyze",
                    "Serial Experiments Lain",
                    "Coche"
                ),
                correctAnswer = "Coche", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Nana", "Paradise Kiss", "Honey and Clover", "Árbol"),
                correctAnswer = "Árbol", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Houseki no Kuni", "Shiki", "Monster Musume", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Planetes", "Eureka Seven", "Pale Cocoon", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Las Flores del Mal",
                    "March Comes in Like a Lion",
                    "Barakamon",
                    "Silla"
                ),
                correctAnswer = "Silla", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Hyouka", "Erased", "Shinsekai Yori", "Nave Espacial"),
                correctAnswer = "Nave Espacial", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Revolutionary Girl Utena", "Princess Tutu", "Red Garden", "Mesa"),
                correctAnswer = "Mesa", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Dorohedoro", "Deadman Wonderland", "Ajin", "Televisión"),
                correctAnswer = "Televisión", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Mushishi", "Ergo Proxy", "Kino's Journey", "Volcán"),
                correctAnswer = "Volcán", // No es un anime o manga
                category = Category.ANIME_AND_MANGA,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )

        private fun cartoonsAndAnimation() = listOf(
            // EASY
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("SpongeBob SquarePants", "The Simpsons", "Tom and Jerry", "Lamp"),
                correctAnswer = "Lamp", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Adventure Time", "Regular Show", "Steven Universe", "Car"),
                correctAnswer = "Car", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Looney Tunes", "Scooby-Doo", "The Flintstones", "Tree"),
                correctAnswer = "Tree", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Rick and Morty", "Futurama", "Family Guy", "Bicycle"),
                correctAnswer = "Bicycle", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Avatar: The Last Airbender",
                    "The Legend of Korra",
                    "Teen Titans",
                    "Pen"
                ),
                correctAnswer = "Pen", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Powerpuff Girls", "Dexter's Laboratory", "Johnny Bravo", "Chair"),
                correctAnswer = "Chair", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Gravity Falls",
                    "Phineas and Ferb",
                    "Star vs. The Forces of Evil",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "DuckTales",
                    "Darkwing Duck",
                    "Chip 'n Dale: Rescue Rangers",
                    "Table"
                ),
                correctAnswer = "Table", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Teenage Mutant Ninja Turtles",
                    "Transformers",
                    "He-Man and the Masters of the Universe",
                    "Television"
                ),
                correctAnswer = "Television", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("The Jetsons", "Pink Panther", "The Smurfs", "Volcano"),
                correctAnswer = "Volcano", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.EASY
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Bob Esponja", "Los Simpson", "Tom y Jerry", "Lámpara"),
                correctAnswer = "Lámpara", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Hora de Aventura", "Un Show Más", "Steven Universe", "Coche"),
                correctAnswer = "Coche", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Looney Tunes", "Scooby-Doo", "Los Picapiedra", "Árbol"),
                correctAnswer = "Árbol", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Rick y Morty", "Futurama", "Padre de Familia", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Avatar: La Leyenda de Aang",
                    "La Leyenda de Korra",
                    "Los Jóvenes Titanes",
                    "Bolígrafo"
                ),
                correctAnswer = "Bolígrafo", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Las Chicas Superpoderosas",
                    "El Laboratorio de Dexter",
                    "Johnny Bravo",
                    "Silla"
                ),
                correctAnswer = "Silla", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Gravity Falls",
                    "Phineas y Ferb",
                    "Star vs. Las Fuerzas del Mal",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Patoaventuras",
                    "El Pato Darkwing",
                    "Chip y Dale: Rescatadores",
                    "Mesa"
                ),
                correctAnswer = "Mesa", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf(
                    "Las Tortugas Ninja",
                    "Transformers",
                    "He-Man y los Amos del Universo",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            FamiliesEntity(
                answers = listOf("Los Supersónicos", "La Pantera Rosa", "Los Pitufos", "Volcán"),
                correctAnswer = "Volcán", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.EASY
            ),
            // MEDIUM
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Over the Garden Wall", "The Owl House", "Amphibia", "Lamp"),
                correctAnswer = "Lamp", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Samurai Jack", "The Boondocks", "Primal", "Car"),
                correctAnswer = "Car", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Invader Zim",
                    "Courage the Cowardly Dog",
                    "Foster's Home for Imaginary Friends",
                    "Tree"
                ),
                correctAnswer = "Tree", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Batman: The Animated Series",
                    "Justice League",
                    "Young Justice",
                    "Bicycle"
                ),
                correctAnswer = "Bicycle", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Danny Phantom", "Ben 10", "Generator Rex", "Pen"),
                correctAnswer = "Pen", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Grim Adventures of Billy & Mandy",
                    "Codename: Kids Next Door",
                    "Ed, Edd n Eddy",
                    "Chair"
                ),
                correctAnswer = "Chair", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Voltron: Legendary Defender",
                    "She-Ra and the Princesses of Power",
                    "Castlevania",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Duck Dodgers", "Tiny Toon Adventures", "Animaniacs", "Table"),
                correctAnswer = "Table", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Gargoyles", "Darkwing Duck", "The Mighty Ducks", "Television"),
                correctAnswer = "Television", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "The Road Runner Show",
                    "Wacky Races",
                    "The Perils of Penelope Pitstop",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.MEDIUM
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("Más Allá del Jardín", "La Casa Búho", "Amphibia", "Lámpara"),
                correctAnswer = "Lámpara", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Samurái Jack", "The Boondocks", "Primal", "Coche"),
                correctAnswer = "Coche", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Invasor Zim",
                    "Coraje, el Perro Cobarde",
                    "La Mansión Foster para Amigos Imaginarios",
                    "Árbol"
                ),
                correctAnswer = "Árbol", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Batman: La Serie Animada",
                    "Liga de la Justicia",
                    "Jóvenes Titanes en Acción",
                    "Bicicleta"
                ),
                correctAnswer = "Bicicleta", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Danny Phantom", "Ben 10", "Generador Rex", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Las Macabras Aventuras de Billy y Mandy",
                    "Código: KND",
                    "Ed, Edd y Eddy",
                    "Silla"
                ),
                correctAnswer = "Silla", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Voltron: El Defensor Legendario",
                    "She-Ra y las Princesas del Poder",
                    "Castlevania",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf("Duck Dodgers", "Tiny Toon Adventures", "Animaniacs", "Mesa"),
                correctAnswer = "Mesa", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "Gárgolas",
                    "El Pato Darkwing",
                    "Los Poderosos Patos",
                    "Televisión"
                ),
                correctAnswer = "Televisión", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            FamiliesEntity(
                answers = listOf(
                    "El Show del Correcaminos",
                    "Los Autos Locos",
                    "Los Peligros de Penélope Glamour",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.MEDIUM
            ),
            // HARD
            //////////////////////// EN ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("BoJack Horseman", "Big Mouth", "F is for Family", "Lamp"),
                correctAnswer = "Lamp", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("ReBoot", "Code Lyoko", "Tron: Uprising", "Car"),
                correctAnswer = "Car", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Final Space", "Disenchantment", "Solar Opposites", "Tree"),
                correctAnswer = "Tree", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Infinity Train", "The Midnight Gospel", "Undone", "Bicycle"),
                correctAnswer = "Bicycle", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Archer", "Daria", "The Critic", "Pen"),
                correctAnswer = "Pen", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("The Dragon Prince", "Tales of Arcadia", "Wakfu", "Chair"),
                correctAnswer = "Chair", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Hilda",
                    "Kipo and the Age of Wonderbeasts",
                    "Carmen Sandiego",
                    "Spaceship"
                ),
                correctAnswer = "Spaceship", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Duckman", "Clone High", "Mission Hill", "Table"),
                correctAnswer = "Table", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("The Venture Bros.", "Metalocalypse", "Moral Orel", "Television"),
                correctAnswer = "Television", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Batman Beyond",
                    "Static Shock",
                    "Justice League Unlimited",
                    "Volcano"
                ),
                correctAnswer = "Volcano", // Not a cartoon or animation
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.EN,
                difficulty = Difficulty.HARD
            ),
            //////////////////////// ES ////////////////////////////////////
            FamiliesEntity(
                answers = listOf("BoJack Horseman", "Big Mouth", "F is for Family", "Lámpara"),
                correctAnswer = "Lámpara", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("ReBoot", "Code Lyoko", "Tron: La Resistencia", "Coche"),
                correctAnswer = "Coche", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Final Space", "Des(encanto)", "Solar Opposites", "Árbol"),
                correctAnswer = "Árbol", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Infinity Train", "The Midnight Gospel", "Undone", "Bicicleta"),
                correctAnswer = "Bicicleta", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Archer", "Daria", "El Crítico", "Bolígrafo"),
                correctAnswer = "Bolígrafo", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("El Príncipe Dragón", "Cuentos de Arcadia", "Wakfu", "Silla"),
                correctAnswer = "Silla", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Hilda",
                    "Kipo y la Era de los Magnimales",
                    "Carmen Sandiego",
                    "Nave Espacial"
                ),
                correctAnswer = "Nave Espacial", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("Duckman", "Clone High", "Mission Hill", "Mesa"),
                correctAnswer = "Mesa", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf("The Venture Bros.", "Metalocalypse", "Moral Orel", "Televisión"),
                correctAnswer = "Televisión", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            ),
            FamiliesEntity(
                answers = listOf(
                    "Batman del Futuro",
                    "Static Shock",
                    "Liga de la Justicia Ilimitada",
                    "Volcán"
                ),
                correctAnswer = "Volcán", // No es un dibujo animado
                category = Category.CARTOON_AND_ANIMATIONS,
                language = Languages.ES,
                difficulty = Difficulty.HARD
            )
        )
    }
}