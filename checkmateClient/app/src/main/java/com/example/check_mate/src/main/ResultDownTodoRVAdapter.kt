package com.example.check_mate.src.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.check_mate.databinding.ItemDownTodoBinding
import com.example.check_mate.src.main.models.ResultDownTodo

class ResultDownTodoRVAdapter(private val dataList: ArrayList<ResultDownTodo>): RecyclerView.Adapter<ResultDownTodoRVAdapter.ResultDownTodoViewHolder>() {
    inner class ResultDownTodoViewHolder(private val viewBinding: ItemDownTodoBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(data: ResultDownTodo){
            viewBinding.etTodo.text = data.todo
            viewBinding.cbTodo.isChecked = data.check
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultDownTodoViewHolder {
        val viewBinding = ItemDownTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultDownTodoViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ResultDownTodoViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = dataList.size

    interface onItemClickListener {
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: onItemClickListener){
        this.itemClickListener = onItemClickListener
    }
    private lateinit var itemClickListener: onItemClickListener

}