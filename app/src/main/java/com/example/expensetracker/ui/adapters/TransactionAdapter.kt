package com.example.expensetracker.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.data.Transaction
import com.example.expensetracker.getFormattedNumber
import kotlinx.android.synthetic.main.list_items.view.*

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionsVh>() {

    private var data = arrayListOf<Transaction>()

    fun setData(list: List<Transaction>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }


    class TransactionsVh(view: View) : RecyclerView.ViewHolder(view) {

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
        holder.itemView.tv_rv_title.text = data[position].title
        holder.itemView.tv_rv_tags.text = data[position].tags
        holder.itemView.tv_rv_amount.text = getFormattedNumber(data[position].amount.toString())
        holder.itemView.tv_rv_date.text = data[position].date

        if (data[position].type == "Expenses") {
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

        Log.d("title", data[position].toString())

    }

    override fun getItemCount(): Int {
        return data.size
    }
}