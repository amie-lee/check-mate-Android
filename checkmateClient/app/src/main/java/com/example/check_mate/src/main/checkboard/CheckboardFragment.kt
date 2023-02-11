package com.example.check_mate.src.main.checkboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginStart
import androidx.core.view.setMargins
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.check_mate.R
import com.example.check_mate.config.BaseFragment
import com.example.check_mate.databinding.FragmentCheckboardBinding
import com.example.check_mate.src.main.TodoDetailActivity
import com.example.check_mate.src.main.checkboard.models.ResultTodoSum
import kotlinx.android.synthetic.main.fragment_checkboard.*
import kotlinx.android.synthetic.main.fragment_checkboard.view.*
import kotlin.properties.Delegates

class CheckboardFragment : BaseFragment<FragmentCheckboardBinding>(FragmentCheckboardBinding::bind, R.layout.fragment_checkboard) {
    val dataList: ArrayList<ResultTodoSum> = arrayListOf()
    val dataRVAdapter = ResultTodoSumRVAdapter(dataList)

    var width by Delegates.notNull<Int>()
    var height by Delegates.notNull<Int>()

    var numNormal : Int = 0
    var numImp : Int = 0
    var numUrg : Int = 0
    var numImpUrg : Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {
                width = binding.root.area_important.width
                height = binding.root.area_important.height

                binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }

        })

        spinnerEvents()
        clickViewEvents()
    }

    private fun spinnerEvents() {
        var spinner = binding.spinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.spinner_day,
            R.layout.row_spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {checkBoardIcon(5)
                        Log.d("선택", "0")}
                        1 -> {
                            checkBoardIcon(3)
                            Log.d("선택", "1")
                        }
                        2 -> {
                            checkBoardIcon(4)
                            Log.d("선택", "2")
                        }
                        else -> Log.d("선택", "nothing")
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        }
    }

    private fun checkBoardIcon(k : Int) {
        val random = java.util.Random()
        binding.areaImportant.removeAllViews()
        binding.areaNormal.removeAllViews()
        binding.areaImpUrg.removeAllViews()
        binding.areaUrgent.removeAllViews()
        for (i: Int in 1..k) {
            var coordX = random.nextInt(width)
            var coordY = random.nextInt(height)
            while (coordX < 100 || coordX>(width-100)){
                coordX = random.nextInt(width)
            }
            while (coordY < 100 || coordY > (height-100)){
                coordY = random.nextInt(height)
            }

            val todo = Button(requireActivity())
            todo.id = i
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )

            layoutParams.width = 60
            layoutParams.height = 60

            todo.x = coordX.toFloat()
            todo.y = coordY.toFloat()
            todo.setBackgroundResource(R.drawable.ch_round_button)

            todo.setOnClickListener {
                dataList.add(ResultTodoSum("$i","2023/1/12", "11시까지 마무리해서 자정 전에 제출", important = true, urgent = false))
                if (dataRVAdapter.itemCount == 2){
                    dataList.removeAt(0)
                }
                binding.rvTodoSum.adapter = dataRVAdapter
                binding.rvTodoSum.layoutManager = LinearLayoutManager(requireActivity())
            }

            todo.layoutParams = layoutParams
            binding.areaImportant.addView(todo)
        }

        for (i: Int in 1..3) {
            var coordX = random.nextInt(width)
            var coordY = random.nextInt(height)
            while (coordX < 100 || coordX>(width-100)){
                coordX = random.nextInt(width)
            }
            while (coordY < 100 || coordY > (height-100)){
                coordY = random.nextInt(height)
            }

            val todo = Button(requireActivity())
            todo.id = i
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )

            layoutParams.width = 60
            layoutParams.height = 60

            todo.x = coordX.toFloat()
            todo.y = coordY.toFloat()
            todo.setBackgroundResource(R.drawable.ch_round_button)

            todo.setOnClickListener {
                dataList.add(ResultTodoSum("과제 제출하기","2023/1/12", "11시까지 마무리해서 자정 전에 제출", important = false, urgent = false))
                if (dataRVAdapter.itemCount == 2){
                    dataList.removeAt(0)
                }
                binding.rvTodoSum.adapter = dataRVAdapter
                binding.rvTodoSum.layoutManager = LinearLayoutManager(requireActivity())
            }

            todo.layoutParams = layoutParams
            binding.areaNormal.addView(todo)
        }

        for (i: Int in 1..2) {
            var coordX = random.nextInt(width)
            var coordY = random.nextInt(height)
            while (coordX < 100 || coordX>(width-100)){
                coordX = random.nextInt(width)
            }
            while (coordY < 100 || coordY > (height-100)){
                coordY = random.nextInt(height)
            }

            val todo = Button(requireActivity())
            todo.id = i
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )

            layoutParams.width = 60
            layoutParams.height = 60

            todo.x = coordX.toFloat()
            todo.y = coordY.toFloat()
            todo.setBackgroundResource(R.drawable.ch_round_button)

            todo.setOnClickListener {
                dataList.add(ResultTodoSum("${i+5}","2023/1/12", "11시까지 마무리해서 자정 전에 제출", important = true, urgent = true))
                if (dataRVAdapter.itemCount == 2){
                    dataList.removeAt(0)
                }
                binding.rvTodoSum.adapter = dataRVAdapter
                binding.rvTodoSum.layoutManager = LinearLayoutManager(requireActivity())
            }

            todo.layoutParams = layoutParams
            binding.areaImpUrg.addView(todo)
        }

        for (i: Int in 1..3) {
            var coordX = random.nextInt(width)
            var coordY = random.nextInt(height)
            while (coordX < 100 || coordX>(width-100)){
                coordX = random.nextInt(width)
            }
            while (coordY < 100 || coordY > (height-100)){
                coordY = random.nextInt(height)
            }

            val todo = Button(requireActivity())
            todo.id = i
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )

            layoutParams.width = 60
            layoutParams.height = 60
            layoutParams.setMargins(5, 5, 5, 5)

            todo.x = coordX.toFloat()
            todo.y = coordY.toFloat()
            todo.setBackgroundResource(R.drawable.ch_round_button)

            todo.setOnClickListener {
                dataList.add(ResultTodoSum("${i+5}","2023/1/12", "11시까지 마무리해서 자정 전에 제출", important = false, urgent = true))
                if (dataRVAdapter.itemCount == 2){
                    dataList.removeAt(0)
                }
                binding.rvTodoSum.adapter = dataRVAdapter
                binding.rvTodoSum.layoutManager = LinearLayoutManager(requireActivity())
            }

            todo.layoutParams = layoutParams
            binding.areaUrgent.addView(todo)
        }
    }

    private fun clickViewEvents() {

        // 사용 설명창
        binding.ibInfo.setOnClickListener {
            val dialog = InfoDialog()
            dialog.isCancelable = false
            dialog.show(activity?.supportFragmentManager!!, "infoDialog")
        }

        // 아이템 눌렀을 때 상세창으로 이동
        dataRVAdapter.setItemClickListener(object : ResultTodoSumRVAdapter.onItemClickListener{
            override fun onClick(v: View, position: Int) {
                val intent = Intent(activity, TodoDetailActivity::class.java)
                startActivity(intent)
            }
        })

    }


}

