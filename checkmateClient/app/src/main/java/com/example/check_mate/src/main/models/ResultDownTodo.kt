package com.example.check_mate.src.main.models

import android.text.Editable

data class ResultDownTodo(
    var todo: Editable?,
    val check: Boolean = false
)