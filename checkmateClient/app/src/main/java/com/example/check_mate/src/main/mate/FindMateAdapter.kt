package com.example.check_mate.src.main.mate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.check_mate.R

class FindMateAdapter(val itemList: ArrayList<FindMateData>): RecyclerView.Adapter<FindMateAdapter.ViewHolder>() {
    // (1) 아이템 레이아웃과 결합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindMateAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mate_find_item, parent, false)
        return ViewHolder(view)
    }
    // (2) 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return itemList.size
    }
    // (3) View에 내용 입력
    override fun onBindViewHolder(holder: FindMateAdapter.ViewHolder, position: Int) {
        holder.findmate_tv_name.text = itemList[position].mate_name
        holder.findmate_tv_id.text = itemList[position].mate_id
    }
    // (4) 레이아웃 내 View 연결
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val findmate_tv_name: TextView = itemView.findViewById(R.id.findmate_tv_name)
        val findmate_tv_id: TextView = itemView.findViewById(R.id.findmate_tv_id)
    }
}