package com.example.foodpick.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodpick.R
import com.example.foodpick.domain.model.Store
import com.example.foodpick.ui.theme.Grey
import com.example.foodpick.ui.theme.Yellow

/**
 * 자주 찾는 가게 아이템 화면
 */
@Composable
fun FavoriteStoreItem(
    store: Store,
    onItemClick: (Store) -> Unit,
    onFavoriteToggle: (Store) -> Unit
) {
    Column(
        modifier = Modifier
            .width(130.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onItemClick(store) }
            .padding(vertical = 15.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        ) {
            val thumbnailUrl = store.thumbnails.firstOrNull()?.takeIf { it.isNotBlank() }
            val imageModel = thumbnailUrl ?: R.drawable.default_image

            //썸네일
            AsyncImage(
                model = imageModel,
                contentDescription = "Store Thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.default_image),
                error = painterResource(id = R.drawable.default_image)
            )

            //찜버튼
            IconButton(
                onClick = { onFavoriteToggle(store) },
                modifier = Modifier.size(25.dp).align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Like Btn",
                    tint = Yellow,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // title
        Text(
            text = store.title,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // category, telephon 값이 있을때만 노출
        listOf(store.category, store.telephone).forEach { text ->
            if(text.isNotBlank()){
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelSmall,
                    color = Grey,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
