package com.example.check_mate.src.signup

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.check_mate.databinding.ActivityRegistBinding
import kotlinx.android.synthetic.main.activity_regist.*
import java.util.regex.Pattern

class RegistActivity : AppCompatActivity() {
    lateinit var registBinding: ActivityRegistBinding
    val TAG: String = "Register"
    var isExistBlank = false
    var isPWSame = false

    override fun onCreate(savedInstanceState: Bundle?) {
        registBinding = ActivityRegistBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(registBinding.root)

        registBinding.btnRegister1.setOnClickListener {
            Log.d(TAG, "회원가입 버튼 클릭")

            val email = email_edit.text.toString()
            val pw = PW_edit.text.toString()
            val pw_check = PW_check_edit.text.toString()

            // 유저가 항목을 다 채우지 않았을 경우
            if (email.isEmpty() || pw.isEmpty() || pw_check.isEmpty()) {
                isExistBlank = true
                //다음으로 넘어가기 비활성화
                btn_register1.isEnabled = false

            } else {
                if (pw == pw_check) {
                    isPWSame = true
                    // 다음 버튼 활성화
                    btn_register1.isEnabled = true
                }
            }
            //원래 Email.matches() 끝인데.. 고침
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                email_text.setTextColor(Color.parseColor(("FFFF5959")))
            } else if (!Pattern.matches(
                    "^(?=.*\\d)(?=.*[~`!@#$%^&*()-])(?=.*[a-zA-Z]).{8,20}$",
                    pw
                )
            ) {
                PW_text.setTextColor(Color.parseColor(("FFFF5959")))

            }

            if (!isExistBlank && isPWSame) {

                // 유저가 입력한 id, pw를 쉐어드에 저장한다.
                val sharedPreference = getSharedPreferences("file name", Context.MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putString("email", email)
                editor.putString("pw", pw)
                editor.apply()

                // 프로필 입력 화면으로 이동
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)

            } else {
                // 상태에 따라 다른 text 색상 다르게 해줌
                if (isExistBlank) {   // 작성 안한 항목이 있을 경우
                    Toast.makeText(
                        this@RegistActivity,
                        "회원 정보를 모두 입력해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (!isPWSame) { // 입력한 비밀번호가 다를 경우 빨간 색으로 문장 드러나게 함
                    PW_check_text.setTextColor(Color.parseColor(("FFFF5959")))

                }
            }

        }
    }


}