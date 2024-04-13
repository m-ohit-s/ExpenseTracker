package com.work.expensetracker.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.work.expensetracker.presentation.home.components.DetailsColumn
import com.work.expensetracker.presentation.home.components.EmptyTransactions
import com.work.expensetracker.presentation.home.components.HomeCard
import com.work.expensetracker.presentation.home.components.InHomeNavBar
import com.work.expensetracker.presentation.home.view_model.HomeScreenViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    Column(
        modifier = modifier.padding(top = 24.dp, start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeCard(modifier = Modifier.height(350.dp), viewModel)
        InHomeNavBar(viewModel = viewModel)
        if(viewModel.transactionsState.value.transactions.isEmpty()){
            EmptyTransactions()
        }
        else{
            DetailsColumn(transactions = viewModel.transactionsState.value.transactions)
        }
    }
}