package com.matheusxreis.notes.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert()
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes ORDER BY id")
    fun readNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE important=1")
    fun readImportantNotes():Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE important=0")
    fun readNotImportantNotes():Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id=:id")
    fun findNoteById(id:Int):Flow<NoteEntity>

    @Update
    fun updateNote(note:NoteEntity)

    @Query("DELETE FROM notes WHERE id=:id")
    fun deleteNote(id:Int)
}