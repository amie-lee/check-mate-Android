package com.example.check_mate.src.main.mate

import com.google.gson.annotations.SerializedName

data class Data (

    @SerializedName("friendId")
    var friendId: Int,

    @SerializedName("isSuccess")
    var isSuccess: Boolean,

    @SerializedName("code")
    var code: Int,

    @SerializedName("message")
    var message: String
)