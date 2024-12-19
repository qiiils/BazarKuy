package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Represents the response from the API when a user registers.
 */
data class RegisterResponse(

	@SerializedName("token")
	val token: String?,

	@SerializedName("message")
	val message: String?,

	@SerializedName("userId")
	val userId: Int?
)

