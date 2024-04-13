package com.work.expensetracker.presentation.home.view_model

import com.work.expensetracker.domain.model.Transaction

sealed class HomeEvent {
    data class DisplayToday(val transactions: List<Transaction>): HomeEvent()
    data class DisplayMonthly(val transactions: List<Transaction>): HomeEvent()
}