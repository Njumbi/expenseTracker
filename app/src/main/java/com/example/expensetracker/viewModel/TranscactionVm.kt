package com.example.expensetracker.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.Transaction
import com.example.expensetracker.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class TransactionType {
    Income,
    Expenses
}

@HiltViewModel
class TranscactionVm @Inject constructor(val repository: TransactionRepository) : ViewModel() {

    val transactions: LiveData<List<Transaction>> = repository.fetchAllTransactions().asLiveData()

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            repository.addTransaction(transaction)
        }
    }

    private val totalIncome: Flow<Double> =
        repository.getAllSingleTransaction(TransactionType.Income.toString())
            .map {
                var total = 0.0;
                it.forEach {
                    total += it.amount!!
                }
                return@map total
            }

    private val totalExpenses: Flow<Double> =
        repository.getAllSingleTransaction(TransactionType.Expenses.toString())
            .map {
                var totalExpenses = 0.0;
                it.forEach {
                    totalExpenses += it.amount!!
                }
                return@map totalExpenses
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

    val totalValue: StateFlow<Double> = flow {
        combine(totalExpenses, totalIncome) { a, b ->
            return@combine ( b-a )
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