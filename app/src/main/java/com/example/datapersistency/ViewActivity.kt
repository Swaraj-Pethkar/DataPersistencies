package com.example.datapersistency

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ViewActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        val students = ArrayList<Student>()
        val dbhelper: Dbhelper= Dbhelper(this)
        val studentList = dbhelper.fetchData();

        val adapter = CustomAdapter(studentList)
        recyclerView.adapter = adapter
    }
}