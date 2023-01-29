package ca.enzeroment.clone.calorytracker.tracker.presentation.overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ca.enzeroment.clone.calorytracker.core.R
import ca.enzeroment.clone.calorytracker.core.ui.LocalSpacing
import ca.enzeroment.clone.calorytracker.core.util.UiEvent
import ca.enzeroment.clone.calorytracker.tracker.presentation.overview.components.*

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val state = viewModel.state

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientHeader(state = state)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DaySelector(
                date = state.date,
                onPrevDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                },
                onNextDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        items(state.meals) { meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceSmall)
                ) {
                    state.trackedFoods.forEach { food ->
                        TrackedFoodItem(
                            trackedFood = food,
                            onDeleteClick = {
                                viewModel.onEvent(
                                    TrackerOverviewEvent.OnDeleteTrackedFoodClick(food)
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    }
                    AddButton(
                        text = stringResource(
                            id = R.string.add_meal,
                            meal.name.asString(context)
                        ),
                        onClick = { viewModel.onEvent(TrackerOverviewEvent.OnAddFoodClick(meal)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}