package com.joaoalmeida.foody.ui.fragments.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaoalmeida.foody.adapters.IngredientsAdapter
import com.joaoalmeida.foody.databinding.FragmentIngredientsBinding
import com.joaoalmeida.foody.models.Result
import com.joaoalmeida.foody.util.Constants.RECIPE_RESULT_KEY

class IngredientsFragment : Fragment() {

    private lateinit var binding : FragmentIngredientsBinding
    private val ingredientAdapter : IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        setupRecyclerView()
        myBundle?.extendedIngredients?.let {
            ingredientAdapter.setData(it)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.ingredientsRecyclerView.apply {
            adapter = ingredientAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

}