package com.example.expensetracker.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\rJ\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\r2\u0006\u0010\u0010\u001a\u00020\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/example/expensetracker/repository/TransactionRepository;", "", "transactionsDao", "Lcom/example/expensetracker/data/TransactionDao;", "(Lcom/example/expensetracker/data/TransactionDao;)V", "getTransactionsDao", "()Lcom/example/expensetracker/data/TransactionDao;", "addTransaction", "", "transaction", "Lcom/example/expensetracker/data/Transaction;", "(Lcom/example/expensetracker/data/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchAllTransactions", "Lkotlinx/coroutines/flow/Flow;", "", "getAllSingleTransaction", "transactionType", "", "app_debug"})
public final class TransactionRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.expensetracker.data.TransactionDao transactionsDao = null;
    
    @javax.inject.Inject()
    public TransactionRepository(@org.jetbrains.annotations.NotNull()
    com.example.expensetracker.data.TransactionDao transactionsDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.expensetracker.data.TransactionDao getTransactionsDao() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.expensetracker.data.Transaction>> fetchAllTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addTransaction(@org.jetbrains.annotations.NotNull()
    com.example.expensetracker.data.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.expensetracker.data.Transaction>> getAllSingleTransaction(@org.jetbrains.annotations.NotNull()
    java.lang.String transactionType) {
        return null;
    }
}