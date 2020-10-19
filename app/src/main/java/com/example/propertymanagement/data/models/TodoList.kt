package com.example.propertymanagement.data.models

data class TodoList (
    val title: String? = null,
    val description: String? = null,
){

    companion object{
        const val COLLECTION_NAME = "todo"
    }
}