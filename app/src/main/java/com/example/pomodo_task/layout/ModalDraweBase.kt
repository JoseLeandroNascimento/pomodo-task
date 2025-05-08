package com.example.pomodo_task.layout

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pomodo_task.optionsMenu

@Composable
fun ModalDrawerBase(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigationDrawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    content: @Composable () -> Unit
) {


    ModalNavigationDrawer(
        modifier = modifier,
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

        content()
    }

}