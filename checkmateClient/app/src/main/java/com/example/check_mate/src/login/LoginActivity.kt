package com.example.check_mate.src.login


import android.app.Instrumentation.ActivityResult
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.check_mate.R
import com.example.check_mate.config.ApplicationClass.Companion.sSharedPreferences
import com.example.check_mate.databinding.ActivityLoginBinding
import com.example.check_mate.src.main.MainActivity
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.NidOAuthPreferencesManager.errorCode
import com.navercorp.nid.oauth.NidOAuthPreferencesManager.errorDescription
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse


class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(loginBinding.root)
        val TAG = "login"

        val kakaoKey = getString(R.string.kakao_native_app_key)
        val naverClientID = getString(R.string.naver_client_id)
        val naverClientSecret = getString(R.string.naver_client_secret)

        KakaoSdk.init(this, kakaoKey)

        fun moveMainPage() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        // 로그인 버튼
        loginBinding.loginBTN.setOnClickListener {
            val id = loginBinding.loginID.text.toString()
            val pw = loginBinding.loginPW!!.text.toString()

            // 쉐어드로부터 저장된 id, pw 값 가져오기
            val savedID = sSharedPreferences.getString("id", "")
            val savedPW = sSharedPreferences.getString("pw", "")


            // 빈 항목이 존재하면 오류 메시지 출력
            if (id == "" || pw == "") {
                loginBinding.errorText.error = "* 모든 항목을 입력해주세요."
            } else {
                // ID PW 일치시 메인 화면으로 이동
                if (id == savedID && pw == savedPW) {
                    moveMainPage()
                } else {
                    // 일치하지 않을 시 오류 메시지 출력력
                    loginBinding.errorText.error = "* 아이디, 비밀번호가 일치하지 않습니다."
                }
            }
        }


        // 네이버 로그인
        loginBinding.naverLogin.setOnClickListener {
            val oAuthLoginCallback = object : OAuthLoginCallback {
                override fun onSuccess() {

                    // 메인 페이지로 이동
                    moveMainPage()

                    // 네이버 로그인 API 호출 성공 시 유저 정보 획득
                    NidOAuthLogin().callProfileApi(object : NidProfileCallback<NidProfileResponse> {
                        override fun onSuccess(result: NidProfileResponse) {
                            val name = result.profile?.name.toString()
                            val email = result.profile?.email.toString()
                            Log.e(TAG, "login(Naver) NAME: $name")
                            Log.e(TAG, "login(Naver) EMAIL: $email")
                            finish()
                        }


                        override fun onError(errorCode: Int, message: String) {
                            onFailure(errorCode, message)
                        }

                        override fun onFailure(httpStatus: Int, message: String) {
                            val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                            val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                            Toast.makeText(
                                this@LoginActivity, "errorCode: ${errorCode}\n" +
                                        "errorDescription: ${errorDescription}", Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

                }

                override fun onError(errorCode: Int, message: String) {
                    val naverAccessToken = NaverIdLoginSDK.getAccessToken()
                    Log.e(TAG, "naverAccessToken : $naverAccessToken")
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                    val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                    Toast.makeText(
                        this@LoginActivity, "errorCode: ${errorCode}\n" +
                                "errorDescription: ${errorDescription}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
            NaverIdLoginSDK.initialize(this, naverClientID, naverClientSecret, "Checkmate")
            NaverIdLoginSDK.authenticate(this, oAuthLoginCallback)
        }



        // 카카오 로그인
        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
            }
            else if (tokenInfo != null) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                moveMainPage()
                finish()
            }
        }
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if (token != null) {
                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                moveMainPage()
                finish()
            }
        }

        loginBinding.kakaoLogin.setOnClickListener {
            if(LoginClient.instance.isKakaoTalkLoginAvailable(this)) {
                LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }


        // 비밀번호 분실
        loginBinding.losePW.setOnClickListener {
            val intent = Intent(this, PwChangeFirstActivity::class.java)
            startActivity(intent)
        }

    }
}
