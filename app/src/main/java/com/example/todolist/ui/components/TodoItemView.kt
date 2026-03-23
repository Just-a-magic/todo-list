package com.example.todolist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.data.local.TodoItem

@Composable
fun TodoItemView(
    item: TodoItem,
    onDelete: () -> Unit,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() }
            .padding(16.dp)
    ) {
        Checkbox(
            checked = item.isDone,
            onCheckedChange = { onToggle() }
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(item.title)
            Text(item.description)
        }

        IconButton(onClick = onDelete) {
            Text("X")
        }
    }
}