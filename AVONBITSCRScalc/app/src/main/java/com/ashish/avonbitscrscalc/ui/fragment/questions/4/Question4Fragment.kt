package com.ashish.avonbitscrscalc.ui.fragment.questions.`4`

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
import com.ashish.avonbitscrscalc.databinding.Question4FragmentBinding
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import org.jetbrains.anko.support.v4.longToast

class Question4Fragment: Fragment() {

    private lateinit var binding: Question4FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Question4FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            var points = listOf(0, 0)

            var selected: String? = null
            var selected4i: String? = null
            var selected4ii: String? = null

            val list = resources.getStringArray(R.array.what_is_your_level_of_education)
            val list4i = resources.getStringArray(R.array.have_yout_earned_a_canadian_degree_diploma_or_certificate)
            val list4ii = resources.getStringArray(R.array.choose_the_best_awnser_to_describe_this_level_of_education)

            for ((j, i) in listOf(spinner, spinner4I, spinner4Ii).withIndex()) i.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, listOf(list, list4i, list4ii)[j])
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected = list[position]

                    points = listOf(
                            listOf(0, 0),
                            listOf(28, 30),
                            listOf(84, 90),
                            listOf(91, 98),
                            listOf(112, 120),
                            listOf(119, 128),
                            listOf(126, 135),
                            listOf(140,150),
                        )[position]
                }
            }

            spinner4I.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected4i = list4i[position]
                    if (position == 1) ly4Ii.visibility = View.VISIBLE
                    else{
                        ly4I.visibility = View.GONE
                        selected4ii = null
                    }
                }
            }

            spinner4Ii.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected4ii = list4ii[position]
                }
            }

            next.setOnClickListener {
                if (selected == null || selected == list[0]) longToast(getString(R.string.select_a_option))
                else{

                    PointsManager.addPoints(4, points)

                    if (spinner4Ii.visibility == View.VISIBLE)
                        PointsManager.addPoints(13,
                            when(spinner4Ii.selectedItemPosition){
                                1 -> listOf(15, 15)
                                2 -> listOf(15, 15)
                                3 -> listOf(30, 30)
                                else -> listOf(0, 0)
                            }
                        )

                    MainActivity.navControllerM.navigate(R.id.question5Fragment)
                }
            }
        }

    }

}