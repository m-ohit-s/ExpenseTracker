package com.work.expensetracker.presentation.tracker_app.componenet

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val itemLabel: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconDesc: String? = null,
    val route: String
)