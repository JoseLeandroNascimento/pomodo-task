package com.example.pomodo_task.features.task.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.pomodo_task.R
import com.example.pomodo_task.Screen
import com.example.pomodo_task.components.TaskCard
import com.example.pomodo_task.features.category.model.CategoryEntity
import com.example.pomodo_task.features.category.ui.viewModel.CategoryViewModel
import com.example.pomodo_task.features.task.ui.viewModel.TaskViewModel
import com.example.pomodo_task.layout.TopBarBase
import com.example.pomodo_task.ui.theme.Gray200
import com.example.pomodo_task.ui.theme.Green300

@Composable
fun MyTasks(
    modifier: Modifier = Modifier,
    onOpenDrawer: (() -> Unit)? = null,
    navController: NavHostController? = null
) {

    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories by categoryViewModel.categoriesActive.collectAsState()
    val taskViewModel: TaskViewModel = hiltViewModel()
    val tasks by taskViewModel.tasks.collectAsState()
    var categoryFilter by remember { mutableStateOf<CategoryEntity?>(null) }


    LaunchedEffect(Unit) {
        taskViewModel.setCategoryFilter(null)
    }

    Scaffold(
        topBar = {
            TopBarBase(
                onOpenDrawer = {
                    onOpenDrawer?.invoke()
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 4.dp, horizontal = 8.dp),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),

                ) {
                item {
                    CategoryFilter(
                        modifier = Modifier,
                        value = categoryFilter,
                        onSelect = {
                            categoryFilter = it
                            taskViewModel.setCategoryFilter(categoryFilter?.id)
                        },
                        categories = categories
                    )
                }
                item {
                    TaskHeader()
                }
                if (tasks.isNotEmpty()) {

                    items(items = tasks, key = { it.id }) {
                        TaskCard(item = it)
                    }
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(bottom = 44.dp, end = 28.dp),
                containerColor = Green300,
                shape = CircleShape,
                onClick = {
                    navController?.let {
                        navController.navigate(Screen.CREATE_TASK.route)
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
            }

        }
    }


}

@Composable
fun TaskHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
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

@Composable
fun CategoryFilter(
    modifier: Modifier = Modifier,
    value: CategoryEntity? = null,
    onSelect: (CategoryEntity?) -> Unit,
    categories: List<CategoryEntity>
) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {

        item {
            FilterChip(
                selected = value == null,
                onClick = {
                    onSelect(null)
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Green300,
                    selectedLabelColor = Color.White
                ),
                label = { Text(text = "Todos") }
            )
        }
        items(items = categories) { category ->
            FilterChip(
                selected = if (value != null) category.id == value.id else false,
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Green300,
                    selectedLabelColor = Color.White
                ),
                onClick = {
                    onSelect(category)
                },
                label = { Text(text = category.name) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyTasksPreview() {

    MyTasks()
}

