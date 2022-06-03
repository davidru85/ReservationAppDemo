package com.ruizurraca.reservationappdemo.classes.data.models

import com.google.gson.annotations.SerializedName


data class Timetable(

    @SerializedName("id") var id: String? = null,
    @SerializedName("time") var time: String? = null

)