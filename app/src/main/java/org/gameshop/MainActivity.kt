package org.gameshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import org.gameshop.common.MainRoute
import org.gameshop.presentation.screens.dashboard.DashboardScreen
import org.gameshop.presentation.screens.home.HomeScreen
import org.gameshop.presentation.screens.login.LoginScreen
import org.gameshop.presentation.ui.theme.GameShopAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseMessaging.getInstance().subscribeToTopic("gs-service")

        setContent {
            GameShopAppTheme {
                MainNavigation()
            }
        }
    }
}

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
) {
    val mainNavController = rememberNavController()

        NavHost(
            navController = mainNavController,
            startDestination = MainRoute.LOGIN.name,
        ) {

            composable(route = MainRoute.LOGIN.name) {
                LoginScreen() {
                    mainNavController.navigate(route = MainRoute.Home.name) {
                        popUpTo(MainRoute.LOGIN.name) {
                            inclusive = true
                        }
                    }
                }
            }
            composable(route = MainRoute.Home.name) {
                HomeScreen(){
                    mainNavController.navigate(route = MainRoute.LOGIN.name) {
                        popUpTo(mainNavController.graph.startDestinationId){
                            inclusive=true
                        }
                    }
                }
            }
        }

    }



