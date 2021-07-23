package com.android.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class DisplayInfoActivity : AppCompatActivity() {

    private lateinit var tvTodoHeader: TextView
    private lateinit var tvTodoInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_info)

        tvTodoHeader = findViewById(R.id.tvTodoHeader)
        tvTodoInfo = findViewById(R.id.tvTodoInfo)

        tvTodoHeader.text = intent.extras?.getString("header", "Internal Error")
        tvTodoInfo.text = intent.extras?.getString("info", "Internal Error")
    }

    fun btnGoBack_Click(v: View) {
        finish()
    }

}