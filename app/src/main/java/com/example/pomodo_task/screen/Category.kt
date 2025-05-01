package com.example.pomodo_task.screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pomodo_task.ui.theme.Gray400
import com.example.pomodo_task.ui.theme.Green300
import com.example.pomodo_task.viewModels.CreateCategoryModalViewModel

@Composable
fun Category(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(top = 4.dp)
            .fillMaxSize()
    ) {

        CategoriesHeader()

        LazyColumn(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp
                )
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {

            items(count = 10) {
                CategoriesItem(item = it)
            }
        }
    }
}

@Composable
fun CategoriesHeader(
    modifier: Modifier = Modifier,
    createCategoryModalViewModel: CreateCategoryModalViewModel = viewModel()
) {

    val showModal by createCategoryModalViewModel.showModal.collectAsState()

    CreateCatory(
        show = showModal,
        onDismissRequest = {
            createCategoryModalViewModel.changeVisibility()
        }
    )

    Row(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(

            text = "Gerenciar categorias",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = if (isSystemInDarkTheme()) Color.White else Gray400
        )

        TextButton(
            onClick = {
                createCategoryModalViewModel.changeVisibility()
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
}

@Composable
fun CategoriesItem(modifier: Modifier = Modifier, item: Int) {

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
            Text(text = "Categoria $item")

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
                        onClick = {}
                    )
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        },
                        text = {
                            Text(text = "Ocultar")
                        },
                        onClick = {}
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
                        onClick = {}
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CategoryPreview() {
    Category()
}