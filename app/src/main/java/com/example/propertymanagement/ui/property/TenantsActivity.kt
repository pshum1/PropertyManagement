
package com.example.propertymanagement.ui.property

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.propertymanagement.R
import com.example.propertymanagement.data.networks.PropertyManagementAPI
import com.example.propertymanagement.data.repositories.UserRepository

class TenantsActivity : AppCompatActivity() {

    lateinit var propertyApi: PropertyManagementAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenants)

        propertyApi = PropertyManagementAPI()

        init()
    }

    private fun init() {
        Log.d("tenantResponse", "Init")

        getData()
    }

    private fun getData() {
        Log.d("tenantResponse", "Getting Data")
        var tenants = UserRepository().getTenants()
    }
}