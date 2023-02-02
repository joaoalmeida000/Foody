package com.joaoalmeida.foody.ui.fragments.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.joaoalmeida.foody.R
import com.joaoalmeida.foody.databinding.FragmentInstructionsBinding
import com.joaoalmeida.foody.databinding.FragmentOverviewBinding
import com.joaoalmeida.foody.models.Result
import com.joaoalmeida.foody.util.Constants

class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInstructionsBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(Constants.RECIPE_RESULT_KEY)

        binding.instructionsWebView.webViewClient = object :WebViewClient() {}
        val websiteUrl : String = myBundle!!.sourceUrl
        binding.instructionsWebView.loadUrl(websiteUrl )

        return binding.root
    }
}