package com.joaoalmeida.foody.data.database

import androidx.room.*
import com.joaoalmeida.foody.data.database.entities.FavoritesEntity
import com.joaoalmeida.foody.data.database.entities.RecipesEntity
import com.joaoalmeida.foody.models.Result
import com.joaoalmeida.foody.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDAO {

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun readRecipes() : Flow<List<RecipesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipesEntity: RecipesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM ${Constants.FAVORITE_RECIPES_TABLE}")
    suspend fun deleteAllFavoriteRecipes()
}
