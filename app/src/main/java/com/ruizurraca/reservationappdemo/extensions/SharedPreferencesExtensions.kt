package com.ruizurraca.reservationappdemo.extensions

import android.content.SharedPreferences

lateinit var Prefs: SharedPreferences

const val COOKIES = "cookies"

// add entry in shared preference
fun SharedPreferences.putAny(name: String, any: Any) {
    when (any) {
        is String -> edit().putString(name, any).apply()
        is Int -> edit().putInt(name, any).apply()
        is Boolean -> edit().putBoolean(name, any).apply()

        // also accepts Float, Long & StringSet
    }
}

// remove entry from shared preference
fun SharedPreferences.remove(name: String) {
    edit().remove(name).apply()
}