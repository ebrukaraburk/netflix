package com.example.netflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.netflix.ui.theme.NetflixTheme
import com.example.netflix.uix.views.SayfaGecisleri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetflixTheme {
                SayfaGecisleri()
            }
        }
    }
}
