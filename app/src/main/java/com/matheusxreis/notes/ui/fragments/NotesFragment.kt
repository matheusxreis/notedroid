package com.matheusxreis.notes.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.matheusxreis.notes.R
import com.matheusxreis.notes.adapters.NotesAdapter
import com.matheusxreis.notes.models.Note
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

        notesAdapter = NotesAdapter()
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

        return mView
    }

    private fun defineSelectedChip(filterId: Int, chipGroup: ChipGroup) {
        if(filterId != 0){
            chipGroup.findViewById<Chip>(filterId).isChecked = true
        }
    }

    fun setUpRecyclerView(){
        mView.notes_rv.adapter = notesAdapter
        mView.notes_rv.layoutManager = LinearLayoutManager(requireContext())
    }

    fun populateRecyclerView() {
        mainViewModel.getNotes()

        mainViewModel.notes.observe(viewLifecycleOwner) {
            notesAdapter.setData(it)
        }
    }


}