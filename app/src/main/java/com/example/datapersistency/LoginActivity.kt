package com.example.datapersistency

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val editUsername = findViewById<EditText>(R.id.editUsername)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val check = findViewById<CheckBox>(R.id.check)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val shared =
            getSharedPreferences("Swaraj", Context.MODE_PRIVATE)
        val username = shared.getString("username", editUsername.text.toString())
        val password = shared.getString("password", editPassword.text.toString())

        editUsername.setText(username)
        editPassword.setText(password)

        check!!.isChecked = !((isEmpty(editUsername)) && (isEmpty(editPassword)))

        btnSave.setOnClickListener{

            if(isEmpty(editUsername)){
                editUsername.error = "Please Enter Username"
                editUsername.requestFocus()
                return@setOnClickListener
            }
            if(isEmpty(editPassword)){
                editPassword.error = "Please Enter Password"
                editPassword.requestFocus();
                return@setOnClickListener
            }

            val sharedPreferences:SharedPreferences = this.getSharedPreferences("Swaraj", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("username", editUsername.text.toString())
            editor.putString("password", editPassword.text.toString())



            if(check.isChecked){
                editor.apply();
                finish();
                toast("Data is Saved")
            }
            else if(!check.isChecked){
                getSharedPreferences("Swaraj", 0).edit().clear().apply();
                finish();
                toast("Data is Clear")
            }
        }
    }

    private fun isEmpty(editText: EditText): Boolean {
        return editText.text.toString().isEmpty()
    }

    private fun toast(msg:String){
        return Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
    }
}