package com.sourabh.stockapp.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=TOP_GAINERS_LOSERS")
    suspend fun getTopGainersLosers(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    companion object {
        const val API_KEY = "EJM90P6N9TAAFS7R"
        const val BASE_URL = "https://alphavantage.co"
    }
}