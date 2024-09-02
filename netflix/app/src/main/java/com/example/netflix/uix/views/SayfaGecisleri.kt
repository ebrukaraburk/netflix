package com.example.netflix.uix.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.netflix.data.entity.Netflix
import com.google.gson.Gson

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf("Home", "Search", "Favorites")
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(item) },
                selected = currentRoute == item.toLowerCase(),
                onClick = {
                    when (item) {
                        "Home" -> navController.navigate("anasayfa") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        "Search" -> navController.navigate("searchPage") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        "Favorites" -> navController.navigate("favoritesPage") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    when (item) {
                        "Home" -> Icon(Icons.Default.Home, contentDescription = null)
                        "Search" -> Icon(Icons.Default.Search, contentDescription = null)
                        "Favorites" -> Icon(Icons.Default.Favorite, contentDescription = null)
                    }
                }
            )
        }
    }
}

@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "anasayfa",
            modifier = Modifier.
            padding(innerPadding)
        ) {
            composable("anasayfa") {
                Anasayfa(navController)
            }
            composable(
                "detaySayfa/{film}",
                arguments = listOf(
                    navArgument("film") {
                        type = NavType.StringType
                    }
                )
            ) {
                val json = it.arguments?.getString("film")
                val nesne = Gson().fromJson(json, Netflix::class.java)
                DetaySayfa(nesne)
            }

            composable("searchPage") {

            }
            composable("favoritesPage") {

            }
        }
    }
}
