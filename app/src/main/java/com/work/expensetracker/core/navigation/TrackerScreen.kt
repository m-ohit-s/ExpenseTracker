package com.work.expensetracker.core.navigation

import com.work.expensetracker.core.Constants

sealed class TrackerScreen(val route: String) {
    object Home: TrackerScreen(route = Constants.ACCOUNTS_HOME_SCREEN)
    object AddTransaction: TrackerScreen(route = Constants.ADD_TRANSACTION_SCREEN)
    object Settings: TrackerScreen(route = Constants.SETTINGS_SCREEN)
    object TransactionList: TrackerScreen(route = Constants.TRANSACTION_LIST_SCREEN)
    object TransactionType: TrackerScreen(route = Constants.TRANSACTION_TYPE_SCREEN)
    object App: TrackerScreen(route = Constants.APP_SCREEN)
}

sealed class Graph(val route: String){
    object NavBar: Graph(Constants.BAR_GRAPH)
    object Transaction: Graph(Constants.TRANSACTION_GRAPH)
    object Root: Graph(Constants.ROOT_GRAPH)
}