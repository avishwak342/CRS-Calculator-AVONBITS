package com.ashish.avonbitscrscalc.ui.fragment.questions.`10`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.Question10FragmentBinding
import com.ashish.avonbitscrscalc.databinding.Question3FragmentBinding
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import org.jetbrains.anko.support.v4.longToast

class Question10Fragment: Fragment() {

    private lateinit var binding: Question10FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Question10FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            var selected: String? = null

            val list = resources.getStringArray(R.array.do_you_or_your_spouse_or_common_law_partner__if_they_will_come_with_you_to_canada__have_at_least_one_brother_or_sister_living_in_canada_who_is_a_citizen_or_permanent_resident)
            spinner.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, list)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected = list[position]
                }
            }

            if (PointsManager.spouseCommonLawComeWithMe) next.text = getString(R.string.next_view)

            next.setOnClickListener {

                val add = {
                    if (spinner.selectedItemPosition == 1) PointsManager.addPoints(13, listOf(15, 15))
                }

                if (selected == null || selected == list[0]) longToast(getString(R.string.select_a_option))
                else if (PointsManager.spouseCommonLawComeWithMe){
                    add()
                    MainActivity.navControllerM.navigate(R.id.question11Fragment)
                }
                else{
                    add()
                    MainActivity.navControllerM.navigate(R.id.completeFragment)
                }
            }
        }

    }

}