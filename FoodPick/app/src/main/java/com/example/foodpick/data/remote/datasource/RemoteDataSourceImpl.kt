package com.example.foodpick.data.remote.datasource

import com.example.foodpick.data.remote.NaverSearchApi
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: NaverSearchApi
): RemoteDataSource {
    override suspend fun getLocalSearch(query: String) = api.localSearch(query)
    override suspend fun getImageSearch(query: String) = api.imageSearch(query)

}
