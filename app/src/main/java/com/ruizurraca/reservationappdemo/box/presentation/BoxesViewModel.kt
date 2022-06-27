package com.ruizurraca.reservationappdemo.box.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruizurraca.reservationappdemo.box.domain.repository.BoxesRepository
import com.ruizurraca.reservationappdemo.box.presentation.models.BoxesListResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoxesViewModel @Inject constructor(private val boxesRepository: BoxesRepository) :
    ViewModel() {

    private val boxesLiveData = MutableLiveData<BoxesListResponseModel>()

    val boxes: MutableLiveData<BoxesListResponseModel>
        get() {
            return boxesLiveData
        }

    fun getBoxes() {
        viewModelScope.launch {
            boxesRepository.getBoxes().let { boxesLiveData.postValue(it) }
        }
    }
}
