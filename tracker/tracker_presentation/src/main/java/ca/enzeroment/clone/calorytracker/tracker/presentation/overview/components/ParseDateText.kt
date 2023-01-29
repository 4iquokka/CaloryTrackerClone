package ca.enzeroment.clone.calorytracker.tracker.presentation.overview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ca.enzeroment.clone.calorytracker.core.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ParseDateText(date : LocalDate) : String{
    val today = LocalDate.now()
    return when(date) {
        today -> stringResource(id = R.string.today)
        today.minusDays(1) -> stringResource(id = R.string.yesterday)
        today.plusDays(1) -> stringResource(id = R.string.tomorrow)
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }
}