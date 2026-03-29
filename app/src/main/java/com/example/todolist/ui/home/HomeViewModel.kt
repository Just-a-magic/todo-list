package com.example.todolist.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.TodoItem
import com.example.todolist.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val items = repository.items

    fun delete(item: TodoItem) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    fun toggle(item: TodoItem) {
        viewModelScope.launch {
            repository.update(item.copy(isDone = !item.isDone))
        }
    }
}