package es.sebas1705.youknow.domain.usecases.games

import es.sebas1705.youknow.core.classes.enums.Category
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Languages
import es.sebas1705.youknow.domain.model.games.FamiliesModel

class GenerateFamilies(

) {
    operator fun invoke(
        numFamilies: Int,
        category: Category,
        difficulty: Difficulty,
        onLoading: () -> Unit,
        onSuccess: (List<FamiliesModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        onSuccess(FamiliesModel.defaultList(numFamilies))
    }
}

data class FamiliesUsesCases(
    val generateFamilies: GenerateFamilies
)