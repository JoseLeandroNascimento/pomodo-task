package com.example.pomodo_task.screen

import Option
import Select
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodo_task.components.InputText
import com.example.pomodo_task.components.PriorityOption
import com.example.pomodo_task.components.PrioritySelect
import com.example.pomodo_task.components.optionsExemple


@Composable
fun CreateTask(modifier: Modifier = Modifier) {

    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf<Option?>(null) }
    var priority by remember { mutableStateOf<PriorityOption?>(null) }

    val focusManager = LocalFocusManager.current

    val categoriesOptions = listOf(
        Option(
            label = "categoria A",
            value = "A"
        ),
        Option(
            label = "categoria B",
            value = "B"
        ),
        Option(
            label = "categoria C",
            value = "C"
        ),
        Option(
            label = "categoria D",
            value = "D"
        ),
        Option(
            label = "categoria E",
            value = "E"
        ),
        Option(
            label = "categoria E",
            value = "E"
        ),
        Option(
            label = "categoria E",
            value = "E"
        ),
        Option(
            label = "categoria E",
            value = "E"
        ),
        Option(
            label = "categoria E",
            value = "E"
        ),
        Option(
            label = "categoria E",
            value = "E"
        ),
        Option(
            label = "categoria E",
            value = "E"
        ),
        Option(
            label = "categoria E",
            value = "E"
        ),
        Option(
            label = "categoria E",
            value = "E"
        ),
        Option(
            label = "categoria E",
            value = "E"
        ),
    )

    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            }
            .fillMaxSize()
            .padding(vertical = 4.dp, horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {

        InputText(
            label = "Título",
            onValueChange = { title = it },
            value = title,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Select(
            label = "Categoria",
            onValueChange = {
                category = it
            },
            value = category,
            options = categoriesOptions,
            modifier = Modifier
                .fillMaxWidth(),
        )

        Column {
            Text(
                modifier = Modifier.padding(start = 14.dp, bottom = 8.dp),
                text = "Prioridade",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            PrioritySelect(
                modifier.fillMaxWidth(),
                options = optionsExemple,
                value = priority,
                onValueChange = { priority = it }
            )
        }

        InputText(
            label = "Descrição",
            onValueChange = { title = it },
            value = title,
            minLines = 4,
            maxLines = 4,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateTaskPreview() {
    CreateTask(modifier = Modifier)
}
