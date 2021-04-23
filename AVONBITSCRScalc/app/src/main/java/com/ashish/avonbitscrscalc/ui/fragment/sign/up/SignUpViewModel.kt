package com.ashish.avonbitscrscalc.ui.fragment.sign.up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.SignUpFragmentBinding
import com.ashish.avonbitscrscalc.model.connectors.AuthDBConnector
import com.ashish.avonbitscrscalc.model.connectors.RealTimeDBConnector
import com.ashish.avonbitscrscalc.model.managers.check
import com.ashish.avonbitscrscalc.model.managers.textS
import com.ashish.avonbitscrscalc.ui.activities.sign.SignActivity.Companion.navControllerS
import com.google.firebase.auth.FirebaseAuthException
import org.jetbrains.anko.longToast

class SignUpViewModel : ViewModel() {

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> get() = _progress

    val signUp = { b: SignUpFragmentBinding ->
        with(b){
            with(b.root.context){
                //Check the data
                if (name.check() && email.check() && password.check() && configPassword.check()){
                    //Validate the password
                    if (password.textS().toCharArray().size <= 6) root.context.longToast(root.context.getString(R.string.mix_six_characters_password_view))
                    else{
                        //Confirm the password
                        if (password.textS() != configPassword.textS()) root.context.longToast(root.context.getString(R.string.not_equals_password_view))
                        else{
                            //Change the progress
                            _progress.value = true
                            //Create a account
                            AuthDBConnector.signUp(email.textS(), password.textS()).addOnCompleteListener { task ->
                                //If its okay, save the data
                                if (task.isComplete) RealTimeDBConnector.findRef(listOf(root.context.getString(R.string.users))).child(task.result!!.user!!.uid).child(getString(R.string.name)).setValue(name.textS()).addOnCompleteListener { navControllerS.popBackStack() }
                                else
                                    //If its bad, show the error
                                    with(b.root.context){
                                        when ((task.exception as FirebaseAuthException).errorCode) {
                                            in arrayListOf(getString(R.string.email_AuthDBConnector_error), getString(R.string.credential_AuthDBConnector_error), getString(R.string.user_mismatch_AuthDBConnector_error), getString(R.string.user_not_found_AuthDBConnector_error)) -> longToast(getString(R.string.error_email_view))
                                            in arrayListOf(getString(R.string.other_password_AuthDBConnector_error), getString(R.string.password_AuthDBConnector_error), getString(R.string.password2_AuthDBConnector_error)) -> longToast(getString(R.string.error_password_view))
                                            else -> longToast(getString(R.string.AuthDBConnector_error_message))
                                        }
                                        //Change the progress
                                        _progress.value = false
                                    }
                            }
                        }
                    }
                }
            }
        }
    }

}