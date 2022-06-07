package com.ruizurraca.reservationappdemo.common

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ruizurraca.reservationappdemo.login.presentation.models.LoginModel
import okhttp3.Cookie

lateinit var Prefs: SharedPreferences

const val COOKIES = "COOKIES"
const val CREDENTIALS = "CREDENTIALS"

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

// -------------------
fun SharedPreferences.saveCredentials(loginModel: LoginModel) {
    Prefs.putAny(CREDENTIALS, Gson().toJson(loginModel))
}

fun SharedPreferences.deleteCredentials() {
    Prefs.remove(CREDENTIALS)
}

fun SharedPreferences.retrieveCredentials(): LoginModel {
    return try {
        Gson().fromJson(Prefs.getString(CREDENTIALS, ""), LoginModel::class.java)
    } catch (exc: Exception) {
        LoginModel()
    }
}


// -------------------

fun SharedPreferences.saveCookies(cookies: List<Cookie>) {
    Prefs.putAny(COOKIES, Gson().toJson(cookies))
}

fun SharedPreferences.deleteCookies() {
    Prefs.remove(COOKIES)
}

fun SharedPreferences.retrieveCookies(): List<Cookie> {
    val cookiesString = Prefs.getString(COOKIES, "")
    return if (cookiesString?.isNotEmpty() == true) {
        try {
            deserializeCookies(cookiesString)
        } catch (ex: Exception) {
            mutableListOf()
        }
    } else {
        mutableListOf()
    }
}

private fun deserializeCookies(itemListJsonString: String): List<Cookie> {
    val itemType = object : TypeToken<List<Cookie>>() {}.type
    return Gson().fromJson(itemListJsonString, itemType)
}