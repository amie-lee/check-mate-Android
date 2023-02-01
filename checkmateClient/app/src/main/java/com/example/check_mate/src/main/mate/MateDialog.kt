package com.example.check_mate.src.main.mate

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.check_mate.databinding.MateCustomdialogChessPawnBinding
import kotlinx.android.synthetic.main.mate_customdialog_chess_pawn.*


class MateDialog(
    context: Context,
    private val okCallback: (String) -> Unit,
) : Dialog(context) { // 뷰를 띄워야하므로 Dialog 클래스는 context를 인자로 받는다.

    private lateinit var binding: MateCustomdialogChessPawnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 만들어놓은 dialog_profile.xml 뷰를 띄운다.
        binding = MateCustomdialogChessPawnBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        // 뒤로가기 버튼, 빈 화면 터치를 통해 dialog가 사라지지 않도록
        setCancelable(false)


        // 닫기 Button 클릭에 대한 Callback 처리
        mate_dialog_btn_close.setOnClickListener {
            dismiss()
        }
    }
}
