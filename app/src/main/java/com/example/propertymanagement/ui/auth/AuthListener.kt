package com.example.propertymanagement.ui.auth

import androidx.lifecycle.LiveData
import com.example.propertymanagement.data.models.UserResponse

interface AuthListener {

    fun onStarted()
    fun onSuccess(response: LiveData<UserResponse>)
    fun failure(message: String)
}