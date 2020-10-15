package com.example.propertymanagement.data.models

data class PropertyImageResponse(
    val `data`: ImageDetails,
    val error: Boolean,
    val message: String
)

data class ImageDetails(
    val acl: String,
    val bucket: String,
    val contentDisposition: Any,
    val contentType: String,
    val encoding: String,
    val etag: String,
    val fieldname: String,
    val key: String,
    val location: String,
    val metadata: Metadata,
    val mimetype: String,
    val originalname: String,
    val serverSideEncryption: Any,
    val size: Int,
    val storageClass: String
) {
    companion object{
        const val KEY_IMAGE_NAME = "image"
        const val KEY_IMAGE_TYPE = "image/jpeg"
        var PROPERTY_IMAGE_PATH: String? = null
    }
}

data class Metadata(
    val fieldName: String
)