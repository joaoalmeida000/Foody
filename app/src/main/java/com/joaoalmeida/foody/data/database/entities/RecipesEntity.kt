package com.joaoalmeida.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joaoalmeida.foody.models.FoodRecipe
import com.joaoalmeida.foody.util.Constants.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0
}