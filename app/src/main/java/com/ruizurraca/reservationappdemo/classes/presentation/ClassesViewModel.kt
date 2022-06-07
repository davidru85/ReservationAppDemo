package com.ruizurraca.reservationappdemo.classes.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruizurraca.reservationappdemo.classes.data.models.BookClassResponse
import com.ruizurraca.reservationappdemo.classes.data.models.Bookings
import com.ruizurraca.reservationappdemo.classes.data.models.ClassesResponse
import com.ruizurraca.reservationappdemo.classes.domain.repository.ClassesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassesViewModel @Inject constructor(private val classesRepository: ClassesRepository) :
    ViewModel() {

    private val classesLiveData = MutableLiveData<ClassesResponse?>()
    private val bookClassLiveData = MutableLiveData<BookClassResponse?>()

    val classes: MutableLiveData<ClassesResponse?>
        get() {
            return classesLiveData
        }

    val bookClass: MutableLiveData<BookClassResponse?>
        get() {
            return bookClassLiveData
        }

    fun getClasses(boxId: String, date: String) {
        viewModelScope.launch {
            classesRepository.getClasses(boxId, date)
                .let { classesLiveData.postValue(it) }
        }
    }

    fun bookClass(currentDate: String, currentClass: Bookings) {
        viewModelScope.launch {
            currentClass.id?.let { classId ->
                classesRepository.bookClass(classId, currentDate)
                    .let { bookClassLiveData.postValue(it) }
            }
        }
    }
}