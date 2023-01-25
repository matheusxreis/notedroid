package com.matheusxreis.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NotesDatabase:RoomDatabase(){

    abstract fun notesDao():NotesDao
}