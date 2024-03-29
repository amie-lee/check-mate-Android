package com.example.check_mate.src.main.mate

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.check_mate.R

class FindMateAdapter(var itemList: ArrayList<FindMateData>)
    : RecyclerView.Adapter<FindMateAdapter.ViewHolder>(), Filterable {
    var TAG = "FindMateAdapter"

    var filterMates = ArrayList<FindMateData>()
    var itemFilter = ItemFilter()


    inner class ViewHolder(itemView: View, con: Context) : RecyclerView.ViewHolder(itemView) {

        var findmate_tv_name: TextView
        var findmate_tv_id: TextView
        var findmate_btn: Button

        init {
            findmate_tv_name = itemView.findViewById(R.id.findmate_tv_name)
            findmate_tv_id = itemView.findViewById(R.id.findmate_tv_id)
            findmate_btn = itemView.findViewById(R.id.findmate_btn)


            val tv_delete = "메이트 삭제"

            findmate_btn.setOnClickListener {
                Toast.makeText(con, "메이트가 되었습니다.", Toast.LENGTH_SHORT).show()
                findmate_btn.setText(tv_delete)
                findmate_btn.setTextColor(Color.BLACK)
                findmate_btn.setBackgroundResource(R.drawable.mate_btn_delete)
            }
        }

    }


    init {
        filterMates.addAll(itemList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val con = parent.context
        val inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.mate_find_item, parent, false)

        return ViewHolder(view,con)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mate: FindMateData = filterMates[position]

        holder.findmate_tv_name.text = mate.mate_name
        holder.findmate_tv_id.text = mate.mate_id
    }

    override fun getItemCount(): Int {
        return filterMates.size
    }

    //-- filter
    override fun getFilter(): Filter {
        return itemFilter
    }

    fun initFilteredMates() {
        filterMates.clear()
        filterMates.addAll(itemList)
    }

    inner class ItemFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterString = charSequence.toString()
            val results = FilterResults()

            Log.d(TAG, "charSequence : $charSequence")

            //검색이 필요없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<FindMateData> = ArrayList<FindMateData>()

            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {

                results.values = itemList
                results.count = itemList.size

                return results

                //그 외의 경우(공백제외 2글자 초과)
            } else {
                for (mate in itemList) {
                    if (mate.mate_id.contains(filterString)) {
                        filteredList.add(mate)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
            filterMates.clear()
            filterMates.addAll(filterResults.values as ArrayList<FindMateData>)
            notifyDataSetChanged()
        }


    }


}