package com.joaoalmeida.foody.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaoalmeida.foody.viewmodels.MainViewModel
import com.joaoalmeida.foody.adapters.RecipesAdapter
import com.joaoalmeida.foody.databinding.FragmentRecipesBinding
import com.joaoalmeida.foody.util.Constants.API_KEY
import com.joaoalmeida.foody.util.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.joaoalmeida.foody.util.Constants.QUERY_API_KEY
import com.joaoalmeida.foody.util.Constants.QUERY_DIET
import com.joaoalmeida.foody.util.Constants.QUERY_FILL_INGREDIENTS
import com.joaoalmeida.foody.util.Constants.QUERY_NUMBER
import com.joaoalmeida.foody.util.Constants.QUERY_TYPE
import com.joaoalmeida.foody.util.NetworkResult
import com.joaoalmeida.foody.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private lateinit var binding: FragmentRecipesBinding
    private val mAdapter by lazy { RecipesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        recipesViewModel = ViewModelProvider(this)[RecipesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupRecyclerView()
        requestApiData()
        return binding.root
    }

    private fun showShimmerEffect() {
        binding.recyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.recyclerView.hideShimmer()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(activity)
            showShimmerEffect()
        }
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner
        ) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let {
                        mAdapter.setData(it)
                    }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }
}
