package ca.enzeroment.clone.calorytracker.tracker.data.mapper

import ca.enzeroment.clone.calorytracker.tracker.data.local.entity.TrackedFoodEntity
import ca.enzeroment.clone.calorytracker.tracker.domain.model.MealType
import ca.enzeroment.clone.calorytracker.tracker.domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood() : TrackedFood{
    return TrackedFood(
        id = id,
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        mealType = MealType.fromString(type),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonth),
        calories = calories
    )
}

fun TrackedFood.toTrackedFoodEntity() : TrackedFoodEntity{
    return TrackedFoodEntity(
        id = id,
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories
    )
}