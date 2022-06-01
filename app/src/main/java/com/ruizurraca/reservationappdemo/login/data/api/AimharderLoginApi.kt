package com.ruizurraca.reservationappdemo.login.data.api


import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AimharderLoginApi {
    @FormUrlEncoded
    @POST("/login")
    suspend fun login(
        @HeaderMap headers: Map<String, String>,
        @Field("login") login: String,
        @Field("loginiframe") loginiframe: Int,
        @Field("mail") mail: String,
        @Field("pw") pw: String
    ): Response<String>
}