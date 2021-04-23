package com.ashish.avonbitscrscalc.ui.fragment.questions.`13`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.Question13FragmentBinding
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import org.jetbrains.anko.support.v4.longToast
import com.ashish.avonbitscrscalc.model.managers.LenguageManager

class Question13Fragment: Fragment() {

    private lateinit var binding: Question13FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Question13FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            var selected: String? = null

            val list = resources.getStringArray(R.array.which_lenguage_test_did_you_take_for_your_first_official_lenguage)

            spinner.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, list)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected = list[position]
                    if (position == list.size) textScores.root.visibility = View.GONE
                    else{
                        LenguageManager.configView(textScores, list[position])
                        textScores.root.visibility = View.VISIBLE
                    }
                }
            }

            next.setOnClickListener {
                if (selected == null || selected == list[0]) longToast(getString(R.string.select_a_option))
                else{

                    for (i in LenguageManager.getResults(textScores))
                        PointsManager.addPoints(13,
                                when(i){
                                    1 -> listOf(5, 5)
                                    2 -> listOf(5, 5)
                                    3 -> listOf(3, 3)
                                    4 -> listOf(3, 3)
                                    5 -> listOf(1, 1)
                                    6 -> listOf(1, 1)
                                    7 -> listOf(0, 0)
                                    else -> listOf(0, 0)
                                }
                        )

                    MainActivity.navControllerM.navigate(R.id.nav_home)
                }
            }
        }

    }

}