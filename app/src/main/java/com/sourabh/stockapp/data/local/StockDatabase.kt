package com.sourabh.stockapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TopGainerEntity::class,TopLoserEntity::class],
    version = 2
)
abstract class StockDatabase: RoomDatabase() {
    abstract val dao: StockDao
}