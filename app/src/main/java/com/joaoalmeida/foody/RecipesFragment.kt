package com.joaoalmeida.foody

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joaoalmeida.foody.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {
    private lateinit var binding : FragmentRecipesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.recyclerView.showShimmer()
        return view
    }
}
