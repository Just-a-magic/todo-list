package com.example.todolist.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.data.local.entity.TodoItem
import com.example.todolist.ui.theme.Shapes
import com.example.todolist.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoItemView(
    item: TodoItem,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onToggle: () -> Unit
) {
    val cardColor = if (item.isDone) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.surface
    }

    Card(
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(
                    onClick = onClick,
                    onLongClick = onLongClick
                )
                .padding(start = 16.dp, end = 8.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 14.dp, bottom = 12.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                val textColor = if (item.isDone) {
                    MaterialTheme.colorScheme.onSurfaceVariant
                } else {
                    MaterialTheme.colorScheme.onSurface
                }

                Text(
                    text = item.title,
                    color = textColor,
                    style = Typography.bodyLarge
                )

                if (item.description.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.description,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = Typography.bodySmall
                    )
                }
            }
            Checkbox(
                checked = item.isDone,
                onCheckedChange = { onToggle() }
            )
        }
    }
}