package com.joaoalmeida.foody.data

import com.joaoalmeida.foody.data.database.RecipesDAO
import com.joaoalmeida.foody.data.database.entities.FavoritesEntity
import com.joaoalmeida.foody.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDAO: RecipesDAO
) {
    suspend fun saveRecipe(recipesEntity: RecipesEntity) {
        recipesDAO.insert(recipesEntity)
    }

    fun readRecipes(): Flow<List<RecipesEntity>> {
       return recipesDAO.readRecipes()
    }

    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDAO.insertFavoriteRecipe(favoritesEntity)
    }

    fun readFavoriteRecipes() : Flow<List<FavoritesEntity>> {
        return recipesDAO.readFavoriteRecipes()
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDAO.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDAO.deleteAllFavoriteRecipes()
    }

}