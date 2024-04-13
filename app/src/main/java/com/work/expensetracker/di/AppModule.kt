package com.work.expensetracker.di

import android.app.Application
import androidx.room.Room
import com.work.expensetracker.data.data_source.TransactionDatabase
import com.work.expensetracker.data.repository.TransactionRepoImpl
import com.work.expensetracker.domain.respository.TransactionRepository
import com.work.expensetracker.domain.use_case.GetTransactions
import com.work.expensetracker.domain.use_case.GetTransactionsByOrder
import com.work.expensetracker.domain.use_case.GetTransactionsSumByType
import com.work.expensetracker.domain.use_case.InsertTransaction
import com.work.expensetracker.domain.use_case.TransactionUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTransactionDatabase(app: Application): TransactionDatabase{
        return Room.databaseBuilder(app, TransactionDatabase::class.java, TransactionDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(db: TransactionDatabase): TransactionRepository{
        return TransactionRepoImpl(db.transactionDao)
    }

    @Provides
    @Singleton
    fun provideTransactionUseCases(repository: TransactionRepository): TransactionUseCases{
        return TransactionUseCases(
            insertTransaction = InsertTransaction(repository),
            getTransactions = GetTransactions(repository),
            getTransactionsSumByType = GetTransactionsSumByType(repository),
            getTransactionsByOrder = GetTransactionsByOrder(repository)
        )
    }
}