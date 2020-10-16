
package com.example.propertymanagement.ui.tenants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.propertymanagement.R
import com.example.propertymanagement.data.networks.PropertyManagementAPI
import com.example.propertymanagement.data.repositories.UserRepository
import kotlinx.android.synthetic.main.activity_tenants.*

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

        val tenants = UserRepository().getTenants()

        tenants.observe(this, Observer {
            tv_tenants.text = "Tenant count: ${tenants.value?.count}"
        })

    }
}