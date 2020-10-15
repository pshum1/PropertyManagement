package com.example.propertymanagement.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.propertymanagement.R
import com.example.propertymanagement.helpers.UserSessionManager
import com.example.propertymanagement.ui.home.LandingActivity

class SplashActivity : AppCompatActivity() {

    private val delayedTime: Long = 1500
    lateinit var sessionManager: UserSessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sessionManager = UserSessionManager(this)

        Handler(Looper.getMainLooper()).postDelayed({
            checkLogin()
        }, delayedTime)
    }

    private fun checkLogin() {
        var intent = if(sessionManager.isLoggedIn()){
            Intent(this, LandingActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}