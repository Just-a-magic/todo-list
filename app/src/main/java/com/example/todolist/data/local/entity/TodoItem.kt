package com.example.todolist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

// эта модель данных определяет структуру таблицы в SQLite

@Entity(tableName = "todo_items")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,                    // id
    val title: String,                  // название
    val description: String = "",       // описание
    val isDone: Boolean = false,        // статус выполнения
    val createdAt: Long = System.currentTimeMillis(),       // время создания в ms
    val selectedDate: LocalDate? = null     // выбранная дата
)