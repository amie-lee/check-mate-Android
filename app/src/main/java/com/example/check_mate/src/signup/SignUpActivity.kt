package com.example.check_mate.src.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Paint
import com.example.check_mate.config.BaseActivity
import com.example.check_mate.databinding.ActivityRegistBinding
import com.example.check_mate.databinding.ActivitySignUpBinding
import com.example.check_mate.src.login.LoginActivity

class SignUpActivity: BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val button = binding.btnLogin
        val buttonregist = binding.btStartEmail
//        button.paintFlags = button.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("text", "first")
            startActivity(intent)
        }

        buttonregist.setOnClickListener{
            val intent = Intent(this, RegistActivity::class.java)
            intent.putExtra("text", "first")
            startActivity(intent)
        }


    }
}