package com.sourabh.stockapp.data.repository

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.sourabh.stockapp.data.local.StockDatabase
import com.sourabh.stockapp.data.mapper.toGainerList
import com.sourabh.stockapp.data.mapper.toGainerListEntity
import com.sourabh.stockapp.data.remote.StockApi
import com.sourabh.stockapp.domain.module.TopGainer
import com.sourabh.stockapp.domain.repository.StockRepository
import com.sourabh.stockapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase
) : StockRepository {

    private fun parseResponseBody(responseBody: ResponseBody): List<TopGainer> {
        val gson = Gson()
        val jsonString = responseBody.string()
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        val jsonArray = jsonObject.getAsJsonArray("top_gainers") // get the "top_gainers" array
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
}