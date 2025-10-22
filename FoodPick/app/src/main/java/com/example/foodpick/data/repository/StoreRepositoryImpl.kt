package com.example.foodpick.data.repository

import com.example.foodpick.common.utils.generateStoreId
import com.example.foodpick.data.local.datasource.LocalDataSource
import com.example.foodpick.data.remote.datasource.RemoteDataSource
import com.example.foodpick.data.mapper.toDomain
import com.example.foodpick.data.mapper.toEntity
import com.example.foodpick.domain.model.Store
import com.example.foodpick.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : StoreRepository {
    override suspend fun getStoreList(query: String): List<Store> {
        val localItems = remoteDataSource.getLocalSearch(query)
        return localItems.map { dto ->
            val id = dto.generateStoreId()
            dto.toDomain(id)
        }
    }

    override suspend fun getThumbnails(title: String): List<String> {
        return remoteDataSource.getImageSearch(title).map { it.thumbnail }
    }

    override fun getFavoriteStores(): Flow<List<Store>> =
        localDataSource.getFavoriteStores().map { list -> list.map { it.toDomain() } }

    override suspend fun toggleFavorite(store: Store) {
        val existing = localDataSource.isLiked(store.id)
        if (existing) {
            localDataSource.unlikeStoreById(store.id)
        } else {
            localDataSource.likeStore(store.toEntity())
        }
    }
}
