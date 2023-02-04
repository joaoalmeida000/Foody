package com.joaoalmeida.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joaoalmeida.foody.util.Constants.FAVORITE_RECIPES_TABLE
import com.joaoalmeida.foody.models.Result

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result : Result
) {
}