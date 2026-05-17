package com.example.todolist.ui.screens.edititem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditItemScreen(
    viewModel: EditItemViewModel,
    itemId: Int,
    onBack: () -> Unit
) {
    val item = viewModel.item

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var isSaving by remember { mutableStateOf(false) }
    val titleIsValid = title.isNotBlank()

    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }
    val initialMillis = remember(selectedDate) {
        selectedDate?.atStartOfDay(ZoneId.of("UTC"))?.toInstant()?.toEpochMilli()
    }
    val datePickerState = remember(initialMillis) {
        DatePickerState(
            initialSelectedDateMillis = initialMillis,
            locale = java.util.Locale.getDefault()
        )
    }
    val formattedDate = selectedDate?.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) ?: ""

    LaunchedEffect(Unit) {
        viewModel.load(itemId)
    }

    LaunchedEffect(item) {
        item?.let {
            title = it.title
            description = it.description
            selectedDate = it.selectedDate
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    stringResource(R.string.edit_task),
                    style = Typography.titleLarge,
                ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    MaterialTheme.colorScheme.background
                ),

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
                    // save icon
                    IconButton(
                        onClick = {
                            isSaving = true
                            viewModel.update(title, description, selectedDate)
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
                            contentDescription = "Save"
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
            // title
            Text(
                text = stringResource(R.string.title),
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(
                    text = stringResource(R.string.edit_title),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = Typography.bodySmall
                ) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // description
            Text(
                text = stringResource(R.string.description),
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(
                    text = stringResource(R.string.edit_description),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = Typography.bodySmall
                ) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // date
            Text(
                text = stringResource(R.string.date),
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = formattedDate,
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(
                            text = stringResource(R.string.edit_date),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = Typography.bodySmall
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            showDatePicker = true
                        }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // save button
            Button(
                onClick = {
                    isSaving = true
                    viewModel.update(title, description, selectedDate)
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

            // date dialog
            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                val selectedMillis = datePickerState.selectedDateMillis
                                if (selectedMillis != null) {
                                    selectedDate = Instant.ofEpochMilli(selectedMillis)
                                        .atZone(ZoneId.of("UTC"))
                                        .toLocalDate()
                                }
                                showDatePicker = false
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.ok),
                                style = Typography.labelLarge
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { showDatePicker = false }
                        ) {
                            Text(
                                text = stringResource(R.string.cancel),
                                style = Typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}