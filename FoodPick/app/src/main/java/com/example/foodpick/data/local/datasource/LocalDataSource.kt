package com.example.foodpick.data.local.datasource

import com.example.foodpick.data.local.entity.StoreEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getFavoriteStores(): Flow<List<StoreEntity>>
    suspend fun isLiked(id: String): Boolean
    suspend fun likeStore(store: StoreEntity)
    suspend fun unlikeStoreById(id: String)
}