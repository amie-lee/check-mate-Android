package com.example.check_mate.src.main

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.check_mate.databinding.ItemDownTodoBinding
import com.example.check_mate.src.main.models.ResultDownTodo

class ResultDownTodoRVAdapter(private val dataList: ArrayList<ResultDownTodo>): RecyclerView.Adapter<ResultDownTodoRVAdapter.ResultDownTodoViewHolder>() {
    inner class ResultDownTodoViewHolder(private val viewBinding: ItemDownTodoBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(data: ResultDownTodo){
            viewBinding.etTodo.text = data.todo
            viewBinding.cbTodo.isChecked = data.check
        }

        fun edit(position: Int){
            viewBinding.etTodo.addTextChangedListener(TodoTextWatcher(position))
        }

    }

    inner class TodoTextWatcher(var position: Int) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s != null) {
                dataList[position].todo = s.toEditable()
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultDownTodoViewHolder {
        val viewBinding = ItemDownTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultDownTodoViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ResultDownTodoViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.edit(position)
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
            notifyDataSetChanged()
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

    interface ItemTouchHelperListener {
        fun onItemMove(fromPosition: Int, toPosition: Int) : Boolean
        fun onItemSwipe(position: Int)
        fun onLeftClick(position: Int, viewHolder: ViewHolder?)
        fun onRightClick(position: Int, viewHolder: ViewHolder?)
    }

    fun CharSequence.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}