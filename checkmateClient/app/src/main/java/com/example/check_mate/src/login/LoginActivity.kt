package com.example.check_mate.src.login

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.check_mate.R

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.btn_login)
        button.paintFlags = button.paintFlags or Paint.UNDERLINE_TEXT_FLAG

    }
}