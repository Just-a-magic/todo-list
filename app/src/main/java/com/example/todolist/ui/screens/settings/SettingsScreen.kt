package com.example.todolist.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.todolist.ui.components.ThemeBottomSheet
import com.example.todolist.ui.theme.Shapes
import com.example.todolist.ui.theme.Typography
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
    val currentLanguage by viewModel.language.collectAsState(initial = AppLanguage.ENGLISH)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = stringResource(R.string.settings),
                    style = Typography.titleLarge
                ) },

                // back icon
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
            Card(
                shape = Shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showLangSheet = true }
                        .padding(horizontal = 18.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = "Language"
                        )
                        Text(
                            stringResource(R.string.language),
                            style = Typography.labelLarge,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(currentLanguage.toDisplayName()),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = Typography.labelLarge,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Choose app language",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // choose app theme button
            Card(
                shape = Shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showThemeSheet = true }
                        .padding(horizontal = 18.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Contrast,
                            contentDescription = "Theme"
                        )
                        Text(
                            text = stringResource(R.string.theme),
                            style = Typography.labelLarge,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = currentTheme.toDisplayName(),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = Typography.labelLarge,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Choose app theme",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // delete all tasks button
            Card(
                shape = Shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDialog = true }
                        .padding(horizontal = 18.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete"
                        )
                        Text(
                            text = stringResource(R.string.delete_all_tasks),
                            style = Typography.labelLarge,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Delete all tasks",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        // language bottom sheet
        if (showLangSheet) {
            LanguageBottomSheet(
                onDismissRequest = { showLangSheet = false },
                onSelectClick = {
                    viewModel.setLanguage(it)
                    applyLanguage(it)
                },
                currentLanguage = currentLanguage
            )
        }

        // theme bottom sheet
        if (showThemeSheet) {
            ThemeBottomSheet(
                onDismissRequest = { showThemeSheet = false },
                onSelectClick = { viewModel.setTheme(it) },
                currentTheme = currentTheme
            )
        }
        // delete all tasks dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                shape = Shapes.large,
                title = { Text(
                    text = stringResource(R.string.confirm),
                    style = Typography.titleLarge
                ) },

                text = { Text(
                    text = stringResource(R.string.del_all),
                    style = Typography.labelLarge
                ) },
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 0.dp,

                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                            viewModel.deleteAll()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.del),
                            style = Typography.labelLarge
                        )
                    }
                },

                dismissButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = Typography.labelLarge
                        )
                    }
                }
            )
        }
    }
}