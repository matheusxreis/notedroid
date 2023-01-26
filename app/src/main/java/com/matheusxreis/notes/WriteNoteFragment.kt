package com.matheusxreis.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController

class WriteNoteFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        class Back(enabled: Boolean) : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
               findNavController().navigate(R.id.action_writeNoteFragment_to_notes_fragment)
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(this, Back(true))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_write_note, container, false)
    }

}