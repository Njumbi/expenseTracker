package com.example.expensetracker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Transaction::class],version = 2)
abstract class AppDatabase: RoomDatabase(){

    abstract fun transactionDao():TransactionDao

}
