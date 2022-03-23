package com.example.expensetracker.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.Transaction
import com.example.expensetracker.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class TransactionType {
    Income,
    Expenses
}

@HiltViewModel
class TranscactionVm @Inject constructor(val repository: TransactionRepository) : ViewModel() {

    private val _transactions = MutableSharedFlow<Transaction>()
    val transactions = _transactions.asSharedFlow()

    private val _allTransactions = MutableSharedFlow<List<Transaction>>()
    val allTransactions = _allTransactions.asSharedFlow()


//    val transactions: LiveData<List<Transaction>> = repository.fetchAllTransactions().asLiveData()

    fun fetchAllTransactions() {
        viewModelScope.launch {
            val response = repository.fetchAllTransactions()
            response.collect {
                _allTransactions.emit(it)
            }
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            repository.addTransaction(transaction)
        }
    }

    val totalIncome: Flow<Double>
        get() {
            return repository.getAllSingleTransaction(TransactionType.Income.toString())
                .map {
                    var total = 0.0;
                    it.forEach {
                        if (it.type == TransactionType.Income.toString()) {
                            total += it.amount!!
                        }
                    }
                    return@map total
                }
        }


 val totalExpenses: Flow<Double>
        get() {
            return repository.getAllSingleTransaction(TransactionType.Expenses.toString())
                .map {
                    var totalExpenses = 0.0;
                    it.forEach {
                        if (it.type == TransactionType.Expenses.toString()){
                            totalExpenses += it.amount!!
                        }

                    }
                    return@map totalExpenses
                }
        }


    fun calculateIncome(): StateFlow<Double> =
        flow<Double> {
            totalIncome.collect {
                emit(it)
            }.runCatching {
                emit(0.0)
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            0.0
        )


    fun calculateExpenses(): StateFlow<Double> =
        flow<Double> {
            totalExpenses.collect {
                emit(it)
            }.runCatching {
                emit(0.0)
            }

        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            0.0
        )

    val totalValue: StateFlow<Double>
        get() {
            return flow {
                combine(totalExpenses, totalIncome) { a, b ->
                    return@combine (b - a)
                }.collect {
                    emit(it)
                }.runCatching {
                    emit(0.0)
                }
            }.stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                0.0
            )
        }
}