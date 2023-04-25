package com.example.check_mate.src.main.todo

import android.os.Bundle
import android.view.View
import com.example.check_mate.R
import com.example.check_mate.config.BaseFragment
import com.example.check_mate.databinding.FragmentTodoBinding

class TodoFragment : BaseFragment<FragmentTodoBinding>(FragmentTodoBinding::bind, R.layout.fragment_todo) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}