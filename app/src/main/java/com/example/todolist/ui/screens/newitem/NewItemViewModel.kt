package com.example.todolist.ui.screens.newitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.entity.TodoItem
import com.example.todolist.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// эта ViewModel отвечает за создание задач

@HiltViewModel
class NewItemViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    // добавление новой задачи
    fun add(title: String, description: String) {
        if (title.isBlank()) return

        viewModelScope.launch {
            repository.insert(      // создает объект TodoItem и отправляет его в репозиторий
                TodoItem(
                    title = title,
                    description = description
                )
            )
        }
    }
}