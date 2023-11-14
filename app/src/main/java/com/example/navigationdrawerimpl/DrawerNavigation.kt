package com.example.navigationdrawerimpl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun DrawerNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Inbox.route
    ) {
        composable(Screens.Inbox.route) {
            InboxScreen()
        }
        composable(Screens.Sent.route) {
            SentScreen()
        }
        composable(Screens.Starred.route) {
            StarredScreen()
        }
        composable(Screens.Spam.route) {
            SpamScreen()
        }
        composable(Screens.Bin.route) {
            BinScreen()
        }
    }
}