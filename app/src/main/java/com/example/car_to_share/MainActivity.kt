package com.example.car_to_share

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.car_to_share.ui.screens.LoginScreen
import com.example.car_to_share.ui.screens.MainScreen
import com.example.car_to_share.ui.theme.Car_to_ShareTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Car_to_ShareTheme {
                val auth = FirebaseAuth.getInstance()
                var user by remember { mutableStateOf(auth.currentUser) }

                if (user == null) {
                    LoginScreen(onLoginSuccess = {
                        user = auth.currentUser
                    })
                } else {
                    MainScreen()
                }
            }
        }
    }
}
