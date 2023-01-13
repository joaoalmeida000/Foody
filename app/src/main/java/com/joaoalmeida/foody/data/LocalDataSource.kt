package com.joaoalmeida.foody.data

import com.joaoalmeida.foody.data.database.RecipesDAO
import com.joaoalmeida.foody.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDAO: RecipesDAO
) {
    suspend fun saveRecipe(recipesEntity: RecipesEntity) {
        recipesDAO.insert(recipesEntity)
    }

    fun readDatabase(): Flow<List<RecipesEntity>> {
       return recipesDAO.readRecipes()
    }
}