package com.example.check_mate.src.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.example.check_mate.R
import com.example.check_mate.config.BaseActivity
import com.example.check_mate.databinding.ActivityPwChangeSecondBinding
import java.util.regex.Pattern

class PwChangeSecondActivity : BaseActivity<ActivityPwChangeSecondBinding>(ActivityPwChangeSecondBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newPw: EditText = binding.etNewPw
        val confirmPw: EditText = binding.etPwConfirm
        val completeBtn : Button = binding.btnComplete

        var pw : String = ""
        completeBtn.isEnabled = false

        newPw.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isEmpty()){
                    binding.layoutNewPw.error = "공백은 허용하지않습니다."
                } else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}\$", p0.toString())) {
                    binding.layoutNewPw.error = getString(R.string.pw_condition)
                } else {
                    binding.layoutNewPw.error = null
                    pw = newPw.text.toString()
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        //비밀번호 확인 말고 비밀번호 자체가 바꼈을때도 감지를 하면 좋을거같은데,,
        confirmPw.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (pw != p0.toString()){
                    binding.layoutPwConfirm.error = getString(R.string.pw_confirm)
                    completeBtn.isEnabled = false
                } else {
                    binding.layoutPwConfirm.error = null
                    completeBtn.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        completeBtn.setOnClickListener {
            showCustomToast("$pw")
        }
    }
}