package com.ruizurraca.reservationappdemo.classes.data.repository

import android.util.Log
import com.ruizurraca.reservationappdemo.classes.data.api.AimharderClassesApi
import com.ruizurraca.reservationappdemo.classes.data.models.BookClassResponse
import com.ruizurraca.reservationappdemo.classes.data.models.ClassesResponse
import com.ruizurraca.reservationappdemo.classes.domain.repository.ClassesRepository
import javax.inject.Inject


class ClassesRepositoryImpl @Inject constructor(private val aimharderClassesApi: AimharderClassesApi) :
    ClassesRepository {

    companion object {
        const val TAG = "ClassesRepositoryImpl"
    }

    override suspend fun getClasses(
        boxId: String,
        date: String
    ): ClassesResponse? {
        val response = aimharderClassesApi.getClasses(
            day = date,
            box = boxId,
            familyId = ""
        )
        Log.d(TAG, "getClasses: $response")
        return response.body()
    }

    override suspend fun bookClass(
        classId: String,
        date: String
    ): BookClassResponse? {
        val response = aimharderClassesApi.bookClass(
            id = classId,
            day = date,
            insist = 0,
            familyId = ""
        )
        Log.d(TAG, "bookClass: $response")
        return response.body()
    }
}