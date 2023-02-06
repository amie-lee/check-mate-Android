package com.example.check_mate.src.main.mate

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.check_mate.config.BaseActivity
import com.example.check_mate.databinding.ActivityFindMateBinding

class FindMateActivity: BaseActivity<ActivityFindMateBinding>(ActivityFindMateBinding::inflate){

    private lateinit var findmateBinding: ActivityFindMateBinding

    val findmateData = arrayListOf<FindMateData>()
    val FindMateAdapter = FindMateAdapter(findmateData)

    override fun onCreate(savedInstanceState: Bundle?) {
        findmateBinding = ActivityFindMateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(findmateBinding.root)

        findmateBinding.findmateImgbtnBack.setOnClickListener {
            finish()
        }

        findmateBinding.recycleFindMate.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        findmateBinding.recycleFindMate.adapter = FindMateAdapter

        findmateData.add(FindMateData("메이트33", "@qwertyuio12345"))
        findmateData.add(FindMateData("메이트33", "@qwertyuio12345"))

    }
}