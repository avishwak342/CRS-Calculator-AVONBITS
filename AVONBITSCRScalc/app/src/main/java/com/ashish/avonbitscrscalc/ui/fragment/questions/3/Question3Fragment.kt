package com.ashish.avonbitscrscalc.ui.fragment.questions.`3`

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
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import org.jetbrains.anko.support.v4.longToast

class Question3Fragment: Fragment() {

    private lateinit var binding: Question3FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Question3FragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            var points: List<Int> = listOf(0, 0)

            var selected: String? = null

            val list = resources.getStringArray(R.array.how_old_are_you)
            spinner.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, list)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) { return }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selected = list[position]

                    points = listOf(
                            listOf(0, 0),
                            listOf(90, 99),
                            listOf(95, 105),
                            listOf(100, 110),
                            listOf(95, 105),
                            listOf(90, 99),
                            listOf(85, 94),
                            listOf(80, 88),
                            listOf(75, 83),
                            listOf(70, 77),
                            listOf(65, 72),
                            listOf(60, 66),
                            listOf(55, 61),
                            listOf(50, 55),
                            listOf(45, 50),
                            listOf(35, 39),
                            listOf(25, 28),
                            listOf(15, 17),
                            listOf(5, 6),
                            listOf(0, 0)
                    )[position]
                }
            }

            next.setOnClickListener {
                if (selected == null || selected == list[0]) longToast(getString(R.string.select_a_option))
                else{

                    PointsManager.addPoints(3, points)

                    MainActivity.navControllerM.navigate(R.id.question4Fragment)

                }
            }
        }

    }

}