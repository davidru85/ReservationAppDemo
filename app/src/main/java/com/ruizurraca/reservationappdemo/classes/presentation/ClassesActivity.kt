package com.ruizurraca.reservationappdemo.classes.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ruizurraca.reservationappdemo.BuildConfig
import com.ruizurraca.reservationappdemo.classes.data.models.Bookings
import com.ruizurraca.reservationappdemo.databinding.ActivityClassesBinding
import com.ruizurraca.reservationappdemo.extensions.COOKIES
import com.ruizurraca.reservationappdemo.extensions.Prefs
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class ClassesActivity : AppCompatActivity() {

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
        Log.d(TAG, "bookClass: $currentClass")
    }

    companion object {
        val TAG = "ClassesActivity"
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
    }

    private fun showClasses(classes: List<Bookings>) {
        classesAdapter.fillData(classes)
    }

    override fun onStart() {
        super.onStart()
        val cookies = getCookies()
        LocalDate.now()?.plusDays(4)?.format((DateTimeFormatter.BASIC_ISO_DATE))?.let { targetDay ->
            viewModel.getClasses(BuildConfig.BOX_ID, targetDay, cookies)
        }
    }

    private fun getCookies(): List<String>? =
        Prefs.getString(COOKIES, null)?.let {
            deserializeCookies(it)
        }

    private fun deserializeCookies(itemListJsonString: String): List<String> {
        val itemType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(itemListJsonString, itemType)
    }
}