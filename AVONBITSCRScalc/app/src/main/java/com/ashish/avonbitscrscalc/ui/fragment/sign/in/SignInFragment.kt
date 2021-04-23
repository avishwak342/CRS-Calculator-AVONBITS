package com.ashish.avonbitscrscalc.ui.fragment.sign.`in`

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ashish.avonbitscrscalc.R
import com.ashish.avonbitscrscalc.databinding.SignInFragmentBinding
import com.ashish.avonbitscrscalc.ui.activities.sign.SignActivity


class SignInFragment : Fragment() {

    private lateinit var binding: SignInFragmentBinding
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SignInFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            //Sign in
            signIn.setOnClickListener {
                (root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(binding.root.windowToken, 0)
                viewModel.signIn(binding)
            }

            //Go to Sign Up
            createAccount.setOnClickListener { SignActivity.navControllerS.navigate(R.id.signUpFragment) }

            //Show the progress
            viewModel.progress.observe(viewLifecycleOwner){
                pb.visibility = if (it) View.VISIBLE else View.GONE
            }

        }
    }
}