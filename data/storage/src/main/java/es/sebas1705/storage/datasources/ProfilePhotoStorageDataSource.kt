package es.sebas1705.storage.datasources

import android.net.Uri
import com.google.android.gms.tasks.Tasks
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.common.utlis.extensions.types.logE
import es.sebas1705.storage.config.SettingsStorage
import javax.inject.Inject

/**
 * Data source for uploading a user's profile photo to Cloud Storage.
 *
 * @property storage [FirebaseStorage]: Storage instance
 * @property logEventDataSource [LogEventDataSource]: Data source for logging events
 *
 * @since 1.3.2
 * @author Sebas1705 30/09/2026
 */
class ProfilePhotoStorageDataSource @Inject constructor(
    private val storage: FirebaseStorage,
    private val logEventDataSource: LogEventDataSource
) {

    //Managers:
    private val taskFlow = TaskFlow(
        this.javaClass.kotlin,
        SettingsStorage.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsStorage.ERROR_GENERIC_MESSAGE_EX
    ) { clazz, error ->
        logE(error)
        logEventDataSource.logError(clazz, error)
    }

    //References:
    private val profilePhotosReference = storage.reference.child(SettingsStorage.PROFILE_PHOTOS_PATH)

    //Tasks:
    /**
     * Upload [uri]'s content as the given user's profile photo (one file per uid, overwriting
     * whatever was there before) and return its public download URL.
     *
     * @param firebaseId [String]: Id of the user the photo belongs to.
     * @param uri [Uri]: Local content/file URI of the picked image.
     *
     * @return [DataFlow]<[String]>: Flow with the uploaded photo's download URL.
     *
     * @since 1.3.2
     * @author Sebas1705 30/09/2026
     */
    fun uploadProfilePhoto(
        firebaseId: String,
        uri: Uri
    ): DataFlow<String> = taskFlow.taskFlowProducer(
        taskAction = {
            val photoReference = profilePhotosReference.child(firebaseId)
            photoReference.putFile(uri).continueWithTask { task ->
                if (!task.isSuccessful) throw task.exception ?: IllegalStateException(SettingsStorage.ERROR_GENERIC_MESSAGE_FAIL)
                photoReference.downloadUrl
            }
        },
        onSuccessListener = { downloadUri -> DataState.Success(downloadUri.toString()) }
    )

    /**
     * Delete the given user's profile photo. A user who never uploaded one (no file at that path)
     * is a success: there is nothing left to delete.
     *
     * @param firebaseId [String]: Id of the user the photo belongs to.
     *
     * @return [DataEmptyFlow]: Flow with the response of the operation.
     *
     * @since 1.6.0
     * @author Sebas1705 11/11/2026
     */
    fun deleteProfilePhoto(
        firebaseId: String
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            profilePhotosReference.child(firebaseId).delete().continueWithTask<Void> { task ->
                val failure = task.exception
                if (failure is StorageException && failure.errorCode == StorageException.ERROR_OBJECT_NOT_FOUND) {
                    Tasks.forResult<Void>(null)
                } else task
            }
        },
        onSuccessListener = { DataState.EmptySuccess }
    )
}
