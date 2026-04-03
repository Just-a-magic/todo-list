package com.example.todolist.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.todolist.domain.model.AppLanguage
import com.example.todolist.domain.model.AppTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsDataStore @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    val themeFlow: Flow<AppTheme> = context.dataStore.data.map { prefs ->
        when (prefs[PreferencesKeys.THEME]) {
            "LIGHT" -> AppTheme.LIGHT
            "DARK" -> AppTheme.DARK
            else -> AppTheme.SYSTEM
        }
    }
    val languageFlow: Flow<AppLanguage> = context.dataStore.data
        .map { prefs ->
            when (prefs[PreferencesKeys.LANGUAGE]) {
                "SYSTEM" -> AppLanguage.SYSTEM
                "ENGLISH" -> AppLanguage.ENGLISH
                "RUSSIAN" -> AppLanguage.RUSSIAN
                else -> AppLanguage.SYSTEM
            }
        }

    suspend fun setTheme(theme: AppTheme) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.THEME] = theme.name
        }
    }
    suspend fun setLanguage(lang: AppLanguage) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.LANGUAGE] = lang.name
        }
    }
}