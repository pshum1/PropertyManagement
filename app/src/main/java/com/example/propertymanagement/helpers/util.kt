package com.example.propertymanagement.helpers

import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toolbar

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.d(message: String){
    Log.d("logdebug", message)
}


