package com.work.expensetracker.domain.use_case

sealed class OrderType {
    object Income: OrderType()
    object Expense: OrderType()
    object Account: OrderType()
}