package com.example.myapplication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.myapplication.MainActivity
import org.junit.Rule
import org.junit.Test

class AppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun app_launches() {
        // Check app launches at the correct destination
        composeTestRule.onNodeWithText("HOME").assertIsDisplayed()
        composeTestRule.onNodeWithText("Android's picks").assertIsDisplayed()
    }

    @Test
    fun app_canNavigateToAllScreens() {
        // Check app launches at HOME
        composeTestRule.onNodeWithText("HOME").assertIsDisplayed()
        composeTestRule.onNodeWithText("Android's picks").assertIsDisplayed()

        // Navigate to Search
        composeTestRule.onNodeWithText("SEARCH").performClick().assertIsDisplayed()
        composeTestRule.onNodeWithText("Categories").assertIsDisplayed()

        // Navigate to Cart
        composeTestRule.onNodeWithText("MY CART").performClick().assertIsDisplayed()
        composeTestRule.onNodeWithText("Order (3 items)").assertIsDisplayed()

        // Navigate to Profile
        composeTestRule.onNodeWithText("PROFILE").performClick().assertIsDisplayed()
        composeTestRule.onNodeWithText("This is currently work in progress").assertIsDisplayed()
    }

    @Test
    fun app_canNavigateToDetailPage() {
        composeTestRule.onNodeWithText("Chips").performClick()
        composeTestRule.onNodeWithText("Lorem ipsum", substring = true).assertIsDisplayed()
    }
}