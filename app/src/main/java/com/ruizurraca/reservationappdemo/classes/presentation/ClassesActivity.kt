package com.ruizurraca.reservationappdemo.classes.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
import java.util.*

@AndroidEntryPoint
class ClassesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClassesBinding
    private val viewModel by viewModels<ClassesViewModel>()

    companion object {
        val TAG = "ClassesActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
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
        Log.d(TAG, "showClasses: $classes")
    }

    override fun onStart() {
        super.onStart()
        val cookies = getCookies()
        val today = LocalDate.now().format((DateTimeFormatter.BASIC_ISO_DATE))
        viewModel.getClasses(BuildConfig.BOX_ID, today, cookies)
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