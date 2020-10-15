package com.example.propertymanagement.ui.auth.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.propertymanagement.R
import com.example.propertymanagement.ui.auth.adapters.AdapterTabViewPager
import com.example.propertymanagement.ui.auth.fragments.LandlordFragment
import com.example.propertymanagement.ui.auth.fragments.TenantFragment
import kotlinx.android.synthetic.main.activity_auth.*

class RegisterActivity : AppCompatActivity() {

    private var authAdapter: AdapterTabViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        //Setup tab layout and view pager
        authAdapter = AdapterTabViewPager(supportFragmentManager)
        view_pager.adapter = authAdapter
        tab_layout.setupWithViewPager(view_pager)

        //Set Fragments to adapter
        setFragments()

    }

    private fun setFragments() {
        authAdapter?.addFragments(TenantFragment(), "Tenant")
        authAdapter?.addFragments(LandlordFragment(), "Landlord")
    }

}
