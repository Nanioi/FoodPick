package com.example.foodpick.presentation.header

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.foodpick.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyHeader(
    currentRoute: String,
    onBackClick: () -> Unit = {},
    isLiked: Boolean = false,
    onLikeClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text("FoodPick!", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Yellow) },
        navigationIcon = {
            if (currentRoute != "storeList") {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Yellow)
                }
            }
        },
        actions = {
            if (currentRoute == "storeDetail") {
                IconButton(onClick = onLikeClick) {
                    val iconRes = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                    Icon(iconRes, contentDescription = "Like", tint = Yellow)
                }
            }
        }
    )
}
