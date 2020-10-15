package com.example.propertymanagement.data.repositories

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagement.data.models.TenantResponse
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.models.UserResponse
import com.example.propertymanagement.data.networks.PropertyManagementAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    var propertyApi = PropertyManagementAPI()

    //REGISTER POST REQUEST

    fun register(user: User): LiveData<UserResponse>{

        Log.d("responseRegister", "user: $user")
        val registerResponse = MutableLiveData<UserResponse>()

        propertyApi.register(user)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    Log.d("responseRegister", "body " + response.body().toString())

                    if(response.isSuccessful){
                        registerResponse.value = response.body()
                        Log.d("responseRegister", registerResponse.value.toString())
                    } else {
                        val tempUserResponse = UserResponse(error = true, message = response.message())
                        registerResponse.value = tempUserResponse
                        Log.d("responseRegister", "error " + registerResponse.value.toString())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                }

            })

        return registerResponse

    }

    //LOGIN POST REQUEST

    fun login(email: String, password: String): LiveData<UserResponse> {

        val user = User(email = email, password =  password)
        val loginResponse = MutableLiveData<UserResponse>()

        propertyApi.login(user)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {


                    if (response.isSuccessful) {
                        loginResponse.value = response.body()
                        Log.d("responseLogin", loginResponse.value.toString())
                    } else {
                        val tempUserResponse = UserResponse(error = true, message = response.message())
                        loginResponse.value = tempUserResponse
                        Log.d("responseLogin", "error " + loginResponse.value.toString())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                }

            })


        return loginResponse
    }

    @SuppressLint("CheckResult")
    fun getTenants(): LiveData<TenantResponse>{
        Log.d("tenantResponse", "getting Tenants")
        var tenantResponse = MutableLiveData<TenantResponse>()
        propertyApi.getTenants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<TenantResponse>(){
                override fun onSuccess(t: TenantResponse) {

                    tenantResponse.value = t
                    Log.d("tenantResponse", "response" + tenantResponse.value)
                }

                override fun onError(e: Throwable) {

                }

            })

        return tenantResponse

    }
}