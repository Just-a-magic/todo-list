package com.example.todolist.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todolist.data.local.TodoItem

@OptIn(ExperimentalFoundationApi::class)
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
            val textDecoration = if (item.isDone) TextDecoration.LineThrough else TextDecoration.None
            val textColor = if (item.isDone) Color.Gray else Color.Unspecified

            Text(
                text = item.title,
                style = TextStyle(textDecoration = textDecoration),
                color = textColor
            )
            Text(
                text = item.description,
                style = TextStyle(textDecoration = textDecoration),
                color = textColor
            )
        }
    }
}