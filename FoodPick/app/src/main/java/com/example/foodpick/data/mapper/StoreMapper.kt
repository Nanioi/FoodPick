package com.example.foodpick.data.mapper

import com.example.foodpick.common.utils.removeHtmlTags
import com.example.foodpick.data.local.entity.StoreEntity
import com.example.foodpick.data.remote.dto.LocalDto
import com.example.foodpick.domain.model.Store

/**
 * LocalDto to Store
 */
fun LocalDto.toDomain(id: String): Store {
    return Store(
        id = id,
        title = title.removeHtmlTags(),
        link = link,
        category = category,
        roadAddress = roadAddress.ifBlank { address } ,
        telephone = telephone,
        description = description,
        mapx = mapx,
        mapy = mapy,
        thumbnails = emptyList(),
        isFavorite = false
    )
}

/**
 * StoreEntity to Store
 */
fun StoreEntity.toDomain(): Store = Store(id, title, link, category, telephone,
    roadAddress, description, mapx, mapy, thumbnails, isFavorite = true)

/**
 * Store to StoreEntity
 */
fun Store.toEntity(): StoreEntity = StoreEntity(id, title, link, category,
    telephone, roadAddress, description, mapx, mapy, thumbnails)