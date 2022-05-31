package com.ruizurraca.reservationappdemo.login.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.ruizurraca.reservationappdemo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginModel = LoginModel()

    companion object {
        val TAG = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            Log.d(TAG, "btnLogin: $loginModel")
        }
    }
}