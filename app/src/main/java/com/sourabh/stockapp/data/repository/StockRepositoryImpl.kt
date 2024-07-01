package com.sourabh.stockapp.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.sourabh.stockapp.data.csv.CSVParser
import com.sourabh.stockapp.data.local.StockDatabase
import com.sourabh.stockapp.data.local.TopLoserEntity
import com.sourabh.stockapp.data.mapper.toCompanyInfo
import com.sourabh.stockapp.data.mapper.toGainerList
import com.sourabh.stockapp.data.mapper.toGainerListEntity
import com.sourabh.stockapp.data.mapper.toLoserList
import com.sourabh.stockapp.data.remote.StockApi
import com.sourabh.stockapp.domain.module.CompanyInfo
import com.sourabh.stockapp.domain.module.IntradayInfo
import com.sourabh.stockapp.domain.module.TopGainer
import com.sourabh.stockapp.domain.module.TopLoser
import com.sourabh.stockapp.domain.repository.StockRepository
import com.sourabh.stockapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import com.sourabh.stockapp.data.mapper.toLoserListEntity

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val intradayInfoParser: CSVParser<IntradayInfo>,

    ) : StockRepository {

    private fun parseResponseBody(responseBody: ResponseBody): List<TopGainer>? {
        val gson = Gson()
        val jsonString = responseBody.string()
        if (jsonString == null) {
            Log.e("parseResponseBody", "jsonString is null")
        }
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        if (jsonObject == null) {
            Log.e("parseResponseBody", "jsonObject is null")
        }
        val jsonArray = jsonObject.getAsJsonArray("top_gainers") // get the "top_gainers" array
        if (jsonArray == null) {
            Log.e("parseResponseBody", "jsonArray is null")
        }
        val listType = object : TypeToken<List<TopGainer>>() {}.type
        return gson.fromJson(jsonArray, listType)
    }

    private val dao = db.dao

    override suspend fun getTopGainerList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<TopGainer>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)

            emit(Resource.Success(
                data = localListings.map { it.toGainerList() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val topGainersFromApi = try {
                val response = api.getTopGainersLosers()
                parseResponseBody(response)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            topGainersFromApi.let {
                dao.clearCompanyListings()
                if (it != null) {
                    dao.insertTopGainers(
                        it.map { it.toGainerListEntity() }
                    )
                }
                if (it != null) {
                    emit(Resource.Success(
                        data = it.map { topGainer ->
                            topGainer.toGainerListEntity().toGainerList()
                        }
                    ))
                }
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getTopLoserList(
        fetchFromRemote: Boolean,
        query2: String
    ): Flow<Resource<List<TopLoser>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchTopLoserListing(query2)

            emit(Resource.Success(
                data = localListings.map { it.toLoserList() }
            ))

            val isDbEmpty = localListings.isEmpty() && query2.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val topLoserFromApi = try {
                val response = api.getTopGainersLosers()
                parseResponseBody(response)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            topLoserFromApi.let {
                dao.clearLoserListings()
                if (it != null) {
                    dao.insertTopLosers(
                        it.map { it.toLoserListEntity() }
                    )
                }
                if (it != null) {
                    emit(Resource.Success(
                        data = it.map { topLoser ->
                            topLoser.toLoserListEntity().toLoserList()
                        }
                    ))
                }
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            val response = api.getIntradayInfo(symbol)
            val results = intradayInfoParser.parse(response.byteStream())
            Resource.Success(results)
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(
                "Couldn't load data"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                "Couldn't load data"
            )
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val response = api.getCompanyInfo(symbol)
            Resource.Success(response.toCompanyInfo())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(
                "Couldn't load data"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                "Couldn't load data"
            )
        }
    }
}
