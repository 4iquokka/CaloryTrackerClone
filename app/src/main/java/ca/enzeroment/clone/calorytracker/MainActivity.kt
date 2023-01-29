package ca.enzeroment.clone.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.enzeroment.clone.calorytracker.core.navigation.Route
import ca.enzeroment.clone.calorytracker.navigation.navigate
import ca.enzeroment.clone.calorytracker.onboarding.presentation.activity.ActivityScreen
import ca.enzeroment.clone.calorytracker.onboarding.presentation.age.AgeScreen
import ca.enzeroment.clone.calorytracker.onboarding.presentation.gender.GenderScreen
import ca.enzeroment.clone.calorytracker.onboarding.presentation.goal.GoalScreen
import ca.enzeroment.clone.calorytracker.onboarding.presentation.height.HeightScreen
import ca.enzeroment.clone.calorytracker.onboarding.presentation.nutrients.NutrientGoalScreen
import ca.enzeroment.clone.calorytracker.onboarding.presentation.weight.WeightScreen
import ca.enzeroment.clone.calorytracker.onboarding.presentation.welcome.WelcomeScreen
import ca.enzeroment.clone.calorytracker.tracker.presentation.overview.TrackerOverviewScreen
import ca.enzeroment.clone.calorytracker.ui.theme.CaloryTrackerCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerCloneTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate,
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.GOAL) {
                            GoalScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW){
                            TrackerOverviewScreen(
                                onNavigate = navController::navigate
                            )
                        }
                    }
                }
            }
        }
    }
}