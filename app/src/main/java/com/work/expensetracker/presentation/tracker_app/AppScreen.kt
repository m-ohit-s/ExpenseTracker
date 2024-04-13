package com.work.expensetracker.presentation.tracker_app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.work.expensetracker.core.navigation.NavBarGraph
import com.work.expensetracker.core.navigation.TrackerScreen
import com.work.expensetracker.presentation.tracker_app.componenet.BottomBar
import com.work.expensetracker.presentation.tracker_app.componenet.NavItemsLoader

@Composable
fun AppScreen(rootNavHostController: NavHostController){
    val navHostController = rememberNavController()
    Scaffold(

        bottomBar = {
            BottomBar(
                items = NavItemsLoader.loadNavItems(),
                navController = navHostController
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    rootNavHostController.navigate(TrackerScreen.AddTransaction.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(TrackerScreen.Home.route){
                            inclusive = false
                        }
                    }
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            NavBarGraph(navHostController = navHostController)
        }
    }
}