package com.ruizurraca.reservationappdemo.login.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.ruizurraca.reservationappdemo.box.presentation.BoxesActivity
import com.ruizurraca.reservationappdemo.common.Prefs
import com.ruizurraca.reservationappdemo.common.deleteCookies
import com.ruizurraca.reservationappdemo.common.deleteCredentials
import com.ruizurraca.reservationappdemo.common.saveCredentials
import com.ruizurraca.reservationappdemo.databinding.ActivityLoginBinding
import com.ruizurraca.reservationappdemo.login.presentation.models.LoginModel
import com.ruizurraca.reservationappdemo.login.presentation.models.LoginResult
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
        if (loginResult.errorString?.isNotEmpty() == true) {
            loginFailed(loginResult.errorString)
        } else {
            loginSuccess(loginResult)
        }
    }

    private fun loginSuccess(loginResult: LoginResult) {
        Prefs.saveCredentials(loginResult.loginModel)
        startActivity(Intent(this, BoxesActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }


    private fun loginFailed(errorString: String?) {
        Prefs.deleteCookies()
        Prefs.deleteCredentials()
        Log.d(TAG, "loginFailed: $errorString")
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
