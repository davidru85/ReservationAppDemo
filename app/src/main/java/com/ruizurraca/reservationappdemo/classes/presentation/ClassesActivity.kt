package com.ruizurraca.reservationappdemo.classes.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ruizurraca.reservationappdemo.databinding.ActivityClassesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClassesBinding
    //private val viewModel by viewModels<LoginViewModel>()

    companion object {
        val TAG = "ClassesActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCookies()
    }

    private fun getCookies() {
        applicationContext.getSharedPreferences("prefs", 0).let { preferences ->
            val cookies = preferences.getString("cookies", "")?.let { cookiesString ->
                deserializeCookies(cookiesString)
            }
            Log.d(TAG, "getCookies: $cookies")
        }
    }

    private fun deserializeCookies(itemListJsonString: String): List<String> {
        val itemType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(itemListJsonString, itemType)
    }
}