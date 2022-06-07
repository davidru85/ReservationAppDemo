package com.ruizurraca.reservationappdemo.classes.data.repository

import android.util.Log
import com.ruizurraca.reservationappdemo.classes.data.api.AimharderClassesApi
import com.ruizurraca.reservationappdemo.classes.data.models.ClassesResponse
import com.ruizurraca.reservationappdemo.classes.domain.repository.ClassesRepository
import com.ruizurraca.reservationappdemo.classes.presentation.models.BookClassModel
import com.ruizurraca.reservationappdemo.classes.presentation.models.ClassesResponseModel
import javax.inject.Inject


class ClassesRepositoryImpl @Inject constructor(private val aimharderClassesApi: AimharderClassesApi) :
    ClassesRepository {

    companion object {
        const val TAG = "ClassesRepositoryImpl"
    }

    override suspend fun getClasses(
        boxId: String,
        date: String
    ): ClassesResponseModel {
        val response = aimharderClassesApi.getClasses(
            day = date,
            box = boxId,
            familyId = ""
        )
        return ClassesResponseModel.fromDTO(response.body())
    }

    override suspend fun bookClass(
        classId: String,
        date: String
    ): BookClassModel {
        val response = aimharderClassesApi.bookClass(
            id = classId,
            day = date,
            insist = 0,
            familyId = ""
        )

        return BookClassModel.fromDTO(
            dto = response.body(),
            id = classId,
            day = date,
        )
    }
}