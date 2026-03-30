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

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    fun provideDao(db: AppDatabase): TodoDao {
        return db.todoDao()
    }

    @Provides
    @Singleton
    fun provideRepository(
        dao: TodoDao,
        settings: SettingsDataStore
    ): TodoRepository {
        return TodoRepository(dao, settings)
    }
}