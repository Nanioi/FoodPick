package com.example.foodpick.domain.model

data class Store(
    val id: String,
    val title: String,
    val link:String,
    val category: String,
    val telephone: String,
    val roadAddress: String,
    val description: String,
    val mapx: String,
    val mapy: String,
    val thumbnails: List<String>,
    val isFavorite: Boolean,  // 기본값 false
)


