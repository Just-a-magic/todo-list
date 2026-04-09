package com.example.todolist.ui.screens.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.data.local.entity.TodoItem
import com.example.todolist.ui.components.TodoItemBottomSheet
import com.example.todolist.ui.components.TodoItemView
import com.example.todolist.ui.theme.Shapes
import com.example.todolist.ui.theme.Typography

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
            CenterAlignedTopAppBar(
                title = { Text(
                    stringResource(R.string.todo),
                    style = Typography.titleLarge
                ) },
                actions = {

                    // settings icon
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        },
        // add new task button
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                modifier = Modifier
                    .size(90.dp)
                    .padding(12.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                shape = Shapes.medium
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add new task",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            // items
            LazyColumn {
                items(items) { item ->
                    TodoItemView(
                        item = item,
                        onClick = { onEditClick(item.id) },
                        onLongClick = { selectedItem = item },
                        onToggle = { viewModel.toggle(item) }
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }

            // item bottom sheet
            selectedItem?.let { item ->
                TodoItemBottomSheet(
                    onDismissRequest = { selectedItem = null },
                    onEditClick = { onEditClick(item.id) },
                    onDeleteClick = { viewModel.delete(item) }
                )
            }
        }
    }
}