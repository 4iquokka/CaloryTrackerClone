package ca.enzeroment.clone.calorytracker.navigation

import androidx.navigation.NavController
import ca.enzeroment.clone.calorytracker.core.util.UiEvent

fun NavController.navigate(event : UiEvent.Navigate) {
    this.navigate(event.route)
}