package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

data class BazarResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("eventDate")
    val eventDate: String,
    @SerializedName("organizerId")
    val organizerId: Int
)