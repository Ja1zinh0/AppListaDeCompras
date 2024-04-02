@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.ja1zinh0.applistadecompras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import com.ja1zinh0.applistadecompras.ui.theme.AppListaDeComprasTheme
import kotlin.math.exp

data class CardItem(val title: String, val description: String)

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val cardList = remember { mutableStateListOf<CardItem>() }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = colorScheme.primaryContainer,
                    titleContentColor = colorScheme.onPrimaryContainer,
                ),
                title = { Text("Minhas listas") },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = colorScheme.primaryContainer,
                contentColor = colorScheme.onPrimaryContainer,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Adicione um novo card à lista
                    cardList.add(CardItem("Casa", "nada ainda"))
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Card")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                cardList.forEach { card ->
                    CardItem(card) {
                        cardList.remove(card)
                    }
                }
            }
        }
    }
}

@Composable
fun CardItem(card: CardItem, onDeleteClicked: () -> Unit) {
    val colorRect = colorScheme.tertiaryContainer
    val colorTextRect = colorScheme.onTertiaryContainer
    val textMeasurer = rememberTextMeasurer()
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 15.dp)
                .requiredHeight(180.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            ),
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxSize(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = card.title,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box() {
                        IconButton(
                            onClick = {
                                expanded = true

                            },
                            modifier = Modifier.offset(18.dp, 0.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Overflow menu",
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            val teste = "asdasd"
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "Excluir",
                                        style = TextStyle(
                                            textAlign = TextAlign.Center,
                                            fontSize = 16.sp,
                                        ),
                                        modifier = Modifier
                                            .fillMaxSize()
                                    )
                                },
                                onClick = {
                                    onDeleteClicked()
                                    expanded = false
                                },
                            )
                        }
                    }
                }

                Spacer(
                    modifier = Modifier
                        .requiredWidth(120.dp)
                        .drawWithCache {
                            val measuredText =
                                textMeasurer.measure(
                                    AnnotatedString("10"),
                                    constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                                    style = TextStyle(
                                        color = colorTextRect,
                                        fontSize = 16.sp,
                                    ),
                                )
                            val text2 =
                                textMeasurer.measure(
                                    AnnotatedString("itens"),
                                    constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                                    style = TextStyle(
                                        color = colorTextRect,
                                        fontSize = 16.sp,
                                    ),
                                )
                            onDrawBehind {
                                drawRoundRect(
                                    colorRect,
                                    size = measuredText.size.toSize(),
                                    cornerRadius = CornerRadius(40f)
                                )
                                drawText(
                                    measuredText,
                                    topLeft = Offset(20f, 0f)
                                )
                                drawText(
                                    text2,
                                    topLeft = Offset(100f, 0f)
                                )
                            }
                        }
                )
                Spacer(
                    modifier = Modifier
                        .requiredWidth(230.dp)
                        .padding(top = 100.dp)
                        .drawWithCache {
                            val measuredText =
                                textMeasurer.measure(
                                    AnnotatedString("Total: "),
                                    constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                                    style = TextStyle(
                                        color = colorTextRect,
                                        fontSize = 16.sp,
                                    ),
                                )
                            val text2 =
                                textMeasurer.measure(
                                    AnnotatedString("R$ 9999,00"),
                                    constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                                    style = TextStyle(
                                        color = colorTextRect,
                                        fontSize = 16.sp,
                                    ),
                                )
                            onDrawBehind {
                                drawRoundRect(
                                    colorRect,
                                    size = measuredText.size.toSize(),
                                    cornerRadius = CornerRadius(40f)
                                )
                                drawText(
                                    measuredText,
                                    topLeft = Offset(20f, 0f)
                                )
                                drawText(
                                    text2,
                                    topLeft = Offset(160f, 0f)
                                )
                            }
                        }
                )
            }
        }
    }
}