package com.work.expensetracker.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.work.expensetracker.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("select * from `transaction`")
    fun getTransactions(): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Query("select * from `transaction` where type=:type")
    fun getTransactionByType(type: String): Flow<List<Transaction>>

    @Query("select sum(amount) from `transaction` where type=:type")
    fun getSumByType(type: String): Flow<Double>
}