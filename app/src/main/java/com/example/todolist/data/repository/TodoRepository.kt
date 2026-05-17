package com.example.todolist.data.repository

import com.example.todolist.domain.model.AppTheme
import com.example.todolist.data.datastore.SettingsDataStore
import com.example.todolist.data.local.db.TodoDao
import com.example.todolist.data.local.entity.TodoItem
import com.example.todolist.domain.model.AppLanguage
import javax.inject.Inject

// этот класс является single source of truth, он объединяет room и datastore,
// предоставляет удобный интерфейс для viewmodel

class TodoRepository @Inject constructor(
    private val dao: TodoDao,
    private val settings: SettingsDataStore
) {

    // flow всех задач из room
    val items = dao.getAll()

    // flow темы из datastore
    val theme = settings.themeFlow

    // flow языка из datastore
    val language = settings.languageFlow

    // сохраняет выбранный язык в datastore
    suspend fun setLanguage(lang: AppLanguage) {
        settings.setLanguage(lang)
    }

    // сохраняет выбранную тему в datastore
    suspend fun setTheme(theme: AppTheme) {
        settings.setTheme(theme)
    }

    suspend fun insert(item: TodoItem) = dao.insert(item)       // создание новой задачи
    suspend fun delete(item: TodoItem) = dao.delete(item)       // удаление конкретной задачи
    suspend fun update(item: TodoItem) = dao.update(item)       // редактирование конкретной задачи
    suspend fun getById(id: Int) = dao.getById(id)              // поиск конкретной задачи по id
    suspend fun deleteAll() = dao.deleteAll()                   // удаление всех задач
}