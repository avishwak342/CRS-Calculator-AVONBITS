package com.ashish.avonbitscrscalc.ui.fragment.questions.`5`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.Question3FragmentBinding
import com.ashish.avonbitscrscalc.databinding.Question5FragmentBinding
import com.ashish.avonbitscrscalc.model.managers.LenguageManager
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import org.jetbrains.anko.support.v4.longToast

class Question5Fragment: Fragment() {

    private lateinit var binding: Question5FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Question5FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            var selected: String? = null

            val list = resources.getStringArray(R.array.are_your_test_result_less_than_two_years_old)
            val listOptions = resources.getStringArray(R.array.which_lenguage_test_did_you_take_for_your_first_official_lenguage)
            val listOptionsWithNotAplicable = ArrayList(resources.getStringArray(R.array.which_lenguage_test_did_you_take_for_your_first_official_lenguage).toList()).apply { add(getString(R.string.not_applicable)) }

            spinner.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, list)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected = list[position]
                    if (position == 1){
                        ly5I.visibility = View.VISIBLE
                        ly5Ii.visibility = View.VISIBLE
                    }
                    else{
                        ly5I.visibility = View.GONE
                        ly5Ii.visibility = View.GONE
                    }
                }
            }

            spinner5I.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, listOptions)
            spinner5I.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    LenguageManager.configView(binding.testScores5I, listOptions[position])
                    testScores5I.root.visibility = View.VISIBLE
                }
            }
            
            spinner5Ii.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, listOptionsWithNotAplicable)
            spinner5Ii.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (position == listOptionsWithNotAplicable.size) testScores5Ii.root.visibility = View.GONE
                    else{
                        LenguageManager.configView(binding.testScores5Ii, listOptionsWithNotAplicable[position])
                        testScores5Ii.root.visibility = View.VISIBLE
                    }
                }
            }

            next.setOnClickListener {
                if (selected == null || selected == list[0]) longToast(getString(R.string.select_a_option))
                else{

                    for (i in LenguageManager.getResults(testScores5I))
                        PointsManager.addPoints(5,
                                when(i){
                                    1 -> listOf(32, 34)
                                    2 -> listOf(29, 31)
                                    3 -> listOf(22, 23)
                                    4 -> listOf(16, 17)
                                    5 -> listOf(8, 9)
                                    6 -> listOf(6, 6)
                                    7 -> listOf(0, 0)
                                    else -> listOf(0, 0)
                                }
                        )

                    if (testScores5Ii.root.visibility == View.VISIBLE)
                        for (i in LenguageManager.getResults(testScores5I))
                            PointsManager.addPoints(5,
                                    when(i){
                                        1 -> listOf(6, 6)
                                        2 -> listOf(6, 6)
                                        3 -> listOf(3, 3)
                                        4 -> listOf(3, 3)
                                        5 -> listOf(1, 1)
                                        6 -> listOf(1, 1)
                                        7 -> listOf(0, 0)
                                        else -> listOf(0, 0)
                                    }
                            )

                    MainActivity.navControllerM.navigate(R.id.question6Fragment)
                }
            }
        }

    }

}