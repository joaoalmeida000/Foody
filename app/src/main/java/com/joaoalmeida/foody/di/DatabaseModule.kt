package com.joaoalmeida.foody.di

import android.app.Application
import androidx.room.Room
import com.joaoalmeida.foody.data.database.RecipesDAO
import com.joaoalmeida.foody.data.database.RecipesDatabase
import com.joaoalmeida.foody.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): RecipesDatabase {
        return Room.databaseBuilder(
            app,
            RecipesDatabase::class.java,
            Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(recipesDatabase: RecipesDatabase) : RecipesDAO {
        return recipesDatabase.getRecipesDAO()
    }
}
