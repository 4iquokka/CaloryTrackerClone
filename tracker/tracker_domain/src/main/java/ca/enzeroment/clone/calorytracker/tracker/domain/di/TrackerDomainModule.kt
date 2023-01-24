package ca.enzeroment.clone.calorytracker.tracker.domain.di

import ca.enzeroment.clone.calorytracker.core.domain.preferences.Preferences
import ca.enzeroment.clone.calorytracker.tracker.domain.repository.TrackerRepository
import ca.enzeroment.clone.calorytracker.tracker.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @Provides
    @ViewModelScoped
    fun providesTrackerUseCases(
        preferences: Preferences,
        trackerRepository: TrackerRepository
    ): TrackerUseCases{
        return TrackerUseCases(
            trackFood = TrackFood(trackerRepository),
            searchFood = SearchFood(trackerRepository),
            deleteTrackedFood = DeleteTrackedFood(trackerRepository),
            getFoodsForDate = GetFoodsForDate(trackerRepository),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
    }
}