package com.example.bazarkuy.data.remote.response

data class CreateBazaarResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val startEventDate: String,
    val endEventDate: String,
    val location: String,
    val status: String
)
