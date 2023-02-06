package com.example.check_mate.src.main.mate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.check_mate.R

class NoticeAdapter(val itemList: ArrayList<NoticeData>): RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {
    // (1) 아이템 레이아웃과 결합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notice_item, parent, false)
        return ViewHolder(view)
    }
    // (2) 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return itemList.size
    }
    // (3) View에 내용 입력
    override fun onBindViewHolder(holder: NoticeAdapter.ViewHolder, position: Int) {
        holder.notice_matename.text = itemList[position].notice_matename
        holder.notice_text.text = itemList[position].notice_text
    }
    // (4) 레이아웃 내 View 연결
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val notice_matename: TextView = itemView.findViewById(R.id.notice_item_matename)
        val notice_text: TextView = itemView.findViewById(R.id.notice_item_text)
    }
}