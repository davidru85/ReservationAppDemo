package com.ruizurraca.reservationappdemo.common

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.dateToApi(): String {
    return format((DateTimeFormatter.BASIC_ISO_DATE))
}

fun String.dateFromApi(): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.BASIC_ISO_DATE)
}

fun String.timeToApi(): String {
    return this.split("-")[0]
}
