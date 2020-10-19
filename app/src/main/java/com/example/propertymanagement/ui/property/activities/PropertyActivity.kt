package com.example.propertymanagement.ui.property.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagement.R
import com.example.propertymanagement.data.models.Property
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.repositories.PropertyRepository
import com.example.propertymanagement.ui.property.adapters.AdapterProperty
import kotlinx.android.synthetic.main.activity_property.*
import kotlinx.android.synthetic.main.app_bar.*
import java.util.*
import kotlin.collections.ArrayList

class PropertyActivity : AppCompatActivity() {

    var adapterProperty: AdapterProperty? = null
    //var mList: ArrayList<Property> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)

        init()
    }

    private fun init() {
        adapterProperty = AdapterProperty(ArrayList())
        recycler_view_properties.layoutManager = LinearLayoutManager(this)
        recycler_view_properties.adapter = adapterProperty
        getData()
        setupToolbar()

        button_add_floating_property.setOnClickListener{
            startActivity(Intent(this, AddPropertyActivity::class.java))
        }
    }

    private fun getData() {
        PropertyRepository().getProperty(User.KEY_USER_ID!!).observe(this, {
            Log.d("GetProperties", "Properties: " + it.data.toString())

            adapterProperty?.setData(it.data!!)
        })
    }

    private fun setupToolbar() {
        var toolbar = toolbar_general
        toolbar.title = "Property List"
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