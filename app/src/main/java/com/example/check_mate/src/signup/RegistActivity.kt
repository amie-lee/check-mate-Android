package com.example.check_mate.src.signup

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.check_mate.databinding.ActivityRegistBinding
import com.example.check_mate.src.login.LoginActivity
import java.util.regex.Pattern

class RegistActivity : AppCompatActivity() {
    private lateinit var registBinding: ActivityRegistBinding
    var imm : InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as
                InputMethodManager?

        registBinding = ActivityRegistBinding.inflate(layoutInflater)
        setContentView(registBinding.root)

        registBinding.btnRegister1.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("text", "first")
            startActivity(intent)
        }
    }
    fun hideKeyboard(v: View){
        if(v != null){
            imm?.hideSoftInputFromWindow(v.windowToken,0)
        }
    }
}