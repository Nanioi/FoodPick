package com.example.foodpick.presentation

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodpick.presentation.detail.StoreDetailLandscapeScreen
import com.example.foodpick.presentation.detail.StoreDetailPortrateScreen
import com.example.foodpick.presentation.header.MyHeader
import com.example.foodpick.presentation.list.StoreListScreen
import com.example.foodpick.presentation.map.StoreMapScreen
import com.example.foodpick.ui.theme.Yellow
import com.example.foodpick.ui.theme.White

@Composable
fun MainScreen(
    viewModel: StoreViewModel = hiltViewModel()
){

    val query = "강남맛집"

    val stores by viewModel.stores.collectAsState(initial = emptyList())
    val favoriteStores by viewModel.favoriteStores.collectAsState(initial = emptyList())
    val selectedStore by viewModel.selectedStore.collectAsState(initial = null)

    LaunchedEffect(viewModel) {
        viewModel.initializeIfNeeded(query)
    }

    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route ?: "storeList"

    Scaffold(
        topBar = {
            MyHeader(
                currentRoute = currentRoute,
                isLiked = selectedStore?.isFavorite ?: false,
                onBackClick = {
                    navController.popBackStack()
                },
                onLikeClick = {
                    selectedStore?.let { viewModel.toggleFavoriteStore(it) }
                }
            )
        },
        floatingActionButton = {
            if(currentRoute == "storeList"){
                Button(
                    onClick = { navController.navigate("storeMap") },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(48.dp)
                        .width(120.dp)
                        .border(1.dp, White, RoundedCornerShape(8.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Yellow,
                        contentColor = White
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 12.dp
                    )
                ) {
                    Text(text = "지도 보기")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ){ innerPadding->
        NavHost(
            navController = navController,
            startDestination = "storeList",
            modifier = Modifier.padding(innerPadding)
        ){
            composable("storeList") {
                StoreListScreen(
                    stores = stores,
                    favoriteStores = favoriteStores,
                    onItemClick = { store ->
                        viewModel.selectStore(store)
                        navController.navigate("storeDetail")
                    },
                    onFavoriteToggle = { store ->
                        viewModel.toggleFavoriteStore(store)
                    },
                )
            }
            composable("storeDetail") {
                selectedStore?.let { store ->
                    val configuration = LocalConfiguration.current
                    if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                        StoreDetailLandscapeScreen(store = store)
                    }else{
                        StoreDetailPortrateScreen(store = store)
                    }
                }
            }
            composable("storeMap") {
                StoreMapScreen(
                    stores = stores,
                    onItemClick = { store ->
                        viewModel.selectStore(store)
                        navController.navigate("storeDetail")
                    },
                )
            }
        }
    }
}