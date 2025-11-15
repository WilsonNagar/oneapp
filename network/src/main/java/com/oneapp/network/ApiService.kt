package com.oneapp.network

import com.squareup.moshi.Json
import retrofit2.http.GET

interface ApiService {
    @GET("health")
    suspend fun getHealth(): HealthResponse
}

data class HealthResponse(
    @Json(name = "status")
    val status: String,
    @Json(name = "timestamp")
    val timestamp: Long
)

