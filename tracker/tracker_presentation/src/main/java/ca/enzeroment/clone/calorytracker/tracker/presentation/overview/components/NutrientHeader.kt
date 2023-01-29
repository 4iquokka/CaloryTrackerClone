package ca.enzeroment.clone.calorytracker.tracker.presentation.overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.enzeroment.clone.calorytracker.core.R
import ca.enzeroment.clone.calorytracker.core.ui.CarbColor
import ca.enzeroment.clone.calorytracker.core.ui.FatColor
import ca.enzeroment.clone.calorytracker.core.ui.LocalSpacing
import ca.enzeroment.clone.calorytracker.core.ui.ProteinColor
import ca.enzeroment.clone.calorytracker.tracker.presentation.components.UnitDisplay
import ca.enzeroment.clone.calorytracker.tracker.presentation.overview.TrackerOverviewState

@Composable
fun NutrientHeader(
    state: TrackerOverviewState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val animatedCalorieCount = animateIntAsState(
        targetValue = state.totalCalories
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )
            )
            .background(MaterialTheme.colors.primary)
            .padding(
                horizontal = spacing.spaceLarge,
                vertical = spacing.spaceExtraLarge
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            UnitDisplay(
                amount = animatedCalorieCount.value,
                unit = stringResource(id = R.string.kcal),
                amountColor = MaterialTheme.colors.onPrimary,
                amountTextSize = 40.sp,
                unitColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier.align(Alignment.Bottom)
            )
            Column {
                Text(
                    text = stringResource(id = R.string.your_goal),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary
                )
                UnitDisplay(
                    amount = animatedCalorieCount.value,
                    unit = stringResource(id = R.string.kcal),
                    amountColor = MaterialTheme.colors.onPrimary,
                    amountTextSize = 40.sp,
                    unitColor = MaterialTheme.colors.onPrimary,
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            NutrientBar(
                carbs = state.totalCarbs,
                protein = state.totalProtein,
                fat = state.totalFat,
                calories = state.totalCalories,
                calorieGoal = state.caloriesGoal,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NutrientCircularBarInfo(
                    value = state.totalCalories,
                    goal = state.carbsGoal,
                    name = stringResource(id = R.string.carbs),
                    color = CarbColor,
                    modifier = Modifier.size(90.dp)
                )
                NutrientCircularBarInfo(
                    value = state.totalProtein,
                    goal = state.proteinGoal,
                    name = stringResource(id = R.string.protein),
                    color = ProteinColor,
                    modifier = Modifier.size(90.dp)
                )
                NutrientCircularBarInfo(
                    value = state.totalFat,
                    goal = state.fatGoal,
                    name = stringResource(id = R.string.fat),
                    color = FatColor,
                    modifier = Modifier.size(90.dp)
                )
            }
        }

    }
}