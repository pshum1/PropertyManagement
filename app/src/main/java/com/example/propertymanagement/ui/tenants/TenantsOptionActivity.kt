package com.example.propertymanagement.ui.tenants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.propertymanagement.R
import kotlinx.android.synthetic.main.activity_tenants_option.*
import kotlinx.android.synthetic.main.app_bar.*

class TenantsOptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenants_option)

        init()

    }

    private fun init() {
        setupToolbar()

        button_all_tenants.setOnClickListener{
            startActivity(Intent(this, TenantsActivity::class.java))
        }
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