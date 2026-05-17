package com.example.todolist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todolist.data.datastore.SettingsDataStore
import com.example.todolist.ui.navigation.AppRoot
import com.example.todolist.ui.utils.applyLanguage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

// входная точка в приложение

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var settingsDataStore: SettingsDataStore // настройки приложения, через hilt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val currentLang = settingsDataStore.languageFlow.first() // запрашивает из настроек выбранный язык
            applyLanguage(currentLang)      // применяет выбранный язык
        }
        enableEdgeToEdge()
        setContent {        // устанавливает jetpack compose в качестве интерфейса
            AppRoot()
        }
    }
}