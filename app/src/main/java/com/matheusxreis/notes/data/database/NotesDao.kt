package com.matheusxreis.notes.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert()
    suspend fun insertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes ORDER BY id")
    fun readNotes(): Flow<List<NoteEntity>>
}