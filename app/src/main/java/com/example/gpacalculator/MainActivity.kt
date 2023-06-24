package com.example.gpacalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var arrayListCourses : ArrayList<View>? = null
    private var scrollViewCourses : ScrollView? = null
    private var scrollViewContainer : LinearLayout? = null
    private var buttonAddCourse : Button? = null
    private var buttonCalculateGPA : Button? = null
    private var textViewGPA : TextView? = null
    private var inflater: LayoutInflater? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        arrayListCourses = ArrayList<View>()
        scrollViewCourses = findViewById(R.id.scrollViewCourses)
        scrollViewContainer = findViewById(R.id.scrollViewContainer)
        buttonAddCourse = findViewById(R.id.buttonAddCourse)
        buttonCalculateGPA = findViewById(R.id.buttonCalculateGPA)
        textViewGPA = findViewById(R.id.textViewGPA)
        inflater = LayoutInflater.from(this)


        buttonAddCourse?.setOnClickListener {
            addCourse()
        }

        buttonCalculateGPA?.setOnClickListener {

        }

    }

    private fun addCourse() {
        var inflatedLayout : View? = inflater?.inflate(R.layout.course_view, null)
        inflatedLayout?.let{
            arrayListCourses?.add(inflatedLayout)
            scrollViewContainer?.addView(inflatedLayout)
        }
    }
}