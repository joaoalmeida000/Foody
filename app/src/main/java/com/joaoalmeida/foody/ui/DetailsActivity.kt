package com.joaoalmeida.foody.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.joaoalmeida.foody.R
import com.joaoalmeida.foody.adapters.PagerAdapter
import com.joaoalmeida.foody.databinding.ActivityDetailsBinding
import com.joaoalmeida.foody.ui.fragments.ingredients.IngredientsFragment
import com.joaoalmeida.foody.ui.fragments.instructions.InstructionsFragment
import com.joaoalmeida.foody.ui.fragments.overview.OverviewFragment

class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val resultBundle = Bundle()
        resultBundle.putParcelable("recipeBundle", args.result)

        val pagerAdapter = PagerAdapter(resultBundle,fragments,this)

        binding.viewpager2.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout,binding.viewpager2) {tab,position ->
            tab.text = titles[position]
        }.attach()

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}