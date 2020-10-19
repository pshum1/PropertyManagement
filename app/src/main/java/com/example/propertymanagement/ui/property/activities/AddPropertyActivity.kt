package com.example.propertymanagement.ui.property.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagement.R
import com.example.propertymanagement.data.models.GetPropertyResponse
import com.example.propertymanagement.data.models.PropertyResponse
import com.example.propertymanagement.databinding.ActivityAddPropertyBinding
import com.example.propertymanagement.helpers.toast
import com.example.propertymanagement.ui.property.PropertyListener
import com.example.propertymanagement.ui.property.PropertyViewModel
import com.example.propertymanagement.ui.property.fragments.BottomSheetFragment

class AddPropertyActivity : AppCompatActivity(), PropertyListener {

    lateinit var binding: ActivityAddPropertyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_property)
        val viewModel = ViewModelProviders.of(this).get(PropertyViewModel::class.java)

        binding.propertyVM = viewModel
        viewModel.propertyListener = this

        init()
    }

    private fun init() {
        binding.buttonAddImage.setOnClickListener {
            val bottomSheetFragment =
                BottomSheetFragment()

            bottomSheetFragment.show(supportFragmentManager, "BottomSheetDialog")
        }

    }

    override fun onStarted() {

    }

    override fun onSuccess(response: LiveData<PropertyResponse>) {
        response.observe(this, Observer {
            this.toast(response.value?.message!!)
        })
    }

    override fun onFailure() {

    }


}