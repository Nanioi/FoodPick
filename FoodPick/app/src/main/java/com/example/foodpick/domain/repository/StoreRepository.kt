package com.example.foodpick.domain.repository

import com.example.foodpick.domain.model.Store
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun getStoreList(query : String): List<Store>

    suspend fun getThumbnails(title: String): List<String>

    fun getFavoriteStores(): Flow<List<Store>>

    suspend fun toggleFavorite(store: Store)

}