package com.ruizurraca.reservationappdemo.classes.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruizurraca.reservationappdemo.classes.data.models.ClassesResponse
import com.ruizurraca.reservationappdemo.classes.domain.repository.ClassesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassesViewModel @Inject constructor(private val classesRepository: ClassesRepository) :
    ViewModel() {

    private val classesLiveData = MutableLiveData<ClassesResponse?>()

    val classes: MutableLiveData<ClassesResponse?>
        get() {
            return classesLiveData
        }

    fun getClasses(boxId: String, date: String, cookies: List<String>?) {
        viewModelScope.launch {
            classesRepository.getClasses(boxId, date, cookies)
                .let { classesLiveData.postValue(it) }
        }
    }
}