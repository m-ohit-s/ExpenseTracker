package com.work.expensetracker.domain.use_case

import com.work.expensetracker.domain.model.Transaction
import com.work.expensetracker.domain.respository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetTransactions(
    private val repository: TransactionRepository
) {
    operator fun invoke(): Flow<List<Transaction>>{
        return repository.getTransactions()
    }
}