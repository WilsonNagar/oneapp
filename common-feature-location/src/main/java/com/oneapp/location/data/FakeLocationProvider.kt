package com.oneapp.location.data

import android.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Fake location provider for testing and development.
 * TODO: Replace with FusedLocationProvider when ready to integrate Google Play Services.
 */
class FakeLocationProvider {
    private val _locationFlow = MutableStateFlow<Location?>(null)
    val locationFlow: Flow<Location?> = _locationFlow.asStateFlow()

    private var isTracking = false

    fun startLocationUpdates() {
        isTracking = true
        // Simulate location updates with fake data
        val fakeLocation = Location("fake").apply {
            latitude = 37.7749
            longitude = -122.4194
            accuracy = 10f
            time = System.currentTimeMillis()
        }
        _locationFlow.value = fakeLocation
    }

    fun stopLocationUpdates() {
        isTracking = false
    }

    fun isLocationTrackingActive(): Boolean = isTracking
}

