package com.matheusxreis.notes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matheusxreis.notes.utils.Constants

@Entity(
    tableName = Constants.TABLE_NAME_NOTE
)
class NoteEntity(
    var title: String,
    var text: String,
    var important: Boolean = false
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}