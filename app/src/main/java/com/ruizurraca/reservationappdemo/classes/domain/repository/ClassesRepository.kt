package com.ruizurraca.reservationappdemo.classes.domain.repository

import com.ruizurraca.reservationappdemo.classes.presentation.models.BookClassModel
import com.ruizurraca.reservationappdemo.classes.presentation.models.ClassesResponseModel

interface ClassesRepository {
    suspend fun getClasses(boxId: String, date: String): ClassesResponseModel

    suspend fun bookClass(classId: String, date: String, time: String? = null): BookClassModel
}
