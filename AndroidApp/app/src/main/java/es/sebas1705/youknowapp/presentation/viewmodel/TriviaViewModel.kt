package es.sebas1705.youknowapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknowapp.domain.model.TriviaResponse
import es.sebas1705.youknowapp.domain.usecases.TriviaUsesCases
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TriviaViewModel @Inject constructor(
    private val triviaUsesCases: TriviaUsesCases
) : ViewModel() {

    private val _responseState = MutableStateFlow<ResponseState>(ResponseState.Loading)
    val responseState : StateFlow<ResponseState> get() = _responseState

    init{
        fetchResponse()
    }

    private fun fetchResponse(){
        viewModelScope.launch {
            try{
                _responseState.value = ResponseState.Loading
                var tries = 0
                var response : TriviaResponse
                do {
                    response = triviaUsesCases.getTriviaTenQuestions()
                    if(response.responseCode!=ResponseCodes.RATE_LIMIT.ordinal) break
                    delay(5000)
                    tries+=1
                }while(tries<5)
                if(tries!=5){
                    if(response.responseCode==ResponseCodes.SUCCESS.ordinal)
                        _responseState.value = ResponseState.Success(response)
                    else _responseState.value = ResponseState.Error("Error api: "+ResponseCodes.entries[response.responseCode])
                }
                else _responseState.value = ResponseState
                    .Error("Too much tries, reset the application or try again more later")
            }catch (e: Exception){
                _responseState.value = ResponseState
                    .Error(e.message ?: "Unknown Error")
            }
        }
    }
}

sealed class ResponseState{
    data object Loading : ResponseState()
    data class Success(val triviaResponse: TriviaResponse) : ResponseState()
    data class Error(val message: String) : ResponseState()
}

enum class ResponseCodes{
    SUCCESS,NO_RESULTS,INVALID_PARAMETER,TOKEN_NOT_FOUND,TOKEN_EMPTY,RATE_LIMIT
}
