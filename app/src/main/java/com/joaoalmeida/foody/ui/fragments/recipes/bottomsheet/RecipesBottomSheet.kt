package com.joaoalmeida.foody.ui.fragments.recipes.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.joaoalmeida.foody.R
import com.joaoalmeida.foody.databinding.FragmentRecipesBinding
import com.joaoalmeida.foody.databinding.RecipesBottomSheetBinding
import com.joaoalmeida.foody.util.Constants.DEFAULT_DIET_TYPE
import com.joaoalmeida.foody.util.Constants.DEFAULT_MEAL_TYPE
import com.joaoalmeida.foody.viewmodels.RecipesViewModel
import java.util.*

class RecipesBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: RecipesBottomSheetBinding
    private lateinit var recipesViewModel: RecipesViewModel
    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = RecipesBottomSheetBinding.inflate(inflater, container, false)

        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner) { value ->
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType
            updateChip(value.selectedMealTypeId,value.selectedDietTypeId)
        }

        binding.mealTypeChipGroup
            .setOnCheckedStateChangeListener { group, checkedIds ->
                val chip = group.findViewById<Chip>(checkedIds.first())
                val selectedMealType = chip.text.toString().lowercase(Locale.ROOT)
                mealTypeChip = selectedMealType
                mealTypeChipId = checkedIds.first()
            }

        binding.dietTypeChipGroup
            .setOnCheckedStateChangeListener { group, checkedIds ->
                val chip = group.findViewById<Chip>(checkedIds.first())
                val selectedDietType = chip.text.toString().lowercase(Locale.ROOT)
                dietTypeChip = selectedDietType
                dietTypeChipId = checkedIds.first()
            }

        binding.applyBtn.setOnClickListener {
            recipesViewModel.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipId
            )
        val action =
            RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment(true)
            findNavController().navigate(action)
        }
        return binding.root
    }

    private fun updateChip(selectedMealTypeId: Int, selectedDietTypeId: Int) {
        binding.mealTypeChipGroup.check(selectedMealTypeId)
        binding.dietTypeChipGroup.check(selectedDietTypeId)
    }
}
