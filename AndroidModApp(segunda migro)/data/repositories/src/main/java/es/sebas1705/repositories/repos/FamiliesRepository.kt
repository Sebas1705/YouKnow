package es.sebas1705.repositories.repos

import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.common.utlis.extensions.types.dataFlow
import es.sebas1705.files.json.FamiliesJson
import es.sebas1705.repositories.interfaces.IFamiliesRepository
import es.sebas1705.room.entities.FamiliesEntity
import javax.inject.Inject

class FamiliesRepository @Inject constructor() : IFamiliesRepository {

    override fun chargeDefaultFamiliesFromLocal(): DataFlow<FamiliesJson> = dataFlow {
        TODO("Not yet implemented")
    }

    override fun saveFamiliesToLocal(families: FamiliesEntity): DataFlow<Boolean> = dataFlow {
        TODO("Not yet implemented")
    }
}
