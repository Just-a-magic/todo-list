package com.example.todolist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.domain.model.AppTheme
import com.example.todolist.domain.model.toDisplayName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeBottomSheet(
    current: AppTheme,
    onSelect: (AppTheme) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column {
            AppTheme.entries.forEach { theme ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(theme) }
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(theme.toDisplayName())

                    if (theme == current) {
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