package ca.enzeroment.clone.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.enzeroment.clone.calorytracker.core.navigation.Route
import ca.enzeroment.clone.calorytracker.navigation.navigate
import ca.enzeroment.clone.calorytracker.onboarding.presentation.welcome.WelcomeScreen
import ca.enzeroment.clone.calorytracker.ui.theme.CaloryTrackerCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerCloneTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.WELCOME
                ) {
                    composable(Route.WELCOME){
                        WelcomeScreen(onNavigate = navController::navigate)
                    }

                }
            }
        }
    }
}