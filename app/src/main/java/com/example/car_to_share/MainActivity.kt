package com.example.car_to_share

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.car_to_share.ui.screens.MainScreen
import com.example.car_to_share.ui.theme.Car_to_ShareTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Car_to_ShareTheme {
                MainScreen()
            }
        }
    }
}
