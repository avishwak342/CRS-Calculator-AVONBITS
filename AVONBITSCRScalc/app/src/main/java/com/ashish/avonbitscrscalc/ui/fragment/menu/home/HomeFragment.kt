package com.ashish.avonbitscrscalc.ui.fragment.menu.home

import android.graphics.Point
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.HomeFragmentBinding
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity.Companion.navControllerM

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        MainActivity.home = true
    }

    override fun onPause() {
        super.onPause()
        MainActivity.home = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            start.setOnClickListener {
                //restart points
                PointsManager.restart()
                //Go to question 1 and 2
                navControllerM.navigate(R.id.questions1And2Fragment)

            }

        }

    }


}