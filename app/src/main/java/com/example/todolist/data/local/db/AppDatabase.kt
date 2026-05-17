package com.example.todolist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todolist.ui.utils.Converters
import com.example.todolist.data.local.entity.TodoItem

// этот файл связывает entities с кодом и управляет созданием самого файла базы данных

@Database(entities = [TodoItem::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)      // конвертация для даты
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}