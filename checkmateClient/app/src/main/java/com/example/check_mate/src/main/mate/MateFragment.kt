package com.example.check_mate.src.main.mate

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.check_mate.R
import com.example.check_mate.config.BaseFragment
import com.example.check_mate.databinding.FragmentMateBinding


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
            showChessDialog()
        }

        mateBinding.mateImgBishop.setOnClickListener{
            showChessDialog()
        }

        mateBinding.recycleGroupMateAchievement.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        mateBinding.recycleGroupMateAchievement.adapter = MateAdapter


        mateData.add(MateData("메이트11", "70"))
        mateData.add(MateData("메이트22", "45"))

        return mateBinding.root
    }
    private fun showChessDialog() {
        MateDialog(requireContext()) {

        }.show()
    }
}