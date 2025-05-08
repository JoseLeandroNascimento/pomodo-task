package com.example.pomodo_task.features.category.ui.screen.managerCategories

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pomodo_task.features.category.model.CategoryEntity
import com.example.pomodo_task.features.category.ui.components.CategoryData
import com.example.pomodo_task.features.category.ui.components.ModalCategory
import com.example.pomodo_task.ui.theme.Gray400
import com.example.pomodo_task.ui.theme.Green300

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Category(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    categoriesViewModel: ManagerCategoriesViewModel = hiltViewModel()
) {

    val categoryUIState by categoriesViewModel.state.collectAsState()

    if (categoryUIState.showModal) {
        ModalCategory(
            onDismissRequest = {
                categoriesViewModel.showModal(false)
            },
            onSubmit = {

                categoriesViewModel.onIdChanged(null)
                categoriesViewModel.onNameChanged(it.name)
                categoriesViewModel.onColorChanged(it.color)
                categoriesViewModel.onActiveChanged(it.active)

                categoriesViewModel.save()
            },
            value = if (categoryUIState.id != null) CategoryData(
                id = categoryUIState.id!!,
                name = categoryUIState.name,
                active = categoryUIState.active,
                color = categoryUIState.color,
            ) else null
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Gerenciar categorias",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (isSystemInDarkTheme()) Color.White else Gray400
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            categoriesViewModel.showModal(!categoryUIState.showModal)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Green300
                        )
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Text(
                            text = "Criar nova",
                            fontSize = 18.sp
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(top = 4.dp)
                .fillMaxSize()
        ) {


            if (categoryUIState.categoriesAll.isNotEmpty() && !categoryUIState.isLoading) {
                CategoriesList(
                    onChangeVisibility = {
                        categoriesViewModel.changeVisibility(it.id, it.active)
                    },
                    onEditSelect = {
                        categoriesViewModel.showModal(true)
                    },
                    onDelete = {
                        categoriesViewModel.delete(it)
                    },
                    categories = categoryUIState.categoriesAll
                )
            }
        }
    }

}

@Composable
fun CategoriesList(
    modifier: Modifier = Modifier,
    onEditSelect: (CategoryEntity) -> Unit,
    onChangeVisibility: (CategoryEntity) -> Unit,
    onDelete: (Int) -> Unit,
    categories: List<CategoryEntity>
) {

    LazyColumn(
        modifier = modifier
            .padding(
                horizontal = 8.dp
            )
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {


        items(items = categories, key = { it.id }) { category ->
            CategoriesItem(
                item = category,
                onEditSelect = { value ->
                    onEditSelect(
                        category.copy(
                            color = value.color,
                            active = value.active,
                            id = value.id,
                            name = value.name
                        )
                    )
                },
                onDelete = {
                    onDelete(it)
                },
                onChangeVisibility = { show ->
                    onChangeVisibility(category.copy(active = show))
                }
            )
        }
    }
}

@Composable
fun CategoriesItem(
    modifier: Modifier = Modifier,
    item: CategoryEntity,
    onDelete: (Int) -> Unit,
    onEditSelect: (CategoryData) -> Unit,
    onChangeVisibility: (Boolean) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White,
        ),
        border = CardDefaults.outlinedCardBorder(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(color = Color(item.color), shape = CircleShape)
                )
                Text(
                    text = item.name,
                    style = TextStyle(
                        lineBreak = LineBreak.Simple
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (!item.active) {
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = "visibility"
                    )
                }
                Box(modifier.wrapContentSize(Alignment.TopStart)) {

                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            expanded = !expanded
                        }
                    ) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                    }

                    DropdownMenu(
                        modifier = Modifier
                            .background(color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White),
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        },
                    ) {
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null
                                )
                            },
                            text = {
                                Text(text = "Editar")
                            },
                            onClick = {
                                val valueEdit = CategoryData(
                                    id = item.id,
                                    name = item.name,
                                    active = item.active,
                                    color = item.color,
                                )
                                onEditSelect(valueEdit)
                                expanded = false

                            }
                        )
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    imageVector = if (item.active) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = null
                                )
                            },
                            text = {
                                Text(text = if (item.active) "Ocultar" else "Exposição")
                            },
                            onClick = {
                                expanded = false
                                onChangeVisibility(!item.active)

                            }
                        )
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null
                                )
                            },
                            text = {
                                Text(text = "Deletar")
                            },
                            onClick = {
                                onDelete(item.id)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CategoryPreview() {
    Category(onBack = {})
}