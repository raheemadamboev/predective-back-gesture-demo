package xyz.teamgravity.predictivebackgesturedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import xyz.teamgravity.predictivebackgesturedemo.ui.theme.PredictiveBackGestureDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PredictiveBackGestureDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                }
            }
        }
    }
}