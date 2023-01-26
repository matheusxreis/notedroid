package com.matheusxreis.notes.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.matheusxreis.notes.data.DataStoreRepository
import com.matheusxreis.notes.data.LocalDataSource
import com.matheusxreis.notes.data.database.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepo: DataStoreRepository,
    private val localDataSource: LocalDataSource
): ViewModel() {

    var notes:MutableLiveData<List<NoteEntity>> = MutableLiveData()
    val actualFilter = dataStoreRepo.readFilterImportant.asLiveData()

    val currentNote:MutableLiveData<NoteEntity> = MutableLiveData()


    fun changeFilter(
        filterImportantName: String,
        filterImportantId: Int
    ) = viewModelScope.launch (Dispatchers.IO){

      try{
          dataStoreRepo.saveImportantFilter(
              importantFilter = filterImportantName,
              importantFilterId = filterImportantId
          )
          // gettings notes always that filter change
          getNotes(filterImportantName)

      }catch(err:Exception){

      }
    }


    fun getNotes(filter:String) = viewModelScope.launch {
        Log.d("cachorr", filter)
        when (filter.lowercase()) {
            "all" -> getAllNotes()
            "importants" -> getImportantsNotes()
            "not importants" -> getNotImportantsNotes()
            else -> getAllNotes()
        }
    }

    private suspend fun getNotImportantsNotes() {
       localDataSource.readNotImportantNotes().collect {
           notes.value = it
       }
    }

    private suspend fun getImportantsNotes() {
       localDataSource.readImportantNotes().collect {
           notes.value = it
       }
    }

    private suspend fun getAllNotes() {
        localDataSource.readNotes().collect {
            notes.value = it
        }
    }

    fun saveNote(title:String, text:String, important: Boolean)= viewModelScope.launch {
        val note = NoteEntity(
            title = title,
            text = text,
            important = important
        )
        localDataSource.insertNote(note)

    }

    fun getNoteById(id:Int) = viewModelScope.launch {
        localDataSource.findNoteById(id).collect {
            currentNote.value = it
        }
    }


}