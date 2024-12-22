package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

data class BazarResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("endEventDate")
    val endEventDate: String,
    @SerializedName("registrationStartDate")
    val registrationStartDate: String,
    @SerializedName("registrationEndDate")
    val registrationEndDate: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("startEventDate")
    val startEventDate: String,
    @SerializedName("organizerId")
    val organizerId: Int
)