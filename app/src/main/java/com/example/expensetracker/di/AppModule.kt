package com.example.expensetracker.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.expensetracker.data.AppDatabase
import com.example.expensetracker.data.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun appDb(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"ourDatabase")
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    @Singleton
    fun transactionDao(db: AppDatabase) : TransactionDao{
        return  db.transactionDao()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) : SharedPreferences =
        context.getSharedPreferences(
            "mypref", Context.MODE_PRIVATE
        )

}