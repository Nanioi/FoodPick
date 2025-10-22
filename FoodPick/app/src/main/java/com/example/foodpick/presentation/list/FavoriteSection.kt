package com.example.foodpick.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodpick.domain.model.Store
import com.example.foodpick.ui.theme.Yellow
import com.example.foodpick.ui.theme.White

/**
 * 자주 찾는 가게 영역
 */
@Composable
fun FavoriteSection(
    favoriteStores: List<Store>,
    onItemClick: (Store) -> Unit,
    onFavoriteToggle: (Store) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Yellow)
            .fillMaxWidth()
    ) {
        Text(
            "자주 찾는 가게",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = White,
            modifier = Modifier.padding(start = 20.dp, top = 20.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(favoriteStores) { store ->
                FavoriteStoreItem(
                    store = store,
                    onItemClick = onItemClick,
                    onFavoriteToggle = onFavoriteToggle
                )
            }
        }
    }
}
