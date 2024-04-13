package com.work.expensetracker.presentation.transaction_list.view_model

sealed class ListEvent {
    object IncomeClicked : ListEvent()
    object ExpenseClicked: ListEvent()
}