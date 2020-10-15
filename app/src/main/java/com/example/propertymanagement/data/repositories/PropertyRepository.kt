package com.example.propertymanagement.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagement.data.models.ImageDetails
import com.example.propertymanagement.data.models.Property
import com.example.propertymanagement.data.models.PropertyImageResponse
import com.example.propertymanagement.data.models.PropertyResponse
import com.example.propertymanagement.data.networks.PropertyManagementAPI
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropertyRepository {

    var propertyApi = PropertyManagementAPI()

    //POST UPLOAD IMAGE
    fun uploadImage(uri: MultipartBody.Part) {

        propertyApi.uploadImage(uri)
            .enqueue(object : Callback<PropertyImageResponse> {
                override fun onResponse(
                    call: Call<PropertyImageResponse>,
                    response: Response<PropertyImageResponse>
                ) {
                    if (response.isSuccessful) {
                        ImageDetails.PROPERTY_IMAGE_PATH =
                            response.body()?.data?.location.toString()
                        Log.d("uploadImageResponse", "location " + ImageDetails.PROPERTY_IMAGE_PATH)
                    } else {
                        Log.d("uploadImageResponse", "error " + response.message().toString())
                    }
                }


                override fun onFailure(call: Call<PropertyImageResponse>, t: Throwable) {
                    Log.d("uploadImageResponse", "error " + t.message)
                }


            })
    }

    //POST PROPERTY
    fun addProperty(property: Property): LiveData<PropertyResponse> {

        val propertyResponse = MutableLiveData<PropertyResponse>()

        propertyApi.addProperty(property)
            .enqueue(object : Callback<PropertyResponse> {
                override fun onFailure(call: Call<PropertyResponse>, t: Throwable) {
                    Log.d("uploadImageResponse", "error " + t.message)
                }

                override fun onResponse(
                    call: Call<PropertyResponse>,
                    response: Response<PropertyResponse>
                ) {
                    if(response.isSuccessful){
                        propertyResponse.value = response.body()
                    }else{
                        val tempPropertyResponse = PropertyResponse(error = true, message = response.message())
                        propertyResponse.value = tempPropertyResponse
                    }
                }

            })
        return propertyResponse
    }
}