package com.example.check_mate.src.main.mate

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.check_mate.databinding.MateDialogKingBinding


class MateDialogKing (
    context: Context,
    private val okCallback: (String) -> Unit,
) : Dialog(context) {

    private lateinit var binding: MateDialogKingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MateDialogKingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 기본 흰색 배경 없애기 위해
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initViews()
    }

    private fun initViews() = with(binding) {
        setCancelable(false)
            mateBtnClose.setOnClickListener {
                dismiss()
            }
    }

}
