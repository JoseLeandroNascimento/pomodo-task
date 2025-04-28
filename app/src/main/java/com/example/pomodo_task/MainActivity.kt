package com.example.pomodo_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.pomodo_task.layout.MenuModalDrawer
import com.example.pomodo_task.layout.MenuModalDrawerItem
import com.example.pomodo_task.layout.ModalDrawerHeader
import com.example.pomodo_task.ui.theme.PomodotaskTheme
import kotlinx.coroutines.launch

val optionsMenu: List<MenuModalDrawerItem> = listOf(
    MenuModalDrawerItem(
        label = "Minhas tarefas",
        route = Screen.MY_TASKS
    ),
    MenuModalDrawerItem(
        label = "Performace",
        route = Screen.PERFORMACE
    )
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navigationDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()

            PomodotaskTheme {

                ModalNavigationDrawer(
                    drawerState = navigationDrawerState,
                    drawerContent = {

                        ModalDrawerSheet(
                            modifier = Modifier.width(280.dp)
                        ) {

                            ModalDrawerHeader(modifier = Modifier)

                            Spacer(modifier = Modifier.height(8.dp))

                            MenuModalDrawer(
                                options = optionsMenu,
                                navController = navController,
                                navigationDrawerState = navigationDrawerState,
                                navigate = {
                                    navController.navigate(it.route)
                                }
                            )
                        }
                    }
                ) {
                    Scaffold(
                        containerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White,
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else Color.White
                                ),
                                title = { Text(text = "") },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            navigationDrawerState.open()
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = null
                                        )
                                    }
                                }
                            )
                        }
                    ) { innerPadding ->
                        Router(modifier = Modifier.padding(innerPadding), navController)
                    }
                }
            }
        }
    }
}



