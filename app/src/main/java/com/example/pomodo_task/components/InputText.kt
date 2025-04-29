package com.example.pomodo_task.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.pomodo_task.ui.theme.Green300


@Composable
fun InputText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    minLines: Int = 1,
    maxLines: Int = 1
) {

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
        value = value,
        minLines = minLines,
        maxLines = maxLines,
        onValueChange = { onValueChange(it) }
    )
}

@Preview(showBackground = true)
@Composable
private fun InputTextPreview() {

    InputText(
        label = "Título",
        onValueChange = {},
        value = "",
        modifier = Modifier
            .fillMaxWidth(),
    )

}