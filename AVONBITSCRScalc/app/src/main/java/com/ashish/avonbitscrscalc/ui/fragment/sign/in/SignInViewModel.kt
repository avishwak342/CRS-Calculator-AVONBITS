package com.ashish.avonbitscrscalc.ui.fragment.sign.`in`

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.SignInFragmentBinding
import com.ashish.avonbitscrscalc.model.connectors.AuthDBConnector
import com.ashish.avonbitscrscalc.model.managers.check
import com.ashish.avonbitscrscalc.model.managers.errorFocus
import com.ashish.avonbitscrscalc.model.managers.textS
import com.ashish.avonbitscrscalc.ui.activities.main.MainActivity
import com.ashish.avonbitscrscalc.ui.activities.sign.SignActivity
import com.google.firebase.auth.FirebaseAuthException
import org.jetbrains.anko.longToast

class SignInViewModel : ViewModel() {

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> get() = _progress

    val signIn = { b: SignInFragmentBinding ->
        with(b){
            with(root.context){
                //Check the email and password
                if (email.check() && password.check()){
                    //Change the progress
                    _progress.value = true
                    //Sign in with email and password
                    AuthDBConnector.signIn(email.textS(), password.textS()).addOnCompleteListener { task ->
                        //If its okay
                        if (task.isComplete && task.isSuccessful){
                            //Start MainActivity and finish SignInActivity
                            startActivity(Intent(this, MainActivity::class.java))
                            SignActivity.finish()
                        }
                        else
                            //If its bad
                            task.exception?.let {
                                //Show the error
                                if (it::class.java == FirebaseAuthException::class.java)
                                    when ((it as FirebaseAuthException).errorCode) {
                                        in arrayListOf(getString(R.string.email_AuthDBConnector_error), getString(R.string.credential_AuthDBConnector_error), getString(R.string.user_mismatch_AuthDBConnector_error), getString(R.string.user_not_found_AuthDBConnector_error)) -> email.errorFocus(getString(R.string.error_email_view))
                                        in arrayListOf(getString(R.string.other_password_AuthDBConnector_error), getString(R.string.password_AuthDBConnector_error), getString(R.string.password2_AuthDBConnector_error)) -> password.errorFocus(getString(R.string.error_password_view))
                                        else -> longToast(getString(R.string.AuthDBConnector_error_message))
                                    }
                                else longToast(getString(R.string.AuthDBConnector_error_message))
                            }

                        //Change the progress
                        _progress.value = false
                    }
                }
            }
        }
    }

}