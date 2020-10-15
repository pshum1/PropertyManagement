package com.example.propertymanagement.ui.start

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.propertymanagement.ui.auth.activities.LoginActivity
import com.example.propertymanagement.R
import com.example.propertymanagement.ui.auth.activities.RegisterActivity

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }

    private fun init() {
        button_register.setOnClickListener(this)
        button_login.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            button_register -> startActivity(Intent(this, RegisterActivity::class.java))
            button_login -> startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}