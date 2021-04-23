package com.ashish.avonbitscrscalc.ui.activities.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    lateinit var viewmodel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel = ViewModelProvider(this).get(SplashViewModel::class.java)

        //animate the logo
        binding.logo.animation = AnimationUtils.loadAnimation(this, R.anim.show)

        //Go to SignActivity or MainActivity
        viewmodel.next(this) { finish() }

    }



}