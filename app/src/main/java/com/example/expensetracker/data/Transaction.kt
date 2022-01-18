package com.example.expensetracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name="title")val title: String?,
    @ColumnInfo(name = "amount")val amount : Double?,
    @ColumnInfo(name ="type") val type : String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "tags") val tags: String?,
    @ColumnInfo(name = "description") val description: String?
)
