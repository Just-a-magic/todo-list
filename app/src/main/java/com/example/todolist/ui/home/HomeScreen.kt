package com.example.todolist.ui.home


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.data.local.TodoItem
import com.example.todolist.ui.components.TodoItemView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit,
    onSettingsClick: () -> Unit
) {
    val items by viewModel.items.collectAsState(initial = emptyList())

    var selectedItem by remember { mutableStateOf<TodoItem?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo List") },
                actions = {

                    // settings
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }

                    // new task
                    IconButton(onClick = onAddClick) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add new task"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Box(modifier = Modifier.padding(padding)) {

            LazyColumn {
                items(items) { item ->
                    TodoItemView(
                        item = item,
                        onClick = { onEditClick(item.id) },
                        onLongClick = { selectedItem = item },
                        onToggle = { viewModel.toggle(item) }
                    )
                }
            }

            // bottom sheet
            selectedItem?.let { item ->
                ModalBottomSheet(
                    onDismissRequest = { selectedItem = null }
                ) {
                    Column {

                        Text(
                            text = "Edit",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedItem = null
                                    onEditClick(item.id)
                                }
                                .padding(28.dp)
                        )

                        Text(
                            text = "Delete",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.delete(item)
                                    selectedItem = null
                                }
                                .padding(28.dp)
                        )
                    }
                }
            }
        }
    }
}