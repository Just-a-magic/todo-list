package com.example.todolist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.screens.edititem.EditItemScreen
import com.example.todolist.ui.screens.edititem.EditItemViewModel
import com.example.todolist.ui.screens.home.HomeScreen
import com.example.todolist.ui.screens.home.HomeViewModel
import com.example.todolist.ui.screens.newitem.NewItemScreen
import com.example.todolist.ui.screens.newitem.NewItemViewModel
import com.example.todolist.ui.screens.settings.SettingsScreen
import com.example.todolist.ui.screens.settings.SettingsViewModel

// этот файл описывает карту навигации приложения, здесь определяются все доступные экраны
// и правила перехода между ними

@Composable
fun NavGraph() {
    val navController = rememberNavController()  // отслеживает стек экранов и позволяет переключаться между ними

    NavHost(navController, startDestination = "home") {     // отображает текущий экран

        // главный экран
        composable("home") {
            val vm: HomeViewModel = hiltViewModel()

            HomeScreen(
                viewModel = vm,
                onAddClick = { navController.navigate("new") },
                onEditClick = { id ->
                    navController.navigate("edit/$id")
                },
                onSettingsClick = {
                    navController.navigate("settings")
                }
            )
        }
        // экран настроек
        composable("settings") {
            val vm: SettingsViewModel = hiltViewModel()

            SettingsScreen(
                viewModel = vm,
                onBack = {navController.popBackStack()}
            )
        }
        // экран добавления задачи
        composable("new") {
            val vm: NewItemViewModel = hiltViewModel()

            NewItemScreen(
                viewModel = vm,
                onBack = { navController.popBackStack() }
            )
        }
        // экран редактирования задачи
        composable(route = "edit/{id}") {
            val id = it.arguments?.getString("id")!!.toInt()    // извлекает id из строки маршрут
            val vm: EditItemViewModel = hiltViewModel()

            EditItemScreen(
                viewModel = vm,
                itemId = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}