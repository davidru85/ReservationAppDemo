package com.ruizurraca.reservationappdemo.login.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.ruizurraca.reservationappdemo.databinding.ActivityLoginBinding
import com.ruizurraca.reservationappdemo.login.data.models.LoginResult
import com.ruizurraca.reservationappdemo.login.presentation.models.LoginModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginModel = LoginModel()
    private val viewModel by viewModels<LoginViewModel>()

    companion object {
        val TAG = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
    }

    private fun initObservers() {
        viewModel.login.observe(this, { manageLoginResult(it) })
    }

    private fun manageLoginResult(loginResult: LoginResult) {
        if (loginResult.success) {
            loginSuccess()
        } else {
            loginFailed()
        }
    }

    private fun loginSuccess() {
        Log.d(TAG, "loginSuccess")
    }

    private fun loginFailed() {
        Log.d(TAG, "loginFailed")
    }

    override fun onStart() {
        super.onStart()
        binding.etLoginUser.doAfterTextChanged {
            loginModel.user = it.toString()
        }
        binding.etLoginPass.doAfterTextChanged {
            loginModel.password = it.toString()
        }
        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        viewModel.login(loginModel)
    }
}