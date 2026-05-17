package com.example.todolist.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.model.AppTheme
import com.example.todolist.data.repository.TodoRepository
import com.example.todolist.domain.model.AppLanguage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// эта ViewModel управляет состоянием приложения

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    // flow темы из репозитория
    val theme = repository.theme

    // flow языка из репозитория
    val language = repository.language

    // изменение языка приложения
    fun setLanguage(lang: AppLanguage) {
        viewModelScope.launch {
            repository.setLanguage(lang)
        }
    }

    // изменение темы приложения
    fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            repository.setTheme(theme)
        }
    }

    // удаление всех задач
    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}