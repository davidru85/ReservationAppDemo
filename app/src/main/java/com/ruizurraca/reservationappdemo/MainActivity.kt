package com.ruizurraca.reservationappdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ruizurraca.reservationappdemo.classes.presentation.ClassesActivity
import com.ruizurraca.reservationappdemo.common.COOKIES
import com.ruizurraca.reservationappdemo.common.Prefs
import com.ruizurraca.reservationappdemo.login.presentation.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        if (Prefs.contains(COOKIES))
            startActivity(Intent(this, ClassesActivity::class.java))
        else
            startActivity(Intent(this, LoginActivity::class.java))
    }
}