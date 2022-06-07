package com.ruizurraca.reservationappdemo.classes.data.models

import com.google.gson.annotations.SerializedName


data class BookClassResponse(
    @SerializedName("clasesContratadas") var clasesContratadas: String? = null,
    @SerializedName("hasPublicMemberships") var hasPublicMemberships: Int? = null,
    @SerializedName("bookState") var bookState: Int? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("max") var max: Int? = null,
    @SerializedName("errorMssg") var errorMssg: String? = null,
    @SerializedName("errorMssgLang") var errorMssgLang: String? = null
)