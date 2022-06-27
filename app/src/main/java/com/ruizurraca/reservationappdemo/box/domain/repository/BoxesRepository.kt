package com.ruizurraca.reservationappdemo.box.domain.repository

import com.ruizurraca.reservationappdemo.box.presentation.models.BoxesListResponseModel

interface BoxesRepository {
    suspend fun getBoxes(): BoxesListResponseModel
}
