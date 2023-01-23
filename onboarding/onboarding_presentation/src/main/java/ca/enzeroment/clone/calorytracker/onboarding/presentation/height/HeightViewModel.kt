package ca.enzeroment.clone.calorytracker.onboarding.presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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
class HeightViewModel
@Inject
constructor(
    private val preferences : Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    var height by mutableStateOf("180")
    private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onHeightEnter(height : String){
        if(height.length <= 3){
            this.height = filterOutDigits(height)
        }
    }

    fun onNextClick(){
        viewModelScope.launch {
            val heightInNum = height.toIntOrNull() ?: kotlin.run{
                _uiEvent.send(UiEvent.ShowSnackbar(
                    UiText.StringResource(R.string.error_height_cant_be_empty)
                ))
                return@launch
            }
            preferences.saveHeight(heightInNum)
            _uiEvent.send(UiEvent.Navigate(Route.WEIGHT))
        }
    }
}