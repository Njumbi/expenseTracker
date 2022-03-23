package com.example.expensetracker.repository

import app.cash.turbine.test
import com.example.expensetracker.MainCoroutineRule
import com.example.expensetracker.data.Transaction
import com.example.expensetracker.data.TransactionDao
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TransactionRepositoryTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    lateinit var transactionDao: TransactionDao
    lateinit var transactionRepository: TransactionRepository

    @Before
    fun setUp() {
        transactionDao = mockk()
        transactionRepository = TransactionRepository(transactionDao)
    }


    @Test
    fun `fetch all transactions returns flow list of transactions`() = runTest {
        //given
        val transaction = Transaction(
            id = 1,
            title = "pay rent",
            amount = 1000.00,
            type = "expenses",
            date = "12/10/12",
            tags = "rent",
            description = "test test"
        )
        //when
        coEvery { transactionDao.getAllTransactions() } returns flowOf(listOf(transaction))

        //expect
        transactionRepository.fetchAllTransactions().test {
            assertThat(awaitItem()).isEqualTo(listOf(transaction))
            cancelAndConsumeRemainingEvents()

            coVerify { transactionDao.getAllTransactions() }
        }
    }

    @Test
    fun `get all single Transactions returns a flow list of transactions`()= runTest{
        //given
        val transaction  = Transaction(
            id = 1,
            title = "lunch with friends",
            amount = 2000.00,
            type = "expenses",
            date = "12/10/22",
            tags = "food and beverages",
            description = "had a buffet lunch at leonardo's restaurant"

        )
        //when
        coEvery{transactionDao.getAllSingleTransaction(any())} returns flowOf(listOf(transaction))
        //expected
        transactionRepository.getAllSingleTransaction(transactionType = "expenses").test {
            assertThat(awaitItem()).isEqualTo(listOf(transaction))
            cancelAndConsumeRemainingEvents()

            coVerify { transactionDao.getAllSingleTransaction(any()) }
        }
    }

    @Test
    fun `add transaction`() = runTest{
        //when
        coEvery { transactionDao.insertTransaction(any()) } just Runs

        //expected
        transactionRepository.addTransaction(transaction = Transaction(
            id = 0,
            title = null,
            amount = null,
            type = null,
            date = null,
            tags = null,
            description = null
        )
        )

        coVerify { transactionDao.insertTransaction(any()) }
    }
}