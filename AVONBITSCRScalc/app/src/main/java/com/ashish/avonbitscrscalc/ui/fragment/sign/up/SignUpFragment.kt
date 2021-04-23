package com.ashish.avonbitscrscalc.ui.fragment.sign.up

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import com.ashish.avonbitscrscalc.databinding.SignUpFragmentBinding
import org.jetbrains.anko.sdk27.coroutines.onFocusChange

class SignUpFragment : Fragment() {

    private lateinit var binding: SignUpFragmentBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SignUpFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            //Dismiss the keyboard
            createAccount.onFocusChange { _ , hasFocus -> if (hasFocus) (root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(binding.root.windowToken, 0) }

            //Sign Up
            createAccount.setOnClickListener { viewModel.signUp(binding) }

            //Show the progress
            viewModel.progress.observe(viewLifecycleOwner){
                pb.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

    }

}