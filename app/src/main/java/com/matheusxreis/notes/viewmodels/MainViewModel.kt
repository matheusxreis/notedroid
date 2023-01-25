package com.matheusxreis.notes.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheusxreis.notes.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    var notes:MutableLiveData<List<Note>> = MutableLiveData()

    private val mockListNotes = listOf(
        Note(id=1, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet.", important = true),
        Note(id=2, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
        Note(id=3, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
        Note(id=4, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
        Note(id=5, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet.", important = true),
        Note(id=6, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet.", important = true),
        Note(id=7, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
        Note(id=8, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
        Note(id=9, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet.", important = true),
        Note(id=10, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
        Note(id=11, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet.", important = true),
        Note(id=12, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
        Note(id=13, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
        Note(id=14, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
        Note(id=15, title="Be or not be", text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. In aliquam et sem a maximus. Integer eleifend nisi a aliquet posuere. Nulla mollis facilisis enim, nec malesuada libero consequat at. Curabitur lacinia pulvinar ultricies. Mauris vel feugiat leo. Sed at placerat nulla, ac maximus velit. Ut aliquet dignissim dolor finibus aliquet."),
        )

    fun getNotes() = viewModelScope.launch {
        notes.value = mockListNotes
    }

}