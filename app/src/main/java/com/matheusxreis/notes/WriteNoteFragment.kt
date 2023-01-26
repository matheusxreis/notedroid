package com.matheusxreis.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.matheusxreis.notes.databinding.FragmentWriteNoteBinding
import com.matheusxreis.notes.models.Note

class WriteNoteFragment : Fragment() {

    lateinit var binding:FragmentWriteNoteBinding

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

        binding = FragmentWriteNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

}