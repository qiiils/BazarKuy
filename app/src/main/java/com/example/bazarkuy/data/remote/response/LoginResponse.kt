package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
	@SerializedName("message")
	val message: String?,

	@SerializedName("role")
	val role: String,

	@SerializedName("token")
	val token: String?
)