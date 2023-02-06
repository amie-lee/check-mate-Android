package com.example.check_mate.src.main.mate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.check_mate.config.BaseActivity
import com.example.check_mate.databinding.ActivityMainBinding
import com.example.check_mate.databinding.ActivityNoticeBinding

class NoticeActivity: BaseActivity<ActivityNoticeBinding>(ActivityNoticeBinding::inflate){

    private lateinit var noticeBinding: ActivityNoticeBinding

    val noticeData = arrayListOf<NoticeData>()
    val NoticeAdapter = NoticeAdapter(noticeData)

    override fun onCreate(savedInstanceState: Bundle?) {
        noticeBinding = ActivityNoticeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(noticeBinding.root)

        noticeBinding.noticeImgbtnBack.setOnClickListener {
            finish()
        }
        

        noticeBinding.recycleNotice.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        noticeBinding.recycleNotice.adapter = NoticeAdapter


        noticeData.add(NoticeData("메이트22", "님과 메이트가 되었습니다."))
        noticeData.add(NoticeData("메이트11", "님이 수집품 룩을 잠금 해제했습니다."))
        noticeData.add(NoticeData("메이트11", "님과 메이트가 되었습니다."))
    }


}
