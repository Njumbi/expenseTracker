package com.example.expensetracker.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetracker.R
import com.example.expensetracker.data.Transaction
import com.example.expensetracker.viewModel.TranscactionVm
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_transaction.*

@AndroidEntryPoint
class AddTransactionActivity : AppCompatActivity() {

    private val vm: TranscactionVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        setSupportActionBar(tb_transaction)
        tb_transaction.showOverflowMenu()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Add Transaction"


        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()


        ly_time.setEndIconOnClickListener {
            datePicker.show(supportFragmentManager, "tag");
        }
        datePicker.addOnPositiveButtonClickListener {
            // Respond to positive button click.
            Log.d("The date selected is", datePicker.headerText)
            et_time.setText(datePicker.headerText)
        }

        val items = listOf("Expenses", "Income")
        val adapter = ArrayAdapter(this, R.layout.transactions_list_items, items)
        (ly_transaction.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        val itemsTags = listOf(
            "Bills",
            "Rent",
            "Food & Beverages",
            "Transportation",
            "Groceries",
            "Clothes",
            "Healthcare",
            "Miscellaneous"
        )
        val itemsAdapter = ArrayAdapter(this, R.layout.transactions_list_items, itemsTags)
        (ly_tags.editText as? AutoCompleteTextView)?.setAdapter(itemsAdapter)

        containedButton.setOnClickListener {
            if (TextUtils.isEmpty(et_title.text)) {
                ly_title.error = "Enter Title"

            }
            if (TextUtils.isEmpty(et_amount.text)) {
                ly_amount.error = "Enter Amount"

            }
            if (TextUtils.isEmpty(et_transaction_type.text)) {
                ly_transaction.error = "Choose Transaction Type"

            }
            if (TextUtils.isEmpty(et_tags.text)) {
                ly_tags.error = "Choose tags"

            }
            if (TextUtils.isEmpty(et_time.text)) {
                ly_time.error = "Choose date"

            }
            if(TextUtils.isEmpty(et_note.text)){
                ly_note.error ="Enter note"
                return@setOnClickListener
            }

            val transactionTitle = et_title.text.toString()
            val transactionAmount = et_amount.text.toString()
            val transactionExpenses = et_transaction_type.text.toString()
            val tagsTransactions = et_tags.text.toString()
            val timeTransactions = et_time.text.toString()
            val noteTransactions = et_note.text.toString()



            val request = Transaction(
               title = transactionTitle,
               amount = transactionAmount.toDouble(),
               type =  transactionExpenses,
               tags = tagsTransactions,
               date = timeTransactions,
                description = noteTransactions
            )

            vm.addTransaction(request)

            Log.d("Message","Returned set on click listener")
            Toast.makeText(this,"Transaction Added Successfully",Toast.LENGTH_LONG).show()

           finish()

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}