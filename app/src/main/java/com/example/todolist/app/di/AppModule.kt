package com.example.todolist.app.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.datastore.SettingsDataStore
import com.example.todolist.data.local.db.AppDatabase
import com.example.todolist.data.local.db.TodoDao
import com.example.todolist.data.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// этот файл является центром внедрения зависимостей, здесь описывается для hilt,
// как именно нужно создавать объекты, которые не имеют простого конструктора

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides       // db
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todo_db"       // название файла db в памяти устройства
        ).build()
    }

    @Provides       // dao
    fun provideDao(db: AppDatabase): TodoDao {
        return db.todoDao()
    }

    @Provides       // repository
    @Singleton
    fun provideRepository(
        dao: TodoDao,
        settings: SettingsDataStore
    ): TodoRepository {
        return TodoRepository(dao, settings)
    }
}