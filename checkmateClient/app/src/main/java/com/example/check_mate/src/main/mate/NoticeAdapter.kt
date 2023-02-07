package com.example.check_mate.src.main.mate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.check_mate.R
import com.example.check_mate.databinding.NoticeItemBinding

class NoticeAdapter(
    private val itemList: List<NoticeData>,

    val onClickDeleteIcon: (noticedata: NoticeData) -> Unit

):
    RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {


    class NoticeViewHolder(val binding: NoticeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
        val noticeItemMatename: TextView

        get() {
            TODO()
        }
    }


    // (1) 아이템 레이아웃과 결합
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.notice_item, viewGroup, false)

        return NoticeViewHolder(NoticeItemBinding.bind(view))
    }

    // (2) 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return itemList.size
    }

    // (3) View에 내용 입력
    override fun onBindViewHolder(noticeViewHolder: NoticeViewHolder, position: Int) {
        val listposition = itemList[position]
        noticeViewHolder.binding.noticeItemMatename.text = listposition.notice_matename
        noticeViewHolder.binding.noticeItemText.text = listposition.notice_text

        noticeViewHolder.binding.noticeItemDeletebtn.setOnClickListener {
            onClickDeleteIcon.invoke(listposition)
        }

        /*holder.notice_matename.text = itemList[position].notice_matename
        holder.notice_text.text = itemList[position].notice_text

        holder.bind*/
    }
    // (4) 레이아웃 내 View 연결
    /*class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val notice_matename: TextView = itemView.findViewById(R.id.notice_item_matename)
        val notice_text: TextView = itemView.findViewById(R.id.notice_item_text)
    }*/
}