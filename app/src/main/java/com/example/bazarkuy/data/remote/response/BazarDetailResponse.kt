package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

data class BazarDetailResponse(
    @SerializedName("id")
    val id: Int,

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
    val termsAndConditions: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("organizerId")
    val organizerId: Int,

    @SerializedName("imageUrl")
    val imageUrl: String?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("organizer")
    val organizer: Organizer
)

data class Organizer(
    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String
)