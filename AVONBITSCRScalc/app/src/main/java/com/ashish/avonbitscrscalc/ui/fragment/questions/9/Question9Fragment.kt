package com.ashish.avonbitscrscalc.ui.fragment.questions.`9`

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
import com.ashish.avonbitscrscalc.databinding.Question9FragmentBinding
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import org.jetbrains.anko.support.v4.longToast

class Question9Fragment: Fragment() {

    private lateinit var binding: Question9FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Question9FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            var selected: String? = null

            val list = resources.getStringArray(R.array.do_you_have_a_nomination_certificate_from_a_province_or_territory)
            spinner.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, list)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected = list[position]
                }
            }

            next.setOnClickListener {
                if (selected == null || selected == list[0]) longToast(getString(R.string.select_a_option))
                else{

                    if (spinner.selectedItemPosition == 1)
                        PointsManager.addPoints(13, listOf(600, 600))

                    MainActivity.navControllerM.navigate(R.id.question10Fragment)
                }
            }
        }

    }

}