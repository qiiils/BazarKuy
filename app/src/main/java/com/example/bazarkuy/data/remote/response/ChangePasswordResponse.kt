package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

data class ChangePasswordResponse(
    @SerializedName("message")
    val message: String
)