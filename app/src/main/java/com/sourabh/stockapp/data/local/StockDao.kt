package com.sourabh.stockapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopGainers(
        topGainerEntities: List<TopGainerEntity>
    )

    @Query("DELETE FROM topgainerentity")
    suspend fun clearCompanyListings()

    @Query(
        """
            SELECT * 
            FROM topgainerentity
            WHERE LOWER(ticker) LIKE '%' || LOWER(:query) || '%'
        """
    )
    suspend fun searchCompanyListing(query: String): List<TopGainerEntity>
}