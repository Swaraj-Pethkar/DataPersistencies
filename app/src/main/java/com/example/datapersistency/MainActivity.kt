package com.example.datapersistency

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val assign1 = findViewById<Button>(R.id.assign1)
        val assign2 = findViewById<Button>(R.id.assign2)
//        val assign3 = findViewById<Button>(R.id.assign3)

        assign1.setOnClickListener{
            val assignment1: Intent = Intent(this,LoginActivity::class.java)
            startActivity(assignment1)
        }

        assign2.setOnClickListener{
            val assignment2: Intent = Intent(this,RegisterActivity::class.java)
            startActivity(assignment2)
        }

//        assign3.setOnClickListener{
//            val assignment3: Intent = Intent(this,FileActivity::class.java)
//            startActivity(assignment3)
//        }
    }
}