package es.sebas1705.storage.repository

import android.net.Uri
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.storage.datasources.ProfilePhotoStorageDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageRepository @Inject constructor(
    private val profilePhotoDataSource: ProfilePhotoStorageDataSource
) {
    // Profile photo operations
    fun uploadProfilePhoto(firebaseId: String, uri: Uri): DataFlow<String> =
        profilePhotoDataSource.uploadProfilePhoto(firebaseId, uri)
}
