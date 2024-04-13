package com.work.expensetracker.presentation.add_transaction.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.expensetracker.domain.model.InvalidTransactionException
import com.work.expensetracker.domain.model.Transaction
import com.work.expensetracker.domain.use_case.TransactionUseCases
import com.work.expensetracker.mock.data.DataLoader
import com.work.expensetracker.mock.model.CategoryMock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionUseCases: TransactionUseCases
): ViewModel() {
    private var _transactionState = mutableStateOf(TransactionState())
    val transactionState = _transactionState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent{
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveTransaction: UiEvent()
    }


    fun onEvent(event: AddTransactionEvent){
        when(event){
            is AddTransactionEvent.AmountChanged -> _transactionState.value = transactionState.value.copy(amount = event.amount)
            is AddTransactionEvent.CategoryChanged -> _transactionState.value = transactionState.value.copy(category = event.category)
            is AddTransactionEvent.DateChanged -> _transactionState.value = transactionState.value.copy(date = event.date)
            is AddTransactionEvent.PaymentModeChanged -> _transactionState.value = transactionState.value.copy(selectedPaymentMode = event.mode)
            is AddTransactionEvent.TitleChanged -> _transactionState.value = transactionState.value.copy(label = event.title)
            is AddTransactionEvent.OtherCategoryChanged -> _transactionState.value = transactionState.value.copy(otherCategory = event.other)
            is AddTransactionEvent.OpenDatePicker -> _transactionState.value = transactionState.value.copy(isDatePickerOpen = event.isOpened)
            is AddTransactionEvent.ChooseOtherCategory -> _transactionState.value = transactionState.value.copy(ifOtherCategory = event.ifOther)
            is AddTransactionEvent.TransactionTypeChanged -> _transactionState.value = transactionState.value.copy(selectedPaymentType = event.transactionType)
            is AddTransactionEvent.SaveTransaction -> {
                viewModelScope.launch {
                    try {
                        convertAmount()
                        transactionUseCases.insertTransaction(
                            Transaction(
                                title = _transactionState.value.label,
                                amount = _transactionState.value.calculatedAmount,
                                timestamp = _transactionState.value.date,
                                paymentMode = _transactionState.value.selectedPaymentMode.toString(),
                                category = if(_transactionState.value.ifOtherCategory) _transactionState.value.otherCategory else _transactionState.value.category!!,
                                type = _transactionState.value.selectedPaymentType
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTransaction)
                    }catch (e: InvalidTransactionException){
                        _eventFlow.emit(UiEvent.ShowSnackBar(e.localizedMessage ?: "Something went wrong"))
                    }
                }
            }
        }
    }

    fun loadCategories(): List<CategoryMock> {
        return DataLoader().loadCategory()
    }
    @Throws(InvalidTransactionException::class)
    private fun convertAmount(){
        try {
            _transactionState.value = _transactionState.value.copy(calculatedAmount = _transactionState.value.amount.toDouble())
        }
        catch (e: NumberFormatException){
            throw InvalidTransactionException(e.localizedMessage?: "Check amount field again")
        }
    }

}

