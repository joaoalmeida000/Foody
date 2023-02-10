package com.joaoalmeida.foody.ui.fragments.favorites

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.joaoalmeida.foody.R
import com.joaoalmeida.foody.adapters.FavoriteRecipesAdapter
import com.joaoalmeida.foody.databinding.FragmentFavoriteRecipesBinding
import com.joaoalmeida.foody.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  FavoriteRecipesFragment : Fragment(),MenuProvider {
    private lateinit var binding: FragmentFavoriteRecipesBinding
    private val mainViewModel : MainViewModel by viewModels()
    private val favoriteRecipesAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter(requireActivity(),mainViewModel) }

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

        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.favoritesRecipeRecyclerView.apply {
            adapter = favoriteRecipesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        favoriteRecipesAdapter.clearContextualActionMode()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.favorite_recipes_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == R.id.deleteAll_favorite_recipes_menu) {
            mainViewModel.deleteAllFavoriteRecipes()
            showSnackBar("All Recipes Removed.")
        }
        return super.onContextItemSelected(menuItem)
    }

    private fun showSnackBar(message:String){
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT,
        ).setAction("Okay"){}
            .show()
    }
}
