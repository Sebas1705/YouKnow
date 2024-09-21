package es.sebas1705.youknowapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknowapp.domain.usecases.LocalUsesCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val localUsesCases: LocalUsesCases
) : ViewModel() {

    fun saveFirstTime(){
        viewModelScope.launch{
            localUsesCases.saveFirstTime()
        }
    }
}