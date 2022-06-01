package com.ruizurraca.reservationappdemo.login.data.models

data class LoginModelApi(
    val login: String = "Iniciar sesión",
    val loginiframe: Int = 0,
    var mail: String,
    var pw: String
)