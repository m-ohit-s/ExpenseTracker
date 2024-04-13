package com.work.expensetracker.mock.model

import com.work.expensetracker.core.enums.TransactionMode
import com.work.expensetracker.core.enums.TransactionType

data class TransactionMock(
    val id: Int,
    val label: String,
    val amount: Int,
    val category: String,
    val description: String,
    val date: String,
    val time: String,
    val type: TransactionType,
    val mode: TransactionMode,
)
