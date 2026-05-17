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
import java.time.LocalDate
import javax.inject.Inject

// эта ViewModel отвечает за редактирование задачи

@HiltViewModel
class EditItemViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    var item by mutableStateOf<TodoItem?>(null)
        private set

    // загрузка данных задачи из db при открытии экрана
    fun load(id: Int) {
        viewModelScope.launch {
            item = repository.getById(id)
        }
    }

    // сохранение отредактированных данных
    fun update(title: String, description: String, selectedDate: LocalDate?) {
        val current = item ?: return

        viewModelScope.launch {
            repository.update(
                current.copy(       // обновляет только название и описание задачи
                    title = title,
                    description = description,
                    selectedDate = selectedDate
                )
            )
        }
    }
}