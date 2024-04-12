@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.ja1zinh0.applistadecompras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.ja1zinh0.applistadecompras.ui.functions.HomePage
import com.ja1zinh0.applistadecompras.ui.theme.AppListaDeComprasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppListaDeComprasTheme {
                HomePage()
            }
        }
    }
}
