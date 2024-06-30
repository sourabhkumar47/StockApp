package com.sourabh.stockapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TopGainerEntity::class],
    version = 1
)
abstract class StockDatabase: RoomDatabase() {
    abstract val dao: StockDao
}