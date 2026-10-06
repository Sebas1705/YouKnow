package es.sebas1705.user.usescases

import android.net.Uri
import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.storage.repository.StorageRepository

/**
 * Use case to upload a picked image as the user's profile photo, then save its download URL on
 * their Firestore document (see [ChangePhotoToUser]).
 *
 * @property storageRepository [StorageRepository]: repository to upload the photo
 * @property changePhotoToUser [ChangePhotoToUser]: use case to save the resulting URL
 *
 * @since 1.3.2
 * @author Sebas1705 30/09/2026
 */
class UploadProfilePhoto(
    private val storageRepository: StorageRepository,
    private val changePhotoToUser: ChangePhotoToUser
) {
    suspend operator fun invoke(
        firebaseId: String,
        uri: Uri,
        onLoading: () -> Unit = {},
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = storageRepository.uploadProfilePhoto(firebaseId, uri).collect(
        onLoading = onLoading,
        onSuccess = { photoUrl ->
            changePhotoToUser(
                firebaseId,
                photoUrl,
                onEmptySuccess = { onSuccess(photoUrl) },
                onError = onError
            )
        },
        onError = onError
    )
}
