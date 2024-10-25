package es.sebas1705.youknow.data.firebase.analytics.config
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

/**
 * Sealed class to represent the event logs
 *
 * @param tag [String]: Tag of the event log
 *
 * @property Error [EventLog]: Error event log
 * @property FirstTime [EventLog]: First time event log
 * @property SignUp [EventLog]: Sign up event log
 * @property SignIn [EventLog]: Sign in event log
 * @property SignOut [EventLog]: Sign out event log
 * @property SignInGoogle [EventLog]: Sign in with google event log
 * @property ChargeTime [EventLog]: Charge time event log
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed class EventLog(val tag: String){
    object Error: EventLog("error")
    object FirstTime: EventLog("first_time")
    object SignUp: EventLog("sign_up")
    object SignIn: EventLog("sign_in")
    object SignOut: EventLog("sign_out")
    object SignInGoogle: EventLog("sign_in_google")
    object ChargeTime: EventLog("charge_time")
}