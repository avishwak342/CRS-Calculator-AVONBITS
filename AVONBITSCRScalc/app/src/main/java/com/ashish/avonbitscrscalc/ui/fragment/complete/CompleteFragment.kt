package com.ashish.avonbitscrscalc.ui.fragment.complete

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.CompleteFragmentBinding
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.model.managers.setCounter
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity.Companion.navControllerM

class CompleteFragment : Fragment() {

    private lateinit var binding: CompleteFragmentBinding
    private val viewModel: CompleteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CompleteFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            //Upload data
            viewModel.upload(score)

            //Go to Home
            complete.setOnClickListener {
                navControllerM.navigate(R.id.nav_home)
            }

        }

    }


}