package com.oneapp.location.domain

import com.oneapp.db.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLastLocation(): Flow<LocationEntity?>
    fun getAllLocations(): Flow<List<LocationEntity>>
    suspend fun startLocationTracking()
    suspend fun stopLocationTracking()
    suspend fun saveLocation(location: LocationEntity)
}

