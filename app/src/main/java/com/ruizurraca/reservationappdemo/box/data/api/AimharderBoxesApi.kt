package com.ruizurraca.reservationappdemo.box.data.api


import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AimharderBoxesApi {
    @POST("/home")
    suspend fun boxes(): Response<String>
}
