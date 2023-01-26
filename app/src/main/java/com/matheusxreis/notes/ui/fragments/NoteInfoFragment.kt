package com.matheusxreis.notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.matheusxreis.notes.R
import com.matheusxreis.notes.data.database.NoteEntity
import com.matheusxreis.notes.data.database.entityToNote
import com.matheusxreis.notes.databinding.FragmentNoteInfoBinding
import com.matheusxreis.notes.viewmodels.MainViewModel


class NoteInfoFragment : Fragment() {


    lateinit var mainViewModel: MainViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        class Back(enabled: Boolean) : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_noteInfoFragment_to_notes_fragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, Back(true))

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        val binding = FragmentNoteInfoBinding.inflate(inflater, container, false)

        mainViewModel.getNoteById(1)
        mainViewModel.currentNote.observe(viewLifecycleOwner) {
            binding.note = it.entityToNote(it)
            }


        return binding.root
    }

}