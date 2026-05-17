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

// этот файл отвечает за сохранение темы и языка в datastore

@Singleton
class SettingsDataStore @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    // flow текущей темы приложения
    val themeFlow: Flow<AppTheme> = context.dataStore.data.map { prefs ->
        when (prefs[PreferencesKeys.THEME]) {
            "LIGHT" -> AppTheme.LIGHT
            "DARK" -> AppTheme.DARK
            else -> AppTheme.SYSTEM     // системная тема по умолчанию
        }
    }
    // flow языка приложения
    val languageFlow: Flow<AppLanguage> = context.dataStore.data
        .map { prefs ->
            when (prefs[PreferencesKeys.LANGUAGE]) {
                "ENGLISH" -> AppLanguage.ENGLISH
                "RUSSIAN" -> AppLanguage.RUSSIAN
                "SYSTEM" -> AppLanguage.SYSTEM
                else -> AppLanguage.SYSTEM      // системный язык по умолчанию
            }
        }

    // сохранение выбранной темы
    suspend fun setTheme(theme: AppTheme) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.THEME] = theme.name
        }
    }
    // сохранение выбранного языка
    suspend fun setLanguage(lang: AppLanguage) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKeys.LANGUAGE] = lang.name
        }
    }
}