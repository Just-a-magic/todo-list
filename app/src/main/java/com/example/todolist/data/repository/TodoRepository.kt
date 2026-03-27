package com.example.todolist.data.repository

import com.example.todolist.data.local.TodoDao
import com.example.todolist.data.local.TodoItem

class TodoRepository(private val dao: TodoDao) {

    val items = dao.getAll()

    suspend fun insert(item: TodoItem) = dao.insert(item)

    suspend fun delete(item: TodoItem) = dao.delete(item)

    suspend fun update(item: TodoItem) = dao.update(item)

    suspend fun getById(id: Int) = dao.getById(id)

    suspend fun deleteAll() = dao.deleteAll()
}