package com.example.check_mate.src.main.mate

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.check_mate.R
import com.example.check_mate.config.BaseFragment
import com.example.check_mate.databinding.FragmentMateBinding
import kotlinx.android.synthetic.main.fragment_mate.*


class  MateFragment : BaseFragment<FragmentMateBinding>(FragmentMateBinding::bind, R.layout.fragment_mate) {

    val mateData = arrayListOf<MateData>()
    val MateAdapter = MateAdapter(mateData)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mateBinding = FragmentMateBinding.inflate(inflater, container,false)

        mateBinding.mateBtnNotice.setOnClickListener {
            val intent = Intent(getActivity(), NoticeActivity::class.java)
            startActivity(intent)
        }


        mateBinding.mateBtnPlus.setOnClickListener {
            val intent = Intent(getActivity(), FindMateActivity::class.java)
            startActivity(intent)
        }


        mateBinding.mateImgPawn.setOnClickListener{
            showDialogPawn()
        }

        mateBinding.mateImgRook.setOnClickListener{
            showDialogRook()
        }

        mateBinding.mateImgBishop.setOnClickListener{
            showDialogBishop()
        }

        mateBinding.mateImgKnight.setOnClickListener{
            showDialogKnight()
        }

        mateBinding.mateImgQueen.setOnClickListener{
            showDialogQueen()
        }

        mateBinding.mateImgKing.setOnClickListener{
            showDialogKing()
        }



        mateBinding.recycleGroupMateAchievement.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        mateBinding.recycleGroupMateAchievement.adapter = MateAdapter


        mateData.add(MateData("메리", "70",70))
        mateData.add(MateData("우사", "45",45))

        return mateBinding.root
    }
    private fun showDialogPawn() {
        MateDialogPawn(requireContext()) {
        }.show()
    }

    private fun showDialogRook() {
        MateDialogRook(requireContext()) {
        }.show()
    }

    private fun showDialogBishop() {
        MateDialogBishop(requireContext()) {
        }.show()
    }

    private fun showDialogKnight() {
        MateDialogKnight(requireContext()) {
        }.show()
    }

    private fun showDialogQueen() {
        MateDialogQueen(requireContext()) {
        }.show()
    }

    private fun showDialogKing() {
        MateDialogKing(requireContext()) {
        }.show()
    }
}