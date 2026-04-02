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

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val currentLang = settingsDataStore.languageFlow.first()
            applyLanguage(currentLang)
        }

        enableEdgeToEdge()
        setContent {
            AppRoot()
        }
    }
}