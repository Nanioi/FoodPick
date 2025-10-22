package com.example.foodpick.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.foodpick.domain.model.Store
import com.example.foodpick.ui.theme.White

/**
 * 화면 1) - 가게 목록 화면
 */
@Composable
fun StoreListScreen(
    stores: List<Store>,
    favoriteStores: List<Store>,
    onItemClick: (Store) -> Unit,
    onFavoriteToggle: (Store) -> Unit,
) {
    val showFavoriteSection = favoriteStores.isNotEmpty() // 자주 찾는 가게 노출 여부
    val insertIndex = 3 // 자주 찾는 가게 섹션 삽입 지점 3번째 이후

    val totalCount = stores.size + if (showFavoriteSection) 1 else 0

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        items(totalCount) { idx ->
            if (showFavoriteSection && idx == insertIndex) {
                FavoriteSection(
                    favoriteStores = favoriteStores,
                    onItemClick = onItemClick,
                    onFavoriteToggle = onFavoriteToggle
                )
            } else {
                val storeIndex = if (showFavoriteSection && idx > insertIndex) idx - 1 else idx
                if (storeIndex in stores.indices) {
                    StoreListItem(
                        store = stores[storeIndex],
                        onItemClick = onItemClick,
                        onFavoriteToggle = onFavoriteToggle
                    )
                }
            }
        }
    }
}