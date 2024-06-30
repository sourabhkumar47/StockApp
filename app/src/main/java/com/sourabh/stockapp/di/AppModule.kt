package com.sourabh.stockapp.di

import android.app.Application
import androidx.room.Room
import com.sourabh.stockapp.data.csv.CSVParser
import com.sourabh.stockapp.data.local.StockDatabase
import com.sourabh.stockapp.data.remote.StockApi
import com.sourabh.stockapp.data.repository.StockRepositoryImpl
import com.sourabh.stockapp.domain.module.IntradayInfo
import com.sourabh.stockapp.domain.repository.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
            )
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(
            app,
            StockDatabase::class.java,
            "stockdb.db"
        ).build()
    }

//    @Provides
//    @Singleton
//    fun provideStockRepository(
//        api: StockApi,
//        db: StockDatabase,
//        csv: CSVParser<IntradayInfo>
//    ): StockRepository = StockRepositoryImpl(api, db, csv)

}