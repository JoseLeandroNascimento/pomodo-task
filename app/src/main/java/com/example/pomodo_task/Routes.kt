package com.example.pomodo_task

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pomodo_task.screen.Category
import com.example.pomodo_task.screen.CreateTask
import com.example.pomodo_task.screen.MyTasks

@Composable
fun Router(modifier: Modifier = Modifier, navController: NavHostController) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.MY_TASKS.route
    ) {
        composable(Screen.MY_TASKS.route) {
            MyTasks(navController = navController)
        }
        composable(Screen.CREATE_TASK.route) {
            CreateTask()
        }
        composable(Screen.CATEGORIES.route) {
            Category()
        }
    }

}