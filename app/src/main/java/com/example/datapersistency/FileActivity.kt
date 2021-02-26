package com.example.datapersistency

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter


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

            if(isEmpty(editName)){
                editName.error = "Please Enter Name"
                editName.requestFocus()
                return@setOnClickListener
            }
            if(isEmpty(editEmail)){
                editEmail.error = "Please Enter Email"
                editEmail.requestFocus();
                return@setOnClickListener
            }
            if(isEmpty(editPhone)){
                editPhone.error = "Please Enter Phone"
                editPhone.requestFocus();
                return@setOnClickListener
            }
            if(isEmpty(editAddress)){
                editAddress.error = "Please Enter Address"
                editAddress.requestFocus();
                return@setOnClickListener
            }
            var fileBody = ""
            val fileName = "file swaraj"
            val file: File = baseContext.getFileStreamPath(fileName)

            if(file.exists().not()){
                val fileOutputStream: FileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
                OutputStreamWriter(fileOutputStream)
            }

            applicationContext.openFileInput(fileName).use { stream ->
                val text = stream.bufferedReader().use {
                    it.readText()
                }
            if(text.isEmpty()){
                fileBody ="[{\"studentName\": \"${editName.text.toString()}\",\"studentEmail\":\"${editEmail.text.toString()}\",\"studentPhone\": \"${editPhone.text.toString()}\",\"studentAddr\":\"${editAddress.text.toString()}\"}]"
            }
            else{
                fileBody = "["+text.substring( 1, text.length - 1 )+","+"{\"studentName\": \"${editName.text.toString()}\",\"studentEmail\":\"${editEmail.text.toString()}\",\"studentPhone\": \"${editPhone.text.toString()}\",\"studentAddr\":\"${editAddress.text.toString()}\"}]"
            }


            applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
                output.write(fileBody.toByteArray())
            }
            }
            toast("Data Inserted To File Successfully")
            editName.text.clear()
            editEmail.text.clear()
            editPhone.text.clear()
            editAddress.text.clear()
        }

        btnView.setOnClickListener{
            val intent = Intent(this,ViewFile::class.java)
            startActivity(intent)
        }

    }

    private fun isEmpty(editText: EditText): Boolean {
        return editText.text.toString().isEmpty()
    }

    private fun toast(msg:String){
        return Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
    }
}
