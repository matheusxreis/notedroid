package com.matheusxreis.notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.matheusxreis.notes.R
import com.matheusxreis.notes.databinding.FragmentNoteInfoBinding


class NoteInfoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        class Back(enabled: Boolean) : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_noteInfoFragment_to_notes_fragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, Back(true))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        val binding = FragmentNoteInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

}