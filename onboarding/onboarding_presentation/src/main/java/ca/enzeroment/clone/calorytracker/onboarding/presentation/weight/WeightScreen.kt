package ca.enzeroment.clone.calorytracker.onboarding.presentation.weight

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ca.enzeroment.clone.calorytracker.R
import ca.enzeroment.clone.calorytracker.core.ui.LocalSpacing
import ca.enzeroment.clone.calorytracker.core.util.UiEvent
import ca.enzeroment.clone.calorytracker.onboarding.presentation.component.ActionButton
import ca.enzeroment.clone.calorytracker.onboarding.presentation.component.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun WeightScreen(
    scaffoldState: ScaffoldState,
    onNavigate : (UiEvent.Navigate) -> Unit,
    viewModel : WeightViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.Navigate ->{
                    onNavigate(event)
                }
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_weight),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.weight,
                onValueChange = viewModel::onWeightEnter,
                unit = stringResource(id = R.string.years)
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next), onClick = { viewModel.onNextClick() },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }


}