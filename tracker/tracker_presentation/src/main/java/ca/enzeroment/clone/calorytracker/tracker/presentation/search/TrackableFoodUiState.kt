package ca.enzeroment.clone.calorytracker.tracker.presentation.search

import ca.enzeroment.clone.calorytracker.tracker.domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded : Boolean = false,
    val amount : String = ""
)
