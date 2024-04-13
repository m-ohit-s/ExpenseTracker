package com.work.expensetracker.presentation.add_transaction.view_model

import com.work.expensetracker.core.enums.TransactionMode
import com.work.expensetracker.core.enums.TransactionType
import java.time.LocalDateTime
import java.time.ZoneOffset

data class TransactionState(
    val label: String = "",
    val amount: String = "",
    val calculatedAmount: Double = 0.0,
    val date: Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
    val selectedPaymentType: String = TransactionType.Income.toString(),
    val category: String? = "",
    val ifOtherCategory: Boolean = true,
    val otherCategory: String = "",
    val isDatePickerOpen: Boolean = false,
    val selectedPaymentMode: String = TransactionMode.Cash.toString()
)
