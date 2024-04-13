package com.work.expensetracker.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.work.expensetracker.presentation.add_transaction.AddTransactionScreen
import com.work.expensetracker.presentation.home.HomeScreen
import com.work.expensetracker.presentation.settings.SettingsScreen
import com.work.expensetracker.presentation.tracker_app.AppScreen
import com.work.expensetracker.presentation.transaction_list.TransactionListScreen
import com.work.expensetracker.presentation.transaction_type.TransactionTypeScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = TrackerScreen.App.route,
    ){
        composable(route = TrackerScreen.App.route){
            AppScreen(rootNavHostController = navHostController)
        }
        composable(route = TrackerScreen.AddTransaction.route){
            AddTransactionScreen(navHostController = navHostController)
        }
    }
}

@Composable
fun NavBarGraph(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = TrackerScreen.Home.route){
        composable(route = TrackerScreen.Home.route){
            HomeScreen()
        }
        composable(route = TrackerScreen.Settings.route){
            SettingsScreen()
        }
        composable(route=TrackerScreen.TransactionList.route){
            TransactionListScreen()
        }
        composable(route=TrackerScreen.TransactionType.route){
            TransactionTypeScreen()
        }

    }
}