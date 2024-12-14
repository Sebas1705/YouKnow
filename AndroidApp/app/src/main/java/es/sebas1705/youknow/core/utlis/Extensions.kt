package es.sebas1705.youknow.core.utlis
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

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import es.sebas1705.youknow.R
import es.sebas1705.youknow.domain.model.PageModel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.text.startsWith

//Any:
/**
 * Transform a [Int] number to a [Dp] number using the context
 * to get the density of the screen in [Dp]
 *
 * @receiver [Int]: the number in px
 *
 * @param context [Context]: context of the app
 *
 * @return [Dp]: the number in [Dp]
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Int.toDp(context: Context): Dp {
    val density = context.resources.displayMetrics.density
    return Dp(this / density)
}

/**
 * Transform a [Dp] number to a [Int] number using the context
 * to get the density of the screen in [Dp]
 *
 * @receiver [Dp]: the number in [Dp]
 *
 * @param context [Context]: context of the app
 *
 * @return [Int]: the number in px
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Dp.toPx(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this.value * density).toInt()
}


//FlowResponse:
/**
 * Catcher function to handle the different states of the response
 *
 * @receiver [FlowResponse]: the response state
 *
 * @param onLoading () -> Unit: Function to handle the loading state
 * @param onSuccess (T) -> Unit: Function to handle the success state
 * @param onError (String) -> Unit: Function to handle the error state
 *
 * @see FlowResponse
 */
suspend fun <T> FlowResponse<T>.catcher(
    onLoading: () -> Unit = {},
    onSuccess: (T) -> Unit = {},
    onError: (String) -> Unit = {}
) {
    this.collect {
        it.catcher(
            onLoading = onLoading,
            onSuccess = onSuccess,
            onError = { onError(it.message) }
        )
    }
}

/**
 * Catcher function to handle the different states of the response
 *
 * @receiver [FlowResponseNothing]: the response state
 *
 * @param onLoading () -> Unit: Function to handle the loading state
 * @param onEmptySuccess () -> Unit: Function to handle the empty success state
 * @param onError (String) -> Unit: Function to handle the error state
 *
 * @see FlowResponseNothing
 */
suspend fun FlowResponseNothing.catcher(
    onLoading: suspend () -> Unit = {},
    onEmptySuccess: suspend () -> Unit = {},
    onError: suspend (String) -> Unit = {}
) {
    this.collect {
        it.catcher(
            onLoading = onLoading,
            onEmptySuccess = onEmptySuccess,
            onError = { onError(it.message) }
        )
    }
}


//Float:
/**
 * Format a float number to a string with two decimal
 *
 * @receiver [Float]: the number to format
 *
 * @return [String]: the number formatted
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@SuppressLint("DefaultLocale")
fun Float.twoDecimalFormat(): String {
    return String.format("%.2f", this)
}

/**
 * Format a float number to a string with two decimal and a percentage symbol
 *
 * @receiver [Float]: the number to format
 *
 * @return [String]: the number formatted
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@SuppressLint("DefaultLocale")
fun Float.percentageFormat(): String {
    return String.format("%.2f", this * 100) + "%"
}

/**
 * Reverse a float number
 *
 * @receiver [Float]: the number to reverse
 *
 * @return [Float]: the number reversed
 *
 * @see reverseOne
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Float.reverseOne(): Float {
    return 1 - this
}

//Context:
/**
 * Generate a list of [PageModel] to use in the guide
 *
 * @receiver [Context]: context of the app
 *
 * @return [List]<[PageModel]>: list of pages
 *
 * @see PageModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Context.generateGuidePages() = listOf(
    PageModel(
        this.getString(R.string.titlePage1),
        this.getString(R.string.desPage1),
        R.drawable.back_boarding
    ),
    PageModel(
        this.getString(R.string.titlePage2),
        this.getString(R.string.desPage2),
        R.drawable.back_boarding
    ),
    PageModel(
        this.getString(R.string.titlePage3),
        this.getString(R.string.desPage3),
        R.drawable.back_boarding
    )
)

/**
 * Print a text generating a [Toast]
 *
 * @receiver [Context]: context of the app
 *
 * @param message [String]: the message to print
 *
 * @see Toast
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Context.printTextInToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


//NavHostController:
/**
 * Navigate to a route and pop up to a route
 *
 * @receiver [NavHostController]: the nav controller
 *
 * @param route [Any]: the route to navigate
 * @param popUpTo [Any]: the route to pop up
 *
 * @see NavHostController
 * @see NavHostController.navigate
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@SuppressLint("RestrictedApi")
fun NavHostController.navAndPopUp(route: Any, popUpTo: Any) {
    this.navigate(route) {
        popUpTo(popUpTo) {
            inclusive = true
        }
    }
}

/**
 * Navigate to a tab and pop up to the start destination
 *
 * @receiver [NavController]: the nav controller
 *
 * @param route [Any]: the route to navigate
 *
 * @see NavController
 * @see NavController.navigate
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun NavController.navToTab(route: Any) {
    this.navigate(route) {
        this@navToTab.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

//Long:
/**
 * Format a long number to a date string
 *
 * @receiver [Long]: the number to format
 *
 * @return [String]: the date formatted
 *
 * @see DateTimeFormatter
 * @see ZoneId
 * @see Instant
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Long.millisToFormatDate(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy")
        .withZone(ZoneId.systemDefault())
    return formatter.format(Instant.ofEpochMilli(this))
}

//String:
fun String.isImageUrl(): Boolean {
    var isImage = false
    try {
        val client = OkHttpClient()
        val request = Request.Builder().url(this).build()
        val response: Response = client.newCall(request).execute()
        val contentType = response.header("Content-Type")
        Log.i("isImageUrl", "Content-Type: $contentType")
        isImage = contentType != null && contentType.startsWith("image/")
    } catch (e: Exception) {
        Log.e("isImageUrl", e.message.toString())
        isImage = false
    }
    Log.i("isImageUrl", "Is image: $isImage")
    return isImage
}

//TextStyles:
fun TextStyle.makeBold(): TextStyle = this.copy(fontWeight = FontWeight.Bold)