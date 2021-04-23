package com.ashish.avonbitscrscalc.ui.fragment.menu.my_account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ashish.avonbitscrscalc.databinding.MyAccountFragmentBinding
import com.ashish.avonbitscrscalc.model.connectors.AuthDBConnector
import com.ashish.avonbitscrscalc.ui.activities.sign.SignActivity
import com.ashish.avonbitscrscalc.ui.activities.sign.SignActivity.Companion.finish

class MyAccountFragment : Fragment() {

    private lateinit var binding: MyAccountFragmentBinding
    private val viewModel: MyAccountViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MyAccountFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            //Get user data
            viewModel.getData(this)

            //Sign out
            signOut.setOnClickListener {
                AuthDBConnector.signOut(this@MyAccountFragment.requireContext()).addOnCompleteListener {
                    startActivity(Intent(this@MyAccountFragment.requireContext(), SignActivity::class.java))
                    finish()
                }
            }

        }

    }

}