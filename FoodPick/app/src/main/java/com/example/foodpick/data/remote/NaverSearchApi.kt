package com.example.foodpick.data.remote

import com.example.foodpick.data.remote.dto.ImageDto
import com.example.foodpick.data.remote.dto.LocalDto
import javax.inject.Inject

class NaverSearchApi @Inject constructor(
    private val service: NaverSearchApiService
) {
    suspend fun localSearch(query: String): List<LocalDto> {
        val response = service.getLocalSearch(query)
        return response.items
    }

    suspend fun imageSearch(query: String): List<ImageDto> {
        val response = service.getImageSearch(query)
        return response.items
    }
}
