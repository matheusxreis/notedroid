package com.matheusxreis.notes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheusxreis.notes.databinding.NotesRowLayoutBinding
import com.matheusxreis.notes.models.Note

class NotesAdapter(private val goToInfo:(id: Int?)->Unit):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(){

    var notes:List<Note> = listOf()

    class NotesViewHolder(private val binding: NotesRowLayoutBinding, private val callback:(id:Int?)->Unit):RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note){
            binding.note = note
            binding.executePendingBindings()

            binding.cardRow.setOnClickListener {
               callback(note.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.NotesViewHolder {

      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = NotesRowLayoutBinding.inflate(layoutInflater, parent, false)
        return NotesViewHolder(binding, goToInfo)
    }
    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
       val currentNote = notes[position]
       holder.bind(currentNote)
    }

    override fun getItemCount(): Int = notes.size;

    fun setData(data: List<Note>){
        notes = data
        notifyItemInserted(itemCount)
        notifyDataSetChanged()
    }
}