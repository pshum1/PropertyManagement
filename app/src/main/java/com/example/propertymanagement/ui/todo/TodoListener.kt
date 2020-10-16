package com.example.propertymanagement.ui.todo

import com.example.propertymanagement.data.models.TodoList

interface TodoListener {

    fun onStarted()
    fun onSuccess(list: ArrayList<TodoList>)
    fun onUpdate(update: ArrayList<TodoList>)
    fun onFailure(message: String)
    fun onComplete()
}