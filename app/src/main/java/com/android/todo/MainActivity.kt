package com.android.todo

import android.app.Dialog
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.time.LocalDateTime


class MainActivity : AppCompatActivity() {

    private lateinit var todoListView: RecyclerView
    private lateinit var emptyListLayout: LinearLayout
    private lateinit var tvGreeting: TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emptyListLayout = findViewById(R.id.emptyListLayout)

        // INIT SharedPreferences:
        App.INIT(this, emptyListLayout)

        todoListView = findViewById(R.id.todoListView)
        todoListView.layoutManager = LinearLayoutManager(this)
        todoListView.setHasFixedSize(true)

        todoListView.adapter = TodoAdapter(App.todoList, this)

        // Empty list layout:
        if (App.todoList.size == 0)
            emptyListLayout.visibility = View.VISIBLE
        else
            emptyListLayout.visibility = View.INVISIBLE

        // Change the greeting accordingly to the system's time:
        tvGreeting = findViewById(R.id.tvGreeting)
        var time = LocalDateTime.now()
        tvGreeting.text = when (time.hour) {
            6,7,8,9,10,11 -> "Good Morning!"
            12,13,14 -> "Good Noon!"
            15,16,17 -> "Good Afternoon!"
            18,19,20 -> "Good Evening!"
            21,22,23,0,1,2,3,4,5 -> "Good Night!"
            else -> "Welcome Back!"
        }
    }

    fun btnAddItem_Click(v: View) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.add_todo_dialog)
        val etTodoHeader = dialog.findViewById<EditText>(R.id.etTodoHeader)
        val etTodoInfo = dialog.findViewById<EditText>(R.id.etTodoInfo)

        val params = WindowManager.LayoutParams()
        params.copyFrom(dialog.window!!.attributes)
        params.width = resources.displayMetrics.widthPixels
        dialog.window?.attributes = params

        dialog.findViewById<TextView>(R.id.btnCreateTodo).setOnClickListener {
            // Check fields:
            if (etTodoHeader.text.toString() != "" && etTodoHeader.text.toString() != "") {
                App.todoList.add(TODO(etTodoInfo.text.toString(), etTodoHeader.text.toString()))
                App.saveTodoListData()
                dialog.dismiss()
            }
        }

        dialog.findViewById<TextView>(R.id.btnCancelTodo).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}