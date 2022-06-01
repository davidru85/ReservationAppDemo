package com.ruizurraca.reservationappdemo.login.presentation.models

import com.ruizurraca.reservationappdemo.login.data.models.LoginModelApi

data class LoginModel(var user: String = "", var password: String = "") {
    fun toLoginModelApi() = LoginModelApi(mail = user, pw = password)
}