package com.oneapp.location.domain

import javax.inject.Inject

class StopLocationTracking @Inject constructor(
    private val repository: LocationRepository
) {
    suspend operator fun invoke() = repository.stopLocationTracking()
}

