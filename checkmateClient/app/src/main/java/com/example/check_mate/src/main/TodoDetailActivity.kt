package com.example.check_mate.src.main

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.check_mate.R
import com.example.check_mate.config.BaseActivity
import com.example.check_mate.databinding.ActivityTodoDetailBinding
import com.example.check_mate.src.main.models.ResultDownTodo

class TodoDetailActivity : BaseActivity<ActivityTodoDetailBinding>(ActivityTodoDetailBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var daySpinner = binding.spinnerCategory

        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_category_tv,
            R.layout.row_category_spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            daySpinner.adapter = adapter
        }

        clickAddBtn()

        overridePendingTransition(R.anim.vertical_enter, R.anim.none)

        binding.ibBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.none, R.anim.vertical_exit)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (isFinishing){
            overridePendingTransition(R.anim.none, R.anim.vertical_exit)
        }
    }

    private fun clickAddBtn() {
        val dataList: ArrayList<ResultDownTodo> = arrayListOf()
        val dataRVAdapter = ResultDownTodoRVAdapter(dataList)

        binding.fabAdd.setOnClickListener {
            dataList.add(ResultDownTodo(null))
            binding.rvLowerTodo.adapter = dataRVAdapter
            binding.rvLowerTodo.layoutManager = LinearLayoutManager(this)
        }
    }
}