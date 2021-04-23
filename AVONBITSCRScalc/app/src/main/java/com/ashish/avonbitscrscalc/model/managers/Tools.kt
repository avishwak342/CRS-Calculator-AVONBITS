package com.ashish.avonbitscrscalc.model.managers

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.dynamicfeatures.DynamicExtras
import androidx.navigation.dynamicfeatures.DynamicInstallMonitor
import com.ashish.avonbitscrscalc.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.play.core.splitinstall.f
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.longToast
import java.io.ByteArrayOutputStream
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


val FragmentManager.navigate: (Int, Fragment) -> Unit
    get() = { int, fragment ->
        with(this.beginTransaction()){
            replace(int, fragment)
            addToBackStack(null)
            commit()
        }
    }

/* EDIT TEXT */
//Check if editext is not empty

val EditText.check: () -> Boolean
    get() = {
        if (this.text.isEmpty()){
            this.error = this.context.getString(R.string.complete_this)
            this.requestFocus()
            false
        }
        else true
    }

//Get text in String
val EditText.textS: () -> String
    get() = { this.text.toString() }

//Set an Error
val EditText.errorFocus: ( String) -> Unit
    get() = {
        this.requestFocus()
        this.error = it
    }

/* TEXT VIEW */

val TextView.setCounter: (Float, CoroutineScope) -> Unit
    get() = { counter: Float, scope: CoroutineScope ->
        scope.launch {
            var counterSecondary = 0

            while (counterSecondary + 10 < counter){
                delay(50)
                this@setCounter.text = counter.toInt().toString()
                counterSecondary += 5
            }

            this@setCounter.text = counter.toInt().toString()

        }

    }