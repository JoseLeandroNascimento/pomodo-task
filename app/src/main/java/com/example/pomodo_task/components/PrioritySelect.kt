package com.example.pomodo_task.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pomodo_task.R
import com.example.pomodo_task.ui.theme.Green300


data class PriorityOption(
    val label: String,
    val icon: @Composable() (() -> Unit)? = null,
    val value: String
)

val optionsExemple = listOf(

    PriorityOption(
        label = "Baixa",
        icon = {
            Image(
                painter = painterResource(id = R.drawable.emoji_flat_cold_face),
                contentDescription = null
            )
        },
        value = "BAIXA"
    ),
    PriorityOption(
        label = "Normal",
        icon = {
            Image(
                painter = painterResource(id = R.drawable.emoji_slightly_smiling_face),
                contentDescription = null
            )
        },
        value = "NORMAL"
    ),
    PriorityOption(
        label = "Alta",
        icon = {
            Image(
                painter = painterResource(id = R.drawable.emoji_noto_hot_face),
                contentDescription = null
            )
        },
        value = "ALTA"
    )
)

@Composable
fun PrioritySelect(
    modifier: Modifier = Modifier,
    value: PriorityOption? = null,
    onValueChange: (PriorityOption) -> Unit,
    options: List<PriorityOption>
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(options.size) {
            AssistChip(
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp),
                onClick = {
                    onValueChange(options[it])
                },
                label = {
                    Text(
                        text = options[it].label,
                        color = if (isSystemInDarkTheme() || options[it].value == value?.value) Color.White else Color.Black
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (options[it].value == value?.value) Green300 else MaterialTheme.colorScheme.surface
                ),
                leadingIcon = { options[it].icon?.invoke() }
            )
        }
    }
}

@Preview
@Composable
private fun PrioritySelectPreview() {
    PrioritySelect(options = optionsExemple, value = null, onValueChange = {})
}