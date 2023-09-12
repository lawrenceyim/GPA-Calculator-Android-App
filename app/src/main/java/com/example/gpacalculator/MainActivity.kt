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
import android.widget.Toast
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
            calculateGPA()
        }

    }

    private fun addCourse() {
        val inflatedLayout : View? = inflater?.inflate(R.layout.course_view, null)
        inflatedLayout?.let{
            arrayListCourses?.add(inflatedLayout)
            scrollViewContainer?.addView(inflatedLayout)
        }
        inflatedLayout?.findViewById<Button>(R.id.buttonRemoveCourse)?.setOnClickListener {
            removeCourse(inflatedLayout)
            Toast.makeText(this, "Course removed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateGPA() {
        var gpa: Float = 0.0F
        var totalCreditHours = 0
        var missingInput = false
        arrayListCourses?.let { list ->
            for (i in list.indices) {
                val specificView : View? = scrollViewContainer?.getChildAt(i)
                val selectedGrade = specificView?.findViewById<Spinner>(R.id.spinnerSelectGrade)?.selectedItem
                specificView?.findViewById<EditText>(R.id.editTextCreditHours)?.setError(null)
                if (specificView?.findViewById<EditText>(R.id.editTextCreditHours)?.text?.toString() == "") {
                    missingInput = true
                    specificView?.findViewById<EditText>(R.id.editTextCreditHours)?.setError("Missing input")
                    continue
                }

                val creditHours: Int = specificView?.findViewById<EditText>(R.id.editTextCreditHours)?.text.toString().toInt()

                totalCreditHours += creditHours
                gpa += when (selectedGrade) {
                    "A" -> 4 * creditHours
                    "B" -> 3 * creditHours
                    "C" -> 2 * creditHours
                    "D" -> creditHours
                    "F" -> 0
                    "W" -> 0
                    else -> 0
                }
            }
        }
        if (missingInput) {
            Toast.makeText(this, "Missing input for credit hours", Toast.LENGTH_SHORT).show()
            textViewGPA?.text = "NaN"
            return
        }
        textViewGPA?.text = "%.2f".format(gpa / totalCreditHours).toString()
    }

    private fun removeCourse(specificView : View) {
        arrayListCourses?.remove(specificView)
        scrollViewContainer?.removeView(specificView)
        println(arrayListCourses?.size)
    }
}