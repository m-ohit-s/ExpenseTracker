package com.work.expensetracker.domain.respository

import com.work.expensetracker.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactions(): Flow<List<Transaction>>

    suspend fun insertTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transaction: Transaction)

    fun getTransactionByType(type: String): Flow<List<Transaction>>

    fun getTransactionSumByType(type: String): Flow<Double>
}