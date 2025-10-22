package com.example.foodpick.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("sizeheight") val sizeheight: String,
    @SerializedName("sizewidth") val sizewidth: String,
)