package com.example.datapersistency

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class ViewFile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_file)
        readFile();
    }

    fun readFile() {

        var displayText = findViewById<TextView>(R.id.textView)
        try {
            val fileInputStream = openFileInput("file name")
            val inputStreamReader =
                InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuffer = StringBuffer()
            var lines: String
//            while (bufferedReader.readLine().also { lines = it } != null) {
//                stringBuffer.append(
//                    """
//                        $lines
//
//                        """.trimIndent()
//                )
//            }
            displayText.setText(stringBuffer.toString())
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}