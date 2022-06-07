package com.ruizurraca.reservationappdemo.classes.presentation.models

import com.ruizurraca.reservationappdemo.classes.data.models.BookingsResponse
import com.ruizurraca.reservationappdemo.classes.data.models.ClassesResponse
import com.ruizurraca.reservationappdemo.classes.data.models.TimetableResponse

data class ClassesResponseModel(
    var clasesDisp: String? = null,
    var timetable: List<TimetableModel> = listOf(),
    var day: String? = null,
    var bookings: List<BookingsModel> = listOf(),
    var seminars: List<String> = listOf()
) {
    companion object {
        fun fromDTO(classesResponse: ClassesResponse?): ClassesResponseModel {
            return ClassesResponseModel(
                clasesDisp = classesResponse?.clasesDisp,
                day = classesResponse?.day,
                seminars = classesResponse?.seminars ?: mutableListOf(),
                timetable = mutableListOf<TimetableModel>().apply {
                    classesResponse?.timetable?.forEach {
                        add(TimetableModel.fromDTO(it))
                    }
                },
                bookings = mutableListOf<BookingsModel>().apply {
                    classesResponse?.bookings?.forEach {
                        add(BookingsModel.fromDTO(it))
                    }
                }
            )
        }
    }
}

data class TimetableModel(
    var id: String? = null,
    var time: String? = null
) {
    companion object {
        fun fromDTO(timetableResponse: TimetableResponse) =
            TimetableModel(id = timetableResponse.id, time = timetableResponse.time)

    }
}

data class BookingsModel(
    var id: String? = null,
    var zoomid: String? = null,
    var zoomJoinUrl: String? = null,
    var zoomJoinPw: String? = null,
    var onlineclass: Int? = null,
    var idres: String? = null,
    var spotres: String? = null,
    var time: String? = null,
    var timeid: String? = null,
    var classId: String? = null,
    var className: String? = null,
    var classDesc: String? = null,
    var boxName: String? = null,
    var boxDir: String? = null,
    var boxPic: String? = null,
    var coachName: String? = null,
    var coachPic: String? = null,
    var enabled: Int? = null,
    var bookState: String? = null,
    var limit: String? = null,
    var limitc: String? = null,
    var ocupation: String? = null,
    var checkAthletesNum: String? = null,
    var waitlist: String? = null,
    var cancelledId: String? = null,
    var color: String? = null,
    var classLength: String? = null,
    var resadmin: String? = null,
    var included: Int? = null
) {
    companion object {
        fun fromDTO(bookingsResponse: BookingsResponse) =
            BookingsModel(
                id = bookingsResponse.id,
                zoomid = bookingsResponse.zoomid,
                zoomJoinUrl = bookingsResponse.zoomJoinUrl,
                zoomJoinPw = bookingsResponse.zoomJoinPw,
                onlineclass = bookingsResponse.onlineclass,
                idres = bookingsResponse.idres,
                spotres = bookingsResponse.spotres,
                time = bookingsResponse.time,
                timeid = bookingsResponse.timeid,
                classId = bookingsResponse.classId,
                className = bookingsResponse.className,
                classDesc = bookingsResponse.classDesc,
                boxName = bookingsResponse.boxName,
                boxDir = bookingsResponse.boxDir,
                boxPic = bookingsResponse.boxPic,
                coachName = bookingsResponse.coachName,
                coachPic = bookingsResponse.coachPic,
                enabled = bookingsResponse.enabled,
                bookState = bookingsResponse.bookState,
                limit = bookingsResponse.limit,
                limitc = bookingsResponse.limitc,
                ocupation = bookingsResponse.ocupation,
                checkAthletesNum = bookingsResponse.checkAthletesNum,
                waitlist = bookingsResponse.waitlist,
                cancelledId = bookingsResponse.cancelledId,
                color = bookingsResponse.color,
                classLength = bookingsResponse.classLength,
                resadmin = bookingsResponse.resadmin,
                included = bookingsResponse.included
            )

    }
}
