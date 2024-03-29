package com.matheusxreis.notes.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
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
import com.matheusxreis.notes.databinding.FragmentNoteInfoBinding
import com.matheusxreis.notes.utils.ellipsize
import com.matheusxreis.notes.viewmodels.MainViewModel


class NoteInfoFragment : Fragment() {


    lateinit var mainViewModel: MainViewModel;
    val args by navArgs<NoteInfoFragmentArgs>()

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

        // Inflate the layout for this fragment

        val binding = FragmentNoteInfoBinding.inflate(inflater, container, false)


        var alert = AlertDialog.Builder(requireActivity())
        alert.setMessage("Do you want to delete this note?")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { _, _ ->
                    try {
                        mainViewModel.deleteNote(args.noteId)
                        goBack()
                        Toast.makeText(requireContext(), "The note was deleted", Toast.LENGTH_LONG)
                            .show()
                    }catch(err:Exception){
                        Toast.makeText(requireContext(), "Something was wrong", Toast.LENGTH_LONG)
                            .show()
                    }

                })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog,id ->
                dialog.dismiss()
            }).create()


        if(args.noteId != 0) {
            mainViewModel.getNoteById(args.noteId)
            mainViewModel.currentNote.observe(viewLifecycleOwner) {
                if(it != null){
                    binding.note = it.entityToNote(it)
                }
            }

            binding.editFab.setOnClickListener {
                val action = NoteInfoFragmentDirections.actionNoteInfoFragmentToWriteNoteFragment(noteId = args.noteId as Int)
                findNavController().navigate(action)
            }

            binding.deleteFab.setOnClickListener {
                 alert.show()
            }
        }




        return binding.root
    }

    fun goBack(){
        findNavController().navigate(R.id.action_noteInfoFragment_to_notes_fragment)

    }
}