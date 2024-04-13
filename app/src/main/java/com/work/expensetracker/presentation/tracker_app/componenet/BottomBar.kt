package com.work.expensetracker.presentation.tracker_app.componenet

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.work.expensetracker.core.navigation.TrackerScreen

@Composable
fun BottomBar(items: List<BottomNavItem>, navController: NavHostController){
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination
    NavigationBar {
        items.forEach{navItem->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any{ it.route == navItem.route} == true,
                onClick = {
                          navController.navigate(navItem.route){
                              launchSingleTop = true
                              restoreState = true
                              popUpTo(TrackerScreen.Home.route){
                                  inclusive = false
                              }
                          }
                },
                icon = {
                       if (currentDestination?.hierarchy?.any{ it.route == navItem.route } == true){
                           Icon(imageVector = navItem.selectedIcon, contentDescription = navItem.iconDesc)
                       } else{
                           Icon(imageVector = navItem.unselectedIcon, contentDescription = navItem.iconDesc)
                       }
                },
                label = {
                    Text(text = navItem.itemLabel)
                }
            )
        }
    }
}