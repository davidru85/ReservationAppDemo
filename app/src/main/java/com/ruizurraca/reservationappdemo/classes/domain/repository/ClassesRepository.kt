package com.ruizurraca.reservationappdemo.classes.domain.repository

import com.ruizurraca.reservationappdemo.classes.data.models.ClassesResponse

interface ClassesRepository {
    suspend fun getClasses(boxId: String, date: String, cookies: List<String>?): ClassesResponse?
}