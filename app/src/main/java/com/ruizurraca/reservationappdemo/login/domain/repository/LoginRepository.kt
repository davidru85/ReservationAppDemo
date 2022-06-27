package com.ruizurraca.reservationappdemo.login.domain.repository

import com.ruizurraca.reservationappdemo.login.data.models.LoginModelApi
import com.ruizurraca.reservationappdemo.login.presentation.models.LoginResult

interface LoginRepository {
    suspend fun login(loginModelApi: LoginModelApi): LoginResult
}
