package com.example.todolist.data.repository

import com.example.todolist.AppTheme
import com.example.todolist.data.local.TodoDao
import com.example.todolist.data.local.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodoRepository(private val dao: TodoDao) {

    val items = dao.getAll()

    private val _theme = MutableStateFlow(AppTheme.SYSTEM)
    val theme: StateFlow<AppTheme> = _theme

    fun setTheme(theme: AppTheme) {
        _theme.value = theme
    }

    suspend fun insert(item: TodoItem) = dao.insert(item)
    suspend fun delete(item: TodoItem) = dao.delete(item)
    suspend fun update(item: TodoItem) = dao.update(item)
    suspend fun getById(id: Int) = dao.getById(id)
    suspend fun deleteAll() = dao.deleteAll()
}