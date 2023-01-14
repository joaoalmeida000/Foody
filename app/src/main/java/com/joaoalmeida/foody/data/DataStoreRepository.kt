package com.joaoalmeida.foody.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.joaoalmeida.foody.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(
    name = Constants.PREFERENCES_NAME
)

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val dataStore = context.dataStore

    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(Constants.PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(Constants.PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(Constants.PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(Constants.PREFERENCES_DIET_TYPE_ID)
    }

    suspend fun saveMealAndDietTypeToPreferencesStore(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int,
    ) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedMealType] = mealType
            preferences[PreferenceKeys.selectedMealTypeId] = mealTypeId
            preferences[PreferenceKeys.selectedDietType] = dietType
            preferences[PreferenceKeys.selectedDietTypeId] = dietTypeId
        }
    }

    val readMealAndDietType: Flow<MealAndDietType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val selectedMealType =
                preferences[PreferenceKeys.selectedMealType] ?: Constants.DEFAULT_MEAL_TYPE
            val selectedMealTypeId =
                preferences[PreferenceKeys.selectedMealTypeId] ?: 0
            val selectedDietType =
                preferences[PreferenceKeys.selectedDietType] ?: Constants.DEFAULT_DIET_TYPE
            val selectedDietTypeId =
                preferences[PreferenceKeys.selectedDietTypeId] ?: 0
            MealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }

    data class MealAndDietType(
        val selectedMealType: String,
        val selectedMealTypeId: Int,
        val selectedDietType: String,
        val selectedDietTypeId: Int,
    )
}
