package com.ruizurraca.reservationappdemo.classes.data.repository

import android.util.Log
import com.ruizurraca.reservationappdemo.classes.data.api.AimharderClassesApi
import com.ruizurraca.reservationappdemo.classes.data.models.ClassesResponse
import com.ruizurraca.reservationappdemo.classes.domain.repository.ClassesRepository
import javax.inject.Inject


class ClassesRepositoryImpl @Inject constructor(private val aimharderClassesApi: AimharderClassesApi) :
    ClassesRepository {

    companion object {
        const val TAG = "ClassesRepositoryImpl"
    }

    override suspend fun getClasses(boxId: String, date: String, cookies: List<String>?): ClassesResponse? {
        Log.d(TAG, "getClasses: ${getHeaders(cookies)}")
        val response = aimharderClassesApi.getClasses(
            headers = getHeaders(cookies),
            day = date,
            box = boxId,
            familyId = ""
        )
        Log.d(TAG, "getClasses: $response")
        return response.body()
    }

    private fun getHeaders(cookies: List<String>?): Map<String, String> {
        return mutableMapOf<String, String>().apply {
            cookies?.forEach { cookie ->

                val splittedCookie = cookie.split("=", limit = 2)
                this[splittedCookie.get(0)] = splittedCookie.get(1)
            }
        }
    }
}