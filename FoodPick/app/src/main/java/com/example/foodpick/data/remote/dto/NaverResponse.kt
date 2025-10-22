package com.example.foodpick.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NaverResponse<T>(
    @SerializedName("lastBuildDate") val lastBuildDate: String,
    @SerializedName("total") val total: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("display") val display: Int,
    @SerializedName("items") val items: List<T>
)
