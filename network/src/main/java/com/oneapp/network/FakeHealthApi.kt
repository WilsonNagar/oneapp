package com.oneapp.network

/**
 * Fake implementation of ApiService for testing purposes.
 * Allows tests to run offline without actual network calls.
 */
class FakeHealthApi : ApiService {
    override suspend fun getHealth(): HealthResponse {
        return HealthResponse(
            status = "ok",
            timestamp = System.currentTimeMillis()
        )
    }
}

