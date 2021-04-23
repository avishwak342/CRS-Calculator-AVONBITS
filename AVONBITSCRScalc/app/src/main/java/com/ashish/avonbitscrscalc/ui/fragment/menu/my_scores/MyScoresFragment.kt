package com.ashish.avonbitscrscalc.ui.fragment.menu.my_scores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ashish.avonbitscrscalc.databinding.MyScoresFragmentBinding
import com.ashish.avonbitscrscalc.model.adapters.Adapter
import com.google.firebase.database.DataSnapshot

class MyScoresFragment : Fragment() {

    private lateinit var binding: MyScoresFragmentBinding
    private val viewModel: MyScoresViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MyScoresFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            //Config recyclerview
            viewModel.configRecycler(recycler)
            //Get Data
            viewModel.getData(root.context)

            //Update recyclerview
            viewModel.listData.observe(viewLifecycleOwner){ list ->
                recycler.adapter?.let { (it as Adapter<DataSnapshot, *>).update(list) }
            }

        }

    }


}