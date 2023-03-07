package com.example.aimcalculator

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // TextViews variables
    private var tvSelectedDate : TextView? = null
    private var tvTotalMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Finding the views in 'activity_main'.
        val btnDatePicker : Button = findViewById(R.id.btn_date_picker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvTotalMinutes = findViewById(R.id.tvTotalMinutes)

        // Setting up a action when button 'Select your date' is clicked.
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonthOfYear, selectedDayOfMonth ->
                // Placing the selected date in the constructors of Calendar object.
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonthOfYear, selectedDayOfMonth)

                // Showing the selected date in the TextView.
                val theDate = "$selectedDayOfMonth/${selectedMonthOfYear + 1}/$selectedYear"
                tvSelectedDate?.text = theDate

                // Difference between the actual date and the selected date.
                val difference = calendar.timeInMillis - selectedDate.timeInMillis

                // 1000 milliseconds in 1 second, 60 seconds in 1 minute.
                val minutes = difference / 60000

                // Showing the selected date in the TextView.
                tvTotalMinutes?.text = minutes.toString()
            },
            year,
            month,
            day
        )
        // Setting a maximum date for the user not to be able to choose a future date.
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000

        datePickerDialog.show()
    }
}