package com.example.propertymanagement.ui.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagement.R
import com.example.propertymanagement.data.models.UserResponse
import com.example.propertymanagement.databinding.FragmentLandlordBinding
import com.example.propertymanagement.helpers.toast
import com.example.propertymanagement.ui.auth.AuthListener
import com.example.propertymanagement.ui.auth.AuthViewModel

class LandlordFragment : Fragment(), AuthListener {

    lateinit var binding: FragmentLandlordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landlord, container, false)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.authVM = viewModel
        viewModel.authListener = this

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onStarted() {

    }

    override fun onSuccess(response: LiveData<UserResponse>) {

        response.observe(this, Observer {
            if (response.value?.error == true) {
                activity!!.toast(response.value?.message!!)
                binding.etRegisterEmail.text.clear()
                binding.etRegisterPassword.text.clear()
                binding.etRegisterConfirmPassword.text.clear()
            } else {
                activity!!.toast("Registration Successful!")
                activity!!.finish()
            }
        })
    }

    override fun failure(message: String) {

    }

}