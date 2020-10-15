package com.example.propertymanagement.data.models

data class TenantResponse(
    val count: Int,
    val `data`: ArrayList<Tenant>,
    val error: Boolean
)

data class Tenant(
    val __v: Int,
    val _id: String,
    val email: String,
    val mobile: String,
    val name: String
)