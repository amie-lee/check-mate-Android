package com.example.check_mate.src.main.mate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.check_mate.R

class MateAdapter(val itemList: ArrayList<MateData>): RecyclerView.Adapter<MateAdapter.ViewHolder>() {
    // (1) 아이템 레이아웃과 결합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mate_achievement_item, parent, false)
        return ViewHolder(view)
    }
    // (2) 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return itemList.size
    }
    // (3) View에 내용 입력
    override fun onBindViewHolder(holder: MateAdapter.ViewHolder, position: Int) {
        holder.recycle_tv_mate.text = itemList[position].mate_name
        holder.recycle_mate_percent.text = itemList[position].mate_ach
    }
    // (4) 레이아웃 내 View 연결
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val recycle_tv_mate: TextView = itemView.findViewById(R.id.recycle_tv_mate)
        val recycle_mate_percent: TextView = itemView.findViewById(R.id.recycle_mate_percent)
    }
}