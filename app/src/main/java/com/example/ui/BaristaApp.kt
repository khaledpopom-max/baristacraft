package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.model.DrinkCategory
import com.example.ui.screens.*
import com.example.ui.theme.CoffeeCaramel
import com.example.ui.theme.MyApplicationTheme

sealed interface Screen {
    object Home : Screen
    data class Search(val initialQuery: String = "") : Screen
    data class Learn(val initialCategory: DrinkCategory? = null) : Screen
    data class DrinkDetail(val drinkId: String) : Screen
    object Customizer : Screen
    object AiBarista : Screen
}

@Composable
fun BaristaApp() {
    val backStack = remember { mutableStateListOf<Screen>(Screen.Home) }
    val currentScreen = backStack.lastOrNull() ?: Screen.Home

    fun navigateTo(screen: Screen) {
        backStack.add(screen)
    }

    fun navigateBack() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.size - 1)
        }
    }

    MyApplicationTheme {
        Scaffold(
            bottomBar = {
                // Bottom navigation bar for easy access between core destinations
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 4.dp
                ) {
                    NavigationBarItem(
                        selected = currentScreen is Screen.Home,
                        onClick = {
                            if (currentScreen !is Screen.Home) {
                                backStack.clear()
                                backStack.add(Screen.Home)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home"
                            )
                        },
                        label = { Text("الرئيسية / Home") },
                        modifier = Modifier.testTag("nav_home")
                    )

                    NavigationBarItem(
                        selected = currentScreen is Screen.Search,
                        onClick = {
                            if (currentScreen !is Screen.Search) {
                                navigateTo(Screen.Search())
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        },
                        label = { Text("بحث / Search") },
                        modifier = Modifier.testTag("nav_search")
                    )

                    NavigationBarItem(
                        selected = currentScreen is Screen.Learn,
                        onClick = {
                            if (currentScreen !is Screen.Learn) {
                                navigateTo(Screen.Learn())
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = "Learn"
                            )
                        },
                        label = { Text("تعلّم / Learn") },
                        modifier = Modifier.testTag("nav_learn")
                    )

                    NavigationBarItem(
                        selected = currentScreen is Screen.AiBarista,
                        onClick = {
                            if (currentScreen !is Screen.AiBarista) {
                                navigateTo(Screen.AiBarista)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.SmartToy,
                                contentDescription = "AI Barista"
                            )
                        },
                        label = { Text("باريستا ذكي / AI") },
                        modifier = Modifier.testTag("nav_ai_barista")
                    )

                    NavigationBarItem(
                        selected = currentScreen is Screen.Customizer,
                        onClick = {
                            if (currentScreen !is Screen.Customizer) {
                                navigateTo(Screen.Customizer)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Tune,
                                contentDescription = "Customizer"
                            )
                        },
                        label = { Text("مخصص / Custom") },
                        modifier = Modifier.testTag("nav_customizer")
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                // BackHandler for Android system back button/gestures
                BackHandler(enabled = backStack.size > 1) {
                    navigateBack()
                }

                when (val screen = currentScreen) {
                    is Screen.Home -> {
                        HomeScreen(
                            onNavigateToSearch = { navigateTo(Screen.Search()) },
                            onNavigateToLearn = { category -> navigateTo(Screen.Learn(category)) },
                            onNavigateToDrinkDetail = { id -> navigateTo(Screen.DrinkDetail(id)) },
                            onNavigateToCustomizer = { navigateTo(Screen.Customizer) },
                            onNavigateToAiBarista = { navigateTo(Screen.AiBarista) }
                        )
                    }

                    is Screen.Search -> {
                        SearchScreen(
                            initialQuery = screen.initialQuery,
                            onNavigateBack = { navigateBack() },
                            onNavigateToDrinkDetail = { id -> navigateTo(Screen.DrinkDetail(id)) },
                            onNavigateToCustomizer = { navigateTo(Screen.Customizer) }
                        )
                    }

                    is Screen.Learn -> {
                        LearnScreen(
                            initialCategory = screen.initialCategory,
                            onNavigateBack = { navigateBack() },
                            onNavigateToDrinkDetail = { id -> navigateTo(Screen.DrinkDetail(id)) }
                        )
                    }

                    is Screen.DrinkDetail -> {
                        DrinkDetailScreen(
                            drinkId = screen.drinkId,
                            onNavigateBack = { navigateBack() }
                        )
                    }

                    is Screen.Customizer -> {
                        CustomizerScreen(
                            onNavigateBack = { navigateBack() },
                            onNavigateToDrinkDetail = { id -> navigateTo(Screen.DrinkDetail(id)) }
                        )
                    }

                    is Screen.AiBarista -> {
                        AiBaristaScreen(
                            onNavigateBack = { navigateBack() }
                        )
                    }
                }
            }
        }
    }
}
