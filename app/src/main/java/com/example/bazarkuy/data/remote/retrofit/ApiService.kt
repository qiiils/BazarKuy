package com.example.bazarkuy.data.remote.retrofit

//import com.example.bazarkuy.data.remote.response.
import com.example.bazarkuy.data.remote.response.LoginRequest
import com.example.bazarkuy.data.remote.response.LoginResponse
import com.example.bazarkuy.data.remote.response.RegisterRequest
import com.example.bazarkuy.data.remote.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>


//    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("/api/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>
}