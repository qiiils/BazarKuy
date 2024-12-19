package com.example.bazarkuy.data.remote.response

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)
