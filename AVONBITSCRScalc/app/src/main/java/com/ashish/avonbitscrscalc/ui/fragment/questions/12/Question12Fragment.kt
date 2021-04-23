package com.ashish.avonbitscrscalc.ui.fragment.questions.`12`

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.Question12FragmentBinding
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import org.jetbrains.anko.support.v4.longToast


class Question12Fragment: Fragment() {

    private lateinit var binding: Question12FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Question12FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            var selected: String? = null

            val list = resources.getStringArray(R.array.in_the_last_ten_years_many_years_of_skilled_worik_)
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

                    PointsManager.addPoints(12,
                        when(spinner.selectedItemPosition){

                            1 -> listOf(0, 0)
                            2 -> listOf(5, 5)
                            3 -> listOf(7, 7)
                            4 -> listOf(8, 8)
                            5 -> listOf(9, 9)
                            6 -> listOf(10, 10)
                            else -> listOf(0, 0)

                        }
                    )

                    MainActivity.navControllerM.navigate(R.id.question13Fragment)
                }
            }
        }

    }

}