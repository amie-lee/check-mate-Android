package com.example.check_mate.src.main.checkboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.check_mate.databinding.ItemTodoBinding
import com.example.check_mate.src.main.checkboard.models.ResultTodoSum

class ResultTodoSumRVAdater(private val dataList: ArrayList<ResultTodoSum>): RecyclerView.Adapter<ResultTodoSumRVAdater.ResultTodoSumViewHolder>() {
    inner class ResultTodoSumViewHolder(private val viewBinding: ItemTodoBinding): RecyclerView.ViewHolder(viewBinding.root){

        fun bind(data: ResultTodoSum){
            viewBinding.tvTodo.text = data.title
            viewBinding.tvDate.text = data.deadline
            viewBinding.tvMemo.text = data.memo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultTodoSumViewHolder {
        val viewBinding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultTodoSumViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ResultTodoSumViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
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