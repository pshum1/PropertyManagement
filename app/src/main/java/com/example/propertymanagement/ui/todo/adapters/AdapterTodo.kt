package com.example.propertymanagement.ui.todo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagement.R
import com.example.propertymanagement.data.models.TodoList
import com.example.propertymanagement.databinding.RowRecyclerTodoBinding

class AdapterTodo(private val context: Context, private var list: ArrayList<TodoList>) :
    RecyclerView.Adapter<AdapterTodo.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RowRecyclerTodoBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoList = list[position]
        holder.bind(todoList)
    }

    fun setData(l: ArrayList<TodoList>) {
        list = l
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RowRecyclerTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todoList: TodoList) {
            binding.tvTaskTitle.text = todoList.title
            binding.tvTaskDescription.text = todoList.description
        }
    }


}