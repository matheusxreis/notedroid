package com.matheusxreis.notes.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
                noteId = it as Int
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
        mView.notes_rv.adapter = notesAdapter
        mView.notes_rv.layoutManager = LinearLayoutManager(requireContext())
    }

    fun populateRecyclerView() {
        mainViewModel.notes.observe(viewLifecycleOwner) {
            val notes = it?.map {  noteEntity -> noteEntity.entityToNote(noteEntity)  }
            if (notes != null) {
                notesAdapter.setData(notes)
            }
        }
    }


}