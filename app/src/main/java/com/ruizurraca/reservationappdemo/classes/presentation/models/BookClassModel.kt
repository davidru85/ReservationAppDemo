package com.ruizurraca.reservationappdemo.classes.presentation.models

import com.ruizurraca.reservationappdemo.classes.data.models.BookClassResponse

data class BookClassModel(
    val bookClassDtoResponse: BookClassDtoResponse?,
    val bookClassRequest: BookClassRequest
) {
    companion object {
        fun fromDTO(
            dto: BookClassResponse?,
            id: String,
            day: String,
        ): BookClassModel {
            return BookClassModel(
                bookClassDtoResponse = BookClassDtoResponse.fromDTO(dto),
                bookClassRequest = BookClassRequest(id = id, day = day)
            )
        }
    }

    data class BookClassRequest(val id: String, val day: String)
    data class BookClassDtoResponse(
        var clasesContratadas: String? = null,
        var hasPublicMemberships: Int? = null,
        var bookState: Int? = null,
        var id: String? = null,
        var max: Int? = null,
        var errorMssg: String? = null,
        var errorMssgLang: String? = null
    ) {
        companion object {
            fun fromDTO(bookClassResponse: BookClassResponse?): BookClassDtoResponse {
                return BookClassDtoResponse(
                    clasesContratadas = bookClassResponse?.clasesContratadas,
                    hasPublicMemberships = bookClassResponse?.hasPublicMemberships,
                    bookState = bookClassResponse?.bookState,
                    id = bookClassResponse?.id,
                    max = bookClassResponse?.max,
                    errorMssg = bookClassResponse?.errorMssg,
                    errorMssgLang = bookClassResponse?.errorMssgLang
                )
            }
        }
    }
}