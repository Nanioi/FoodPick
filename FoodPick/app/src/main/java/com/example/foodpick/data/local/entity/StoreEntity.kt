package com.example.foodpick.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_store")
data class StoreEntity(
    @PrimaryKey val id: String,
    val title: String,
    val link: String,
    val category: String,
    val telephone: String,
    val roadAddress: String,
    val description: String,
    val mapx: String,
    val mapy: String,
    val thumbnails: List<String>,
)
