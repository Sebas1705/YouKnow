package es.sebas1705.auth.usescases
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

import es.sebas1705.authentication.repository.AuthenticationRepository
import es.sebas1705.common.utlis.extensions.types.catcher

/**
 * Use case to send forgot password
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to send forgot password
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class SendForgotPassword(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        email: String,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.sendForgotPassword(email)
        .catcher(onLoading, onEmptySuccess, onError)
}