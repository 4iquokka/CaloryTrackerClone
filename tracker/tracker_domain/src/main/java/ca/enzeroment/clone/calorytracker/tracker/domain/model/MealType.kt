package ca.enzeroment.clone.calorytracker.tracker.domain.model

sealed class MealType(val name : String){
    object BreakFast : MealType("breakfast")
    object Lunch : MealType("lunch")
    object Dinner : MealType("dinner")
    object Snack : MealType("snack")

    companion object {
        fun fromString(type: String): MealType {
            return when (type) {
                "breakfast" -> BreakFast
                "lunch" -> Lunch
                "dinner" -> Dinner
                "snack" -> Snack
                else -> BreakFast
            }
        }
    }
}
