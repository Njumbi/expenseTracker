package com.example.expensetracker.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.data.Transaction
import com.example.expensetracker.getFormattedNumber
import kotlinx.android.synthetic.main.list_items.view.*

class TransactionAdapter : ListAdapter<Transaction, TransactionAdapter.TransactionsVh>(TransactionsComparator()) {


    class TransactionsVh(view: View) : RecyclerView.ViewHolder(view) {

    }

    class TransactionsComparator : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionsVh {
        return TransactionsVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TransactionsVh, position: Int) {
        val data = getItem(position);
        holder.itemView.tv_rv_title.text = data.title
        holder.itemView.tv_rv_tags.text = data.tags
        holder.itemView.tv_rv_amount.text = getFormattedNumber(data.amount.toString())
        holder.itemView.tv_rv_date.text = data.date

        if (data.type == "Expenses") {
            holder.itemView.tv8.text = "-"
            holder.itemView.tv_rv_amount.setTextColor(
                holder.itemView.context.resources.getColor(
                    android.R.color.holo_red_light
                )
            )
        } else {
            holder.itemView.tv8.text = "+"
            holder.itemView.tv_rv_amount.setTextColor(
                holder.itemView.context.resources.getColor(
                    android.R.color.holo_green_light
                )
            )
        }

        Log.d("title", data.toString())

    }

}