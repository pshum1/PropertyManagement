
package com.example.propertymanagement.ui.tenants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.propertymanagement.R
import com.example.propertymanagement.data.networks.PropertyManagementAPI
import com.example.propertymanagement.data.repositories.UserRepository
import kotlinx.android.synthetic.main.activity_tenants.*
import kotlinx.android.synthetic.main.app_bar.*

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

        setupToolbar()

        getData()
    }

    private fun getData() {

        val tenants = UserRepository().getTenants()

        tenants.observe(this, Observer {
            tv_tenants.text = "Count: ${tenants.value?.count}"
        })

    }

    private fun setupToolbar() {
        var toolbar = toolbar_general
        toolbar.title = "Tenants"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return true
    }
}