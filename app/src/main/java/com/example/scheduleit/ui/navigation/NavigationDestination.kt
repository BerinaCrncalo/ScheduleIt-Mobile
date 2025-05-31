package com.example.scheduleit.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Interface representing a navigation destination in the ScheduleIt app.
 */
interface NavigationDestination {
    /**
     * The route used for navigation.
     */
    val route: String

    /**
     * The string resource ID for the screen title.
     */
    val titleRes: Int

    /**
     * The icon representing this destination.
     */
    val icon: ImageVector
}
