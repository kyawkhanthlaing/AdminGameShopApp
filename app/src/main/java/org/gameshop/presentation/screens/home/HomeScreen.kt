package org.gameshop.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.gameshop.DrawerBody
import org.gameshop.DrawerHeader
import org.gameshop.common.HomeRoute
import org.gameshop.presentation.components.AppBar
import org.gameshop.presentation.screens.OrderManagementScreen
import org.gameshop.presentation.screens.dashboard.DashboardScreen


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit
) {

    val homeNavController = rememberNavController()
    val backStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentScreen = HomeRoute.valueOf(
        backStackEntry?.destination?.route ?: HomeRoute.DASHBOARD.name
    )

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                title = currentScreen.name,
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "dashboard",
                        title = "Dashboard",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "orderManagement",
                        title = "Order Management",
                        contentDescription = "Go to settings screen",
                        icon = Icons.Default.Settings
                    ),
                    MenuItem(
                        id = "logout",
                        title = "Logout",
                        contentDescription = "Logout",
                        icon = Icons.Default.KeyboardArrowRight
                    ),
                ),
                onDashboardClick = {
                    homeNavController.navigate(HomeRoute.DASHBOARD.name) {
                        popUpTo(homeNavController.graph.startDestinationId){
                            inclusive=true
                        }
                        launchSingleTop = true
                    }
                },
                onOrderClick = {
                    homeNavController.navigate(HomeRoute.ORDER_MANAGEMENT.name) {
                        popUpTo(homeNavController.graph.startDestinationId){
                            inclusive=true
                        }
                        launchSingleTop = true
                    }
                },
                onlogoutClick = {
                    homeNavController.popBackStack()
                    onLogoutClick()
                },
                drawerClose = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }

            )
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background
    ) { innerPaddingValues ->

        NavHost(
            navController = homeNavController,
            startDestination = HomeRoute.DASHBOARD.name,
            modifier = modifier.padding(innerPaddingValues)
        ) {

            composable(route = HomeRoute.DASHBOARD.name) {
                DashboardScreen()
            }
            composable(route = HomeRoute.ORDER_MANAGEMENT.name) {
                OrderManagementScreen()
            }

        }

    }

}