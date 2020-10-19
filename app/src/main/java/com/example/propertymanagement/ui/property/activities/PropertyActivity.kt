package com.example.propertymanagement.ui.property.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagement.R
import com.example.propertymanagement.data.models.Property
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.repositories.PropertyRepository
import com.example.propertymanagement.ui.property.adapters.AdapterProperty
import kotlinx.android.synthetic.main.activity_property.*
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
    }

    private fun getData() {
        PropertyRepository().getProperty(User.KEY_USER_ID!!).observe(this, {
            Log.d("GetProperties", "Properties: " + it.data.toString())

            adapterProperty?.setData(it.data!!)
        })
    }

}