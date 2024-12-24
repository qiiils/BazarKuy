package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
	@SerializedName("message")
	val message: String?,

	@SerializedName("token")
	val token: String?,

	@SerializedName("user")
	val user: User
)

data class User(
	@SerializedName("id")
	val id: Int,

	@SerializedName("name")
	val name: String,

	@SerializedName("email")
	val email: String,

	@SerializedName("role")
	val role: String
)