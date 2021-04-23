package com.ashish.avonbitscrscalc.model.managers

import android.widget.ArrayAdapter
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.TestScoresBinding
object LenguageManager {

    //Get the index of the selected Item to calculate the result
    val getResults = { b: TestScoresBinding ->
        listOf(b.speaking.selectedItemPosition,
                b.listening.selectedItemPosition,
                b.reading.selectedItemPosition,
                b.writing.selectedItemPosition
        )
    }

    //Config the spinner to pick a calification
    val configView = { b: TestScoresBinding, type: String ->
        with(b){
            with(b.root.context){

                //A list to save te points of the speaking, writing, listening, reading
                val list = arrayListOf<ArrayList<String>>().apply { repeat(4){ add(arrayListOf()) } }

                //A list to save the Options of lenguage
                val options = resources.getStringArray(R.array.which_lenguage_test_did_you_take_for_your_first_official_lenguage)

                //Lambda to complete the data of list
                val addAllLists = { l1: Array<String>, l2: Array<String>, l3: Array<String>, l4: Array<String>  ->
                    for ((j, i) in list.withIndex()) i.addAll(listOf(l1, l2, l3, l4)[j])
                }

                // For each type use a list
                when(type){

                    options[0] -> for (i in list){ i.add(getString(R.string.select_a_option)) }

                    options[1] -> for (i in list) i.addAll(resources.getStringArray(R.array.celpip_g))

                    options[2] ->
                        addAllLists(
                                resources.getStringArray(R.array.ielts_speaking_writing),
                                resources.getStringArray(R.array.ielts_listening),
                                resources.getStringArray(R.array.ielts_reading),
                                resources.getStringArray(R.array.ielts_speaking_writing),
                        )

                    options[3] ->
                        addAllLists(
                                resources.getStringArray(R.array.tef_canada_speaking_writing),
                                resources.getStringArray(R.array.tef_canada_listening),
                                resources.getStringArray(R.array.tef_canada_reading),
                                resources.getStringArray(R.array.tef_canada_speaking_writing),
                        )


                    options[4] ->
                        addAllLists(
                                resources.getStringArray(R.array.tcd_canada_speaking_writing),
                                resources.getStringArray(R.array.tcd_canada_listening),
                                resources.getStringArray(R.array.tcd_canada_reading),
                                resources.getStringArray(R.array.tcd_canada_speaking_writing),
                        )
                }


                for ((j, i) in listOf(b.speaking, b.listening, b.reading, b.writing).withIndex()) i.adapter = ArrayAdapter(this, R.layout.spinner_item, list[j])
            }
        }
    }

}