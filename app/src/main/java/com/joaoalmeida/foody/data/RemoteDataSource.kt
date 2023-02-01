package com.joaoalmeida.foody.data

import com.joaoalmeida.foody.data.network.FoodRecipesApi
import com.joaoalmeida.foody.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries : Map<String,String>) : Response<FoodRecipe>{
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQueries : Map<String,String>) : Response<FoodRecipe>{
        return foodRecipesApi.searchRecipes(searchQueries)
    }
}