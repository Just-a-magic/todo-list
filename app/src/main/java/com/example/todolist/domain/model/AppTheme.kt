package com.example.todolist.domain.model

import androidx.compose.runtime.Composable

enum class AppTheme {
    LIGHT,
    DARK,
    SYSTEM
}

@Composable
fun AppTheme.toDisplayName(): String {
    return when (this) {
        AppTheme.LIGHT -> "Light"
        AppTheme.DARK -> "Dark"
        AppTheme.SYSTEM -> "System"
    }
}