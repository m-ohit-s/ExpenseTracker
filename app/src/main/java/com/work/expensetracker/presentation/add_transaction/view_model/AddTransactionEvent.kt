package com.work.expensetracker.presentation.add_transaction.view_model

sealed class AddTransactionEvent {
    data class AmountChanged(val amount: String): AddTransactionEvent()
    data class TitleChanged(val title: String): AddTransactionEvent()
    data class DateChanged(val date: Long): AddTransactionEvent()
    data class PaymentModeChanged(val mode: String): AddTransactionEvent()
    data class CategoryChanged(val category: String?): AddTransactionEvent()
    data class ChooseOtherCategory(val ifOther: Boolean): AddTransactionEvent()
    data class OtherCategoryChanged(val other: String): AddTransactionEvent()
    data class OpenDatePicker(val isOpened: Boolean): AddTransactionEvent()
    data class TransactionTypeChanged(val transactionType: String): AddTransactionEvent()
    object SaveTransaction: AddTransactionEvent()
}