package com.example.check_mate.src.main

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.check_mate.R
import com.example.check_mate.config.BaseActivity
import com.example.check_mate.databinding.ActivityTodoDetailBinding
import com.example.check_mate.src.main.models.ResultDownTodo

class TodoDetailActivity : BaseActivity<ActivityTodoDetailBinding>(ActivityTodoDetailBinding::inflate) {
    val dataList: ArrayList<ResultDownTodo> = arrayListOf()
    private val dataRVAdapter = ResultDownTodoRVAdapter(dataList)

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

        dataList.apply { add(ResultDownTodo(Editable.Factory.getInstance().newEditable("아이디어 구상"))) }
        binding.rvLowerTodo.adapter = dataRVAdapter
        binding.rvLowerTodo.layoutManager = LinearLayoutManager(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (isFinishing){
            overridePendingTransition(R.anim.none, R.anim.vertical_exit)
        }
    }

    private fun clickAddBtn() {


        binding.fabAdd.setOnClickListener {
            dataList.add(ResultDownTodo(null))
            binding.rvLowerTodo.adapter = dataRVAdapter
            binding.rvLowerTodo.layoutManager = LinearLayoutManager(this)
        }

        dataRVAdapter.setItemClickListener(object : ResultDownTodoRVAdapter.onItemClickListener{
            override fun onClick(v: View, position: Int) {
                dataList.removeAt(position)
            }
        })
    }
}