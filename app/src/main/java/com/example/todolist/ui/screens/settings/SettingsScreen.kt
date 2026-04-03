package com.example.todolist.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.domain.model.AppLanguage
import com.example.todolist.domain.model.AppTheme
import com.example.todolist.domain.model.toDisplayName
import com.example.todolist.ui.components.LanguageBottomSheet
import com.example.todolist.ui.components.ThemeItem
import com.example.todolist.ui.utils.applyLanguage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var showThemeSheet by remember { mutableStateOf(false) }
    var showLangSheet by remember { mutableStateOf(false) }
    val currentTheme by viewModel.theme.collectAsState(initial = AppTheme.SYSTEM)

    val language by viewModel.language.collectAsState(initial = AppLanguage.ENGLISH)

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

            // choose app language button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showLangSheet = true }
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(stringResource(R.string.language))
                    Text(text = stringResource(language.toDisplayName()))
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Choose app language"
                )
            }

            // choose app theme button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showThemeSheet = true }
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "App theme")
                    Text(text = currentTheme.toDisplayName())
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Choose app theme"
                )
            }

            // delete all tasks button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showDialog = true }
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = stringResource(R.string.delete_all_tasks))
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Delete all tasks"
                )
            }
        }

        // language bottom sheet
        if (showLangSheet) {
            LanguageBottomSheet(
                current = language,
                onSelect = {
                    viewModel.setLanguage(it)
                    applyLanguage(it)
                    showLangSheet = false
                },
                onDismiss = { showLangSheet = false }
            )
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
                        viewModel.setTheme(AppTheme.SYSTEM)
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