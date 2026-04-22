package com.example.todolist.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.data.local.entity.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_items ORDER BY createdAt DESC")
    fun getAll(): Flow<List<TodoItem>>

    @Query("SELECT * FROM todo_items WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): TodoItem?

    @Query("DELETE FROM todo_items")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: TodoItem)

    @Delete
    suspend fun delete(item: TodoItem)

    @Update
    suspend fun update(item: TodoItem)
}