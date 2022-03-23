package com.example.expensetracker.viewModel

import app.cash.turbine.test
import com.example.expensetracker.MainCoroutineRule
import com.example.expensetracker.data.Transaction
import com.example.expensetracker.repository.TransactionRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TransactionVmTest() {

    lateinit var viewModel: TranscactionVm
    lateinit var repository: TransactionRepository

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

//    @get: Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = mockk()

        viewModel = TranscactionVm(repository)
    }

    @Test
    fun `fetch all transactions`() = runTest {
        //given
        val transaction = Transaction(
            id = 0,
            title = null,
            amount = null,
            type = null,
            date = null,
            tags = null,
            description = null

        )
        // when
        coEvery { repository.fetchAllTransactions() } returns flowOf(listOf(transaction))

        // expect
        viewModel.allTransactions.test {
            viewModel.fetchAllTransactions()
            assertThat(awaitItem()).isEqualTo(listOf(transaction))
            cancelAndConsumeRemainingEvents()

            coVerify { repository.fetchAllTransactions() }
        }
    }

    @Test
    fun `totalIncome returns sum of amount in all income transactions`() = runTest {
        // given
        val sampleTransactions = listOf(
            Transaction(
                id = 0,
                title = null,
                amount = 10.0,
                type = TransactionType.Income.toString(),
                date = null,
                tags = null,
                description = null
            ), Transaction(
                id = 1,
                title = null,
                amount = 20.0,
                type = TransactionType.Income.toString(),
                date = null,
                tags = null,
                description = null
            )
        )
        // when
        coEvery { repository.getAllSingleTransaction(TransactionType.Income.toString()) } returns flowOf(
            sampleTransactions
        )

        // expect
        viewModel.totalIncome.test {
            assertThat(awaitItem()).isEqualTo(30.0)
            cancelAndConsumeRemainingEvents()

            coVerify { repository.getAllSingleTransaction(TransactionType.Income.toString()) }
        }
    }

    @Test
    fun `total expenses returns sum amount  of all expenses`() = runTest {
        //given
        val sampleTransactions = listOf(
            Transaction(
                id = 0,
                title = null,
                amount = 4.0,
                type = TransactionType.Expenses.toString(),
                date = null,
                tags = null,
                description = null

            ),
            Transaction(
                id = 1,
                title = null,
                amount = 5.0,
                type = TransactionType.Expenses.toString(),
                date = null,
                tags = null,
                description = null

            )
        )
        //when
        coEvery { repository.getAllSingleTransaction(TransactionType.Expenses.toString()) } returns
                flowOf(sampleTransactions)

        //expected
        viewModel.totalExpenses.test {
            assertThat(awaitItem()).isEqualTo(9.0)
            cancelAndConsumeRemainingEvents()

            coVerify { repository.getAllSingleTransaction(TransactionType.Expenses.toString()) }
        }

    }

    @Test
    fun `total expenses returns total expenses amount`() = runTest {
        val sampleTransactions = listOf(
            Transaction(
                id = 0,
                title = null,
                amount = 4.0,
                type = TransactionType.Expenses.toString(),
                date = null,
                tags = null,
                description = null

            ),
            Transaction(
                id = 1,
                title = null,
                amount = 5.0,
                type = TransactionType.Expenses.toString(),
                date = null,
                tags = null,
                description = null

            )
        )
        coEvery { repository.getAllSingleTransaction(TransactionType.Expenses.toString()) } returns
                flowOf(sampleTransactions)

        viewModel.totalExpenses

        viewModel.calculateExpenses().test {
            // first time it emits 0
            assertThat(awaitItem()).isEqualTo(0.0)
            // second time it emits 9
            assertThat(awaitItem()).isEqualTo(9.0)
            cancelAndConsumeRemainingEvents()

            coVerify { repository.getAllSingleTransaction(TransactionType.Expenses.toString()) }
        }
    }

    @Test
    fun `total income returns total amount`() = runTest {
        // given
        val sampleTransactions = listOf(
            Transaction(
                id = 0,
                title = null,
                amount = 10.0,
                type = TransactionType.Income.toString(),
                date = null,
                tags = null,
                description = null
            ), Transaction(
                id = 1,
                title = null,
                amount = 20.0,
                type = TransactionType.Income.toString(),
                date = null,
                tags = null,
                description = null
            )
        )
        // when
        coEvery { repository.getAllSingleTransaction(TransactionType.Income.toString()) } returns flowOf(
            sampleTransactions
        )

        viewModel.totalIncome

        viewModel.calculateIncome().test {
            //first time it emits 0.0
            assertThat(awaitItem()).isEqualTo(0.0)
            //second time it emits 30.0
            assertThat(awaitItem()).isEqualTo(30.0)
            cancelAndConsumeRemainingEvents()
            coVerify { repository.getAllSingleTransaction(TransactionType.Income.toString()) }
        }

    }

    @Test
    fun `total value remaining is equals to income minus expenses`() = runTest {
        val sampleTransactions = listOf(
            Transaction(
                id = 0,
                title = null,
                amount = 40.0,
                type = TransactionType.Income.toString(),
                date = null,
                tags = null,
                description = null
            ), Transaction(
                id = 1,
                title = null,
                amount = 20.0,
                type = TransactionType.Expenses.toString(),
                date = null,
                tags = null,
                description = null
            )
        )

        coEvery { repository.getAllSingleTransaction(TransactionType.Income.toString()) } returns flowOf(
            sampleTransactions
        )

        coEvery { repository.getAllSingleTransaction(TransactionType.Expenses.toString()) } returns flowOf(
            sampleTransactions
        )

        viewModel.totalIncome
        viewModel.totalExpenses

        viewModel.totalValue.test {
            assertThat(awaitItem()).isEqualTo(0.0)
            assertThat(awaitItem()).isEqualTo(20.0)
            cancelAndConsumeRemainingEvents()

            coVerify { repository.getAllSingleTransaction(TransactionType.Income.toString()) }
            coVerify { repository.getAllSingleTransaction(TransactionType.Expenses.toString()) }
        }


    }

}