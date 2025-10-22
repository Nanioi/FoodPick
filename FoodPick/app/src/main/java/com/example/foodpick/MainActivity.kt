package com.example.foodpick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.foodpick.presentation.MainScreen
import com.example.foodpick.ui.theme.FoodPickTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodPickTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    MainScreen()
}