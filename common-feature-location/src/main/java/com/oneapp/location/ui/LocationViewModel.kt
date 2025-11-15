package com.oneapp.location.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oneapp.db.LocationEntity
import com.oneapp.location.domain.GetLastLocation
import com.oneapp.location.domain.StartLocationTracking
import com.oneapp.location.domain.StopLocationTracking
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LocationUiState(
    val lastLocation: LocationEntity? = null,
    val isTracking: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLastLocation: GetLastLocation,
    private val startLocationTracking: StartLocationTracking,
    private val stopLocationTracking: StopLocationTracking
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocationUiState())
    val uiState: StateFlow<LocationUiState> = _uiState.asStateFlow()

    init {
        observeLastLocation()
    }

    private fun observeLastLocation() {
        viewModelScope.launch {
            getLastLocation().collect { location ->
                _uiState.value = _uiState.value.copy(
                    lastLocation = location,
                    isLoading = false
                )
            }
        }
    }

    fun startTracking() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                startLocationTracking()
                _uiState.value = _uiState.value.copy(
                    isTracking = true,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun stopTracking() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                stopLocationTracking()
                _uiState.value = _uiState.value.copy(
                    isTracking = false,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}

