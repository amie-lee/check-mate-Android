package com.example.check_mate.src.main.checkboard

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.check_mate.R
import com.example.check_mate.config.BaseFragment
import com.example.check_mate.databinding.FragmentCheckboardBinding
import com.example.check_mate.src.main.checkboard.models.ResultTodoSum
import kotlinx.android.synthetic.main.dialog_info.view.*
import kotlinx.android.synthetic.main.fragment_checkboard.view.*

class CheckboardFragment : BaseFragment<FragmentCheckboardBinding>(FragmentCheckboardBinding::bind, R.layout.fragment_checkboard) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var spinner = binding.spinner

        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.spinner_day,
            R.layout.row_spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        clickViewEvents()
    }

    private fun clickViewEvents() {
        binding.ibInfo.setOnClickListener {
            val dialog = InfoDialog()
            dialog.isCancelable = false
            dialog.show(activity?.supportFragmentManager!!, "infoDialog")
        }

        binding.btnTodo.setOnClickListener {
            val dataList: ArrayList<ResultTodoSum> = arrayListOf()
            val dataRVAdapter = ResultTodoSumRVAdater(dataList)

            dataList.add(ResultTodoSum("제목","2023/01/31", "와하하"))
            binding.rvTodoSum.adapter = dataRVAdapter
            binding.rvTodoSum.layoutManager = LinearLayoutManager(requireActivity())
        }
    }

}