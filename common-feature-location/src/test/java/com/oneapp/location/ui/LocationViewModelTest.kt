package com.oneapp.location.ui

import com.oneapp.db.LocationEntity
import com.oneapp.location.domain.GetLastLocation
import com.oneapp.location.domain.StartLocationTracking
import com.oneapp.location.domain.StopLocationTracking
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LocationViewModelTest {

    private lateinit var getLastLocation: GetLastLocation
    private lateinit var startLocationTracking: StartLocationTracking
    private lateinit var stopLocationTracking: StopLocationTracking
    private lateinit var viewModel: LocationViewModel

    @Before
    fun setup() {
        getLastLocation = mockk()
        startLocationTracking = mockk()
        stopLocationTracking = mockk()

        every { getLastLocation() } returns flowOf(null)

        viewModel = LocationViewModel(
            getLastLocation = getLastLocation,
            startLocationTracking = startLocationTracking,
            stopLocationTracking = stopLocationTracking
        )
    }

    @Test
    fun `initial state is correct`() = runTest {
        val state = viewModel.uiState.value
        assertEquals(null, state.lastLocation)
        assertFalse(state.isTracking)
        assertFalse(state.isLoading)
        assertEquals(null, state.error)
    }

    @Test
    fun `start tracking updates state`() = runTest {
        coVerify(exactly = 0) { startLocationTracking() }
        viewModel.startTracking()
        coVerify(exactly = 1) { startLocationTracking() }
    }

    @Test
    fun `stop tracking updates state`() = runTest {
        coVerify(exactly = 0) { stopLocationTracking() }
        viewModel.stopTracking()
        coVerify(exactly = 1) { stopLocationTracking() }
    }
}

