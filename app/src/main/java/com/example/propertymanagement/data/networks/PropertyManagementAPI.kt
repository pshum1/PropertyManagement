package com.example.propertymanagement.data.networks

import com.example.propertymanagement.app.Config
import com.example.propertymanagement.app.Endpoints
import com.example.propertymanagement.data.models.*
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface PropertyManagementAPI {

    @POST(Endpoints.URL_REGISTER)// REGISTER
    fun register(@Body user: User): Call<UserResponse>

    @POST(Endpoints.URL_LOGIN) // LOGIN
    fun login(@Body user: User): Call<UserResponse>

    @GET(Endpoints.URL_TENANT) // GET TENANTS
    fun getTenants(): Single<TenantResponse>

    @Multipart
    @POST(Endpoints.URL_UPLOAD_IMAGE) //UPLOAD A IMAGE
    fun uploadImage(
        @Part image: MultipartBody.Part,
    ): Call<PropertyImageResponse>

    @POST(Endpoints.URL_ADD_PROPERTY)
    fun addProperty(@Body property: Property): Call<PropertyResponse>

    companion object {
        operator fun invoke(): PropertyManagementAPI {
            return Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(APIWorker.client)
                .build()
                .create(PropertyManagementAPI::class.java)
        }
    }
}