package es.sebas1705.youknowapp.data.source.remote.analyticsManager

enum class EventLog(val tag: String){
    FIRST_TIME("first_time"),
    SIGN_UP("sign_up"),
    SIGN_IN("sign_in"),
    SIGN_OUT("sign_out"),
    SIGN_IN_GOOGLE("sign_in_google"),
    SIGN_IN_FACEBOOK("sign_in_facebook"),
    CHARGE_TIME("charge_time"),
}

enum class UserProperty(val tag: String) {
    FAVORITE_CATEGORY("favorite_category"),
    FAVORITE_DIFFICULTY("favorite_difficulty"),
    FAVORITE_TYPE("favorite_type"),
    PLAYER_LEVEL("player_level"),
}