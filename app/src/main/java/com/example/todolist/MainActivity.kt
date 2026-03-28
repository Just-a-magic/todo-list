package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.todolist.data.local.AppDatabase
import com.example.todolist.data.repository.TodoRepository
import com.example.todolist.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(this)

        enableEdgeToEdge()
        setContent {
            val repository = remember { TodoRepository(db.todoDao()) }

            val theme by repository.theme.collectAsState()

            val isDark = when (theme) {
                AppTheme.DARK -> true
                AppTheme.LIGHT -> false
                AppTheme.SYSTEM -> isSystemInDarkTheme()
            }

            MaterialTheme(
                colorScheme = if (isDark) darkColorScheme() else lightColorScheme()
            ) {
            NavGraph(repository = repository)
            }
        }
    }
}