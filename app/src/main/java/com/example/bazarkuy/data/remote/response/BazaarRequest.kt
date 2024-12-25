package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

data class BazaarRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("startEventDate")
    val startEventDate: String,
    @SerializedName("endEventDate")
    val endEventDate: String,
    @SerializedName("registrationStartDate")
    val registrationStartDate: String,
    @SerializedName("registrationEndDate")
    val registrationEndDate: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("termsAndConditions")
    val termsAndConditions: String
)
