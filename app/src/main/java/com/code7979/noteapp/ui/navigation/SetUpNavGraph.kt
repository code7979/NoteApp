package com.code7979.noteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.code7979.noteapp.viewmodels.MainViewModel
import com.code7979.noteapp.ui.screen.EditScreen
import com.code7979.noteapp.ui.screen.MainScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {

        composable(route = Screen.MainScreen.route) {
            MainScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = Screen.EditScreen.route,
            arguments = listOf(navArgument(NOTE_ID) {
                type = NavType.LongType
            })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getLong(NOTE_ID) ?: -1L
            EditScreen(
                noteId = noteId,
                navController = navController
            )
        }
    }

}