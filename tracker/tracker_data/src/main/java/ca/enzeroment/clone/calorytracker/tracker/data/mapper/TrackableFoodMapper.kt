package ca.enzeroment.clone.calorytracker.tracker.data.mapper

import ca.enzeroment.clone.calorytracker.tracker.data.remote.dto.Product
import ca.enzeroment.clone.calorytracker.tracker.domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood() : TrackableFood? {
    val carbsPer100g = nutrients.carbohydrates100g.roundToInt()
    val proteinPer100g = nutrients.protein100g.roundToInt()
    val fatPer100g = nutrients.fat100g.roundToInt()
    val caloriesPer100g = nutrients.energyKcal100g.roundToInt()

    return TrackableFood(
        name = productName ?: return null,
        carbsPer100g = carbsPer100g,
        proteinPer100g = proteinPer100g,
        fatPer100g = fatPer100g,
        caloriesPer100g = caloriesPer100g,
        imageUrl = imageFrontThumbUrl
    )
}