package com.ashish.avonbitscrscalc.ui.fragment.questions.`8`

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
import com.ashish.avonbitscrscalc.databinding.Question8FragmentBinding
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import org.jetbrains.anko.support.v4.longToast

class Question8Fragment: Fragment() {

    private lateinit var binding: Question8FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Question8FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            var selected: String? = null
            var selected8i: String? = null

            val list = resources.getStringArray(R.array.do_you_have_a_valid_job_offer_supported_by_a_labour_market_impact_assessment_if_needed)
            val list8i = resources.getStringArray(R.array.which_noc_skill_type_or_level_is_the_job_offer)

            spinner.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, list)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected = list[position]
                    if (position == 1){
                        ly8I.visibility = View.VISIBLE
                    }
                    else{
                        ly8I.visibility = View.GONE
                        selected8i = null
                    }
                }
            }

            spinner8I.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, list8i)
            spinner8I.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected8i = list8i[position]
                }
            }


            next.setOnClickListener {
                if (selected == null || selected == list[0]) longToast(getString(R.string.select_a_option))
                else{

                    if (selected8i != null) PointsManager.addPoints(13, if (spinner8I.selectedItemPosition == 1) listOf(200, 200) else listOf(50, 50))

                    MainActivity.navControllerM.navigate(R.id.question9Fragment)
                }
            }
        }

    }

}