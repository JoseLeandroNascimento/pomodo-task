package com.example.pomodo_task.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pomodo_task.Screen
import com.example.pomodo_task.optionsMenu
import com.example.pomodo_task.ui.theme.Green300
import kotlinx.coroutines.launch

data class MenuModalDrawerItem(
    val label: String,
    val icon: ImageVector,
    val route: Screen
)

@Composable
fun MenuModalDrawer(
    modifier: Modifier = Modifier,
    navigate: ((Screen) -> Unit)?,
    options: List<MenuModalDrawerItem> = listOf(),
    navController: NavHostController,
    navigationDrawerState: DrawerState,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(count = options.size) { routeIndex ->
            NavigationDrawerItem(
                label = {
                    Text(
                        text = options[routeIndex].label,
                        fontWeight = FontWeight.Medium
                    )
                },
                icon = { Icon(imageVector = options[routeIndex].icon, contentDescription = null) },
                selected = options[routeIndex].route.route == currentRoute,
                onClick = {
                    scope.launch {
                        navigate?.let {
                            navigate.invoke(options[routeIndex].route)
                        }
                        navigationDrawerState.close()
                    }
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedIconColor = Green300,
                    selectedTextColor = Green300,
                    selectedContainerColor = Green300.copy(alpha = .1f)
                ),
                shape = RoundedCornerShape(4.dp)
            )
        }

    }
}

@Preview
@Composable
private fun MenuModalDrawerPreview() {

    MenuModalDrawer(
        navController = rememberNavController(),
        options = optionsMenu,
        navigate = {},
        navigationDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
}