package com.ruizurraca.reservationappdemo.classes.presentation

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalDate

class DatePickerFragment(
    private val currentDate: LocalDate?,
    val listener: (day: Int, month: Int, year: Int) -> Unit
) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth, month + 1, year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val localDate = currentDate ?: LocalDate.now()
        return DatePickerDialog(
            activity as Context,
            this,
            localDate.year,
            localDate.monthValue - 1,
            localDate.dayOfMonth
        )
    }
}
