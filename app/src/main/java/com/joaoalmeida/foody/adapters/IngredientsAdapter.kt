package com.joaoalmeida.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.joaoalmeida.foody.R
import com.joaoalmeida.foody.databinding.IngredientsRowLayoutBinding
import com.joaoalmeida.foody.models.ExtendedIngredient
import com.joaoalmeida.foody.util.Constants.BASE_IMAGE_URL
import com.joaoalmeida.foody.util.RecipesDiffUtil

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentIngredient = ingredientsList[position]
        holder.bind(currentIngredient)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    fun setData(newData: List<ExtendedIngredient>) {
        val ingredientsDiffUtil = RecipesDiffUtil(ingredientsList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
        ingredientsList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

    class MyViewHolder(private val binding : IngredientsRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root){

        fun bind(ingredient: ExtendedIngredient) {
            binding.ingredientImageView.load(BASE_IMAGE_URL + ingredient.image) {
                crossfade(600)
                error(R.drawable.error)
            }
            binding.ingredientNameTextView.text = ingredient.name.replaceFirstChar { it -> it.uppercaseChar() }
            binding.ingredientAmountTextView.text = ingredient.amount.toString()
            binding.ingredientUnitTextView.text = ingredient.unit
            binding.ingredientConsistencyTextView.text = ingredient.consistency
            binding.ingredientOriginalTextView.text = ingredient.originalName
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientsRowLayoutBinding.inflate(layoutInflater, parent, false)
                return IngredientsAdapter.MyViewHolder(binding)
            }
        }
    }
}