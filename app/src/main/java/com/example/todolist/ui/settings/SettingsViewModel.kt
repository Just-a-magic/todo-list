package com.example.todolist.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.AppTheme
import com.example.todolist.data.repository.TodoRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: TodoRepository) : ViewModel() {

    val theme = repository.theme

    fun setTheme(theme: AppTheme) {
        repository.setTheme(theme)
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}