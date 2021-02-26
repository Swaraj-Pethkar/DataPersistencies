package com.example.datapersistency

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val editName = findViewById<EditText>(R.id.editName)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editPhone = findViewById<EditText>(R.id.editPhone)
        val editAddress = findViewById<EditText>(R.id.editAddress)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnView = findViewById<Button>(R.id.btnView)

        btnRegister.setOnClickListener{
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


            val dbhelper: Dbhelper= Dbhelper(this)
            val status = dbhelper.insertData(editName.text.toString(),editEmail.text.toString(),editPhone.text.toString(),editAddress.text.toString());
            if(status > -1){
                toast("Data Inserted Successfully")
                editName.text.clear()
                editEmail.text.clear()
                editPhone.text.clear()
                editAddress.text.clear()
            }
            else{
                toast("Something Went Wrong")
            }
        }

        btnView.setOnClickListener{
            val intent = Intent(this,ViewActivity::class.java)
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