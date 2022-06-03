package com.ruizurraca.reservationappdemo.classes.data.models

import com.google.gson.annotations.SerializedName


data class ClassesResponse(

    @SerializedName("clasesDisp") var clasesDisp: String? = null,
    @SerializedName("timetable") var timetable: List<Timetable> = listOf(),
    @SerializedName("day") var day: String? = null,
    @SerializedName("bookings") var bookings: List<Bookings> = listOf(),
    @SerializedName("seminars") var seminars: List<String> = listOf()

)