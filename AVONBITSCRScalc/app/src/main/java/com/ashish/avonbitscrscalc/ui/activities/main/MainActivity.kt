package com.ashish.avonbitscrscalc.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{

        lateinit var navControllerM: NavController

        var home = true

    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get Navigation Controller
        navControllerM = Navigation.findNavController(this@MainActivity, R.id.f_main)

        with(binding){

            //Config side menu
            NavigationUI.setupWithNavController(binding.navViewMain, Navigation.findNavController(this@MainActivity, R.id.f_main))

            //Config the button to oppen the side menu
            val toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayoutMain, binding.appBarMain.toolbarMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            drawerLayoutMain.addDrawerListener(toggle)
            toggle.syncState()

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(!home) navControllerM.navigate(R.id.nav_home)
        else finish()
    }

}