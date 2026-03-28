package com.example.todolist.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.AppTheme
import com.example.todolist.ui.components.ThemeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    var showThemeSheet by remember { mutableStateOf(false) }
    val currentTheme by viewModel.theme.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },

                // back icon
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            // theme button
            Button(
                onClick = { showThemeSheet = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Theme: $currentTheme")
            }

            // delete all tasks button
            Button(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Delete all tasks")
            }
        }

        // theme bottom sheet
        if (showThemeSheet) {
            ModalBottomSheet(
                onDismissRequest = { showThemeSheet = false }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    ThemeItem("Light") {
                        viewModel.setTheme(AppTheme.LIGHT)
                        showThemeSheet = false
                    }

                    ThemeItem("Dark") {
                        viewModel.setTheme(AppTheme.DARK)
                        showThemeSheet = false
                    }

                    ThemeItem("System") {
                        viewModel.setTheme(AppTheme.LIGHT)
                        showThemeSheet = false
                    }
                }
            }
        }

        // delete all tasks dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Confirm") },
                text = { Text(text = "Delete all tasks?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.deleteAll()
                            showDialog = false
                        }
                    ) {
                        Text(text = "Delete")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            )
        }
    }
}