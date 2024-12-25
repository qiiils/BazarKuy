// data/remote/response/ProfileResponse.kt
package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

//data class ProfileResponse(
//        @SerializedName("message")
//        val message: String? = null,
//
//        @SerializedName("user")
//        val user: UserData? = null,
//
//        @SerializedName("error")
//        val error: String? = null
//)

data class ProfileResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("username")
    val username: String? = null,

    @SerializedName("profileImage")
    val profileImage: String? = null,

    @SerializedName("tiktokLink")
    val tiktokLink: String? = null,

    @SerializedName("facebookLink")
    val facebookLink: String? = null,

    @SerializedName("instagramLink")
    val instagramLink: String? = null
)