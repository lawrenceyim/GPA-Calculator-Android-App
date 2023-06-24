package com.example.gpacalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import kotlin.math.truncate

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
            var gpa: Float = 0.0F
            var totalCreditHours = 0
            arrayListCourses?.let { list ->
                for (i in list.indices) {
                    val specificView : View? = scrollViewContainer?.getChildAt(i)
                    val spinner: Spinner? = specificView?.findViewById<Spinner>(R.id.spinnerSelectGrade) as Spinner
                    val creditHours: EditText? = specificView?.findViewById<EditText>(R.id.editTextCreditHours) as EditText
                    var selectedGrade = spinner?.selectedItem
                    totalCreditHours += creditHours?.text.toString().toInt()
                    when (selectedGrade) {
                        "A" -> gpa += 4 * creditHours?.text.toString().toInt()
                        "B" -> gpa += 3 * creditHours?.text.toString().toInt()
                        "C" -> gpa += 2 * creditHours?.text.toString().toInt()
                        "D" -> gpa += creditHours?.text.toString().toInt()
                        "F" -> gpa += 0
                        "W" -> gpa += 0
                        else -> gpa += 0
                    }

                }

            }
            textViewGPA?.text = "%.2f".format(gpa / totalCreditHours).toString()
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