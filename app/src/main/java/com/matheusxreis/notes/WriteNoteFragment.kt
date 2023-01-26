package com.matheusxreis.notes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.matheusxreis.notes.databinding.FragmentWriteNoteBinding
import com.matheusxreis.notes.viewmodels.MainViewModel

class WriteNoteFragment : Fragment() {

    lateinit var binding:FragmentWriteNoteBinding
    lateinit var mainViewModel: MainViewModel;
    lateinit var title:String
    lateinit var text:String
    var important:Boolean = false

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
        binding.addButton.setOnClickListener {
            val titleText = binding.editTextTitle.text
            val textText = binding.editTextText.text

            if(titleText.isNullOrBlank()){
                binding.editTextTitleLayout.error = "Give a title to a note"
            }
            if(textText.isNullOrBlank()){
                binding.editTextTextLayout.error = "Write something"
            }


            if(!textText.isNullOrBlank() && !titleText.isNullOrBlank()){

                title = titleText.toString()
                text = textText.toString()
                important = binding.switchButton.isChecked

                binding.editTextTextLayout.error = null
                binding.editTextTitleLayout.error = null

                mainViewModel.saveNote(
                    title = title,
                    text = text,
                    important = important
                    )

                binding.editTextTitle.text = null
                binding.editTextText.text = null

                goBack()
            }

        }

        return binding.root
    }

    fun goBack(){
        findNavController().navigate(R.id.action_writeNoteFragment_to_notes_fragment)
    }

}