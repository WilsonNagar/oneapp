package com.oneapp.location.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oneapp.common.ui.ErrorCard
import com.oneapp.common.ui.LoadingState

@Composable
fun LocationScreen(
    viewModel: LocationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            uiState.isLoading -> LoadingState()
            uiState.error != null -> ErrorCard(message = uiState.error ?: "Unknown error")
            else -> {
                uiState.lastLocation?.let { location ->
                    Text(
                        text = "Last Location:",
                        style = androidx.compose.material3.MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Lat: ${location.latitude}",
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(text = "Lng: ${location.longitude}")
                    Text(
                        text = "Time: ${java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date(location.timestamp))}",
                        modifier = Modifier.padding(top = 8.dp)
                    )
                } ?: Text(
                    text = "No location data available",
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = {
                        if (uiState.isTracking) {
                            viewModel.stopTracking()
                        } else {
                            viewModel.startTracking()
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(if (uiState.isTracking) "Stop Tracking" else "Start Tracking")
                }
            }
        }
    }
}

