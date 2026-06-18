package com.example.medvisionsort.data

import com.example.medvisionsort.data.api.ApiClient
import com.example.medvisionsort.data.model.AuthResponse
import com.example.medvisionsort.data.model.LoginRequest
import com.example.medvisionsort.data.model.MedicalImage
import com.example.medvisionsort.data.model.MedicalStats
import com.example.medvisionsort.data.model.RegisterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

interface DataRepository {
    val data: Flow<List<String>>

    suspend fun login(request: LoginRequest): AuthResponse
    suspend fun register(request: RegisterRequest): AuthResponse

    fun getStatsFlow(): Flow<MedicalStats>
    fun getRecentImagesFlow(): Flow<List<MedicalImage>>
    suspend fun classifyImage(fileBytes: ByteArray, filename: String, patientName: String, patientId: String): MedicalImage
}

class DefaultDataRepository : DataRepository {
    override val data: Flow<List<String>> = flow {
        emit(listOf("Triage Dashboard", "Scan Upload", "Activity History"))
    }

    override suspend fun login(request: LoginRequest): AuthResponse {
        return ApiClient.apiService.login(request)
    }

    override suspend fun register(request: RegisterRequest): AuthResponse {
        return ApiClient.apiService.register(request)
    }

    override fun getStatsFlow(): Flow<MedicalStats> = flow {
        emit(ApiClient.apiService.getStats())
    }

    override fun getRecentImagesFlow(): Flow<List<MedicalImage>> = flow {
        emit(ApiClient.apiService.getRecentImages())
    }

    override suspend fun classifyImage(fileBytes: ByteArray, filename: String, patientName: String, patientId: String): MedicalImage {
        val requestBody = fileBytes.toRequestBody("image/*".toMediaTypeOrNull(), 0, fileBytes.size)
        val part = MultipartBody.Part.createFormData("file", filename, requestBody)
        val nameBody = patientName.toRequestBody("text/plain".toMediaTypeOrNull())
        val idBody = patientId.toRequestBody("text/plain".toMediaTypeOrNull())
        return ApiClient.apiService.classifyImage(part, nameBody, idBody)
    }
}
