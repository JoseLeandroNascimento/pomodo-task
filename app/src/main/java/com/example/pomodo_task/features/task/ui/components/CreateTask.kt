package com.example.pomodo_task.features.task.ui.components

import Option
import Select
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pomodo_task.components.InputDate
import com.example.pomodo_task.components.InputText
import com.example.pomodo_task.components.PriorityOption
import com.example.pomodo_task.components.PrioritySelect
import com.example.pomodo_task.components.optionsExemple
import com.example.pomodo_task.features.category.ui.viewModel.CategoryViewModel
import com.example.pomodo_task.ui.theme.Green300
import kotlinx.coroutines.flow.map
import java.util.Date


@Composable
fun CreateTask(
    modifier: Modifier = Modifier,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {

    val categoriesOptions = categoryViewModel.categories.map { categories ->
        categories.map { category ->
            Option(
                label = category.name,
                value = category.id
            )
        }
    }.collectAsState(initial = emptyList()).value

    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf<Option?>(null) }
    var priority by remember { mutableStateOf<PriorityOption?>(null) }
    var date by remember { mutableStateOf<Date?>(null) }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            }
            .fillMaxSize()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),

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

        InputDate(
            modifier = Modifier
                .fillMaxWidth(),
            label = "Data de vencimento",
            value = date,
            onValueChange = {
                date = it
            },
        )

        Spacer(modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green300
            ),
            onClick = {},
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White
                )
                Text(
                    "Salvar",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateTaskPreview() {
    CreateTask(modifier = Modifier)
}
