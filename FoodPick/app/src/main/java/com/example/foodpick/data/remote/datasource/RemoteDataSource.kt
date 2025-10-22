package com.example.foodpick.data.remote.datasource

import com.example.foodpick.data.remote.dto.ImageDto
import com.example.foodpick.data.remote.dto.LocalDto

interface RemoteDataSource {
    suspend fun getLocalSearch(query: String): List<LocalDto>
    suspend fun getImageSearch(query: String): List<ImageDto>
}
