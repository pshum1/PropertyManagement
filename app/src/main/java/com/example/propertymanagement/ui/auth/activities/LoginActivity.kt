package com.example.propertymanagement.ui.auth.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagement.R
import com.example.propertymanagement.app.Config
import com.example.propertymanagement.data.models.UserResponse
import com.example.propertymanagement.databinding.ActivityLoginBinding
import com.example.propertymanagement.helpers.UserSessionManager
import com.example.propertymanagement.helpers.toast
import com.example.propertymanagement.ui.auth.AuthListener
import com.example.propertymanagement.ui.auth.AuthViewModel
import com.example.propertymanagement.ui.home.LandingActivity

class LoginActivity : AppCompatActivity(),
    AuthListener {

    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.authVM = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        binding.progressBarLogin.visibility = View.VISIBLE
    }

    override fun onSuccess(response: LiveData<UserResponse>) {
        //this.toast("onSuccess")

        response.observe(this, Observer{
            if(response.value?.error == true){
                this.toast(response.value?.message!!)
                binding.progressBarLogin.visibility = View.INVISIBLE
            } else {
                val sessionManager = UserSessionManager(this)
                sessionManager.saveUserInput(response.value!!)
                startActivity(Intent(this, LandingActivity::class.java))
                finish()
            }
        })
    }

    override fun failure(message: String) {
        this.toast(message)
    }
}