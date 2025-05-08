package com.example.pomodo_task.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodo_task.features.task.data.TaskEntity
import com.example.pomodo_task.ui.theme.Gray200
import com.example.pomodo_task.ui.theme.Gray400
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    item: TaskEntity
) {
    var expanded by remember { mutableStateOf(false) }

    val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy") }
    val dataFormatted by remember {
        mutableStateOf(
            dateFormat.format(item.dateLimit)
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = .4.dp
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Gray200
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 34.dp)
                        .horizontalScroll(state = rememberScrollState())
                ) {
                    Text(
                        text = item.name,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (isSystemInDarkTheme()) Gray200 else Color.Black
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(14.dp),
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null
                    )
                    Text(
                        text = dataFormatted,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (isSystemInDarkTheme()) Gray200 else Color.Black
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                CategoryChip(idCategory = item.categoryId)
                PriorityChip(value = item.priority)
            }

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TaskCardActions()
            }
        }
    }
}

@Composable
fun TaskCardActions(modifier: Modifier = Modifier) {

    var expandedMenuMais by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionButton(
            icon = Icons.Default.PlayArrow,
            onCLick = {},
            label = "Iniciar"
        )
        ActionButton(
            icon = Icons.Default.Visibility,
            onCLick = {},
            label = "Detalhes"
        )
        Box(
            modifier = Modifier.wrapContentSize(Alignment.TopStart)
        ) {
            ActionButton(
                icon = Icons.Default.MoreVert,
                onCLick = {
                    expandedMenuMais = !expandedMenuMais
                },
                label = "Mais"
            )

            DropdownMenu(
                modifier = Modifier
                    .background(color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White),
                expanded = expandedMenuMais,
                onDismissRequest = {
                    expandedMenuMais = false
                }
            ) {
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "edite"
                        )
                    },
                    text = {
                        Text("Editar")
                    },
                    onClick = {}
                )
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete"
                        )
                    },
                    text = {
                        Text("Delete")
                    },
                    onClick = {}
                )
            }
        }
    }

}

@Composable
private fun ActionButton(icon: ImageVector, onCLick: () -> Unit, label: String) {
    TextButton(
        onClick = {
            onCLick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = if (isSystemInDarkTheme()) Color.White else Gray400,
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
            Text(text = label)
        }
    }
}

@Preview
@Composable
private fun TaskCardPreview() {
    TaskCard(
        item = TaskEntity(
            id = 1,
            name = "teste",
            priority = 1,
            description = "",
            dateLimit = Date(),
            categoryId = 1
        )
    )
}
