package es.sebas1705.youknowapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknowapp.domain.usecases.AppEntryUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel(){

    fun onEvent(event: OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry ->{
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry(){
        viewModelScope.launch{
            appEntryUseCases.saveAppEntry()
        }
    }

}

sealed class OnBoardingEvent {

    data object SaveAppEntry : OnBoardingEvent()

}