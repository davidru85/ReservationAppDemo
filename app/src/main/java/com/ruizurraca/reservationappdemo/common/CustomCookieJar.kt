package com.ruizurraca.reservationappdemo.common

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class CustomCookieJar : CookieJar {

    override fun loadForRequest(url: HttpUrl): List<Cookie> =
        Prefs.retrieveCookies()


    override fun saveFromResponse(url: HttpUrl, currentCookies: List<Cookie>) {
        var globalCookies = Prefs.retrieveCookies()
        globalCookies =
            (globalCookies.toMutableList().apply { addAll(currentCookies) }).distinctBy { it.name }

        globalCookies.let { cookies ->
            Prefs.saveCookies(cookies)
        }
    }
}