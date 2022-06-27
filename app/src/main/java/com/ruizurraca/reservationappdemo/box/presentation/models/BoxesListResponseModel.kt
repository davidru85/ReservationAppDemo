package com.ruizurraca.reservationappdemo.box.presentation.models

data class BoxesListResponseModel(val boxesList: List<BoxResponseModel>)

data class BoxResponseModel(
    var url: String? = null,
    var title: String? = null,
    var photo: String? = null
) {
    fun isFilled() = url != null && title != null && photo != null
}

