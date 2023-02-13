package com.code7979.noteapp.data

import com.example.sqldelight.hockey.data.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

interface NoteDataSource {
    suspend fun getNoteById(id: Long): NoteEntity?

    fun getAllNotes(context: CoroutineContext): Flow<List<NoteEntity>>

    suspend fun deleteNoteById(id: Long)

    suspend fun insertNote(id: Long? = null, title: String, content: String, lastModified: Long)
}