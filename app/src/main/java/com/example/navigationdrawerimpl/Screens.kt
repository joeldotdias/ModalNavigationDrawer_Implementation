package com.example.navigationdrawerimpl

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val title: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int? = null
) {
    object Inbox: Screens(
        title = "Inbox",
        route = "inbox",
        icon = Icons.Default.Email,
        badgeCount = 31
    )

    object Sent: Screens(
        title = "Sent",
        route = "sent",
        icon = Icons.Default.Send
    )

    object Starred: Screens(
        title = "Starred Messages",
        route = "starred",
        icon = Icons.Default.Star,
        badgeCount = 15
    )

    object Spam: Screens(
        title = "Spam",
        route = "spam",
        icon = Icons.Default.Warning
    )

    object Bin: Screens(
        title = "Deleted Messages",
        route = "bin",
        icon = Icons.Default.Delete
    )
}