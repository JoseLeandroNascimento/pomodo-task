package com.example.pomodo_task.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodo_task.R
import com.example.pomodo_task.components.TaskCard
import com.example.pomodo_task.ui.theme.Gray200

@Composable
fun MyTasks(modifier: Modifier = Modifier) {

    val categories: List<String> = listOf(
        "Todo",
        "Trabalho",
        "Escola",
        "Mercado",
        "Domestica",
        "Domestica"
    )

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(count = categories.size) {
                    FilterChip(
                        selected = true,
                        onClick = {},
                        label = { Text(text = categories[it]) }
                    )
                }
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Minhas Tarefas",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = {},
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_filter_list_24),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            if (isSystemInDarkTheme()) Gray200 else Color.Black
                        )

                    )
                }
            }
        }
        items(count = 10) {
            TaskCard()
        }
    }
}

