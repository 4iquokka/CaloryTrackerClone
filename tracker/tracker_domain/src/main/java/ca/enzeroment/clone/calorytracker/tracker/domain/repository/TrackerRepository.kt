package ca.enzeroment.clone.calorytracker.tracker.domain.repository

import ca.enzeroment.clone.calorytracker.tracker.domain.model.TrackableFood
import ca.enzeroment.clone.calorytracker.tracker.domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ) : Result<List<TrackableFood>>

    suspend fun insertTrackedFood(trackedFood: TrackedFood)

    suspend fun deleteTrackedFood(trackedFood: TrackedFood)

    fun getFoodForDate(date : LocalDate) : Flow<List<TrackedFood>>
}