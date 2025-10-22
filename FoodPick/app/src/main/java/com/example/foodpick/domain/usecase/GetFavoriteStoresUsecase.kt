package com.example.foodpick.domain.usecase

import com.example.foodpick.domain.model.Store
import com.example.foodpick.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteStoresUseCase @Inject constructor(
    private val repository: StoreRepository
) {
    operator fun invoke(): Flow<List<Store>> = repository.getFavoriteStores()
}