package com.example.foodpick.data.remote

import com.example.foodpick.data.remote.dto.ImageDto
import com.example.foodpick.data.remote.dto.NaverResponse
import com.example.foodpick.data.remote.dto.LocalDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverSearchApiService {
    @GET("local.json")
    suspend fun getLocalSearch(
        @Query("query") query: String,
        @Query("display") display: Int = 5
    ): NaverResponse<LocalDto>

    @GET("image.json")
    suspend fun getImageSearch(
        @Query("query") query: String
    ): NaverResponse<ImageDto>
}
