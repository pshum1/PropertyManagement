package com.example.propertymanagement.ui.todo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagement.R
import com.example.propertymanagement.data.models.TodoList
import com.example.propertymanagement.helpers.toast
import com.example.propertymanagement.ui.todo.TodoListener
import com.example.propertymanagement.ui.todo.TodoViewModel
import com.example.propertymanagement.ui.todo.adapters.AdapterTodo
import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.android.synthetic.main.app_bar.*

class TodoActivity : AppCompatActivity(), TodoListener {

    private var adapterTodo: AdapterTodo? = null
    lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        viewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        viewModel.todoListener = this


        init()
    }

    private fun init() {

        setupToolbar()


        viewModel.getData()

        button_add_floating.setOnClickListener {
            startActivity(Intent(this, AddTodoItemActivity::class.java))
        }
    }


    override fun onStarted() {

    }

    override fun onSuccess(list: ArrayList<TodoList>) {
        //tv_no_items_todo.visibility = View.GONE


    }

    override fun onUpdate(update: ArrayList<TodoList>) {
        adapterTodo = AdapterTodo(this, update)
        recycler_view_todo.layoutManager = LinearLayoutManager(this)
        recycler_view_todo.adapter = adapterTodo
       //  adapterTodo?.setData(update)
    }

    override fun onFailure(message: String) {
        Log.d("FirebaseDB", "activity ONFAILURE")
        this.toast("No items retrieved")
        tv_no_items_todo.visibility = View.VISIBLE
    }

    override fun onComplete() {

    }

    private fun setupToolbar() {
        var toolbar = toolbar_general
        toolbar.title = "To-do List"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return true
    }
}