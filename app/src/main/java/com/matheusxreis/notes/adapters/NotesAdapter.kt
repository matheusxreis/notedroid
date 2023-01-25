package com.matheusxreis.notes.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheusxreis.notes.models.Note

class NotesAdapter():RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){

    var notes:List<Note> = listOf()

    class NotesViewHolder(parent: ViewGroup):RecyclerView.ViewHolder(parent) {
        fun bind(note: Note){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.NotesViewHolder {
       return NotesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
       val currentNote = notes[position]

        holder.bind(currentNote)
    }

    override fun getItemCount(): Int = notes.size;

    fun setData(data: List<Note>){
        notes = data
        notifyItemInserted(itemCount)
    }
}