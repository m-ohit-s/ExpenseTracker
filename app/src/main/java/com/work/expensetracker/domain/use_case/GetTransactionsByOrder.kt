package com.work.expensetracker.domain.use_case

import com.work.expensetracker.core.enums.TransactionType
import com.work.expensetracker.domain.model.Transaction
import com.work.expensetracker.domain.respository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

class GetTransactionsByOrder(
    private val repository: TransactionRepository
) {
    operator fun invoke(
        transactionOrder: TransactionOrder = TransactionOrder.All(orderType = OrderType.Account)
    ): Flow<List<Transaction>> {
        return repository.getTransactions().map{transactions->
            when(transactionOrder.orderType){
                is OrderType.Income ->{
                    when(transactionOrder){
                        is TransactionOrder.All -> transactions.filter {
                            it.type == TransactionType.Income.toString()
                        }
                        is TransactionOrder.PresentDay -> transactions.filter {
                            val calendar = Calendar.getInstance()
                            calendar.time = Date(it.timestamp)
                            it.type == TransactionType.Income.toString() &&
                                    calendar.get(Calendar.DAY_OF_MONTH) == LocalDate.now().dayOfMonth &&
                                    calendar.get(Calendar.MONTH) + 1 == LocalDate.now().monthValue&&
                                    calendar.get(Calendar.YEAR) == LocalDate.now().year
                        }
                        is TransactionOrder.PresentMonth -> transactions.filter {
                            val calendar = Calendar.getInstance()
                            calendar.time = Date(it.timestamp)
                            it.type == TransactionType.Income.toString() &&
                                    calendar.get(Calendar.MONTH) + 1 == LocalDate.now().monthValue&&
                                    calendar.get(Calendar.YEAR) == LocalDate.now().year
                        }
                        is TransactionOrder.PresentYear -> transactions.filter {
                            val calendar = Calendar.getInstance()
                            calendar.time = Date(it.timestamp)
                            it.type == TransactionType.Income.toString() &&
                                    calendar.get(Calendar.YEAR) == LocalDate.now().year
                        }
                    }.sortedByDescending {
                        it.timestamp
                    }
                }
                is OrderType.Expense ->{
                    when(transactionOrder){
                        is TransactionOrder.All -> transactions.filter {
                            it.type == TransactionType.Expense.toString()
                        }
                        is TransactionOrder.PresentDay -> transactions.filter {
                            val calendar = Calendar.getInstance()
                            calendar.time = Date(it.timestamp)
                            it.type == TransactionType.Expense.toString() &&
                                    calendar.get(Calendar.DAY_OF_MONTH) == LocalDate.now().dayOfMonth &&
                                    calendar.get(Calendar.MONTH) + 1 == LocalDate.now().monthValue&&
                                    calendar.get(Calendar.YEAR) == LocalDate.now().year
                        }
                        is TransactionOrder.PresentMonth -> transactions.filter {
                            val calendar = Calendar.getInstance()
                            calendar.time = Date(it.timestamp)
                            it.type == TransactionType.Expense.toString() &&
                                    calendar.get(Calendar.MONTH) + 1 == LocalDate.now().monthValue&&
                                    calendar.get(Calendar.YEAR) == LocalDate.now().year
                        }
                        is TransactionOrder.PresentYear -> transactions.filter {
                            val calendar = Calendar.getInstance()
                            calendar.time = Date(it.timestamp)
                            it.type == TransactionType.Expense.toString() &&
                                    calendar.get(Calendar.YEAR) == LocalDate.now().year
                        }
                    }.sortedByDescending {
                        it.timestamp
                    }
                }

                is OrderType.Account -> {
                    when(transactionOrder){
                        is TransactionOrder.All -> transactions.filter {
                            true
                        }
                        is TransactionOrder.PresentDay -> transactions.filter {
                            val calendar = Calendar.getInstance()
                            calendar.time = Date(it.timestamp)
                            (calendar.get(Calendar.DAY_OF_MONTH) == LocalDate.now().dayOfMonth) &&
                                    ((calendar.get(Calendar.MONTH)+1) == LocalDate.now().monthValue) &&
                                    (calendar.get(Calendar.YEAR) == LocalDate.now().year)
                        }
                        is TransactionOrder.PresentMonth -> transactions.filter {
                            val calendar = Calendar.getInstance()
                            calendar.time = Date(it.timestamp)
                            calendar.get(Calendar.MONTH) + 1 == LocalDate.now().monthValue&&
                                    calendar.get(Calendar.YEAR) == LocalDate.now().year
                        }
                        is TransactionOrder.PresentYear -> transactions.filter {
                            val calendar = Calendar.getInstance()
                            calendar.time = Date(it.timestamp)
                            calendar.get(Calendar.YEAR) == LocalDate.now().year
                        }
                    }.sortedByDescending {
                        it.timestamp
                    }
                }
            }
        }
    }
}