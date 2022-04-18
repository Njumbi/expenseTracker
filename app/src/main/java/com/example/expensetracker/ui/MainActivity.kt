package com.example.expensetracker.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.getFormattedNumber
import com.example.expensetracker.ui.adapters.TransactionAdapter
import com.example.expensetracker.viewModel.TranscactionVm
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: TranscactionVm by viewModels()

    private lateinit var transactionAdapter: TransactionAdapter

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    @Inject
    lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.showOverflowMenu()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.title = "Dashboard"


        iniFingerPrint() { it ->
            if (it) {
                transactionAdapter = TransactionAdapter()

                rv_current_transacation.layoutManager =
                    LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                rv_current_transacation.adapter = transactionAdapter

                fab.setOnClickListener {
                    val intent = Intent(this, AddTransactionActivity::class.java)
                    startActivity(intent)
                }
                // call to get all transactions
                vm.fetchAllTransactions()

                // listen for emitted transactions
                lifecycleScope.launchWhenStarted {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        vm.allTransactions.collect {
                            transactionAdapter.submitList(it)
                        }
                    }
                }

                getTotals()
                getTotalExpenses()
                getTotalBalance()
            }
        }
    }

    private fun iniFingerPrint(cb: (Boolean) -> Unit) {
        val isChecked = preferences.getBoolean("checked", true)

        if (isChecked) {
            executor = ContextCompat.getMainExecutor(this)
            biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(
                        errorCode: Int,
                        errString: CharSequence
                    ) {
                        super.onAuthenticationError(errorCode, errString)
                        Toast.makeText(
                            applicationContext,
                            "Authentication error: $errString", Toast.LENGTH_SHORT
                        )
                            .show()
                        cb.invoke(false)
                    }

                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult
                    ) {
                        super.onAuthenticationSucceeded(result)
                        cb.invoke(true)
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Toast.makeText(
                            applicationContext, "Authentication failed",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        cb.invoke(false)
                    }

                })

            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build()

            biometricPrompt.authenticate(promptInfo)
        } else {
            cb.invoke(true)

        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.more -> {
                true
            }
            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getTotalBalance() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.totalValue.collect {
                    tv_balance.text = getFormattedNumber(it.toString())
                }
            }
        }
    }

    private fun getTotals() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.calculateIncome().collect {
                    tv_total_income.text = getFormattedNumber(it.toString())
                }
            }
        }
    }

    private fun getTotalExpenses() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.calculateExpenses().collect() {
                    tv_total_expenses.text = getFormattedNumber(it.toString())
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.more_action, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        vm.fetchAllTransactions()
    }


}
