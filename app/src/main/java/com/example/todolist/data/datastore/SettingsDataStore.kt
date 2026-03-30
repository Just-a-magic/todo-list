package com.example.todolist.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.todolist.domain.model.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataStore @Inject constructor(
    private val context: Context
) {
    val themeFlow: Flow<AppTheme> = context.dataStore.data.map { prefs ->
        when (prefs[PreferencesKeys.THEME]) {
            "LIGHT" -> AppTheme.LIGHT
            "DARK" -> AppTheme.DARK
            else -> AppTheme.SYSTEM
        }
    }

    suspend fun setTheme(theme: AppTheme) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.THEME] = theme.name
        }
    }
}