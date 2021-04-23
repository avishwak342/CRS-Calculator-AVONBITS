package com.ashish.avonbitscrscalc.ui.fragment.questions.`1_2`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.Questions1And2FragmentBinding
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity.Companion.navControllerM
import org.jetbrains.anko.support.v4.longToast

class Questions1And2Fragment: Fragment() {

    private lateinit var binding: Questions1And2FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Questions1And2FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            var selected: String? = null
            var selected2i: String? = null
            var selected2ii: String? = null

            val list = resources.getStringArray(R.array.what_is_your_marital_status)
            val list2i = resources.getStringArray(R.array.is_your_spouse_or_common_law_partnert_a_citizen_or_permanent_residen_of_canada)
            val list2ii = resources.getStringArray(R.array.will_your_spouse_or_common_law_partner_come_with_you_to_canada)

            for ((j, i) in listOf(spinner, spinner2I, spinner2Ii).withIndex()) i.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, listOf(list, list2i, list2ii)[j])
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected = list[position]

                    if (position == 2 || position == 5){
                        ly2I.visibility = View.GONE
                        ly2Ii.visibility = View.GONE
                        selected2i = null
                        selected2ii = null
                    }
                    else ly2I.visibility = View.VISIBLE
                }
            }

            spinner2I.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected2i = list2i[position]
                    if (position == 2) ly2Ii.visibility = View.VISIBLE
                    else{
                        ly2Ii.visibility = View.GONE
                        selected2ii = null
                    }
                }
            }

            spinner2Ii.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected2ii = list2ii[position]

                }
            }

            next.setOnClickListener {
                if (selected == null || selected == list[0]) longToast(getString(R.string.select_a_option))
                else{
                    if (selected == list[2] || selected == list[5]) {
                        if (selected2ii == list[2]) PointsManager.spouseCommonLawComeWithMe = true
                        PointsManager.spouseCommonLaw = true
                    }
                    navControllerM.navigate(R.id.question3Fragment)
                }
            }

        }

    }
}