package com.matheusxreis.notes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matheusxreis.notes.models.Note
import com.matheusxreis.notes.utils.Constants

@Entity(
    tableName = Constants.TABLE_NAME_NOTE
)
class NoteEntity(
    var title: String,
    var text: String,
    var important: Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1
}


fun NoteEntity.entityToNote(noteEntity: NoteEntity): Note {
    return Note(
        id = noteEntity.id,
        title = noteEntity.title,
        text = noteEntity.text,
        important = noteEntity.important
    )
}