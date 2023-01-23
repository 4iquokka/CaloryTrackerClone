package ca.enzeroment.clone.calorytracker.tracker.data.repository

import ca.enzeroment.clone.calorytracker.tracker.data.local.TrackerDao
import ca.enzeroment.clone.calorytracker.tracker.data.mapper.toTrackableFood
import ca.enzeroment.clone.calorytracker.tracker.data.mapper.toTrackedFood
import ca.enzeroment.clone.calorytracker.tracker.data.mapper.toTrackedFoodEntity
import ca.enzeroment.clone.calorytracker.tracker.data.remote.OpenFoodApi
import ca.enzeroment.clone.calorytracker.tracker.domain.model.TrackableFood
import ca.enzeroment.clone.calorytracker.tracker.domain.model.TrackedFood
import ca.enzeroment.clone.calorytracker.tracker.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(searchDto.products.mapNotNull { it.toTrackableFood() })
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(trackedFood: TrackedFood) {
        dao.insertTrackedFood(trackedFood.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        dao.deleteTrackedFood(trackedFood.toTrackedFoodEntity())
    }

    override fun getFoodForDate(date: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = date.dayOfMonth,
            month = date.monthValue,
            year = date.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }

}