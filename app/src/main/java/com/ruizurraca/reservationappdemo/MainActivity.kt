package com.ruizurraca.reservationappdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ruizurraca.reservationappdemo.box.presentation.BoxesActivity
import com.ruizurraca.reservationappdemo.classes.presentation.ClassesActivity
import com.ruizurraca.reservationappdemo.common.BOX
import com.ruizurraca.reservationappdemo.common.COOKIES
import com.ruizurraca.reservationappdemo.common.CREDENTIALS
import com.ruizurraca.reservationappdemo.common.Prefs
import com.ruizurraca.reservationappdemo.login.presentation.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        when {
            Prefs.contains(COOKIES) && Prefs.contains(CREDENTIALS) && !Prefs.contains(BOX) -> {
                startActivity(Intent(this, BoxesActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                })
            }
            Prefs.contains(COOKIES) && Prefs.contains(CREDENTIALS) && Prefs.contains(BOX) -> {
                startActivity(Intent(this, ClassesActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                })
            }
            else -> {
                startActivity(Intent(this, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                })
            }
        }
    }
}
