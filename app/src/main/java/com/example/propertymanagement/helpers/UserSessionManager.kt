package com.example.propertymanagement.helpers

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.models.UserResponse

class UserSessionManager (context: Context){

    private val FILE_NAME = "user_pref"
    private val KEY_TOKEN = "token"
    private val KEY_ID = "id"
    private val KEY_NAME = "name"
    private val KEY_EMAIL = "email"
    private val KEY_TYPE = "type"

    var sharePreferences: SharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharePreferences.edit()


    fun saveUserInput(userResponse: UserResponse){
        val user = userResponse.user
        editor.putString(KEY_TOKEN, userResponse.token)
        editor.putString(KEY_ID, user?._id)
        editor.putString(KEY_NAME, user?.name)
        editor.putString(KEY_EMAIL, user?.email)
        editor.putString(KEY_TYPE, user?.type)
        editor.commit()
    }

    fun isLoggedIn(): Boolean{
        var loggedIn = sharePreferences.getString(KEY_TOKEN, null)
        return if(loggedIn != null){
            Log.d("loginStatus", loggedIn)
            true
        } else {
            Log.d("loginStatus", "nothing here")
            false
        }
    }

    fun getUser(): User?{
        val name = sharePreferences.getString(KEY_NAME, null)
        val email = sharePreferences.getString(KEY_EMAIL, null)
        return User(name = name, email = email)
    }

    fun getToken(): String?{
        return sharePreferences.getString(KEY_TOKEN, null)
    }

    fun getId(): String?{
        return sharePreferences.getString(KEY_ID, null)
    }

    fun logout(){
        editor.clear()
        editor.commit()
    }

    companion object{

    }

}
