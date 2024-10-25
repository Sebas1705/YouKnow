package es.sebas1705.youknow.data.apis.opendb.config

import java.net.URLDecoder
import java.nio.charset.StandardCharsets

/**
 * Decode a string from URL format using [URLDecoder.decode]
 * and standard charset [StandardCharsets.UTF_8]
 */
fun String.decodeUrl(): String {
    return URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}