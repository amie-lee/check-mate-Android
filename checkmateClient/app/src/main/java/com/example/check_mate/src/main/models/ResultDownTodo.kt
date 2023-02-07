package com.example.check_mate.src.main.models

import android.text.Editable

data class ResultDownTodo(
    val todo: Editable?,
    val check: Boolean = false
)