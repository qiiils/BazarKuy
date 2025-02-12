package com.example.bazarkuy.data.remote.retrofit

//import com.example.bazarkuy.data.remote.response.
//import BazarDetailResponse
import com.example.bazarkuy.data.remote.request.ChangePasswordRequest
import com.example.bazarkuy.data.remote.request.ProfileRequest
import com.example.bazarkuy.data.remote.response.ApplyRequest
import com.example.bazarkuy.data.remote.response.ApplyResponse
import com.example.bazarkuy.data.remote.response.BazaarRequest
import com.example.bazarkuy.data.remote.response.BazarResponse
import com.example.bazarkuy.data.remote.response.BazarDetailResponse
import com.example.bazarkuy.data.remote.response.ChangePasswordResponse
import com.example.bazarkuy.data.remote.response.CreateBazaarResponse
import com.example.bazarkuy.data.remote.response.LoginRequest
import com.example.bazarkuy.data.remote.response.LoginResponse
import com.example.bazarkuy.data.remote.response.RegisterRequest
import com.example.bazarkuy.data.remote.response.RegisterResponse
import com.example.bazarkuy.data.remote.response.NotificationResponse
import com.example.bazarkuy.data.remote.response.ProfileResponse
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
import retrofit2.http.PUT
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

    @POST("api/applications/bazar/{bazarId}/apply")
    suspend fun applyToBazar(
        @Path("bazarId") bazarId: Int,
        @Header("Authorization") token: String,
        @Body request: ApplyRequest
    ): Response<ApplyResponse>

    @GET("api/notifications")
    suspend fun getNotifications(
        @Header("Authorization") token: String
    ): Response<List<NotificationResponse>>

    @GET("api/history/applications")
    suspend fun getApplicationHistory(): List<ApplyResponse>

    // Endpoint untuk riwayat bazar (Penyelenggara Bazar)
    @GET("api/history/bazars")
    suspend fun getBazarHistory(): List<BazarResponse>

    @GET("/api/users/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<ProfileResponse>

    @POST("/api/users/profile/update")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body request: ProfileRequest
    ): Response<ProfileResponse>

    @PUT("api/users/change-password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body request: ChangePasswordRequest
    ): Response<ChangePasswordResponse>

    @POST("/api/bazars")
    suspend fun createBazaar(
        @Header("Authorization") token: String,
        @Body request: BazaarRequest
    ): Response<BazarResponse>
}
// Response wrapper
data class BazarListResponse(
    val message: String,
    val data: List<BazarResponse>
)

