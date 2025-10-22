package com.example.foodpick.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodpick.R
import com.example.foodpick.domain.model.Store
import com.example.foodpick.ui.theme.*

/**
 * 가게 아이템 화면
 */
@Composable
fun StoreListItem(
    store: Store,
    onItemClick: (Store) -> Unit,
    onFavoriteToggle: (Store) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onItemClick(store) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = White),
    ) {
        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            val thumbnailUrl = store.thumbnails.firstOrNull { it.isNotBlank() }
            val imageModel = thumbnailUrl ?: R.drawable.default_image

            AsyncImage(
                model = imageModel,
                contentDescription = "Store Thumbnail",
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.default_image),
                error = painterResource(id = R.drawable.default_image)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = store.title, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                // category, telephon 값이 있을때만 노출
                listOf(store.category, store.telephone).forEach { text ->
                    if(text.isNotBlank()) {
                        Text(text = text, style = MaterialTheme.typography.bodySmall, color = Grey, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }

            IconButton(onClick = { onFavoriteToggle(store) }) {
                Icon(
                    imageVector = if (store.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (store.isFavorite) "찜 해제" else "찜 추가",
                    tint = Yellow
                )
            }
        }
    }

}