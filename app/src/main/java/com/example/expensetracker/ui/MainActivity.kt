package com.example.expensetracker.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.whenCreated
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.getFormatedNumber
import com.example.expensetracker.ui.adapters.TransactionAdapter
import com.example.expensetracker.viewModel.TranscactionVm
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: TranscactionVm by viewModels()

    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar)
        toolbar.showOverflowMenu()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.title = "Dashboard"

        transactionAdapter = TransactionAdapter()

        rv_current_transacation.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_current_transacation.adapter = transactionAdapter


        fab.setOnClickListener {
            val intent = Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
        }

        vm.transactions.observe(this, {
            it.let { it1 -> transactionAdapter.setData(it1) }
            Log.d(
                "list", it.toString()
            )
        })

        getTotals()
        getTotalExpenses()
        getTotalBalance()
    }

    private fun getTotalBalance(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                vm.totalValue.collect {
                    tv_balance.text = getFormatedNumber(it.toString())
                }
            }
        }
    }

    private fun getTotals() {
        lifecycleScope.launch {
            repeatOnLifecycle( Lifecycle.State.STARTED ){
                vm.calculateIncome().collect {
                    tv_total_income.text = getFormatedNumber(it.toString())
                }
            }
        }
    }

    private fun getTotalExpenses(){
        lifecycleScope.launch {
            repeatOnLifecycle( Lifecycle.State.STARTED){
                vm.calculateExpenses().collect() {
                    tv_total_expenses.text = getFormatedNumber(it.toString())
                }
            }
        }
    }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.more_action, menu)
        return true
    }
}