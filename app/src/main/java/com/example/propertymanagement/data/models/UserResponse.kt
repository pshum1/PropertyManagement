package com.example.propertymanagement.data.models

data class UserResponse(
    val token: String? = null,
    val user: User? = null,
    val error: Boolean? = null,
    val message: String? = null,
)

data class User(
    val _id: String? = null,
    val email: String? = null,
    val landlordEmail: String? = null,
    val name: String? = null,
    val password: String? = null,
    val type: String? = null
) {
    companion object{
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"
        var KEY_USER_ID: String? = null
        var TOKEN: String? = null
    }
}

