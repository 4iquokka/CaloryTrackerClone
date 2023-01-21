package ca.enzeroment.clone.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.enzeroment.clone.calorytracker.core.navigation.Route
import ca.enzeroment.clone.calorytracker.navigation.navigate
import ca.enzeroment.clone.calorytracker.onboarding.presentation.gender.GenderScreen
import ca.enzeroment.clone.calorytracker.onboarding.presentation.welcome.WelcomeScreen
import ca.enzeroment.clone.calorytracker.ui.theme.CaloryTrackerCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                    composable(Route.AGE){
                        WelcomeScreen(onNavigate = navController::navigate)
                    }
                    composable(Route.GENDER){
                        GenderScreen(onNavigate = navController::navigate)
                    }

                }
            }
        }
    }
}