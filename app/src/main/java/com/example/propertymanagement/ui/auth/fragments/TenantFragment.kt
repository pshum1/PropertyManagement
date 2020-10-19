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
import com.example.propertymanagement.databinding.FragmentTenantBinding
import com.example.propertymanagement.helpers.d
import com.example.propertymanagement.helpers.toast
import com.example.propertymanagement.ui.auth.AuthListener
import com.example.propertymanagement.ui.auth.AuthViewModel


class TenantFragment : Fragment(), AuthListener {

    lateinit var binding: FragmentTenantBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tenant, container, false)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.authVM = viewModel
        viewModel.authListener = this

        //return the layout for fragment
        return binding.root
    }

    override fun onStarted() {
        activity!!.d("onStart TenantFragment")
    }

    override fun onSuccess(response: LiveData<UserResponse>) {
        activity!!.d("onSuccess TenantFragment")

        response.observe(this, Observer {
            if (response.value?.error == true) {
                activity!!.toast(response.value?.message!!)
                binding.etRegisterEmail.text?.clear()
                binding.etRegisterPassword.text?.clear()
                binding.etRegisterConfirmPassword.text?.clear()
            } else {
                activity!!.toast("Registration Successful!")
                activity!!.finish()
            }
        })
    }

    override fun failure(message: String) {
        activity!!.d("onFailure TenantFragment")
        activity!!.toast(message)
        binding.etRegisterPassword.text.clear()
        binding.etRegisterConfirmPassword.text.clear()
    }

}