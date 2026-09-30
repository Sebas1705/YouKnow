package es.sebas1705.resources.games


import es.sebas1705.core.resources.R

/**
 * Enum class that will manage the categories
 *
 * @property id [Int?]: Category id
 * @property strRes [Int]: Category string resources
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
enum class Category(val id: Int?, val strRes: Int) {
    ANY(null, R.string.core_resources_any),
    GENERAL_KNOWLEDGE(9, R.string.core_resources_cat_general_knowledge),
    BOOKS(10, R.string.core_resources_cat_entertainment_books),
    FILMS(11, R.string.core_resources_cat_entertainment_film),
    MUSIC(12, R.string.core_resources_cat_entertainment_music),
    MUSICALS_AND_THEATRES(13, R.string.core_resources_cat_entertainment_musicals_and_theatres),
    TELEVISION(14, R.string.core_resources_cat_entertainment_television),
    VIDEO_GAMES(15, R.string.core_resources_cat_entertainment_video_games),
    BOARD_GAMES(16, R.string.core_resources_cat_entertainment_board_games),
    SCIENCE_AND_NATURE(17, R.string.core_resources_cat_science_and_nature),
    COMPUTERS(18, R.string.core_resources_cat_science_computers),
    MATHEMATICS(19, R.string.core_resources_cat_science_mathematics),
    MYTHOLOGY(20, R.string.core_resources_cat_mythology),
    SPORTS(21, R.string.core_resources_cat_sports),
    GEOGRAPHY(22, R.string.core_resources_cat_geography),
    HISTORY(23, R.string.core_resources_cat_history),
    POLITICS(24, R.string.core_resources_cat_politics),
    ART(25, R.string.core_resources_cat_art),
    CELEBRITIES(26, R.string.core_resources_cat_celebrities),
    ANIMALS(27, R.string.core_resources_cat_animals),
    VEHICLES(28, R.string.core_resources_cat_vehicles),
    COMICS(29, R.string.core_resources_cat_entertainment_comics),
    GADGETS(30, R.string.core_resources_cat_science_gadgets),
    ANIME_AND_MANGA(31, R.string.core_resources_cat_entertainment_japanese_anime_and_manga),
    CARTOON_AND_ANIMATIONS(32, R.string.core_resources_cat_entertainment_cartoon_and_animations);

    companion object {
        fun getCategory(id: Int): Category = entries.find { it.id == id } ?: ANY

        /**
         * Matches an OpenTDB API response's `category` display string (e.g. "Science: Computers")
         * back to its [Category], since the API only echoes the name, never the numeric id it
         * accepted as a request filter.
         */
        fun getCategoryByOpendbName(name: String): Category = OPENDB_NAMES[name] ?: ANY

        private val OPENDB_NAMES = mapOf(
            "General Knowledge" to GENERAL_KNOWLEDGE,
            "Entertainment: Books" to BOOKS,
            "Entertainment: Film" to FILMS,
            "Entertainment: Music" to MUSIC,
            "Entertainment: Musicals & Theatres" to MUSICALS_AND_THEATRES,
            "Entertainment: Television" to TELEVISION,
            "Entertainment: Video Games" to VIDEO_GAMES,
            "Entertainment: Board Games" to BOARD_GAMES,
            "Science & Nature" to SCIENCE_AND_NATURE,
            "Science: Computers" to COMPUTERS,
            "Science: Mathematics" to MATHEMATICS,
            "Mythology" to MYTHOLOGY,
            "Sports" to SPORTS,
            "Geography" to GEOGRAPHY,
            "History" to HISTORY,
            "Politics" to POLITICS,
            "Art" to ART,
            "Celebrities" to CELEBRITIES,
            "Animals" to ANIMALS,
            "Vehicles" to VEHICLES,
            "Entertainment: Comics" to COMICS,
            "Science: Gadgets" to GADGETS,
            "Entertainment: Japanese Anime & Manga" to ANIME_AND_MANGA,
            "Entertainment: Cartoon & Animations" to CARTOON_AND_ANIMATIONS,
        )
    }
}
