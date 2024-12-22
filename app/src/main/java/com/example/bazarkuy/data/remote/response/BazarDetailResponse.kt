package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

    data class BazarDetailResponse(
        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("eventDate")
        val eventDate: String,

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

        @SerializedName("createdAt")
        val createdAt: String,

        @SerializedName("updatedAt")
        val updatedAt: String,

        @SerializedName("organizer")
        val organizer: OrganizerResponse
    )

    data class OrganizerResponse(
        @SerializedName("name")
        val name: String
    )