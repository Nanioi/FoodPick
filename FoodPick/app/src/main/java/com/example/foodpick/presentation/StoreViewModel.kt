package com.example.foodpick.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodpick.domain.model.Store
import com.example.foodpick.domain.usecase.GetFavoriteStoresUseCase
import com.example.foodpick.domain.usecase.GetStoreListUsecase
import com.example.foodpick.domain.usecase.ToggleFavoriteStoreUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val getStoreListUsecase: GetStoreListUsecase,
    private val getFavoriteStoresUsecase: GetFavoriteStoresUseCase,
    private val toggleFavoriteStoreUsecase: ToggleFavoriteStoreUsecase,
) : ViewModel() {

    private var _isInitialized = false

    fun initializeIfNeeded(query: String) {
        if (!_isInitialized) {
            getStoreList(query)
            getFavoriteStores()
            _isInitialized = true
        }
    }

    private val _stores = MutableStateFlow<List<Store>>(emptyList())

    private val _favoriteStores = MutableStateFlow<List<Store>>(emptyList())
    val favoriteStores = _favoriteStores.asStateFlow()

    val stores: StateFlow<List<Store>> = combine(_stores, _favoriteStores) { stores, favorites ->
        val favoriteIds = favorites.map { it.id }.toSet()
        stores.map { store -> store.copy(isFavorite = store.id in favoriteIds) }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _selectedStoreId = MutableStateFlow<String?>(null)
    val selectedStore: StateFlow<Store?> = combine(_selectedStoreId, stores) { selectedId, stores ->
        selectedId?.let { id -> stores.find { it.id == id } }
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    private fun getStoreList(query: String) = viewModelScope.launch {
        getStoreListUsecase(query = query).collectLatest { _stores.value = it }
    }

    private fun getFavoriteStores() = viewModelScope.launch {
        getFavoriteStoresUsecase().collectLatest { _favoriteStores.value = it }
    }

    fun selectStore(store: Store) {
        _selectedStoreId.value = store.id
    }
    fun clearSelectedStore() {
        _selectedStoreId.value = null
    }

    fun toggleFavoriteStore(store: Store) = viewModelScope.launch {
        toggleFavoriteStoreUsecase(store = store)
    }
}