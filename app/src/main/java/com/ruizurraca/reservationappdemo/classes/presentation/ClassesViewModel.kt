package com.ruizurraca.reservationappdemo.classes.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruizurraca.reservationappdemo.classes.domain.repository.ClassesRepository
import com.ruizurraca.reservationappdemo.classes.presentation.models.BookClassModel
import com.ruizurraca.reservationappdemo.classes.presentation.models.BookingsModel
import com.ruizurraca.reservationappdemo.classes.presentation.models.ClassesResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassesViewModel @Inject constructor(private val classesRepository: ClassesRepository) :
    ViewModel() {

    private val classesLiveData = MutableLiveData<ClassesResponseModel>()
    private val bookClassLiveData = MutableLiveData<BookClassModel>()

    val classes: MutableLiveData<ClassesResponseModel>
        get() {
            return classesLiveData
        }

    val bookClass: MutableLiveData<BookClassModel>
        get() {
            return bookClassLiveData
        }

    fun getClasses(boxId: String, date: String) {
        viewModelScope.launch {
            classesRepository.getClasses(boxId, date)
                .let { classesLiveData.postValue(it) }
        }
    }

    fun bookClass(currentDate: String, currentClass: BookingsModel) {
        viewModelScope.launch {
            currentClass.id?.let { classId ->
                classesRepository.bookClass(classId, currentDate)
                    .let { bookClassLiveData.postValue(it) }
            }
        }
    }
}