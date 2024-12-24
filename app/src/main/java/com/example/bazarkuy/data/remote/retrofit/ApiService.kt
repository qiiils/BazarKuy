package com.example.bazarkuy.data.remote.retrofit

//import com.example.bazarkuy.data.remote.response.
//import BazarDetailResponse
import com.example.bazarkuy.data.remote.response.BazarDetailResponse
import com.example.bazarkuy.data.remote.response.BazarResponse
import com.example.bazarkuy.data.remote.response.LoginRequest
import com.example.bazarkuy.data.remote.response.LoginResponse
import com.example.bazarkuy.data.remote.response.RegisterRequest
import com.example.bazarkuy.data.remote.response.RegisterResponse
import kotlinx.coroutines.runBlocking
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
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

    // Login
    @Headers("Content-Type: application/json")
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // Register
    @Headers("Content-Type: application/json")
    @POST("/api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    // Fetch all ongoing bazaars
    @GET("/api/bazars/ongoing")
    suspend fun getOngoingBazaars(
        @Header("Authorization") token: String // Tambahkan header token
    ): Response<List<BazarResponse>>

    // Fetch all coming-soon bazaars
    @GET("/api/bazars/coming-soon")
    suspend fun getComingSoonBazaars(
        @Header("Authorization") token: String // Tambahkan header token
    ): Response<List<BazarResponse>>

    @GET("/api/bazars/open")
    suspend fun getOpenBazaars(
        @Header("Authorization") token: String // Tambahkan header token
    ): Response<List<BazarResponse>>

    // Fetch all bazaars
    @GET("api/bazars")
    suspend fun getAllBazars(
        @Header("Authorization") token: String // Tambahkan header token
    ): Response<List<BazarResponse>>

    // Fetch bazar detail by ID
    @GET("api/bazars/{id}")
    suspend fun getBazarDetail(
        @Path("id") id: Int,
        @Header("Authorization") token: String // Tambahkan header token
    ): Response<BazarDetailResponse>

}


// Response wrapper
data class BazarListResponse(
    val message: String,
    val data: List<BazarResponse>
)

