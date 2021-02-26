package com.example.datapersistency

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        val editName = findViewById<EditText>(R.id.editName)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editPhone = findViewById<EditText>(R.id.editPhone)
        val editAddress = findViewById<EditText>(R.id.editAddress)
        val btnFile = findViewById<Button>(R.id.btnFile)
        val btnView = findViewById<Button>(R.id.btnView)

        btnFile.setOnClickListener{
            val fOut = openFileOutput("file swaraj", Context.MODE_PRIVATE)
            val str = "[{\"studentName\": \"${editName.text.toString()}\",\"studentEmail\":\"${editEmail.text.toString()}\",\"studentPhone\": \"${editPhone.text.toString()}\",\"studentAddr\":\"${editAddress.text.toString()}\"}]"
            fOut.write(str.toByteArray())
            toast("Data is Added To File")
        }

        btnView.setOnClickListener{
            var intent = Intent(this,ViewFile::class.java)
            startActivity(intent)
        }

    }

    private fun toast(msg:String){
        return Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
    }
}
