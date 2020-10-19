package com.example.propertymanagement.data.models

data class PropertyResponse(
    val `data`: Property? = null,
    val error: Boolean,
    val message: String
)

data class GetPropertyResponse(
    val `data`: ArrayList<Property>? = null,
    val error: Boolean,
)

data class Property(
    val _id: String? = null,
    val address: String? = null,
    val city: String? = null,
    val country: String? = null,
    val image: String? = null,
    val mortageInfo: Boolean? = null,
    val propertyStatus: Boolean? = null,
    val purchasePrice: String? = null,
    val state: String? = null
)