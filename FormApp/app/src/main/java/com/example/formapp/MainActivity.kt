package com.example.formapp

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var etBirthday: EditText
    private lateinit var btnSelect: Button
    private lateinit var calendarContainer: FrameLayout
    private lateinit var calendarView: CalendarView
    private lateinit var etAddress: EditText
    private lateinit var etEmail: EditText
    private lateinit var cbTerms: CheckBox
    private lateinit var btnRegister: Button
    private var isCalendarVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        rgGender = findViewById(R.id.rgGender)
        etBirthday = findViewById(R.id.etBirthday)
        btnSelect = findViewById(R.id.btnSelect)
        calendarContainer = findViewById(R.id.calendarContainer)
        etAddress = findViewById(R.id.etAddress)
        etEmail = findViewById(R.id.etEmail)
        cbTerms = findViewById(R.id.cbTerms)
        btnRegister = findViewById(R.id.btnRegister)

        calendarView = CalendarView(this)
        calendarView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        calendarContainer.addView(calendarView)
        calendarContainer.visibility = View.GONE

        btnSelect.setOnClickListener {
            if (!isCalendarVisible) {
                calendarContainer.visibility = View.VISIBLE
                isCalendarVisible = true
            } else {
                calendarContainer.visibility = View.GONE
                isCalendarVisible = false
            }
        }

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year)
            etBirthday.setText(selectedDate)
            calendarContainer.visibility = View.GONE
            isCalendarVisible = false
        }

        btnRegister.setOnClickListener {
            validateForm()
        }
    }

    private fun validateForm() {
        var isValid = true

        if (etFirstName.text.toString().trim().isEmpty()) {
            etFirstName.setBackgroundResource(R.drawable.edittext_error)
            isValid = false
        } else {
            etFirstName.setBackgroundResource(R.drawable.edittext_background)
        }

        if (etLastName.text.toString().trim().isEmpty()) {
            etLastName.setBackgroundResource(R.drawable.edittext_error)
            isValid = false
        } else {
            etLastName.setBackgroundResource(R.drawable.edittext_background)
        }

        if (rgGender.checkedRadioButtonId == -1) {
            findViewById<RadioButton>(R.id.rbMale).setBackgroundColor(Color.RED)
            findViewById<RadioButton>(R.id.rbFemale).setBackgroundColor(Color.RED)
            isValid = false
        } else {
            findViewById<RadioButton>(R.id.rbMale).setBackgroundColor(Color.TRANSPARENT)
            findViewById<RadioButton>(R.id.rbFemale).setBackgroundColor(Color.TRANSPARENT)
        }

        if (etBirthday.text.toString().trim().isEmpty()) {
            etBirthday.setBackgroundResource(R.drawable.edittext_error)
            isValid = false
        } else {
            etBirthday.setBackgroundResource(R.drawable.edittext_background)
        }

        if (etAddress.text.toString().trim().isEmpty()) {
            etAddress.setBackgroundResource(R.drawable.edittext_error)
            isValid = false
        } else {
            etAddress.setBackgroundResource(R.drawable.edittext_background)
        }

        if (etEmail.text.toString().trim().isEmpty()) {
            etEmail.setBackgroundResource(R.drawable.edittext_error)
            isValid = false
        } else {
            etEmail.setBackgroundResource(R.drawable.edittext_background)
        }

        if (!cbTerms.isChecked) {
            cbTerms.setBackgroundColor(Color.RED)
            isValid = false
        } else {
            cbTerms.setBackgroundColor(Color.TRANSPARENT)
        }

        if (isValid) {
            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
        }
    }
}

