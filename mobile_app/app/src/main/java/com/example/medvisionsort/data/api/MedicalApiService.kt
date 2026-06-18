package com.example.medvisionsort.data.api

import com.example.medvisionsort.data.model.AuthResponse
import com.example.medvisionsort.data.model.LoginRequest
import com.example.medvisionsort.data.model.MedicalImage
import com.example.medvisionsort.data.model.MedicalStats
import com.example.medvisionsort.data.model.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MedicalApiService {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    @GET("api/stats")
    suspend fun getStats(): MedicalStats

    @GET("api/images")
    suspend fun getRecentImages(): List<MedicalImage>

    @Multipart
    @POST("api/classify")
    suspend fun classifyImage(
        @Part file: MultipartBody.Part,
        @Part("patientName") patientName: RequestBody,
        @Part("patientId") patientId: RequestBody
    ): MedicalImage
}
