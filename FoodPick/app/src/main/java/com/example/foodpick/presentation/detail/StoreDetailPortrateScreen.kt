package com.example.foodpick.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodpick.domain.model.Store

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun StoreDetailPortrateScreen(
    store: Store,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        StoreDetailThumbnails(
            store.thumbnails,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        StoreDetailDescription(store)

    }
}
