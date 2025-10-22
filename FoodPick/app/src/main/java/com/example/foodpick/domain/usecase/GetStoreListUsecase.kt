package com.example.foodpick.domain.usecase

import android.util.Log
import com.example.foodpick.domain.model.Store
import com.example.foodpick.domain.repository.StoreRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStoreListUsecase @Inject constructor(
    private val repository: StoreRepository
) {
    operator fun invoke(query: String): Flow<List<Store>> = flow {
        try {
            val stores = repository.getStoreList(query)
            emit(stores) // 썸네일 이미지가 느리게 내려올 수 있으므로 먼저 반영

            val storesWithThumbnails = fetchThumbnailsForStores(stores)
            emit(storesWithThumbnails) // 썸네일 받아오면 반영

        } catch (e: Exception) {
            Log.e("GetStoreListUsecase", "Error fetching store list for query: $query", e)
            emit(emptyList())
        }
    }

    private suspend fun fetchThumbnailsForStores(stores: List<Store>): List<Store> =
        coroutineScope {
            stores.map { store ->
                async {
                    val thumbnails = try {
                        repository.getThumbnails(store.title)
                    } catch (e: Exception) {
                        Log.e("GetStoreListUsecase", "Failed to fetch thumbnails for ${store.title}", e)
                        emptyList()
                    }
                    store.copy(thumbnails = thumbnails)
                }
            }.awaitAll()
        }
}