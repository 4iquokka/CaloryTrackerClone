package ca.enzeroment.clone.calorytracker.onboarding.domain.di

import ca.enzeroment.clone.calorytracker.onboarding.domain.usecases.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {

    @Provides
    @ViewModelScoped
    fun provideValidateNutrientUseCase() : ValidateNutrients{
        return ValidateNutrients()
    }
}