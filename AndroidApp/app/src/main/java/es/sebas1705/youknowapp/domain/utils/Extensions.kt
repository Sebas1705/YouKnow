package es.sebas1705.youknowapp.domain.utils

import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun String.decodeUrl(): String {
    return URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}