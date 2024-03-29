package com.matheusxreis.notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.matheusxreis.notes.R
import com.matheusxreis.notes.data.database.entityToNote
import com.matheusxreis.notes.databinding.FragmentWriteNoteBinding
import com.matheusxreis.notes.viewmodels.MainViewModel

class WriteNoteFragment : Fragment() {

    lateinit var binding: FragmentWriteNoteBinding
    lateinit var mainViewModel: MainViewModel;
    lateinit var title: String
    lateinit var text: String
    var important: Boolean = false

    val args by navArgs<WriteNoteFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        class Back(enabled: Boolean) : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                goBack()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(this, Back(true))

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWriteNoteBinding.inflate(inflater, container, false)

        if (args.noteId != 0) {
            mainViewModel.getNoteById(id)
            mainViewModel.currentNote.observe(viewLifecycleOwner) {
                if(it!=null){
                    binding.note = it.entityToNote(it)
                    binding.switchButton.isChecked = it.important
                    binding.addButton.text = "Edit"
                }
            }
        }
        binding.addButton.setOnClickListener {
            val titleText = binding.editTextTitle.text
            val textText = binding.editTextText.text

            if (titleText.isNullOrBlank()) {
                binding.editTextTitleLayout.error = "Give a title to a note"
            }
            if (textText.isNullOrBlank()) {
                binding.editTextTextLayout.error = "Write something"
            }


            if (!textText.isNullOrBlank() && !titleText.isNullOrBlank()) {

                title = titleText.toString()
                text = textText.toString()
                important = binding.switchButton.isChecked

                binding.editTextTextLayout.error = null
                binding.editTextTitleLayout.error = null

                handleAction()

                binding.editTextTitle.text = null
                binding.editTextText.text = null

                mainViewModel.getNotes(mainViewModel.actualFilter.value.toString())
                goBack()
            }

        }

        return binding.root
    }

    fun goBack() {

        val actionToNoteInfo = WriteNoteFragmentDirections.actionWriteNoteFragmentToNoteInfoFragment( noteId = args.noteId)
        val navController = findNavController()
        val id = when(args.noteId){
            0 -> navController.navigate(R.id.action_writeNoteFragment_to_notes_fragment)
            else ->  navController.navigate(actionToNoteInfo)
        }

    }

    fun handleAction(){
        if(args.noteId != 0){
            mainViewModel.updateNote(
                id = args.noteId,
                title = title,
                text = text,
                important = important
            )

            Toast.makeText(requireContext(), "The note was updated", Toast.LENGTH_LONG)
                .show()
        }else {
            mainViewModel.saveNote(
                title = title,
                text = text,
                important = important
            )


            Toast.makeText(requireContext(), "The note was created", Toast.LENGTH_LONG)
                .show()
        }
    }

}