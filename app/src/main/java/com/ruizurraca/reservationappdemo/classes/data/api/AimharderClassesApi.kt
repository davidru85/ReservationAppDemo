package com.ruizurraca.reservationappdemo.classes.data.api


import com.ruizurraca.reservationappdemo.classes.data.models.ClassesResponse
import retrofit2.Response
import retrofit2.http.*

interface AimharderClassesApi {
    @GET("/api/bookings")
    suspend fun getClasses(
        @HeaderMap headers: Map<String, String>,
        @Query("day") day: String,
        @Query("box") box: String,
        @Query("familyId") familyId: String
    ): Response<ClassesResponse>
}