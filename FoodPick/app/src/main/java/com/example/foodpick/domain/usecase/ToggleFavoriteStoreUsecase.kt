package com.example.foodpick.domain.usecase

import com.example.foodpick.domain.model.Store
import com.example.foodpick.domain.repository.StoreRepository
import javax.inject.Inject

class ToggleFavoriteStoreUsecase @Inject constructor(
    private val repository: StoreRepository
) {
    suspend operator fun invoke(store: Store) = repository.toggleFavorite(store)
}