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

    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTopLosers(
//        topLoserEntities: List<TopLoserEntity>
//    )
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopLosers(
        topLoserEntities: List<TopLoserEntity>
    )

    @Query("DELETE FROM toploserentity")
    suspend fun clearLoserListings()

//    @Query("SELECT * FROM TopLoserEntity")
//    fun getAllTopLosers(): Flow<List<TopLoserEntity>>


    @Query(
        """
            SELECT * 
            FROM topgainerentity
            WHERE LOWER(ticker) LIKE '%' || LOWER(:query) || '%'
        """
    )
    suspend fun searchCompanyListing(query: String): List<TopGainerEntity>

    @Query(
        """
            SELECT * 
            FROM toploserentity
            WHERE LOWER(ticker) LIKE '%' || LOWER(:query2) || '%'
        """
    )
    suspend fun searchTopLoserListing(query2: String): List<TopLoserEntity>
}