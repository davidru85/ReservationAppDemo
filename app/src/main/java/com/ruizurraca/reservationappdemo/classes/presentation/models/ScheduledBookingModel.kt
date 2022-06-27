package com.ruizurraca.reservationappdemo.classes.presentation.models

import com.ruizurraca.reservationappdemo.common.dateFromApi
import com.ruizurraca.reservationappdemo.common.dateToApi
import com.ruizurraca.reservationappdemo.common.timeToApi
import com.ruizurraca.reservationappdemo.login.presentation.models.LoginModel

data class ScheduledBookingModel(
    val user: String,
    val pass: String,
    val scheduledDate: String,
    val scheduledTime: String,
    val bookingId: String,
    val bookingDay: String
) {
    companion object {
        fun fromFailedRequest(
            bookClassModel: BookClassModel,
            credentials: LoginModel
        ): ScheduledBookingModel? =
            if (isScheduleValid(bookClassModel, credentials)) {
                ScheduledBookingModel(
                    user = credentials.user,
                    pass = credentials.password,
                    scheduledDate = getScheduledDate(bookClassModel.bookClassRequest.day, 4),
                    scheduledTime = bookClassModel.bookClassRequest.time!!.timeToApi(),
                    bookingId = bookClassModel.bookClassRequest.id,
                    bookingDay = bookClassModel.bookClassRequest.day
                )
            } else {
                null
            }

        private fun isScheduleValid(
            bookClassModel: BookClassModel,
            credentials: LoginModel
        ): Boolean = when {
            credentials.user.isNotEmpty() &&
            credentials.password.isNotEmpty() &&
            bookClassModel.bookClassRequest.day.isNotEmpty() &&
            bookClassModel.bookClassRequest.time?.isNotEmpty() == true &&
            bookClassModel.bookClassRequest.id.isNotEmpty() &&
            bookClassModel.bookClassRequest.day.isNotEmpty() -> true
            else -> false
        }

        private fun getScheduledDate(day: String, daysInAdvance: Long): String =
            day.dateFromApi().minusDays(daysInAdvance).dateToApi()
    }
}
