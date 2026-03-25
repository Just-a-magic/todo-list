package com.example.todolist.ui.components

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.data.local.TodoItem

@Composable
fun TodoItemView(
    item: TodoItem,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(16.dp)
    ) {

        Checkbox(
            checked = item.isDone,
            onCheckedChange = { onToggle() }
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = item.title)
            Text(text = item.description)
        }
    }
}