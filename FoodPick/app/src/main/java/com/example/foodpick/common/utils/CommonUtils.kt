package com.example.foodpick.common.utils

import com.example.foodpick.data.remote.dto.LocalDto
import com.naver.maps.geometry.LatLng
import org.jsoup.Jsoup


/**
 * Store ID 생성 함수
 */
fun LocalDto.generateStoreId(): String {
    val rawId = "${title.removeHtmlTags().trim()}|${roadAddress.trim()}"
    return rawId.hashCode().toString()
}

/**
 * String HTML 태그 제거
 */
fun String.removeHtmlTags(): String {
    return Jsoup.parse(this).text()
}

/**
 * (mapx, mapy) to LatLng
 */
fun wgs84ToLatLng(mapx: String, mapy: String): LatLng {
    val longitude = (mapx.toDoubleOrNull() ?: 0.0) / 1e7
    val latitude = (mapy.toDoubleOrNull() ?: 0.0) / 1e7
    return LatLng(latitude,longitude)
}