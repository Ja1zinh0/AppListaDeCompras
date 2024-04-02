package com.ja1zinh0.applistadecompras.ui.functions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize


data class CardItem(val title: String, val description: String)

@Composable
fun CardItem(card: CardItem, onDeleteClicked: () -> Unit) {
    val colorRect = MaterialTheme.colorScheme.tertiaryContainer
    val colorTextRect = MaterialTheme.colorScheme.onTertiaryContainer
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
                    Box {
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