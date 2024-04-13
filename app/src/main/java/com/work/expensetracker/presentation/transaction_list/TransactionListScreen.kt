package com.work.expensetracker.presentation.transaction_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.work.expensetracker.domain.use_case.OrderType
import com.work.expensetracker.presentation.transaction_list.components.DurationDropDown
import com.work.expensetracker.presentation.transaction_list.components.IncomeExpensePieChart
import com.work.expensetracker.presentation.transaction_list.components.TransactionListCard
import com.work.expensetracker.presentation.transaction_list.view_model.ListEvent
import com.work.expensetracker.presentation.transaction_list.view_model.TransactionListViewModel

@Composable
fun TransactionListScreen(
    viewModel: TransactionListViewModel = hiltViewModel()
) {
    val items = listOf("Today", "This Month", "This Year", "All")
    val screenState = viewModel.transactionsListState
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)) {
            DurationDropDown(items = items, viewModel = viewModel)
            Spacer(modifier = Modifier.height(32.dp))
            IncomeExpensePieChart(viewModel)
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(2.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.onEvent(ListEvent.IncomeClicked)
                            }
                            .background(
                                if (screenState.value.transactionOrder.orderType == OrderType.Income) {
                                    Brush.linearGradient(
                                        listOf(
                                            Color(0xFF1DD1A1),
                                            Color(0xFFD3D3D3)
                                        )
                                    )
                                } else {
                                    Brush.linearGradient(
                                        listOf(
                                            Color.Transparent,
                                            Color.Transparent
                                        )
                                    )
                                }
                            )
                            .weight(1f)
                            .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Income",
                                style = MaterialTheme.typography.titleMedium,
                                color = if (screenState.value.transactionOrder.orderType == OrderType.Income)
                                    Color.DarkGray
                                else
                                    MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.onEvent(ListEvent.ExpenseClicked)
                            }
                            .background(
                                if (screenState.value.transactionOrder.orderType == OrderType.Expense) {
                                    Brush.linearGradient(
                                        listOf(
                                            Color(0xFFE3E3E3),
                                            Color(0xFFD93965)
                                        )
                                    )
                                } else {
                                    Brush.linearGradient(
                                        listOf(
                                            Color.Transparent,
                                            Color.Transparent
                                        )
                                    )
                                }
                            )
                            .weight(1f)
                            .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Expense",
                                style = MaterialTheme.typography.titleMedium,
                                color = if (screenState.value.transactionOrder.orderType == OrderType.Expense)
                                    Color.DarkGray
                                else
                                    MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
        }
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp)) {
            items(viewModel.transactionsListState.value.transactions){
                TransactionListCard(transaction = it)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}