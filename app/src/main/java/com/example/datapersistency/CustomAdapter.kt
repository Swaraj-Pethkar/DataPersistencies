package com.example.datapersistency

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val studentList: ArrayList<Student>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student: Student = studentList[position]
        holder?.bindItems(studentList[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(student: Student) {
            val studentName = itemView.findViewById<TextView>(R.id.studentName)
            val studentEmail = itemView.findViewById<TextView>(R.id.studentEmail)
            val studentPhone = itemView.findViewById<TextView>(R.id.studentPhone)
            val studentAddr = itemView.findViewById<TextView>(R.id.studentAddr)
            studentName.text = student.student_name
            studentEmail.text = student.student_email
            studentAddr.text = student.student_addr
            studentPhone.text = student.student_phone

        }
    }




}