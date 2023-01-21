package ca.enzeroment.clone.calorytracker.core.util

// Define all kinds of events that we would like to send from viewModel to our composables
// 1. Navigating to the route
// 2. Popping the backstack
sealed class UiEvent{
    data class Navigate(val route : String) : UiEvent()
    object NavigateUp : UiEvent()
}
