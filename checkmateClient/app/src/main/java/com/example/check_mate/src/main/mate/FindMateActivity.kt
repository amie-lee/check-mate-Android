package com.example.check_mate.src.main.mate

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.check_mate.R
import com.example.check_mate.config.BaseActivity
import com.example.check_mate.databinding.ActivityFindMateBinding


class FindMateActivity: BaseActivity<ActivityFindMateBinding>(ActivityFindMateBinding::inflate){

    val TAG = "FindMateActivity"

    var findmateAdapter: FindMateAdapter? = null

    lateinit var rv_mate_book: RecyclerView
    lateinit var mates: ArrayList<FindMateData>

    lateinit var search_view_mate_book: SearchView




    lateinit var chess: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_mate)

        rv_mate_book = findViewById(R.id.recycle_find_mate)
        search_view_mate_book = findViewById(R.id.searchview_findmate)
        search_view_mate_book.setOnQueryTextListener(searchViewTextListener)

        val backbtn = findViewById<ImageButton>(R.id.findmate_imgbtn_back) as ImageButton?
        mates = tempMates()

        if (backbtn != null) {
            backbtn.setOnClickListener(View.OnClickListener {
                finish()
            })
        }


    }


    override fun onResume() {
        super.onResume()
        if (findmateAdapter == null) {
            setAdapter()
        } else {
            findmateAdapter!!.initFilteredMates()
            findmateAdapter!!.notifyDataSetChanged()
            findmateAdapter!!.filter.filter(search_view_mate_book.query)
        }
    }

    // SearchView 텍스트 입력 시 이벤트
   var searchViewTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            //텍스트 입력/수정 시에 호출
            override fun onQueryTextChange(s: String): Boolean {
                findmateAdapter?.filter?.filter(s)
                Log.d(ContentValues.TAG, "SearchVies Text is changed : $s")
                return false
            }

        }


    fun setAdapter(){
        //리사이클러뷰에 리사이클러뷰 어댑터 부착
        rv_mate_book.layoutManager = LinearLayoutManager(this)
        findmateAdapter = FindMateAdapter(mates)
        rv_mate_book.adapter = findmateAdapter
    }

    fun tempMates(): ArrayList<FindMateData> {
        var tempMates = ArrayList<FindMateData>()
        tempMates.add(FindMateData("메이트11", "@apple"))
        tempMates.add(FindMateData("메이트22", "@orange"))
        tempMates.add(FindMateData("메이트33", "@qwertyuio12345"))
        tempMates.add(FindMateData("메이트44", "@grape"))
        tempMates.add(FindMateData("메이트55", "@yellow"))
        tempMates.add(FindMateData("메이트66", "@happy"))
        tempMates.add(FindMateData("메이트77", "@sky"))

        return tempMates
    }


}