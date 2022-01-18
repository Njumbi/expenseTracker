package com.example.expensetracker.repository

import com.example.expensetracker.data.Transaction
import com.example.expensetracker.data.TransactionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TransactionRepository @Inject constructor(val transactionsDao: TransactionDao) {

    fun fetchAllTransactions() : Flow<List<Transaction>>{
        return transactionsDao.getAllTransactions()
            .flowOn(Dispatchers.Default)
            .conflate()
    }
   suspend fun addTransaction(transaction: Transaction){
         transactionsDao .insertTransaction(transaction)
    }

   fun getAllSingleTransaction(transactionType: String): Flow<List<Transaction>>{
        return transactionsDao.getAllSingleTransaction(transactionType)
    }
}