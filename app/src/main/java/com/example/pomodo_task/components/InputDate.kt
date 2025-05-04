package com.example.pomodo_task.components

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodo_task.ui.theme.Green300
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDate(
    modifier: Modifier = Modifier,
    label: String,
    onValueChange: (Date) -> Unit,
    value: Date? = null
) {

    var showModal by remember { mutableStateOf(false) }
    val datePikerState = rememberDatePickerState(initialSelectedDateMillis = value?.time ?: Date().time)
    val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy") }
    var selectedText by remember {
        mutableStateOf(
           ""
        )
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressed) {
        if (isPressed) {
            showModal = !showModal
        }
    }

    OutlinedTextField(
        modifier = modifier,
        label = {
            Text(
                text = label,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Green300,
            focusedContainerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White,
            unfocusedContainerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White,
            focusedLabelColor = Green300
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        value = selectedText,
        readOnly = true,
        onValueChange = { },
        interactionSource = interactionSource,
        trailingIcon = {
            IconButton(onClick = {
                showModal = !showModal
            }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
            }
        }
    )

    if (showModal) {
        DatePickerDialog(
            onDismissRequest = {
                showModal = false
            },
            dismissButton = {
                TextButton(onClick = {
                    showModal = false
                }) {
                    Text(text = "Cancelar", color = Green300)
                }
            },
            colors = DatePickerDefaults.colors(
                containerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White,
            ),
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green300
                    ),
                    onClick = {
                        val millis = datePikerState.selectedDateMillis
                        if (millis != null) {
                            val date = Date(millis)
                            selectedText = dateFormat.format(date)
                            onValueChange(date)
                        }
                        showModal = false
                    }) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "Confirmar")
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Confirm")
                    }
                }
            }
        ) {
            DatePicker(
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = Green300,
                    todayDateBorderColor = Green300,
                    selectedYearContainerColor = Green300,
                ),
                state = datePikerState
            )
        }
    }

}

@Preview
@Composable
private fun InputDatePreview() {
    InputDate(
        label = "Date",
        onValueChange = {},
    )
}