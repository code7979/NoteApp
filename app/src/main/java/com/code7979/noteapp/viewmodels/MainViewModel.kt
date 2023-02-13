package com.code7979.noteapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code7979.noteapp.data.NoteDataSource
import com.code7979.noteapp.ui.SearchWidgetState
import com.example.sqldelight.hockey.data.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource
) : ViewModel() {

    private val _notes = noteDataSource.getAllNotes(Dispatchers.IO)

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val allNotes = searchText
        .combine(_notes) { text, notes ->
            if (text.isBlank()) {
                notes
            } else {
                notes.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDataSource.deleteNoteById(noteId)
        }
    }

}

private fun NoteEntity.doesMatchSearchQuery(text: String): Boolean {
    val matchingCombinations = listOf(
        "$title$content",
        "$title $content",
        "${title.first()} ${content.first()}",
    )

    return matchingCombinations.any {
        it.contains(text, ignoreCase = true)
    }
}
