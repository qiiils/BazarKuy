package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

// data/remote/response/ApplyResponse.kt

data class ApplyResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("bazarId")
    val bazarId: String,

    @SerializedName("umkmId")
    val umkmId: Int,

    @SerializedName("businessName")
    val businessName: String,

    @SerializedName("businessDescription")
    val businessDescription: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)