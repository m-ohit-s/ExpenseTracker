package com.work.expensetracker.presentation.tracker_app.componenet

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import com.work.expensetracker.core.navigation.TrackerScreen

object NavItemsLoader {
    fun loadNavItems(): List<BottomNavItem>{
        return listOf(
            BottomNavItem(
                itemLabel = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                route = TrackerScreen.Home.route
            ),
            BottomNavItem(
                itemLabel = "Transactions",
                selectedIcon = Icons.AutoMirrored.Filled.List,
                unselectedIcon = Icons.AutoMirrored.Outlined.List,
                route = TrackerScreen.TransactionList.route
            ),
            BottomNavItem(
                itemLabel = "Mode",
                selectedIcon = Icons.Filled.Create,
                unselectedIcon = Icons.Outlined.Create,
                route = TrackerScreen.TransactionType.route
            ),
            BottomNavItem(
                itemLabel = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                route = TrackerScreen.Settings.route
            )
        )
    }
}