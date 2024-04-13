package com.work.expensetracker.presentation.transaction_list.view_model

import com.work.expensetracker.domain.model.Transaction
import com.work.expensetracker.domain.use_case.OrderType
import com.work.expensetracker.domain.use_case.TransactionOrder

data class TransactionListState(
    val transactions: List<Transaction> = emptyList(),
    val filteredTransactions: List<Transaction> = emptyList(),
    val filteringDuration: String = "All",
    val transactionOrder: TransactionOrder = TransactionOrder.All(OrderType.Income),
    val incomeSum: Double = 0.0,
    val expenseSum: Double = 0.0
)
