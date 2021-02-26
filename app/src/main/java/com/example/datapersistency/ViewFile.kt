package com.example.datapersistency

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.File


class ViewFile : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_file)
        val fileName = "file swaraj"
        val file: File = baseContext.getFileStreamPath(fileName)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        if(file.exists()) {
            applicationContext.openFileInput(fileName).use { stream ->
                val text = stream.bufferedReader().use {
                    it.readText()
                }

                val data:ArrayList<Student> = ArrayList<Student>()

                val jArray = JSONArray(text)
                for (i in 0 until jArray.length()) {
                    val jsonData = jArray.getJSONObject(i)
                    val student = Student(student_name = jsonData.getString("studentName"), student_email = jsonData.getString("studentEmail"), student_phone = jsonData.getString("studentPhone"), student_addr = jsonData.getString("studentAddr"))
                    data.add(student);
                    val adapter = CustomAdapter(data)
                    recyclerView.adapter = adapter
                }

            }
        }
    }

}
