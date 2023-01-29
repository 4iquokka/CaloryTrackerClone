package ca.enzeroment.clone.calorytracker.tracker.presentation.overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import ca.enzeroment.clone.calorytracker.core.ui.CarbColor
import ca.enzeroment.clone.calorytracker.core.ui.FatColor
import ca.enzeroment.clone.calorytracker.core.ui.ProteinColor

@Composable
fun NutrientBar(
    carbs: Int,
    protein: Int,
    fat: Int,
    calories : Int,
    calorieGoal: Int,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colors.background
    val caloriesExceedColor = MaterialTheme.colors.error

    val carbWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidtRatio = remember {
        Animatable(0f)
    }
    val fatWidtRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbs){
        carbWidthRatio.animateTo(
            targetValue = ((carbs * 4f) / calorieGoal)
        )
    }
    LaunchedEffect(key1 = protein){
        proteinWidtRatio.animateTo(
            targetValue = ((protein * 4f) / calorieGoal)
        )
    }
    LaunchedEffect(key1 = fat){
        fatWidtRatio.animateTo(
            targetValue = ((fat * 4f) / calorieGoal)
        )
    }

    Canvas(modifier = modifier){
        if(calories <= calorieGoal){
            val carbsWidth = carbWidthRatio.value * size.width
            val proteinWidth = proteinWidtRatio.value * size.width
            val fatWidth = fatWidtRatio.value * size.width

            //Background
            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
            //Fat
            drawRoundRect(
                color = FatColor,
                size = Size(
                    width = carbsWidth + proteinWidth + fatWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            //Protein
            drawRoundRect(
                color = ProteinColor,
                size = Size(
                    width = carbsWidth + proteinWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            //Carbs
            drawRoundRect(
                color = CarbColor,
                size = Size(
                    width = carbsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )

        } else {
            drawRoundRect(
                color = caloriesExceedColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }
}