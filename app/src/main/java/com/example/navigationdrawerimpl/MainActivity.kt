package com.example.navigationdrawerimpl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.navigationdrawerimpl.ui.theme.NavigationDrawerImplTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerImplTheme {
                NavDrawer()
            }
        }
    }
}