package com.example.todolist.data.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

// этот файл инициализирует само хранилище

val Context.dataStore by preferencesDataStore(name = "settings")