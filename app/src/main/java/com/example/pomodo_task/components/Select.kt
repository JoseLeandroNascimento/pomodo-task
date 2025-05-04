import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.pomodo_task.ui.theme.Green300

data class Option<T>(
    val label: String,
    val value: T
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T>Select(
    modifier: Modifier = Modifier,
    value: Option<T>? = null,
    onValueChange: (Option<T>) -> Unit,
    label: String,
    options: List<Option<T>>
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            value = value?.label ?: "",
            onValueChange = {},
            label = {
                Text(
                    text = label,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Green300,
                focusedContainerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White,
                unfocusedContainerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White,
                focusedLabelColor = Green300
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        DropdownMenu(
            modifier = Modifier
                .heightIn(max = 220.dp)
                .exposedDropdownSize()
                .background(if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            properties = PopupProperties(
                focusable = true,
                dismissOnClickOutside = true,
                dismissOnBackPress = true,
            ),
            scrollState = rememberScrollState(),
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption.label) },
                    onClick = {
                        onValueChange(selectionOption)
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White),
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun SelectPreview() {
    Select<Int>(
        modifier = Modifier.fillMaxWidth(),
        label = "Categoria",
        value = null,
        onValueChange = {},
        options = listOf()
    )
}
