package com.work.expensetracker.data.repository

import com.work.expensetracker.data.data_source.TransactionDao
import com.work.expensetracker.domain.model.Transaction
import com.work.expensetracker.domain.respository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class TransactionRepoImpl(
    private val transactionDao: TransactionDao
): TransactionRepository {
    override fun getTransactions(): Flow<List<Transaction>> {
        return transactionDao.getTransactions()
    }

    override suspend fun insertTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }

    override fun getTransactionByType(type: String): Flow<List<Transaction>> {
        return transactionDao.getTransactionByType(type)
    }

    override fun getTransactionSumByType(type: String): Flow<Double> {
        return transactionDao.getSumByType(type)
    }

}