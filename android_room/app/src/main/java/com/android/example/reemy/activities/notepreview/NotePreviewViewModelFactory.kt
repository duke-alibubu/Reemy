package com.android.example.reemy.activities.notepreview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.EventDay

class NotePreviewViewModelFactory (val event: EventDay, private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotePreviewViewModel::class.java)) {
            return NotePreviewViewModel(event, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}