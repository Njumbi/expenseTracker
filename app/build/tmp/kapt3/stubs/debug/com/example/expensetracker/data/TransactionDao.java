package com.example.expensetracker.data;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\'\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lcom/example/expensetracker/data/TransactionDao;", "", "getAllSingleTransaction", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/expensetracker/data/Transaction;", "transactionType", "", "getAllTransactions", "insertTransaction", "", "transaction", "(Lcom/example/expensetracker/data/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nuke", "app_debug"})
public abstract interface TransactionDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM `transaction`")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.expensetracker.data.Transaction>> getAllTransactions();
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert()
    public abstract java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull()
    com.example.expensetracker.data.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM `transaction` WHERE `type` == :transactionType")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.expensetracker.data.Transaction>> getAllSingleTransaction(@org.jetbrains.annotations.NotNull()
    java.lang.String transactionType);
    
    @androidx.room.Delete()
    public abstract void nuke(@org.jetbrains.annotations.NotNull()
    com.example.expensetracker.data.Transaction transaction);
}