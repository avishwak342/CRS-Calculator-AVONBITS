package com.ashish.avonbitscrscalc.ui.activities.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {

    companion object{

        lateinit var navControllerS: NavController

        lateinit var finish: () -> Unit

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        finish = { finish() }

        //Get the navigation controller
        navControllerS = Navigation.findNavController(this, R.id.f_sign)

    }

}