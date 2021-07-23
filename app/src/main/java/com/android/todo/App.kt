package com.android.todo

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class App {

    companion object : AppCompatActivity() {

        lateinit var sp: SharedPreferences
        lateinit var todoList: ArrayList<TODO>
        lateinit var noItemsLayout: LinearLayout

        fun INIT(context: Context, noItemsLayout: LinearLayout) {
            sp = context.getSharedPreferences("TODO", MODE_PRIVATE)
            todoList = loadTodoListData()
            this.noItemsLayout = noItemsLayout
        }

        fun deleteTodoAt(position: Int) {
            todoList.removeAt(position)
            saveTodoListData()
        }

        fun saveTodoListData() {
            val json = Gson().toJson(todoList)
            sp.edit().putString("todoList", json).apply()
            if (todoList.size > 0)
                noItemsLayout.visibility = View.INVISIBLE
            else
                noItemsLayout.visibility = View.VISIBLE
        }

        fun loadTodoListData(): ArrayList<TODO> {
            val json = sp.getString("todoList", null)
            if (json != null)
                return Gson().fromJson(json, object : TypeToken<ArrayList<TODO>>() {}.type)
            return ArrayList<TODO>()
        }
    }



}