package com.work.expensetracker.presentation.home.view_model

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.expensetracker.core.enums.HomeDuration
import com.work.expensetracker.core.enums.TransactionType
import com.work.expensetracker.domain.use_case.OrderType
import com.work.expensetracker.domain.use_case.TransactionOrder
import com.work.expensetracker.domain.use_case.TransactionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val useCases: TransactionUseCases
): ViewModel() {
    private val _income = mutableDoubleStateOf(0.00)
    val income = _income
    private val _expense = mutableDoubleStateOf(0.00)
    val expense = _expense

    private val _transactionsState = mutableStateOf(TransactionsState())
    val transactionsState = _transactionsState


    private var incomeJob : Job? = null
    private var expenseJob: Job? = null
    private var transactionsJob: Job? = null
    private var transactionsByOrderJob: Job? = null

    init {
        getIncome()
        getExpense()
        getTransactionsByOrder(TransactionOrder.PresentDay(OrderType.Account))
    }

    private fun getIncome(){
        incomeJob?.cancel()
        incomeJob = useCases.getTransactionsSumByType(TransactionType.Income.toString()).onEach {
            _income.doubleValue = it
        }.launchIn(viewModelScope)
    }

    private fun getExpense(){
        expenseJob?.cancel()
        expenseJob = useCases.getTransactionsSumByType(TransactionType.Expense.toString()).onEach {
            _expense.doubleValue = it
        }.launchIn(viewModelScope)
    }

    private fun getTransactions(){
        transactionsJob?.cancel()
        transactionsJob = useCases.getTransactions().onEach {
            _transactionsState.value = transactionsState.value.copy(transactions = it.sortedByDescending {transaction->
                transaction.timestamp
            })
        }.launchIn(viewModelScope)
    }

    private fun getTransactionsByOrder(transactionOrder: TransactionOrder){
        transactionsByOrderJob?.cancel()
        transactionsByOrderJob = useCases.getTransactionsByOrder(transactionOrder).onEach {
            _transactionsState.value = _transactionsState.value.copy(
                transactions = it,
                transactionOrder = transactionOrder
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeEvent){

        when(event){

            is HomeEvent.DisplayToday -> {
                getTransactionsByOrder(TransactionOrder.PresentDay(OrderType.Account))
                _transactionsState.value = transactionsState.value.copy(filteredBy = HomeDuration.Today)

            }

            is HomeEvent.DisplayMonthly -> {
                getTransactionsByOrder(TransactionOrder.PresentMonth(OrderType.Account))
                _transactionsState.value = transactionsState.value.copy(filteredBy = HomeDuration.Monthly)
            }
        }
    }
}