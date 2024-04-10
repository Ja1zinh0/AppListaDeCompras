@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.ja1zinh0.applistadecompras.ui.functions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

data class CardItem(val title: String)

@Composable
fun CardItem(
    card: CardItem,
    onDeleteClicked: () -> Unit,
) {

    val colorRect = MaterialTheme.colorScheme.tertiaryContainer
    val colorTextRect = MaterialTheme.colorScheme.onTertiaryContainer
    val textMeasurer = rememberTextMeasurer()

    var expanded by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val showDialog = remember { mutableStateOf(false) }


    data class ItemFromCard(val description: String, val valor: Float)

    val itemList = remember { mutableStateListOf<ItemFromCard>() }

    val totalItems = itemList.size
    val totalValue = itemList.fold(0f) { acc, item -> acc + item.valor }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Card(modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(top = 15.dp)
            .requiredHeight(180.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 12.dp
            ),
            onClick = {
                showBottomSheet = true
            }) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxSize(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                                        "Excluir", style = TextStyle(
                                            textAlign = TextAlign.Center,
                                            fontSize = 18.sp,
                                        ), modifier = Modifier.fillMaxSize()
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
                            val measuredText = textMeasurer.measure(
                                AnnotatedString(totalItems.toString()),
                                constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                                style = TextStyle(
                                    color = colorTextRect,
                                    fontSize = 16.sp,
                                ),
                            )

                            val text2 = if (totalItems == 1) {
                                textMeasurer.measure(
                                    AnnotatedString("item"),
                                    constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                                    style = TextStyle(
                                        color = colorTextRect,
                                        fontSize = 16.sp,
                                    ),
                                )
                            } else {
                                textMeasurer.measure(
                                    AnnotatedString("itens"),
                                    constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                                    style = TextStyle(
                                        color = colorTextRect,
                                        fontSize = 16.sp,
                                    ),
                                )
                            }


                            onDrawBehind {
                                drawRoundRect(
                                    colorRect,
                                    size = measuredText.size.toSize(),
                                    cornerRadius = CornerRadius(40f)
                                )
                                drawText(
                                    measuredText, topLeft = Offset(30f, 0f)
                                )
                                drawText(
                                    text2, topLeft = Offset(90f, 0f)
                                )
                            }
                        }
                )
                Spacer(modifier = Modifier
                    .requiredWidth(230.dp)
                    .padding(top = 100.dp)
                    .drawWithCache {
                        val measuredText = textMeasurer.measure(
                            AnnotatedString("Total: "),
                            constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
                            style = TextStyle(
                                color = colorTextRect,
                                fontSize = 16.sp,
                            ),
                        )
                        val text2 = textMeasurer.measure(

                            AnnotatedString(
                                "R$ ${
                                    String
                                        .format("%.2f", totalValue)
                                        .replace(".", ",")
                                }"
                            ),
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
                                measuredText, topLeft = Offset(20f, 0f)
                            )
                            drawText(
                                text2, topLeft = Offset(160f, 0f)
                            )
                        }
                    })
                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        }, sheetState = sheetState, modifier = Modifier.fillMaxHeight()
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column {
                                itemList.forEach { item ->
                                    val formattedValue =
                                        String.format("%.2f", item.valor).replace(".", ",")
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                    ) {
                                        Text(
                                            text = item.description,
                                            modifier = Modifier.padding(start = 16.dp)
                                        )
                                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                            Text(text = "R$ $formattedValue")
                                            IconButton(
                                                onClick = {
                                                    itemList.remove(item)
                                                },

                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.Delete,
                                                    contentDescription = "Overflow menu",
                                                    modifier = Modifier.padding(bottom = 24.dp, end = 10.dp)
                                                )
                                            }
                                        }

                                    }

                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    TextButton(onClick = {
                                        showDialog.value = true
                                    }) {
                                        Text(text = "Adicionar item")
                                    }
                                }
                            }

                            if (showDialog.value) {
                                ItemCardDialog(
                                    onDismissRequest = { showDialog.value = false },
                                    onConfirmation = { description, value ->
                                        itemList.add(ItemFromCard(description, value))
                                        showDialog.value = false
                                    },
                                    dialogTitle = "Digite as informações",
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
    dialogTitle: String,
) {
    var textFieldValue by remember { mutableStateOf("") }
    val dialogContent = @Composable {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = textFieldValue,
                onValueChange = { newValue ->
                    val maxTextFieldLength = 10
                    if (newValue.length <= maxTextFieldLength) {
                        textFieldValue = newValue
                    }
                },
                maxLines = 1,
            )
        }
    }
    AlertDialog(onDismissRequest = onDismissRequest, confirmButton = {
        Button(onClick = {
            onConfirmation(textFieldValue)
        }) {
            Text("Criar")
        }
    }, dismissButton = {
        Button(
            onClick = onDismissRequest
        ) {
            Text("Cancelar")
        }
    }, title = {
        Text(
            text = dialogTitle, modifier = Modifier.fillMaxWidth(), style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )
        )
    }, text = dialogContent
    )
}

@Composable
fun ItemCardDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String, Float) -> Unit,
    dialogTitle: String,
) {
    var textFieldValue by remember { mutableStateOf("") }
    var numberFieldValue by remember { mutableStateOf("") }
    val dialogContent = @Composable {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                label = { Text("Produto") },
                placeholder = { Text(text = "Digite o nome do produto") },
                value = textFieldValue,
                onValueChange = { newValue ->
                    val maxTextFieldLength = 10
                    if (newValue.length <= maxTextFieldLength) {
                        textFieldValue = newValue
                    }
                },
                maxLines = 1,
            )
            OutlinedTextField(
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                label = { Text("Valor") },
                placeholder = { Text(text = "Digite o valor do produto") },
                value = numberFieldValue,
                onValueChange = { newValue ->
                    val maxTextFieldLength = 6
                    val sanitizedValue = newValue.replace(',', '.')
                    if (newValue.length <= maxTextFieldLength) {
                        numberFieldValue = sanitizedValue
                    }
                },
                maxLines = 1,
                modifier = Modifier.padding(top = 15.dp),

                )
        }

    }
    AlertDialog(onDismissRequest = onDismissRequest, confirmButton = {
        Button(onClick = {
            onConfirmation(textFieldValue, numberFieldValue.toFloat())
        }) {
            Text("Adicionar")
        }
    }, dismissButton = {
        Button(
            onClick = onDismissRequest
        ) {
            Text("Cancelar")
        }
    }, title = {
        Text(
            text = dialogTitle, modifier = Modifier.fillMaxWidth(), style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )
        )
    }, text = dialogContent
    )
}