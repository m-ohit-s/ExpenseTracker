package com.work.expensetracker.presentation.transaction_list.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.expensetracker.core.enums.TransactionType
import com.work.expensetracker.domain.use_case.OrderType
import com.work.expensetracker.domain.use_case.TransactionOrder
import com.work.expensetracker.domain.use_case.TransactionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val useCases: TransactionUseCases
) : ViewModel() {

    private val _transactionsListState = mutableStateOf(TransactionListState())
    val transactionsListState = _transactionsListState

    private val _dropDownState = mutableStateOf(DropDownState())
    val dropDownState = _dropDownState

    init {
        getTransactionsByOrder(TransactionOrder.All(OrderType.Income))
        getTransactionsSumByOrder(TransactionOrder.All(OrderType.Account))
    }

    private var transactionsByOrderJob: Job? = null
    private var transactionsSumByOrderJob: Job? = null

    private fun getTransactionsByOrder(transactionOrder: TransactionOrder){
        transactionsByOrderJob?.cancel()
        transactionsByOrderJob = useCases.getTransactionsByOrder(transactionOrder).onEach {
            _transactionsListState.value = _transactionsListState.value.copy(
                transactions = it,
                transactionOrder = transactionOrder,
            )
        }.launchIn(viewModelScope)
    }

    private fun getTransactionsSumByOrder(transactionOrder: TransactionOrder){
        transactionsSumByOrderJob?.cancel()
        transactionsByOrderJob = useCases.getTransactionsByOrder(transactionOrder).onEach {
            _transactionsListState.value = transactionsListState.value.copy(
                incomeSum = it.filter {transaction ->
                    transaction.type  == TransactionType.Income.toString()
                }.sumOf {transaction->
                    transaction.amount
                },
                expenseSum = it.filter {transaction ->
                    transaction.type  == TransactionType.Expense.toString()
                }.sumOf {transaction->
                    transaction.amount
                }
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ListEvent){
        when(event){
            is ListEvent.ExpenseClicked -> viewModelScope.launch {
                getTransactionsByOrder(transactionOrder = _transactionsListState.value.transactionOrder.copy(OrderType.Expense))
            }
            is ListEvent.IncomeClicked -> viewModelScope.launch {
                getTransactionsByOrder(transactionOrder = _transactionsListState.value.transactionOrder.copy(OrderType.Income))
            }
        }
    }

    fun onDropDownEvent(event: DropDownEvent){
        when(event){
            is DropDownEvent.ExpandButtonClicked -> {
                _dropDownState.value = dropDownState.value.copy(expanded = event.isExpanded)
            }

            is DropDownEvent.ItemSelected -> {
                getTransactionsByOrder(getOrderTypeByDropDown(event.item, transactionsListState.value.transactionOrder.orderType))
                getTransactionsSumByOrder(getOrderTypeByDropDown(event.item, OrderType.Account))
                _dropDownState.value = dropDownState.value.copy(selectedItem = event.item)
                _transactionsListState.value = transactionsListState.value.copy(
                    filteringDuration = event.item
                )
            }
        }
    }

    private fun getOrderTypeByDropDown(duration: String, orderType: OrderType): TransactionOrder{
    return when (duration) {
        "All" -> {
            TransactionOrder.All(orderType)
        }
        "Today" -> {
            TransactionOrder.PresentDay(orderType)
        }
        "This Month" -> {
            TransactionOrder.PresentMonth(orderType)
        }
        "This Year" -> {
            TransactionOrder.PresentYear(orderType)
        }

        else -> {
            TransactionOrder.All(OrderType.Account)
        }
    }
    }
}

