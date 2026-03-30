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
import com.example.todolist.ui.settings.SettingsScreen
import com.example.todolist.ui.settings.SettingsViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {

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
        composable("settings") {
            val vm: SettingsViewModel = hiltViewModel()

            SettingsScreen(
                viewModel = vm,
                onBack = {navController.popBackStack()}
            )
        }

        composable("new") {
            val vm: NewItemViewModel = hiltViewModel()

            NewItemScreen(
                viewModel = vm,
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = "edit/{id}") {

            val id = it.arguments?.getString("id")!!.toInt()
            val vm: EditItemViewModel = hiltViewModel()

            EditItemScreen(
                viewModel = vm,
                itemId = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}