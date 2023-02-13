package com.code7979.noteapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.code7979.noteapp.ui.componets.TransparentHintTextField
import com.code7979.noteapp.ui.navigation.Screen
import com.code7979.noteapp.ui.theme.IconColorOnLite
import com.code7979.noteapp.ui.theme.Typography
import com.code7979.noteapp.utils.DateTimeUtil
import com.code7979.noteapp.utils.DateTimeUtil.toDateTime
import com.code7979.noteapp.viewmodels.NoteEditViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditScreen(
    noteId: Long,
    navController: NavController,
    viewModel: NoteEditViewModel = hiltViewModel()
) {
    val screenTitle = if (noteId < 0) "Add New Note" else "Edit Note"
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = Color(0xFFf3f6fb),
                title = {
                    Text(text = screenTitle)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.MainScreen.route) {
                            popUpTo(Screen.MainScreen.route) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = IconColorOnLite
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.saveNote()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = null,
                            tint = IconColorOnLite,
                        )
                    }
                }
            )
        },

        ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.align(alignment = Alignment.End),
                text = DateTimeUtil.formatNoteDate(state.noteLastModified.toDateTime()),
                color = Color.Gray
            )
            TransparentHintTextField(
                text = state.noteTitle,
                hint = "Title",
                isHintVisible = state.isNoteTitleHintVisible,
                onValueChanged = viewModel::onNoteTitleChanged,
                onFocusChanged = {
                    viewModel.onNoteTitleFocusChanged(it.isFocused)
                },
                singleLine = true,
                textStyle = Typography.h6
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = state.noteContent,
                hint = "Enter your note here",
                isHintVisible = state.isNoteContentHintVisible,
                onValueChanged = viewModel::onNoteContentChanged,
                onFocusChanged = {
                    viewModel.onNoteContentFocusChanged(it.isFocused)
                },
                singleLine = false,
                modifier = Modifier.fillMaxSize(),
                textStyle = Typography.body1
            )
        }
    }
}


//@Preview
//@Composable
//fun DeatilScreenPreview() {
//    DetailScreen(
//        navController = rememberNavController(),
//        viewModel = hiltViewModel()
//    )
//
//}