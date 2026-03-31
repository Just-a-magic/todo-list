package com.example.todolist.ui.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist.domain.model.AppTheme
import com.example.todolist.ui.screens.settings.SettingsViewModel

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

    MaterialTheme(
        colorScheme = if (isDark) darkColorScheme() else lightColorScheme()
    ) {
        NavGraph()
    }
}