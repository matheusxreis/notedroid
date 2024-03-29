package com.matheusxreis.notes.ui.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.matheusxreis.notes.R
import com.matheusxreis.notes.adapters.NotesAdapter
import com.matheusxreis.notes.data.database.entityToNote
import com.matheusxreis.notes.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_notes.view.*

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private lateinit var notesAdapter: NotesAdapter;
    private lateinit var mView: View;
    private lateinit var mainViewModel: MainViewModel;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notesAdapter = NotesAdapter {
            val action = NotesFragmentDirections.actionNotesFragmentToNoteInfoFragment(
                noteId = it as Int,
                noteTitle = "See your note"
            )
            findNavController().navigate(action)
        }
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_notes, container, false);

        mainViewModel.actualFilter.observe(viewLifecycleOwner) {
            value ->
            defineSelectedChip(value.selectedImportantFilterId, mView.chip_group)
        }


        if(mainViewModel.notes.value.isNullOrEmpty()){
            showNoResultValues()
        }
        setUpRecyclerView()
        populateRecyclerView()


        mView.chip_group.setOnCheckedChangeListener { group, selectedId ->
            val chip = group.findViewById<Chip>(selectedId)
            val selectedFilterName = chip.text.toString().lowercase()
            mainViewModel.changeFilter(
                filterImportantName = selectedFilterName,
                filterImportantId = selectedId
            )
        }

        mView.fab.setOnClickListener {
             findNavController().navigate(
                 R.id.action_notes_fragment_to_writeNoteFragment
             )
        }


        return mView
    }

    private fun defineSelectedChip(filterId: Int, chipGroup: ChipGroup) {
        if(filterId != 0){
            val chip:Chip = chipGroup.findViewById<Chip>(filterId)
            chip.isChecked = true
            mainViewModel.changeFilter(filterImportantName = chip.text.toString().lowercase(),
                                        filterImportantId = filterId)
        }
    }

    fun setUpRecyclerView(){
        ItemTouchHelper(ItemTouchCallback()).attachToRecyclerView(mView.notes_rv)
        mView.notes_rv.adapter = notesAdapter
        mView.notes_rv.layoutManager = LinearLayoutManager(requireContext())
    }

    fun populateRecyclerView() {
        mainViewModel.notes.observe(viewLifecycleOwner) {
            val notes = it?.map {  noteEntity -> noteEntity.entityToNote(noteEntity)  }

            if (notes != null) {
                notesAdapter.setData(notes)
            }
            if(notes.isNullOrEmpty()){
               showNoResultValues()
            }else {
                hideNoResultValues()
            }
        }
    }

    fun showNoResultValues(){
        val text =  mView.no_result_tv
        val image =  mView.no_result_image

        if(text.visibility == View.INVISIBLE){
            text.visibility = View.VISIBLE
            image.visibility = View.VISIBLE
        }

    }

    fun hideNoResultValues(){
        val text =  mView.no_result_tv
        val image =  mView.no_result_image

        if(text.visibility == View.VISIBLE) {
            text.visibility = View.INVISIBLE
            image.visibility = View.INVISIBLE
        }
    }


    inner class ItemTouchCallback(): ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.DOWN or ItemTouchHelper.UP,
        ItemTouchHelper.RIGHT
    ){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {

            val startPosition = viewHolder.adapterPosition
            val endPosition = target.adapterPosition
            recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val noteId = notesAdapter.notes[viewHolder.adapterPosition].id

            if(!noteId.toString().isNullOrBlank()){
                try{
                    mainViewModel.deleteNote(noteId as Int)
                    Toast.makeText(requireContext(), "The note was deleted", Toast.LENGTH_LONG).show()
                }catch(err:Exception){
                    Toast.makeText(requireContext(), "Something was wrong", Toast.LENGTH_LONG).show()

                }

            }

        }
    }

}