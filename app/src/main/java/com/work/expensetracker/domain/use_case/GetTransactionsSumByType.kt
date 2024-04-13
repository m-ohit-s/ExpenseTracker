package com.work.expensetracker.domain.use_case

import com.work.expensetracker.domain.respository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTransactionsSumByType(
    private val repository: TransactionRepository
) {
    operator fun invoke(type: String): Flow<Double>{
        return repository.getTransactionSumByType(type)
    }
}