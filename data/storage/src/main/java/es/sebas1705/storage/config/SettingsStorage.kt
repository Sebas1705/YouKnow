package es.sebas1705.storage.config

/**
 * Settings for Cloud Storage
 *
 * @property PROFILE_PHOTOS_PATH [String]: Folder profile photos are uploaded to, one file per uid
 * @property ERROR_GENERIC_MESSAGE_EX [String]: Generic error message for exceptions
 * @property ERROR_GENERIC_MESSAGE_FAIL [String]: Generic error message for failure listeners
 *
 * @since 1.3.2
 * @author Sebas1705 30/09/2026
 */
object SettingsStorage {
    const val PROFILE_PHOTOS_PATH = "profile_photos"

    const val ERROR_GENERIC_MESSAGE_EX = "An error occurred on storage by an exception"
    const val ERROR_GENERIC_MESSAGE_FAIL = "An error occurred on storage by failure listener"
}
