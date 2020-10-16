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
//        val inflater: RowRecyclerTodoBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(parent.context),
//            R.layout.row_recycler_todo,
//            parent,
//            false
//        )

        val view =
            LayoutInflater.from(context).inflate(R.layout.row_recycler_todo, parent, false)!!
        RowRecyclerTodoBinding.bind(view)

        return ViewHolder(view)
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

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(todoList: TodoList) {
            val rowRecyclerTodoBinding =
                DataBindingUtil.getBinding<RowRecyclerTodoBinding>(itemView)
            rowRecyclerTodoBinding?.tvTaskTitle?.text = todoList.title
            rowRecyclerTodoBinding?.tvTaskDescription?.text = todoList.description
        }
    }


}