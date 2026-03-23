package com.example.todolist.ui.newitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.TodoItem
import com.example.todolist.data.repository.TodoRepository
import kotlinx.coroutines.launch

class NewItemViewModel(private val repository: TodoRepository) : ViewModel() {

    fun add(title: String, description: String) {
        if (title.isBlank()) return

        viewModelScope.launch {
            repository.insert(
                TodoItem(
                    title = title,
                    description = description
                )
            )
        }
    }
}