package com.matheusxreis.notes.data

import com.matheusxreis.notes.data.database.NoteEntity
import com.matheusxreis.notes.data.database.NotesDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val notesDao: NotesDao
) {

    suspend fun insertNote(note:NoteEntity){
        notesDao.insertNote(note)
    }
    fun readNotes () = notesDao.readNotes()
    fun readImportantNotes () = notesDao.readImportantNotes()
    fun readNotImportantNotes () = notesDao.readNotImportantNotes()
    suspend fun findNoteById (id:Int) = notesDao.findNoteById(id)
    suspend fun updateNote (note: NoteEntity) = notesDao.updateNote(note)
}