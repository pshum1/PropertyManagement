package com.example.propertymanagement.data.networks

import android.util.Log
import com.example.propertymanagement.app.Config
import com.example.propertymanagement.data.models.User
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object APIWorker {

    private const val REQUEST_TIMEOUT: Long = 60
    private var okHttpClient: OkHttpClient? = null

    val client: OkHttpClient
        get() {
            if (okHttpClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                var httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .addInterceptor(object : Interceptor {
                        override fun intercept(chain: Interceptor.Chain): Response {
                            val original = chain!!.request()
                            val requestBuilder = original.newBuilder()
                                .addHeader(
                                    "Authorization",
                                    "Bearer ${User.TOKEN}"
                                )
                                .addHeader("Accept", "application/json")
                                .addHeader("Content-Type", "application/json")
                            val request = requestBuilder.build()
                            return chain.proceed(request)
                        }

                    })
                okHttpClient = httpBuilder.build()
            }
            Log.d("OkHttpLogger", okHttpClient.toString())
            return okHttpClient!!
        }
}