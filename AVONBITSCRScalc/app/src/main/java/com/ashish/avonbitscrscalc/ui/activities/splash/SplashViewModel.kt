package com.ashish.avonbitscrscalc.ui.activities.splash

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.avonbitscrscalc.model.connectors.AuthDBConnector
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import com.ashish.avonbitscrscalc.ui.activities.sign.SignActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel:ViewModel() {

    val next = { context: Context, unit: () -> Unit ->
        with(context){
            //Open a coroutine
            viewModelScope.launch {
                //Wait 3 s
                delay(3000)
                //If user not exist go to Sign in else go to Main
                startActivity(Intent(context, if(AuthDBConnector.myAuth.currentUser != null) MainActivity::class.java else SignActivity::class.java))
                //Finish splash activity
                unit()
            }
        }
    }

}