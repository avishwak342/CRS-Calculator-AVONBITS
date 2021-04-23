package com.ashish.avonbitscrscalc.ui.fragment.complete

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.model.connectors.AuthDBConnector
import com.ashish.avonbitscrscalc.model.connectors.RealTimeDBConnector
import com.ashish.avonbitscrscalc.model.data_class.NewScoreDataClass
import com.ashish.avonbitscrscalc.model.managers.PointsManager
import com.ashish.avonbitscrscalc.model.managers.setCounter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CompleteViewModel : ViewModel() {

    val upload = { score: TextView ->
        with(score.context){

            //Get Result
            val scoreInt = PointsManager.getResult()

            //Show in screen
            score.setCounter(scoreInt.toFloat(), viewModelScope)

            //Wait the user
            AuthDBConnector.userAwait(viewModelScope){ auth ->
                //Find a user Ref
                with(RealTimeDBConnector.findRef(listOf(getString(R.string.users), auth.currentUser!!.uid, getString(R.string.scores)))){
                    //Create a id
                    val id = this.push().key!!

                    //Save the result and the date in the user ref and id
                    this.child(id).setValue(NewScoreDataClass(scoreInt, SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())))

                    //Restart points
                    PointsManager.restart()
                }
            }
        }
    }

}