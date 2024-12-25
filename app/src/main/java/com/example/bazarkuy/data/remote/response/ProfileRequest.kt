package com.example.bazarkuy.data.remote.request

import com.google.gson.annotations.SerializedName

data class ProfileRequest(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("username")
    val username: String? = null,

    @SerializedName("tiktokLink")
    val tiktokLink: String? = null,

    @SerializedName("facebookLink")
    val facebookLink: String? = null,

    @SerializedName("instagramLink")
    val instagramLink: String? = null
)

data class ChangePasswordRequest(
    @SerializedName("currentPassword")
    val currentPassword: String,

    @SerializedName("newPassword")
    val newPassword: String
)