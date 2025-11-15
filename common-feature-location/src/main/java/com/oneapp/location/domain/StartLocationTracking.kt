package com.oneapp.location.domain

import javax.inject.Inject

class StartLocationTracking @Inject constructor(
    private val repository: LocationRepository
) {
    suspend operator fun invoke() = repository.startLocationTracking()
}

