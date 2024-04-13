package com.work.expensetracker.presentation.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.work.expensetracker.domain.model.Transaction

@Composable
fun DetailsColumn(modifier: Modifier = Modifier, transactions: List<Transaction>) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            items(transactions) { transaction ->
                TransactionCard(transaction = transaction)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
}