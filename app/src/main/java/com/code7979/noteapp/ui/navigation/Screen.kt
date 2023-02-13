package com.code7979.noteapp.ui.navigation

const val NOTE_ID = "noteId"

sealed class Screen(var route: String) {
    object MainScreen : Screen(route = "main_screen")
    object EditScreen : Screen(route = "detail_screen/{$NOTE_ID}") {

        fun passId(noteId: Long): String {
            return this.route.replace(oldValue = "{$NOTE_ID}", newValue = noteId.toString())
        }
    }
}
