package com.joaoalmeida.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joaoalmeida.foody.data.database.entities.FavoritesEntity
import com.joaoalmeida.foody.databinding.FavoriteRecipesRowLayoutBinding
import com.joaoalmeida.foody.util.RecipesDiffUtil

class FavoriteRecipesAdapter : RecyclerView.Adapter<FavoriteRecipesAdapter.MyViewHolder>() {

    private var favoriteRecipesList = emptyList<FavoritesEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = favoriteRecipesList[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        return favoriteRecipesList.size
    }

    fun setData(newData: List<FavoritesEntity>) {
        val favoriteRecipesDiffUtil = RecipesDiffUtil(favoriteRecipesList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipesList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    class MyViewHolder(private val binding: FavoriteRecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favoriteRecipe: FavoritesEntity) {
            binding.favoritesEntity = favoriteRecipe
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return FavoriteRecipesAdapter.MyViewHolder(binding)
            }
        }
    }


}