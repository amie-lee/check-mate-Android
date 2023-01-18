package com.example.check_mate.src.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.check_mate.R
import com.example.check_mate.config.BaseActivity
import com.example.check_mate.databinding.ActivityPwChangeFirstBinding

class PwChangeFirstActivity : BaseActivity<ActivityPwChangeFirstBinding>(ActivityPwChangeFirstBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val emailEdit: EditText = binding.etEmail
        val submitBtn: Button = binding.btnSubmit
        val nextBtn: Button = binding.btnNext

        var errorMsg: TextView = binding.tvError
        var email: String = ""
        var certification = false

        errorMsg.text = getString(R.string.email_empty)
        submitBtn.isEnabled = false
        nextBtn.isEnabled = false

        emailEdit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                email = emailEdit.text.toString()
                if(email.isEmpty()){
                    errorMsg.text = getString(R.string.email_empty)
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    errorMsg.text = getString(R.string.email_error)
                } else {
                    errorMsg.text = ""
                    submitBtn.isEnabled = true
                    submitBtn.setOnClickListener {
                        showCustomToast("$email")
                        nextBtn.isEnabled = true
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.btnNext.setOnClickListener {
            var intent = Intent(this, PwChangeSecondActivity::class.java)
            startActivity(intent)
        }
    }
}