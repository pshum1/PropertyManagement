package com.example.propertymanagement.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.repositories.UserRepository

class AuthViewModel: ViewModel() {

    var name: String? = null
    var email: String? = null
    var landlordEmail: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    var authListener: AuthListener? = null

    // REGISTER ACTIVITY

    fun onRegisterButtonTenantClick(view: View){
        if(registerCheck()){
            val user = User(name = name, email = email, landlordEmail = landlordEmail, password = password, type = "tenant")
            val registerResponse = UserRepository().register(user)
            authListener?.onSuccess(registerResponse)
        }
        return
    }

    fun onRegisterButtonLandlordClick(view: View){
        if(registerCheck()){
            val user = User(name = name, email = email, password = password, type = "landlord")
            val registerResponse = UserRepository().register(user)
            authListener?.onSuccess(registerResponse)
        }
        return
    }

    // CHECK IF PASSWORDS MATCH
    private fun registerCheck(): Boolean {
        authListener?.onStarted()
        if(password != confirmPassword){
            authListener?.failure("Passwords do not match")
            return false
        }
        return true
    }

    // LOGIN ACTIVITY

    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            //failure
            authListener?.failure("Please enter email/password")
            return
        }
        //success
        val loginResponse = UserRepository().login(email!!, password!!)

        authListener?.onSuccess(loginResponse)
    }




}