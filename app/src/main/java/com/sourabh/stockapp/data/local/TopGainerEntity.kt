package com.sourabh.stockapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopGainerEntity(
    val change_amount: String,
    val change_percentage: String,
    val price: String,
    val ticker: String,
    val volume: String,
    @PrimaryKey val id: Int? = null
)