package com.code7979.noteapp.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.sqldelight.hockey.data.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class NoteDataSourceImpl(
    db: com.code7979.Database
) : NoteDataSource {
    private val queries = db.personEntityQueries
    override suspend fun getNoteById(id: Long): NoteEntity? {
        return withContext(Dispatchers.IO) {
            queries.getNoteById(id).executeAsOneOrNull()
        }
    }

    override fun getAllNotes(context: CoroutineContext): Flow<List<NoteEntity>> {
        return queries.getAllNotes().asFlow().mapToList(context)
    }

    override suspend fun deleteNoteById(id: Long) {
        withContext(Dispatchers.IO) {
            queries.deleteNoteById(id)
        }
    }

    override suspend fun insertNote(
        id: Long?,
        title: String,
        content: String,
        lastModified: Long
    ) {

        withContext(Dispatchers.IO) {
            queries.insertNote(null, title, content, lastModified)
        }

    }

}