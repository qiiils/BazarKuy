package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

data class ApplyRequest(
    @SerializedName("businessName")
    val businessName: String,

    @SerializedName("businessDescription")
    val businessDescription: String
)