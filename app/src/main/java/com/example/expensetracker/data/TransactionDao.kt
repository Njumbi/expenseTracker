package com.example.expensetracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface TransactionDao {

    @Query("SELECT * FROM `transaction`")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Insert
   suspend fun insertTransaction(transaction: Transaction)

    // get all income or expense list by transaction type param
    @Query("SELECT * FROM `transaction` WHERE `type` == :transactionType")
    fun getAllSingleTransaction(transactionType: String): Flow<List<Transaction>>

    @Delete()
    fun nuke(transaction: Transaction)



}