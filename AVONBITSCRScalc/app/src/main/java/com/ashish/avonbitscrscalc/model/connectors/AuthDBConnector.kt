package com.ashish.avonbitscrscalc.model.connectors

import android.content.Context
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AuthDBConnector {

    var myAuth = Firebase.auth

    private val myAuthDBConnectorUI = AuthUI.getInstance()

    val userAwait = { scope: CoroutineScope?, unit: (it: FirebaseAuth) -> Unit ->
        lateinit var remove: () -> Unit

        val stateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser != null){
                if (scope != null) scope.launch { unit(it) }
                else unit(it)
                remove()
            }
        }

        remove = { myAuth.removeAuthStateListener(stateListener) }

        myAuth.addAuthStateListener(stateListener)
    }

    val facebookCredential = { token: String -> FacebookAuthProvider.getCredential(token) }

    val googleCredential = { token: String -> GoogleAuthProvider.getCredential(token, null) }

    val checkUser =  { myAuth.addAuthStateListener { if (it.currentUser != null) myAuth.updateCurrentUser(it.currentUser!!) } }

    val signIn: (String, String) -> Task<AuthResult> = { email: String, password: String -> myAuth.signInWithEmailAndPassword(email, password) }

    val signUp: (String, String) -> Task<AuthResult> = { email: String, password: String -> myAuth.createUserWithEmailAndPassword(email, password)}

    val signInWitToken: (AuthCredential) -> Task<AuthResult> = { token: AuthCredential -> myAuth.signInWithCredential(token) }

    val signOut = { context: Context -> myAuthDBConnectorUI.signOut(context) }

    val resetPassword = { email: String -> myAuth.sendPasswordResetEmail(email) }

}