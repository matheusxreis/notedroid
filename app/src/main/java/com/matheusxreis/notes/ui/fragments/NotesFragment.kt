package com.matheusxreis.notes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheusxreis.notes.R
import com.matheusxreis.notes.adapters.NotesAdapter
import com.matheusxreis.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes.view.*

class NotesFragment : Fragment() {

    private lateinit var notesAdapter: NotesAdapter;
    private lateinit var mView: View;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notesAdapter = NotesAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_notes, container, false);

        setUpRecyclerView()
        populateRecyclerView()

        return mView
    }

    fun setUpRecyclerView(){
        mView.notes_rv.adapter = notesAdapter
        mView.notes_rv.layoutManager = LinearLayoutManager(requireContext())
    }

    fun populateRecyclerView() {
        val mockListNotes = listOf(
            Note(id=1, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
            Note(id=2, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
            Note(id=3, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
            Note(id=4, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
            Note(id=5, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
            Note(id=6, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
            )
        notesAdapter.setData(mockListNotes)
    }
}