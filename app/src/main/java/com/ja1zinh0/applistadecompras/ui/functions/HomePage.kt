@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.ja1zinh0.applistadecompras.ui.functions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@ExperimentalMaterial3Api
@Composable
fun HomePage() {
    val cardList = remember { mutableStateListOf<CardItem>() }
    val scrollState = rememberScrollState()
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                title = { Text("Minhas listas") },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(enabled = true, state = scrollState)
            ) {
                cardList.forEach { card ->
                    CardItem(card) {
                        cardList.remove(card)
                    }
                }
                TextButton(
                    onClick = { showDialog.value = true },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text(text = "+ Nova lista", style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    ))
                }
            }
        }
        if (showDialog.value) {
            CustomAlertDialog(
                onDismissRequest = { showDialog.value = false },
                onConfirmation = { title ->
                    if(title.isNotEmpty()){
                        cardList.add(CardItem(title))
                        showDialog.value = false
                    }
                },
                dialogTitle = "Nome da lista",
            )
        }
    }
}
