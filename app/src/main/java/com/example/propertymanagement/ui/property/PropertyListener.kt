package com.example.propertymanagement.ui.property

import androidx.lifecycle.LiveData
import com.example.propertymanagement.data.models.PropertyResponse

interface PropertyListener {

    fun onStarted()
    fun onSuccess(response: LiveData<PropertyResponse>)
    fun onFailure(message: String)
}