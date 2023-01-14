package com.joaoalmeida.foody.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.joaoalmeida.foody.data.DataStoreRepository
import com.joaoalmeida.foody.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    app: Application,
    private val dataStoreRepository: DataStoreRepository,
) : AndroidViewModel(app) {

    private var mealType = Constants.DEFAULT_MEAL_TYPE
    private var dietType = Constants.DEFAULT_DIET_TYPE

    val readMealAndDietType = dataStoreRepository.readMealAndDietType

    fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietTypeToPreferencesStore(
                mealType, mealTypeId, dietType, dietTypeId
            )
        }
    }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        viewModelScope.launch {
            dataStoreRepository.readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }

        queries[Constants.QUERY_NUMBER] = Constants.DEFAULT_RECIPES_NUMBER
        queries[Constants.QUERY_API_KEY] = Constants.API_KEY
        queries[Constants.QUERY_TYPE] = mealType
        queries[Constants.QUERY_DIET] = dietType
        queries[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[Constants.QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }
}
