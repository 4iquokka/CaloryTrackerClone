package ca.enzeroment.clone.calorytracker.core.domain.usecase

class FilterOutDigits {
    operator fun invoke(text : String): String {
        return text.filter { it.isDigit() }
    }
}