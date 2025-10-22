package com.example.foodpick.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.foodpick.common.utils.StoreIntentUtils
import com.example.foodpick.domain.model.Store

@Composable
fun StoreDetailDescription(
    store: Store,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        StoreDetailHeaderSection(store = store)
        ScrollInfoSection(store = store)
    }
}

@Composable
fun StoreDetailHeaderSection(store: Store){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (store.title.isNotBlank()) {
            Text(
                text = store.title,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (store.category.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = store.category,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ScrollInfoSection(store : Store){

    val context =  LocalContext.current
    val infoList = listOfNotNull(
        store.telephone.takeIf { it.isNotBlank() }?.let { phone ->
            Triple("연락처", phone) { StoreIntentUtils.dialPhone(context, phone) }
        },
        store.roadAddress.takeIf { it.isNotBlank() }?.let { address ->
            Triple("주소", address) { StoreIntentUtils.openMap(context, address) }
        },
        (store.description.ifBlank { store.link }).takeIf { it.isNotBlank() }?.let { desc ->
            val onClick = if (store.description.isBlank() && store.link.isNotBlank()) {
                { StoreIntentUtils.openLink(context, store.link) }
            } else {
                null
            }
            Triple("설명", desc, onClick)
        },
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 25.dp, vertical = 15.dp)
    ) {
        infoList.forEachIndexed { index, (label, value, onClick) ->
            Spacer(modifier = Modifier.height(8.dp))
            ClickableDescText(
                label = label,
                value = value,
                onClick = onClick
            )
        }
    }
}
