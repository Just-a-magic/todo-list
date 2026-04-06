package com.example.todolist.ui.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.domain.model.AppTheme
import com.example.todolist.ui.screens.settings.SettingsViewModel
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun AppRoot(
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val theme by settingsViewModel.theme.collectAsState(initial = AppTheme.SYSTEM)

    val isDark = when (theme) {
        AppTheme.DARK -> true
        AppTheme.LIGHT -> false
        AppTheme.SYSTEM -> isSystemInDarkTheme()
    }

    TodoListTheme(darkTheme = isDark) {
        NavGraph()
    }
}