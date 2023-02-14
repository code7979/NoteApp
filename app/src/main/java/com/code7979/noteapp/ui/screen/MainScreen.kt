package com.code7979.noteapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.code7979.noteapp.ui.SearchWidgetState
import com.code7979.noteapp.ui.componets.CustomStaggeredVerticalGrid
import com.code7979.noteapp.ui.componets.MyGridItem
import com.code7979.noteapp.ui.componets.NormalAppBar
import com.code7979.noteapp.ui.componets.SearchAppBar
import com.code7979.noteapp.ui.navigation.Screen
import com.code7979.noteapp.ui.theme.IconColorOnDark
import com.code7979.noteapp.utils.DateTimeUtil.toStringDate
import com.code7979.noteapp.viewmodels.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val searchWidgetState by viewModel.searchWidgetState

    val searchText by viewModel.searchText.collectAsState()
    val noteList by viewModel.allNotes.collectAsState(emptyList())

    Scaffold(
        topBar = {
            CustomTopBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchText,
                onSearchWidgetOpen = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchWidgetClose = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                },
                onTextChanged = viewModel::onSearchTextChange
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = Screen.EditScreen.passId(-1))
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = IconColorOnDark
                )
            }
        }

    ) { paddingValues ->
        Column(
            // in this column we are adding modifier to it
            // and adding padding from all sides and vertical scroll.
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            CustomStaggeredVerticalGrid(
                numColumns = 2,
                modifier = Modifier.padding(8.dp)
            ) {
                noteList.forEach { note ->
                    MyGridItem(
                        title = note.title,
                        content = note.content,
                        date = note.last_modified.toStringDate(),
                        onClick = {
                            navController.navigate(route = Screen.EditScreen.passId(note.id))
                        }
                    ) {
                        viewModel.deleteNote(note.id)
                    }
                }
            }
        }
    }

}

@Composable
fun CustomTopBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onSearchWidgetOpen: () -> Unit,
    onSearchWidgetClose: () -> Unit,
    onTextChanged: (String) -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                onClickActivation = onSearchWidgetOpen,
                text = searchTextState,
                onTextChange = onTextChanged
            )
        }
        SearchWidgetState.CLOSED -> {
            NormalAppBar(
                onClickActivation = onSearchWidgetClose
            )
        }
    }
}