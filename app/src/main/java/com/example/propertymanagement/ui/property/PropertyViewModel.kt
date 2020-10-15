package com.example.propertymanagement.ui.property

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.data.models.ImageDetails
import com.example.propertymanagement.data.models.Property
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.repositories.PropertyRepository


class PropertyViewModel : ViewModel() {

    var id: String? = User.KEY_USER_ID
    var propertyStatus: Boolean? = null
    var mortgageInfo: Boolean? = null
    var address: String? = null
    var city: String? = null
    var state: String? = null

    var propertyListener: PropertyListener? = null

    fun onAddPropertyButtonClick(view: View) {

        val img = ImageDetails.PROPERTY_IMAGE_PATH

        val property = Property(_id = id, address = address, city = city, state = state, image = img)

        Log.d("property", "property $property")

        val addPropertyResponse = PropertyRepository().addProperty(property)
        propertyListener?.onSuccess(addPropertyResponse)

    }


}