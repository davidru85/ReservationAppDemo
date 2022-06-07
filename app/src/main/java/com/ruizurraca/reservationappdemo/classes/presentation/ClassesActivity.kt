package com.ruizurraca.reservationappdemo.classes.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruizurraca.reservationappdemo.BuildConfig
import com.ruizurraca.reservationappdemo.classes.presentation.models.BookClassModel
import com.ruizurraca.reservationappdemo.classes.presentation.models.BookingsModel
import com.ruizurraca.reservationappdemo.databinding.ActivityClassesBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class ClassesActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ClassesActivity"
    }

    var currentDate = LocalDate.now()?.plusDays(4)

    private lateinit var binding: ActivityClassesBinding
    private val viewModel by viewModels<ClassesViewModel>()
    private val classesAdapter = ClassesAdapter().apply {
        listener = object : ClassClickListener {
            override fun classClick(currentClass: BookingsModel) {
                bookClass(currentClass)
            }
        }
    }

    private fun bookClass(currentClass: BookingsModel) {
        currentDate?.let { targetDay ->
            viewModel.bookClass(targetDay.format((DateTimeFormatter.BASIC_ISO_DATE)), currentClass)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        initBindings()
    }

    private fun initBindings() {
        binding.rvClassesList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = classesAdapter
        }
        binding.tvCurrentDate.apply {
            text = currentDate?.format((DateTimeFormatter.ISO_DATE))
            setOnClickListener { showDatePickerDialog() }
        }
    }

    private fun showDatePickerDialog() {
        val datePicker =
            DatePickerFragment(currentDate) { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        currentDate = LocalDate.of(year, month, day)
        binding.tvCurrentDate.text = currentDate?.format((DateTimeFormatter.ISO_DATE))
        getClasses(currentDate)
    }

    private fun initObservers() {
        viewModel.classes.observe(this, {
            it?.bookings?.let { classes ->
                showClasses(classes)
            }
        })

        viewModel.bookClass.observe(this, { response ->
            response.bookClassDtoResponse?.let { bookClassResponse ->
                manageBookClassResponse(bookClassResponse)
            }
        })
    }

    private fun manageBookClassResponse(bookClassResponse: BookClassModel.BookClassDtoResponse) {
        val message = if (bookClassResponse.errorMssg?.isNotEmpty() == true) {
            bookClassResponse.errorMssg
        } else {
            "Success"
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showClasses(classes: List<BookingsModel>) {
        classesAdapter.fillData(classes)
    }

    override fun onStart() {
        super.onStart()
        getClasses(currentDate)
    }

    private fun getClasses(localDate: LocalDate? = LocalDate.now()) {
        localDate?.let { targetDay ->
            viewModel.getClasses(
                BuildConfig.BOX_ID,
                targetDay.format((DateTimeFormatter.BASIC_ISO_DATE))
            )
        }
    }
}