package ca.enzeroment.clone.calorytracker.tracker.domain.usecases

import ca.enzeroment.clone.calorytracker.tracker.domain.model.MealType
import ca.enzeroment.clone.calorytracker.tracker.domain.model.TrackableFood
import ca.enzeroment.clone.calorytracker.tracker.domain.model.TrackedFood
import ca.enzeroment.clone.calorytracker.tracker.domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood(
    private val trackerRepository: TrackerRepository
) {
    suspend operator fun invoke(
        food: TrackableFood,
        amount : Int,
        mealType : MealType,
        date: LocalDate
    ) {
        trackerRepository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                carbs = ((food.carbsPer100g / 100f) * amount).roundToInt(),
                protein = ((food.proteinPer100g / 100f) * amount).roundToInt(),
                fat = ((food.fatPer100g / 100f) * amount).roundToInt(),
                calories = ((food.caloriesPer100g / 100f) * amount).roundToInt(),
                imageUrl = food.imageUrl,
                mealType = mealType,
                amount = amount,
                date = date
            )
        )
    }
}