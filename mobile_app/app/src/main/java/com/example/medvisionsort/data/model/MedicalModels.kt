package com.example.medvisionsort.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ModalityCounts(
    val xray: Int = 0,
    val mri: Int = 0,
    val ct: Int = 0,
    val unknown: Int = 0
)

@Serializable
data class MedicalStats(
    val totalImages: Int,
    val accuracy: Double,
    val processingTime: Double,
    val modalities: Int,
    val counts: ModalityCounts
)

@Serializable
data class MedicalImage(
    val id: String,
    val url: String,
    val type: String,
    val confidence: Double,
    val date: String,
    val patientId: String,
    val patientName: String,
    val status: String,
    val originalFilename: String,
    val storagePath: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

@Serializable
data class UserSessionData(
    val name: String,
    val email: String,
    val role: String,
    val department: String,
    val hospital: String
)

@Serializable
data class AuthResponse(
    val success: Boolean,
    val message: String? = null,
    val user: UserSessionData? = null
)
