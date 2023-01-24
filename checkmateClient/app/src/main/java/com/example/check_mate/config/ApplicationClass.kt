package com.example.check_mate.config

import android.app.Application
import android.content.SharedPreferences
import com.example.check_mate.R
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

//앱이 실행될 때 1번만 실행
class ApplicationClass: Application() {
    //val API_URL = "추후에 추가"

    companion object {
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var sSharedPreferences: SharedPreferences

        // Retrofit 인스턴스, 앱 실행시 한번만 생성하여 사용합니다.
        lateinit var sRetrofit: Retrofit
    }

    // 앱이 처음 생성되는 순간, SP를 새로 만들어주고 (, 레트로핏 인스턴스를 생성합니다.)
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, getString(R.string.kakao_native_app_key))
        NaverIdLoginSDK.initialize(this, getString(R.string.naver_client_id), getString(R.string.naver_client_secret), getString(R.string.app_name))
        sSharedPreferences =
            applicationContext.getSharedPreferences("CHECK_MATE_APP", MODE_PRIVATE)
    }

}