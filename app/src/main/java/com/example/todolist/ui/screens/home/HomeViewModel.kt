package com.example.todolist.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.entity.TodoItem
import com.example.todolist.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// эта ViewModel хранит состояние и обрабатывает действия пользователя

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    // flow всех задач из репозитория в ui
    val items = repository.items

    // удаление задачи
    fun delete(item: TodoItem) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    // переключение статуса задачи
    fun toggle(item: TodoItem) {
        viewModelScope.launch {
            repository.update(item.copy(isDone = !item.isDone))
        }
    }
}