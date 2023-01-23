package ca.enzeroment.clone.calorytracker.onboarding.presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.enzeroment.clone.calorytracker.R
import ca.enzeroment.clone.calorytracker.core.domain.preferences.Preferences
import ca.enzeroment.clone.calorytracker.core.domain.usecase.FilterOutDigits
import ca.enzeroment.clone.calorytracker.core.navigation.Route
import ca.enzeroment.clone.calorytracker.core.util.UiEvent
import ca.enzeroment.clone.calorytracker.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel
@Inject
constructor(
    private val preferences: Preferences
) : ViewModel() {

    var weight by mutableStateOf("80.0")
    private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightEnter(weight : String){
        // TODO: You can improve the below logic through using the regex
        if(weight.length <= 5){
            this.weight = weight
        }
    }

    fun onNextClick(){
        viewModelScope.launch {
            val weightInNum = weight.toFloatOrNull() ?: kotlin.run{
                _uiEvent.send(UiEvent.ShowSnackbar(
                    UiText.StringResource(R.string.error_weight_cant_be_empty)
                ))
                return@launch
            }
            preferences.saveWeight(weightInNum)
            _uiEvent.send(UiEvent.Navigate(Route.ACTIVITY))
        }
    }
}