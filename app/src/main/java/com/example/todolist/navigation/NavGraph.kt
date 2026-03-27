package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.data.local.AppDatabase
import com.example.todolist.data.repository.TodoRepository
import com.example.todolist.ui.edititem.EditItemScreen
import com.example.todolist.ui.edititem.EditItemViewModel
import com.example.todolist.ui.home.HomeScreen
import com.example.todolist.ui.home.HomeViewModel
import com.example.todolist.ui.newitem.NewItemScreen
import com.example.todolist.ui.newitem.NewItemViewModel
import com.example.todolist.ui.settings.SettingsScreen
import com.example.todolist.ui.settings.SettingsViewModel

@Composable
fun NavGraph(database: AppDatabase) {
    val navController = rememberNavController()

    val repository = remember { TodoRepository(database.todoDao()) }

    NavHost(navController, startDestination = "home") {

        composable("home") {
            val vm = remember { HomeViewModel(repository) }

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
        composable("settings") {
            val vm = remember { SettingsViewModel(repository) }

            SettingsScreen(
                viewModel = vm,
                onBack = {navController.popBackStack()}
            )
        }

        composable("new") {
            val vm = remember { NewItemViewModel(repository) }

            NewItemScreen(
                viewModel = vm,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "edit/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id") ?: return@composable
            val vm = remember { EditItemViewModel(repository) }

            EditItemScreen(
                viewModel = vm,
                itemId = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}