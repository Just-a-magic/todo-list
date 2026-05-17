package com.example.todolist.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.data.local.entity.TodoItem
import kotlinx.coroutines.flow.Flow

// это интерфейс dao, он является прослойкой между kotlin и sql
// room генерирует реализацию этих методов на основе аннотаций

@Dao
interface TodoDao {

    // выбор всех задач
    @Query("SELECT * FROM todo_items ORDER BY createdAt ASC")
    fun getAll(): Flow<List<TodoItem>>

    // поиск конкретной задачи по id
    @Query("SELECT * FROM todo_items WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): TodoItem?

    // удаление всех задач
    @Query("DELETE FROM todo_items")
    suspend fun deleteAll()

    // создание новой задачи
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: TodoItem)

    // удаление конкретной задачи
    @Delete
    suspend fun delete(item: TodoItem)

    // редактирование конкретной задачи
    @Update
    suspend fun update(item: TodoItem)
}