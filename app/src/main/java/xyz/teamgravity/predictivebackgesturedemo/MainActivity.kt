package xyz.teamgravity.predictivebackgesturedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.teamgravity.predictivebackgesturedemo.ui.Route
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
                    val controller = rememberNavController()

                    NavHost(
                        navController = controller,
                        startDestination = Route.ScreenA,
                        modifier = Modifier.padding(padding)
                    ) {
                        composable<Route.ScreenA> {
                            ScreenA(controller)
                        }
                        composable<Route.ScreenB> {
                            ScreenB()
                        }
                    }
                }
            }
        }
    }
}