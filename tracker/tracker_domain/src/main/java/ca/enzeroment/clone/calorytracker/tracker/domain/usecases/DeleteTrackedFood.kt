package ca.enzeroment.clone.calorytracker.tracker.domain.usecases

import ca.enzeroment.clone.calorytracker.tracker.domain.model.TrackedFood
import ca.enzeroment.clone.calorytracker.tracker.domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val trackerRepository: TrackerRepository
) {
    suspend operator fun invoke(
        trackedFood: TrackedFood
    ) {
        trackerRepository.deleteTrackedFood(trackedFood)
    }
}