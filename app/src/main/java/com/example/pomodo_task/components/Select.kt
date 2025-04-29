import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.pomodo_task.ui.theme.Green300

data class Option(
    val label: String,
    val value: Any
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Select(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (Any) -> Unit,
    label: String,
    options: List<Option>
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf<Option?>(null) }

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
            value = if (selected != null) selected!!.label else "",
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
                        selected = selectionOption
                        onValueChange(selectionOption.value)
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
    Select(
        modifier = Modifier.fillMaxWidth(),
        label = "Categoria",
        value = "Categoria A",
        onValueChange = {},
        options = listOf()
    )
}
