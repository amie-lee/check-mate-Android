package com.example.check_mate.src.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.check_mate.databinding.ActivityProfileBinding
import com.example.check_mate.src.main.MainActivity

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister2.setOnClickListener {
            val intent: Intent = Intent(this@ProfileActivity, MainActivity::class.java)
            startActivity(intent)
            // 완료 하고 나면 mainactivity 의 로그인 화면으로 이동하도록 해 놓음.        }
        }
    }
}