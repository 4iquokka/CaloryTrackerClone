package ca.enzeroment.clone.calorytracker.onboarding.presentation.gender

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import ca.enzeroment.clone.calorytracker.core.R
import ca.enzeroment.clone.calorytracker.core.domain.model.Gender
import ca.enzeroment.clone.calorytracker.core.ui.LocalSpacing
import ca.enzeroment.clone.calorytracker.core.util.UiEvent
import ca.enzeroment.clone.calorytracker.onboarding.presentation.component.ActionButton
import ca.enzeroment.clone.calorytracker.onboarding.presentation.component.SelectableButton

@Composable
fun GenderScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel : GenderViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(spacing.spaceLarge)){
        Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
               text = stringResource(id = R.string.whats_your_gender),
               style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row{
                SelectableButton(
                    text = stringResource(id = R.string.male),
                    isSelected = viewModel.selectedGender is Gender.Male,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onGenderClick(Gender.Male)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.female),
                    isSelected = viewModel.selectedGender is Gender.Female,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onGenderClick(Gender.Female)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        ActionButton(text = stringResource(id = R.string.next), onClick = {viewModel.onNextClick()},
        modifier = Modifier.align(Alignment.BottomEnd))
    }
}