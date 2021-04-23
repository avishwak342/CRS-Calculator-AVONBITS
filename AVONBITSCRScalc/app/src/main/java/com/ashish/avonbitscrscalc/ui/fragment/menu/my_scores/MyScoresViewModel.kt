package com.ashish.avonbitscrscalc.ui.fragment.menu.my_scores

import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.ScoreItemBinding
import com.ashish.avonbitscrscalc.model.adapters.Adapter
import com.ashish.avonbitscrscalc.model.connectors.AuthDBConnector
import com.ashish.avonbitscrscalc.model.connectors.RealTimeDBConnector
import com.ashish.avonbitscrscalc.model.connectors.getS
import com.google.firebase.database.DataSnapshot

class MyScoresViewModel : ViewModel() {

    private val _listData = MutableLiveData<List<DataSnapshot>>().apply { value = ArrayList() }
    val listData: LiveData<List<DataSnapshot>> get() = _listData

    val configRecycler = { r: RecyclerView ->
        //Create a adapter
        r.adapter = Adapter(listData.value!!, { ScoreItemBinding.inflate(LayoutInflater.from(it.context)) }, { it.root }){
            item: DataSnapshot, itemView: ScoreItemBinding ->
            with(itemView){
                with(itemView.root.context){
                    //Show the data
                    date.text = item.getS(getString(R.string.date))
                    score.text = item.getS(getString(R.string.score))
                }
            }
        }
    }

    val getData = { context: Context ->
        with(context){
            //Wait the user
            AuthDBConnector.userAwait(viewModelScope){ auth ->
                //Get the data of the user
                RealTimeDBConnector.getItem(RealTimeDBConnector.findRef(listOf(getString(R.string.users), auth.uid!!, getString(R.string.scores))), true){
                    for (i in it.children)
                        //Add to a list
                        _listData.value = ArrayList<DataSnapshot>().apply {
                            addAll(_listData.value!!)
                            add(i)
                        }
                }
            }
        }
    }

}