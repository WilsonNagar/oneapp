package com.oneapp.location.data

import com.oneapp.db.LocationDao
import com.oneapp.db.LocationEntity
import com.oneapp.location.domain.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationDao: LocationDao
) : LocationRepository {

    private var isTracking = false

    override fun getLastLocation(): Flow<LocationEntity?> {
        return locationDao.getLastLocation()
    }

    override fun getAllLocations(): Flow<List<LocationEntity>> {
        return locationDao.getAllLocations()
    }

    override suspend fun startLocationTracking() {
        isTracking = true
        // TODO: Replace with actual FusedLocationProvider implementation
        // For now, this is a stub that allows the project to compile
    }

    override suspend fun stopLocationTracking() {
        isTracking = false
        // TODO: Stop actual location updates
    }

    override suspend fun saveLocation(location: LocationEntity) {
        locationDao.insertLocation(location)
    }
}

