package com.example.foodpick.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodpick.domain.model.Store

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun StoreDetailLandscapeScreen(
    store: Store,
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        StoreDetailThumbnails(
            store.thumbnails,
            modifier = Modifier
            .fillMaxHeight()
            .width(400.dp)
        )
        StoreDetailDescription(store)
    }
}
