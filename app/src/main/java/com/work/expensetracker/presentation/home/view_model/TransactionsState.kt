package com.work.expensetracker.presentation.home.view_model

import com.work.expensetracker.core.enums.HomeDuration
import com.work.expensetracker.domain.model.Transaction
import com.work.expensetracker.domain.use_case.OrderType
import com.work.expensetracker.domain.use_case.TransactionOrder


data class TransactionsState(
    val transactions: List<Transaction> = emptyList(),
    val filteredTransactions: List<Transaction> = emptyList(),
    val filteredBy: HomeDuration = HomeDuration.Today,
    val transactionOrder: TransactionOrder = TransactionOrder.All(OrderType.Account)
 )