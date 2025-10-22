package com.example.foodpick.presentation.map

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.foodpick.R
import com.example.foodpick.common.utils.wgs84ToLatLng
import com.example.foodpick.domain.model.Store
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun StoreMapScreen(
    stores: List<Store>,
    onItemClick: (Store) -> Unit,
) {

    val initialPosition = stores.firstOrNull()?.let {
        wgs84ToLatLng(it.mapx, it.mapy)
    } ?: LatLng(127.027621, 37.497942)

    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition(initialPosition, 13.0)
    }

    NaverMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        stores.forEach { store ->
            val position = wgs84ToLatLng(store.mapx, store.mapy)
            val icon = getResizedOverlayImage(
                resId = if (store.isFavorite) R.drawable.favorite_marker else R.drawable.unfavorite_marker,
                size = 70
            )

            Marker(
                state = MarkerState(position = position),
                captionText = store.title,
                icon = icon,
                onClick = {
                    onItemClick(store)
                    true
                }
            )
        }
    }
}

@Composable
fun getResizedOverlayImage(@DrawableRes resId: Int, size: Int = 64): OverlayImage {
    val context = LocalContext.current
    val bitmap = BitmapFactory.decodeResource(context.resources, resId)
    val scaledBitmap = Bitmap.createScaledBitmap(bitmap, size, size, true)
    return OverlayImage.fromBitmap(scaledBitmap)
}
