package com.example.todolist.ui.screens.newitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.ui.theme.Shapes
import com.example.todolist.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewItemScreen(
    viewModel: NewItemViewModel,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var isSaving by remember { mutableStateOf(false) }

    val titleIsValid = title.isNotBlank()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    stringResource(R.string.new_task),
                    style = Typography.titleLarge
                ) },

                navigationIcon = {
                    // back icon
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                actions = {
                    // add icon
                    IconButton(
                        onClick = {
                            isSaving = true
                            viewModel.add(title, description)
                            onBack()
                        },
                        enabled = titleIsValid && !isSaving,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = if (titleIsValid && !isSaving) {
                                MaterialTheme.colorScheme.onSurface
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Add"
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
            // title text field
            Text(
                text = stringResource(R.string.title),
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(
                    text = stringResource(R.string.add_title),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = Typography.bodySmall
                ) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // description text field
            Text(
                text = stringResource(R.string.description),
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(
                    text = stringResource(R.string.add_description),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = Typography.bodySmall
                ) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // save button
            Button(
                onClick = {
                    isSaving = true
                    viewModel.add(title, description)
                    onBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = Shapes.large,
                enabled = titleIsValid && !isSaving
            ) {
                Text(
                    text = stringResource(R.string.done),
                    style = Typography.labelLarge
                )
            }
        }
    }
}