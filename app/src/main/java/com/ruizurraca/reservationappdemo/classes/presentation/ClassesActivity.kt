package com.ruizurraca.reservationappdemo.classes.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruizurraca.reservationappdemo.BuildConfig
import com.ruizurraca.reservationappdemo.classes.data.models.Bookings
import com.ruizurraca.reservationappdemo.databinding.ActivityClassesBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class ClassesActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ClassesActivity"
    }

    var currentDate = LocalDate.now()?.plusDays(4)?.format((DateTimeFormatter.BASIC_ISO_DATE))

    private lateinit var binding: ActivityClassesBinding
    private val viewModel by viewModels<ClassesViewModel>()
    private val classesAdapter = ClassesAdapter().apply {
        listener = object : ClassClickListener {
            override fun classClick(currentClass: Bookings) {
                bookClass(currentClass)
            }
        }
    }

    private fun bookClass(currentClass: Bookings) {
        currentDate?.let { targetDay ->
            viewModel.bookClass(targetDay, currentClass)
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
    }

    private fun initObservers() {
        viewModel.classes.observe(this, {
            Log.d(TAG, "initObservers: $it")
            it?.bookings?.let { classes ->
                showClasses(classes)
            }
        })

        viewModel.bookClass.observe(this, {
            Log.d(TAG, "initObservers: $it")
        })
    }

    private fun showClasses(classes: List<Bookings>) {
        classesAdapter.fillData(classes)
    }

    override fun onStart() {
        super.onStart()
        currentDate?.let { targetDay ->
            viewModel.getClasses(BuildConfig.BOX_ID, targetDay)
        }
    }
}