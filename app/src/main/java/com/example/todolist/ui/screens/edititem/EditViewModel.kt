package com.example.todolist.ui.screens.edititem

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.entity.TodoItem
import com.example.todolist.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class EditItemViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    var item by mutableStateOf<TodoItem?>(null)
        private set

    fun load(id: Int) {
        viewModelScope.launch {
            item = repository.getById(id)
        }
    }

    fun update(title: String, description: String) {
        val current = item ?: return

        viewModelScope.launch {
            repository.update(
                current.copy(
                    title = title,
                    description = description
                )
            )
        }
    }
}