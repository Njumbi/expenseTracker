package com.example.expensetracker.viewModel;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\bJ\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00120\u0018J\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00120\u0018J\u0006\u0010\"\u001a\u00020\u001eR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u00188F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\r\u00a8\u0006#"}, d2 = {"Lcom/example/expensetracker/viewModel/TranscactionVm;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/expensetracker/repository/TransactionRepository;", "(Lcom/example/expensetracker/repository/TransactionRepository;)V", "_allTransactions", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "Lcom/example/expensetracker/data/Transaction;", "_transactions", "allTransactions", "Lkotlinx/coroutines/flow/SharedFlow;", "getAllTransactions", "()Lkotlinx/coroutines/flow/SharedFlow;", "getRepository", "()Lcom/example/expensetracker/repository/TransactionRepository;", "totalExpenses", "Lkotlinx/coroutines/flow/Flow;", "", "getTotalExpenses", "()Lkotlinx/coroutines/flow/Flow;", "totalIncome", "getTotalIncome", "totalValue", "Lkotlinx/coroutines/flow/StateFlow;", "getTotalValue", "()Lkotlinx/coroutines/flow/StateFlow;", "transactions", "getTransactions", "addTransaction", "", "transaction", "calculateExpenses", "calculateIncome", "fetchAllTransactions", "app_debug"})
public final class TranscactionVm extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.expensetracker.repository.TransactionRepository repository = null;
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.example.expensetracker.data.Transaction> _transactions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.example.expensetracker.data.Transaction> transactions = null;
    private final kotlinx.coroutines.flow.MutableSharedFlow<java.util.List<com.example.expensetracker.data.Transaction>> _allTransactions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<java.util.List<com.example.expensetracker.data.Transaction>> allTransactions = null;
    
    @javax.inject.Inject()
    public TranscactionVm(@org.jetbrains.annotations.NotNull()
    com.example.expensetracker.repository.TransactionRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.expensetracker.repository.TransactionRepository getRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.example.expensetracker.data.Transaction> getTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<java.util.List<com.example.expensetracker.data.Transaction>> getAllTransactions() {
        return null;
    }
    
    public final void fetchAllTransactions() {
    }
    
    public final void addTransaction(@org.jetbrains.annotations.NotNull()
    com.example.expensetracker.data.Transaction transaction) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Double> getTotalIncome() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Double> getTotalExpenses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> calculateIncome() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> calculateExpenses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getTotalValue() {
        return null;
    }
}