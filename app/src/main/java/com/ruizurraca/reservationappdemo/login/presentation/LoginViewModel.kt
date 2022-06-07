package com.ruizurraca.reservationappdemo.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruizurraca.reservationappdemo.login.presentation.models.LoginResult
import com.ruizurraca.reservationappdemo.login.domain.repository.LoginRepository
import com.ruizurraca.reservationappdemo.login.presentation.models.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val loginLiveData = MutableLiveData<LoginResult>()

    val login: MutableLiveData<LoginResult>
        get() {
            return loginLiveData
        }

    fun login(loginModel: LoginModel) {
        viewModelScope.launch {
            loginRepository.login(loginModel.toLoginModelApi()).let { loginLiveData.postValue(it) }
        }
    }
}