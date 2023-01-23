package ca.enzeroment.clone.calorytracker.di

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import ca.enzeroment.clone.calorytracker.CaloryTrackerApp
import ca.enzeroment.clone.calorytracker.core.data.preferences.DefaultPreferences
import ca.enzeroment.clone.calorytracker.core.domain.preferences.Preferences
import ca.enzeroment.clone.calorytracker.core.domain.usecase.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Hilt is monolithic, it needs to know all the module at the compile time
// For Dynamic Feature, you need to use Dagger2, Hilt does not support it.

@Module
@InstallIn(SingletonComponent::class) // These dependencies live as long as the application does.
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext app: Context
    ) : SharedPreferences{
        return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(
        sharedPreferences: SharedPreferences
    ) : Preferences {
        return DefaultPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideFilterOutDigitUseCase() : FilterOutDigits {
        return FilterOutDigits()
    }
}