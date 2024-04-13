package com.work.expensetracker.presentation.add_transaction.view_model

data class ProcessResult(
    val successful: Boolean,
    val error: String? = null
)