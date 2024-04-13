package com.work.expensetracker.domain.use_case

data class TransactionUseCases(
    val insertTransaction: InsertTransaction,
    val getTransactions: GetTransactions,
    val getTransactionsSumByType: GetTransactionsSumByType,
    val getTransactionsByOrder: GetTransactionsByOrder
)