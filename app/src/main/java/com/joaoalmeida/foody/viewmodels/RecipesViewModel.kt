package com.joaoalmeida.foody.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.joaoalmeida.foody.util.Constants

class RecipesViewModel(app: Application) : AndroidViewModel(app) {

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[Constants.QUERY_NUMBER] = "50"
        queries[Constants.QUERY_API_KEY] = Constants.API_KEY
        queries[Constants.QUERY_TYPE] = "snack"
        queries[Constants.QUERY_DIET] = "vegan"
        queries[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[Constants.QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }
}
