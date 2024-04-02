package com.ja1zinh0.applistadecompras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
