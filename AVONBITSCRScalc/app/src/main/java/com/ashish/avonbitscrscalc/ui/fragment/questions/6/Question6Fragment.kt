package com.ashish.avonbitscrscalc.ui.fragment.questions.`6`

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
import com.ashish.avonbitscrscalc.databinding.Question6FragmentBinding
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import org.jetbrains.anko.support.v4.longToast

class Question6Fragment: Fragment() {

    private lateinit var binding: Question6FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Question6FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            var selected: String? = null
            var selected6I: String? = null

            val list = resources.getStringArray(R.array.in_the_last_ten_years_how_many_years_of_skilled_work_experience_in_canada_do_you_have)
            val list6I = resources.getStringArray(R.array.in_the_last_10_years_how_many_total_years_of_foreign_skilled_work_experience_do_you_have)

            spinner.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, list)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected = list[position]
                }
            }

            spinner6I.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, list6I)
            spinner6I.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected6I = list6I[position]
                }
            }

            next.setOnClickListener {
                if (selected == null || selected == list[0]) longToast(getString(R.string.select_a_option))
                else{

                    PointsManager.addPoints(6,
                        when(spinner.selectedItemPosition){
                            1 -> listOf(0, 0)
                            2 -> listOf(35, 40)
                            3 -> listOf(46, 53)
                            4 -> listOf(56, 64)
                            5 -> listOf(63, 72)
                            6 -> listOf(70, 80)
                            else -> listOf(0, 0)
                        }
                    )

                    if (selected6I != null)
                        PointsManager.addPoints(6,
                                when(spinner6I.selectedItemPosition){
                                    1 -> listOf(13, 25)
                                    2 -> listOf(13, 25)
                                    3 -> listOf(25, 50)
                                    else -> listOf(0, 0)
                                }
                        )

                    MainActivity.navControllerM.navigate(R.id.question7Fragment)
                }
            }
        }

    }

}