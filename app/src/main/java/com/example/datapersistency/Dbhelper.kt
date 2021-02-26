package com.example.datapersistency

import android.R.attr.data
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


private val DATABASE_VERSION = 1
private val DATABASE_NAME = "dbstudent"
private val TABLE_NAME = "tblstudent"
private val KEY_STUD_ID = "student_id"
private val KEY_STUD_NAME = "student_name"
private val KEY_STUD_EMAIL = "student_email"
private val KEY_STUD_PHONE = "student_phone"
private val KEY_STUD_ADDR = "student_addr"

class Dbhelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (student_id INTEGER PRIMARY KEY AUTOINCREMENT, student_name VARCHAR(255), student_email VARCHAR(255), student_phone VARCHAR(255), student_addr VARCHAR(255))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    public fun insertData(
        student_name: String?,
        student_email: String?,
        student_phone: String?,
        student_addr: String?
    ): Long {
        val db = this.readableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_STUD_NAME, student_name)
        contentValues.put(KEY_STUD_EMAIL, student_email)
        contentValues.put(KEY_STUD_PHONE, student_phone)
        contentValues.put(KEY_STUD_ADDR, student_addr)
        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close() // Closing database connection
        return success
    }

    public fun fetchData(): ArrayList<Student> {
        val db = this.readableDatabase
        val studList:ArrayList<Student> = ArrayList<Student>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null)

        if (cursor.moveToFirst()) {
            do {
                val student_name = cursor.getColumnIndex("student_name")
                val student_email = cursor.getColumnIndex("student_email")
                val student_phone = cursor.getColumnIndex("student_phone")
                val student_addr = cursor.getColumnIndex("student_addr")

                val name = firstCapital(cursor.getString(student_name))
                val email = cursor.getString(student_email)
                val phone = cursor.getString(student_phone)
                val addr = firstCapital(cursor.getString(student_addr))
                val stud = Student(student_name = name, student_email = email, student_phone = phone, student_addr = addr)
                studList.add(stud)

            } while (cursor.moveToNext())
        }

        return studList
    }


    private fun firstCapital(data: String): String {
        return data.toString().substring(0, 1).toUpperCase(Locale.ROOT) + data.toString().substring(1).toLowerCase(
            Locale.ROOT
        )
    }
}