package com.ruizurraca.reservationappdemo.classes.data.models

import com.google.gson.annotations.SerializedName


data class ClassesResponse(
    @SerializedName("clasesDisp") var clasesDisp: String? = null,
    @SerializedName("timetable") var timetable: List<TimetableResponse> = listOf(),
    @SerializedName("day") var day: String? = null,
    @SerializedName("bookings") var bookings: List<BookingsResponse> = listOf(),
    @SerializedName("seminars") var seminars: List<String> = listOf()
)

data class TimetableResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("time") var time: String? = null
)

data class BookingsResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("zoomid") var zoomid: String? = null,
    @SerializedName("zoomJoinUrl") var zoomJoinUrl: String? = null,
    @SerializedName("zoomJoinPw") var zoomJoinPw: String? = null,
    @SerializedName("onlineclass") var onlineclass: Int? = null,
    @SerializedName("idres") var idres: String? = null,
    @SerializedName("spotres") var spotres: String? = null,
    @SerializedName("time") var time: String? = null,
    @SerializedName("timeid") var timeid: String? = null,
    @SerializedName("classId") var classId: String? = null,
    @SerializedName("className") var className: String? = null,
    @SerializedName("classDesc") var classDesc: String? = null,
    @SerializedName("boxName") var boxName: String? = null,
    @SerializedName("boxDir") var boxDir: String? = null,
    @SerializedName("boxPic") var boxPic: String? = null,
    @SerializedName("coachName") var coachName: String? = null,
    @SerializedName("coachPic") var coachPic: String? = null,
    @SerializedName("enabled") var enabled: Int? = null,
    @SerializedName("bookState") var bookState: String? = null,
    @SerializedName("limit") var limit: String? = null,
    @SerializedName("limitc") var limitc: String? = null,
    @SerializedName("ocupation") var ocupation: String? = null,
    @SerializedName("checkAthletesNum") var checkAthletesNum: String? = null,
    @SerializedName("waitlist") var waitlist: String? = null,
    @SerializedName("cancelledId") var cancelledId: String? = null,
    @SerializedName("color") var color: String? = null,
    @SerializedName("classLength") var classLength: String? = null,
    @SerializedName("resadmin") var resadmin: String? = null,
    @SerializedName("included") var included: Int? = null
)