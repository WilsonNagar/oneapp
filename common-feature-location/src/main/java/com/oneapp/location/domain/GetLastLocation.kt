package com.oneapp.location.domain

import com.oneapp.db.LocationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastLocation @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(): Flow<LocationEntity?> = repository.getLastLocation()
}

