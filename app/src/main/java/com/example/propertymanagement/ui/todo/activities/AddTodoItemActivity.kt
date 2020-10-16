package com.example.propertymanagement.ui.todo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagement.R
import com.example.propertymanagement.data.models.TodoList
import com.example.propertymanagement.databinding.ActivityAddTodoItemBinding
import com.example.propertymanagement.helpers.toast
import com.example.propertymanagement.ui.todo.TodoListener
import com.example.propertymanagement.ui.todo.TodoViewModel

class AddTodoItemActivity : AppCompatActivity(), TodoListener {

    lateinit var binding: ActivityAddTodoItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo_item)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_todo_item)
        val viewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        binding.todoVM = viewModel
        viewModel.todoListener = this

    }

    override fun onStarted() {

    }

    override fun onSuccess(list: ArrayList<TodoList>) {

    }

    override fun onUpdate(update: ArrayList<TodoList>) {

    }

    override fun onFailure(message: String) {
        this.toast(message)
    }

    override fun onComplete() {
        finish()
    }

}