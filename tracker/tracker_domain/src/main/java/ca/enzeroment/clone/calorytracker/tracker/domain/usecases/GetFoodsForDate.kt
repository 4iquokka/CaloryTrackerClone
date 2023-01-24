package ca.enzeroment.clone.calorytracker.tracker.domain.usecases

import ca.enzeroment.clone.calorytracker.tracker.domain.model.TrackedFood
import ca.enzeroment.clone.calorytracker.tracker.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val trackerRepository: TrackerRepository
) {
    operator fun invoke(
        date: LocalDate
    ) : Flow<List<TrackedFood>> {
        return trackerRepository.getFoodForDate(date)
    }
}