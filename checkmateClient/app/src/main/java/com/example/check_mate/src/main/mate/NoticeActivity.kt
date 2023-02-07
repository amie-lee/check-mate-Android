package com.example.check_mate.src.main.mate

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.check_mate.R
import com.example.check_mate.config.BaseActivity
import com.example.check_mate.databinding.ActivityNoticeBinding

class NoticeActivity: BaseActivity<ActivityNoticeBinding>(ActivityNoticeBinding::inflate){

    private lateinit var noticeBinding: ActivityNoticeBinding

    private val data = arrayListOf<NoticeData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        noticeBinding = ActivityNoticeBinding.inflate(layoutInflater)
        val view = noticeBinding.root
        setContentView(view)

        noticeBinding.recycleNotice.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        noticeBinding.recycleNotice.adapter = NoticeAdapter(data,
            onClickDeleteIcon = {
                deleteTask(it)
            }
        )

        // <- 버튼 누르면 mate 첫 화면으로 이동
        noticeBinding.noticeImgbtnBack.setOnClickListener {
            finish()
        }

        data.add(NoticeData("메이트22", "님과 메이트가 되었습니다."))
        data.add(NoticeData("메이트11", "님이 수집품 룩을 잠금 해제했습니다."))
        data.add(NoticeData("메이트11", "님과 메이트가 되었습니다."))
    }


    fun deleteTask(noticeData: NoticeData) {
        data.remove(noticeData)
        noticeBinding.recycleNotice.adapter?.notifyDataSetChanged()
    }

}
