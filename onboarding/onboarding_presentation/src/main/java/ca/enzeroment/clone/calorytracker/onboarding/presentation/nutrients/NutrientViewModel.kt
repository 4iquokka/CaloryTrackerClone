package ca.enzeroment.clone.calorytracker.onboarding.presentation.nutrients

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.enzeroment.clone.calorytracker.core.domain.preferences.Preferences
import ca.enzeroment.clone.calorytracker.core.domain.usecase.FilterOutDigits
import ca.enzeroment.clone.calorytracker.core.navigation.Route
import ca.enzeroment.clone.calorytracker.core.util.UiEvent
import ca.enzeroment.clone.calorytracker.onboarding.domain.usecases.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientViewModel
@Inject
constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
) : ViewModel() {

    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                if (event.ratio.length <= 3) {
                    state = state.copy(
                        carbRatio = filterOutDigits(event.ratio)
                    )
                }
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                if (event.ratio.length <= 3) {
                    state = state.copy(
                        proteinRatio = filterOutDigits(event.ratio)
                    )
                }
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                if (event.ratio.length <= 3) {
                    state = state.copy(
                        fatRatio = filterOutDigits(event.ratio)
                    )
                }
            }
            is NutrientGoalEvent.OnNextClick -> {
                val result = validateNutrients(
                    carbRatioInText = state.carbRatio,
                    proteinRatioInText = state.proteinRatio,
                    fatRatioInText = state.fatRatio
                )

                when (result) {
                    is ValidateNutrients.Result.Success -> {
                        preferences.saveCarbRatio(result.carbRatio)
                        preferences.saveProteinRatio(result.proteinRatio)
                        preferences.saveFatRatio(result.fatRatio)
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))
                        }
                    }
                    is ValidateNutrients.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackbar(result.message))
                        }
                    }
                }
            }
        }
    }
}