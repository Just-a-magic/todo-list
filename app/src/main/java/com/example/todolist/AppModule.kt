package com.example.todolist

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.local.AppDatabase
import com.example.todolist.data.local.TodoDao
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
    fun provideRepository(dao: TodoDao): TodoRepository {
        return TodoRepository(dao)
    }
}
