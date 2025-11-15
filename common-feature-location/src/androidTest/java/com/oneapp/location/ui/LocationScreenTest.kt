package com.oneapp.location.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class LocationScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun locationScreen_displaysContent() {
        composeTestRule.setContent {
            LocationScreen()
        }

        // Basic assertion that screen renders
        // In a real scenario, you would test specific UI elements
        composeTestRule.onNodeWithText("No location data available")
            .assertIsDisplayed()
    }
}

