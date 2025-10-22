package com.example.foodpick.data.local.datasource

import com.example.foodpick.data.local.dao.StoreDao
import com.example.foodpick.data.local.entity.StoreEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val storeDao: StoreDao
) : LocalDataSource {

    override fun getFavoriteStores(): Flow<List<StoreEntity>> {
        return storeDao.getFavoriteStores()
    }

    override suspend fun isLiked(id: String): Boolean {
        return storeDao.isLiked(id)
    }

    override suspend fun likeStore(store: StoreEntity) {
        storeDao.likeStore(store)
    }

    override suspend fun unlikeStoreById(id: String) {
        storeDao.unlikeStoreById(id)
    }
}
