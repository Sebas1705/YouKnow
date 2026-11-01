package es.sebas1705.firestore.documents

import com.google.firebase.firestore.DocumentSnapshot

/**
 * [DocumentSnapshot.toObject] that survives a document of the wrong shape. Clients write most of
 * these documents and security rules may have been looser when they did, so a wrong type must cost
 * that one document, not the whole list it belongs to or the app.
 *
 * @param type [Class]<[T]>: Class to map the document onto.
 * @param onMalformed ([String]) -> [Unit]: Told when the document can't be mapped. The message names
 * the class only: the document id can be a uid, and logs carry no personal data.
 *
 * @return [T]?: The mapped document, or null if it doesn't exist or can't be mapped.
 *
 * @since 1.5.0
 * @author Sebas1705 31/10/2026
 */
internal fun <T : Any> DocumentSnapshot.toObjectOrNull(
    type: Class<T>,
    onMalformed: (String) -> Unit
): T? = try {
    toObject(type)
} catch (_: RuntimeException) {
    onMalformed("Malformed ${type.simpleName} ignored")
    null
}
