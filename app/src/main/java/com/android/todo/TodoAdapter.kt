package com.android.todo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class TodoAdapter(private val todoList: ArrayList<TODO>, private val context: Context) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodoItem = todoList[position]
        holder.todoHeader.text = currentTodoItem.Header
        holder.todoInfo.text = currentTodoItem.Info
        if (currentTodoItem.Info.length > 20)
            holder.todoInfo.text = holder.todoInfo.text.toString().substring(0, 17) + "..."
        holder.btnDelete.setOnClickListener {
            todoList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount);
            App.todoList = todoList
            App.saveTodoListData()
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DisplayInfoActivity::class.java)
            intent.putExtra("header", currentTodoItem.Header)
            intent.putExtra("info", currentTodoItem.Info)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val todoHeader: TextView = itemView.findViewById(R.id.tvHeader)
        val todoInfo: TextView = itemView.findViewById(R.id.tvTodoInfo)
        val btnDelete: TextView = itemView.findViewById(R.id.btnDelete)

    }

}