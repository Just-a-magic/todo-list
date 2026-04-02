package com.example.todolist.data.repository

import com.example.todolist.domain.model.AppTheme
import com.example.todolist.data.datastore.SettingsDataStore
import com.example.todolist.data.local.db.TodoDao
import com.example.todolist.data.local.entity.TodoItem
import com.example.todolist.domain.model.AppLanguage
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val dao: TodoDao,
    private val settings: SettingsDataStore
) {

    val items = dao.getAll()

    val theme = settings.themeFlow

    val language = settings.languageFlow

    suspend fun setLanguage(lang: AppLanguage) {
        settings.setLanguage(lang)
    }

    suspend fun setTheme(theme: AppTheme) {
        settings.setTheme(theme)
    }

    suspend fun insert(item: TodoItem) = dao.insert(item)
    suspend fun delete(item: TodoItem) = dao.delete(item)
    suspend fun update(item: TodoItem) = dao.update(item)
    suspend fun getById(id: Int) = dao.getById(id)
    suspend fun deleteAll() = dao.deleteAll()
}