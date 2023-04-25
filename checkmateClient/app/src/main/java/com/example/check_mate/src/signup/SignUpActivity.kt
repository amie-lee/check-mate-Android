package com.example.check_mate.src.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Paint
import com.example.check_mate.config.BaseActivity
import com.example.check_mate.databinding.ActivitySignUpBinding

class SignUpActivity: BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val button = binding.btnLogin
        button.paintFlags = button.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
}