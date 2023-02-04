package com.joaoalmeida.foody.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaoalmeida.foody.R
import com.joaoalmeida.foody.adapters.FavoriteRecipesAdapter
import com.joaoalmeida.foody.databinding.FragmentFavoriteRecipesBinding
import com.joaoalmeida.foody.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  FavoriteRecipesFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteRecipesBinding
    private val favoriteRecipesAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter() }
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = favoriteRecipesAdapter

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.favoritesRecipeRecyclerView.apply {
            adapter = favoriteRecipesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
