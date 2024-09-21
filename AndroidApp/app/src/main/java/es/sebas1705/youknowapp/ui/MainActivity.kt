package es.sebas1705.youknowapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import es.sebas1705.youknowapp.presentation.screens.splash.SplashScreen



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.apply {
            setOnApplyWindowInsetsListener { _, insets ->
                hideBarsAfterDelay()
                insets
            }
            windowInsetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            windowInsetsController?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        setContent {
            SplashScreen()
        }
    }

    private val hideHandler = Handler(Looper.getMainLooper())

    private fun hideSystemBars(){
        window.decorView.windowInsetsController?.apply{
            hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun hideBarsAfterDelay() {
        hideHandler.postDelayed(
            {hideSystemBars()},
            2000
        )
    }
}
