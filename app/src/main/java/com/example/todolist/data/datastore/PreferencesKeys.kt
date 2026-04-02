package com.example.todolist.data.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val THEME = stringPreferencesKey("app_theme")

    val LANGUAGE = stringPreferencesKey("app_language")
}