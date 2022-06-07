package com.ruizurraca.reservationappdemo

import android.app.Application
import com.ruizurraca.reservationappdemo.common.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ReservationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Prefs = applicationContext.getSharedPreferences("prefs", 0)
    }
}