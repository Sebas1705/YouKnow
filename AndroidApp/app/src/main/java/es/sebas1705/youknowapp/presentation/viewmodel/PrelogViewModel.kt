package es.sebas1705.youknowapp.presentation.viewmodel

import android.content.Context
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.domain.model.Page
import es.sebas1705.youknowapp.domain.usecases.AppEntryUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrelogViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    fun saveAppEntry(){
        viewModelScope.launch{
            appEntryUseCases.saveAppEntry()
        }
    }

}