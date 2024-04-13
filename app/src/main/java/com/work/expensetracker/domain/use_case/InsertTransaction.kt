package com.work.expensetracker.domain.use_case

import com.work.expensetracker.domain.model.InvalidTransactionException
import com.work.expensetracker.domain.model.Transaction
import com.work.expensetracker.domain.respository.TransactionRepository

class InsertTransaction(
    val repository: TransactionRepository
) {
    @Throws(InvalidTransactionException::class)
    suspend operator fun invoke(transaction: Transaction){
        if(transaction.title.isBlank()){
            throw InvalidTransactionException("Title cannot be blank")
        }
        if(transaction.amount == 0.0){
            throw InvalidTransactionException("Amount cannot be 0")
        }
        if(transaction.category.isBlank()){
            throw InvalidTransactionException("Select a category or add one")
        }
        repository.insertTransaction(transaction)
    }
}