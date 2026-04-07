package com.example.todolist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.domain.model.AppTheme
import com.example.todolist.domain.model.toDisplayName
import com.example.todolist.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeBottomSheet(
    onDismissRequest: () -> Unit,
    onSelectClick: (AppTheme) -> Unit,
    currentTheme: AppTheme
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        Column {

            // sheet title
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onDismissRequest,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = stringResource(R.string.theme),
                    style = Typography.titleLarge
                )
            }

            AppTheme.entries.forEach { theme ->

                // sheet item
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDismissRequest()
                            onSelectClick(theme)
                        }
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        theme.toDisplayName(),
                        style = Typography.labelLarge
                    )

                    if (theme == currentTheme) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected"
                        )
                    }
                }
            }
        }
    }
}