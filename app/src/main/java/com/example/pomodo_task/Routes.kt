package com.example.pomodo_task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pomodo_task.features.category.ui.screen.managerCategories.Category
import com.example.pomodo_task.features.task.ui.components.CreateTask
import com.example.pomodo_task.features.task.ui.components.MyTasks
import com.example.pomodo_task.layout.MenuModalDrawerItem
import com.example.pomodo_task.layout.ModalDrawerBase
import kotlinx.coroutines.launch

val optionsMenu: List<MenuModalDrawerItem> = listOf(
    MenuModalDrawerItem(
        label = "Minhas tarefas",
        icon = Icons.Default.Checklist,
        route = Screen.MY_TASKS
    ),
    MenuModalDrawerItem(
        label = "Categorias",
        icon = Icons.Default.Category,
        route = Screen.CATEGORIES
    ),
    MenuModalDrawerItem(
        label = "Performace",
        icon = Icons.Default.AccessTime,
        route = Screen.PERFORMACE
    )
)

@Composable
fun Router(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.MY_TASKS.route
    ) {
        composable(Screen.MY_TASKS.route) {

            ModalDrawerBase(
                navigationDrawerState = drawerState,
                navController = navController
            ) {
                MyTasks(
                    navController = navController,
                    onOpenDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }

        }
        composable(Screen.CREATE_TASK.route) {

            CreateTask(onBack = {
                navController.popBackStack()
            })
        }
        composable(Screen.CATEGORIES.route) {
            Category(onBack = { navController.popBackStack() })
        }
    }

}