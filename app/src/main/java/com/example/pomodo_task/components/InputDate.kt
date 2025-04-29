package com.example.pomodo_task.components

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
    val datePikerState = rememberDatePickerState()
    val dateFormat = remember {SimpleDateFormat("dd/MM/yyyy") }
    var selectedText by remember { mutableStateOf("") }

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
        onValueChange = { },
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
                // Se quiser, adicione um botão de cancelar aqui
            },
            confirmButton = {
                Button(onClick = {
                    val millis = datePikerState.selectedDateMillis
                    if (millis != null) {
                        val date = Date(millis)
                        selectedText = dateFormat.format(date)
                        onValueChange(date)
                    }
                    showModal = false
                }) {
                    Text(text = "Confirmar")
                }
            }
        ) {
            DatePicker(state = datePikerState)
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