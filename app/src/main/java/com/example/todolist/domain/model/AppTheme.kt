package com.example.todolist.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.todolist.R

// этот файл описывает перечисление всех тем приложения

enum class AppTheme {
    LIGHT,
    DARK,
    SYSTEM
}

// позволяет получить ссылку на строковый ресурс из объекта AppTheme
@Composable
fun AppTheme.toDisplayName(): String {
    return when (this) {
        AppTheme.LIGHT -> stringResource(R.string.theme_light)
        AppTheme.DARK -> stringResource(R.string.theme_dark)
        AppTheme.SYSTEM -> stringResource(R.string.theme_system)
    }
}