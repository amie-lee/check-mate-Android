package com.example.check_mate.src.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import com.example.check_mate.R
import com.example.check_mate.config.BaseActivity
import com.example.check_mate.databinding.ActivityMainBinding
import com.example.check_mate.src.main.checkboard.CheckboardFragment
import com.example.check_mate.src.main.mate.MateFragment
import com.example.check_mate.src.main.setting.SettingFragment
import com.example.check_mate.src.main.todo.TodoFragment
import java.util.InputMismatchException

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){
    var imm : InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as
                InputMethodManager?


        supportFragmentManager
            .beginTransaction()
            .replace(binding.mainFrm.id, CheckboardFragment())
            .commitAllowingStateLoss()

        binding.navBottom.run {
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.menu_check_board -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.mainFrm.id, CheckboardFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_todo -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.mainFrm.id, TodoFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_mate -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.mainFrm.id, MateFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_setting -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.mainFrm.id, SettingFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_check_board
        }

    }
    fun hideKeyboard(v:View){
        if(v != null){
            imm?.hideSoftInputFromWindow(v.windowToken,0)
        }
    }
}