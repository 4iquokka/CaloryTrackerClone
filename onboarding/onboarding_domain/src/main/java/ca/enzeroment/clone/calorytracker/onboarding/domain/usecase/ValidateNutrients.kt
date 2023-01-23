package ca.enzeroment.clone.calorytracker.onboarding.domain.usecase


import ca.enzeroment.clone.calorytracker.core.util.UiText

class ValidateNutrients {
    operator fun invoke(
        carbRatioInText: String,
        proteinRatioInText: String,
        fatRatioInText: String,
    ): Result {
        val carbRatioInNum = carbRatioInText.toIntOrNull()
        val proteinRatioInNum = proteinRatioInText.toIntOrNull()
        val fatRatioInNum = fatRatioInText.toIntOrNull()

        if (carbRatioInNum == null || proteinRatioInNum == null || fatRatioInNum == null) {
            return Result.Error(
                UiText.StringResource(ca.enzeroment.clone.calorytracker.core.R.string.error_invalid_values)
            )
        }

        if (carbRatioInNum + proteinRatioInNum + fatRatioInNum != 100) {
            return Result.Error(
                UiText.StringResource(ca.enzeroment.clone.calorytracker.core.R.string.error_not_100_percent)
            )
        }

        return Result.Success(
            carbRatioInNum / 100f,
            proteinRatioInNum / 100f,
            fatRatioInNum / 100f
        )
    }

    sealed class Result {
        data class Success(
            val carbRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float
        ) : Result()

        data class Error(
            val message: UiText
        ) : Result()
    }
}