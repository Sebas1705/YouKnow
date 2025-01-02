package es.sebas1705.youknow.core.classes.enums

import es.sebas1705.youknow.R

enum class Category(val id: Int?, val strRes: Int) {
    ANY(null, R.string.any),
    GENERAL_KNOWLEDGE(9, R.string.cat_general_knowledge),
    BOOKS(10, R.string.cat_entertainment_books),
    FILMS(11, R.string.cat_entertainment_film),
    MUSIC(12, R.string.cat_entertainment_music),
    MUSICALS_AND_THEATRES(13, R.string.cat_entertainment_musicals_and_theatres),
    TELEVISION(14, R.string.cat_entertainment_television),
    VIDEO_GAMES(15, R.string.cat_entertainment_video_games),
    BOARD_GAMES(16, R.string.cat_entertainment_board_games),
    SCIENCE_AND_NATURE(17, R.string.cat_science_and_nature),
    COMPUTERS(18, R.string.cat_science_computers),
    MATHEMATICS(19, R.string.cat_science_mathematics),
    MYTHOLOGY(20, R.string.cat_mythology),
    SPORTS(21, R.string.cat_sports),
    GEOGRAPHY(22, R.string.cat_geography),
    HISTORY(23, R.string.cat_history),
    POLITICS(24, R.string.cat_politics),
    ART(25, R.string.cat_art),
    CELEBRITIES(26, R.string.cat_celebrities),
    ANIMALS(27, R.string.cat_animals),
    VEHICLES(28, R.string.cat_vehicles),
    COMICS(29, R.string.cat_entertainment_comics),
    GADGETS(30, R.string.cat_science_gadgets),
    ANIME_AND_MANGA(31, R.string.cat_entertainment_japanese_anime_and_manga),
    CARTOON_AND_ANIMATIONS(32, R.string.cat_entertainment_cartoon_and_animations);

    companion object {
        fun getCategory(id: Int): Category = entries.find { it.id == id } ?: ANY
    }
}
