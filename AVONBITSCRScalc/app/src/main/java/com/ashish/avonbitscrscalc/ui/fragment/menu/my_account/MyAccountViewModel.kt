package com.ashish.avonbitscrscalc.ui.fragment.menu.my_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.MyAccountFragmentBinding
import com.ashish.avonbitscrscalc.model.connectors.AuthDBConnector
import com.ashish.avonbitscrscalc.model.connectors.RealTimeDBConnector
import com.ashish.avonbitscrscalc.model.connectors.getS

class MyAccountViewModel : ViewModel() {

    val getData = { b: MyAccountFragmentBinding ->
        with(b.root.context){
            with(b){
                //Wait the user
                AuthDBConnector.userAwait(viewModelScope){ auth ->
                    //Get the user Ref
                    RealTimeDBConnector.getItem(RealTimeDBConnector.findRef(listOf(getString(R.string.users), auth.currentUser!!.uid)), true){
                        //Show the data
                        name.text = it.getS(getString(R.string.name))
                        email.text = auth.currentUser!!.email

                    }
                }
            }
        }

    }
}