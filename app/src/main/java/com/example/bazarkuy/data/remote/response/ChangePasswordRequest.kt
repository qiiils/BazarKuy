package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("currentPassword")
    val currentPassword: String,
    @SerializedName("newPassword")
    val newPassword: String
)