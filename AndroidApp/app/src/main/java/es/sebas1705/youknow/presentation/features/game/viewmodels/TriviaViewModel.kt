package es.sebas1705.youknow.presentation.features.game.viewmodels
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.data.apis.opendb.dtos.ResponseOpendbDto
import es.sebas1705.youknow.domain.usecases.TriviaUsesCases
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Trivia Screen that will fetch the questions from the API.
 *
 * @param triviaUsesCases [TriviaUsesCases]: UseCase to get the questions from the API.
 *
 * @see ViewModel
 * @see HiltViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
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
                var response : ResponseOpendbDto
                do {
                    response = triviaUsesCases.getTriviaTenQuestions()
                    if(response.responseCode!= ResponseCodes.RATE_LIMIT.ordinal) break
                    delay(5000)
                    tries+=1
                }while(tries<5)
                if(tries!=5){
                    if(response.responseCode== ResponseCodes.SUCCESS.ordinal)
                        _responseState.value = ResponseState.Success(response)
                    else _responseState.value =
                        ResponseState.Error("Error api: " + ResponseCodes.entries[response.responseCode])
                }
                else _responseState.value =
                    ResponseState.Error("Too much tries, reset the application or try again more later")
            }catch (e: Exception){
                _responseState.value = ResponseState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}

sealed class ResponseState{
    data object Loading : ResponseState()
    data class Success(val responseOpendbDto: ResponseOpendbDto) : ResponseState()
    data class Error(val message: String) : ResponseState()
}

enum class ResponseCodes{
    SUCCESS,NO_RESULTS,INVALID_PARAMETER,TOKEN_NOT_FOUND,TOKEN_EMPTY,RATE_LIMIT
}
