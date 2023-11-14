package com.example.navigationdrawerimpl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: Screens.Inbox
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val screens = listOf(
        Screens.Inbox,
        Screens.Sent,
        Screens.Starred,
        Screens.Spam,
        Screens.Bin
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                screens.forEach { screen ->
                    NavigationDrawerItem(
                        label = { Text(text = screen.title) },
                        icon = {
                               Icon(imageVector = screen.icon, contentDescription = "${screen.title} icon")
                        },
                        selected = currentRoute == screen.route,
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        onClick = {
                            if(screen.route == Screens.Inbox.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(screen.route)
                                }
                            }
                            else {
                                navController.navigate(screen.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = currentRoute.toString().replaceFirstChar { it.uppercase() }) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu icon")
                        }
                    }
                )
            }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                DrawerNavigation(navController)
            }
        }
    }
}