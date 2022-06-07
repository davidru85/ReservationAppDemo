package com.ruizurraca.reservationappdemo.classes.data.api


import com.ruizurraca.reservationappdemo.classes.data.models.BookClassResponse
import com.ruizurraca.reservationappdemo.classes.data.models.ClassesResponse
import retrofit2.Response
import retrofit2.http.*

interface AimharderClassesApi {
    @GET("/api/bookings")
    suspend fun getClasses(
        @Query("day") day: String,
        @Query("box") box: String,
        @Query("familyId") familyId: String = ""
    ): Response<ClassesResponse>

    @FormUrlEncoded
    @POST("/api/book")
    suspend fun bookClass(
        @Field("id") id: String,
        @Field("day") day: String,
        @Field("insist") insist: Int = 0,
        @Field("familyId") familyId: String = ""
    ): Response<BookClassResponse>
}