package com.android.example.reemy.activities.notepreview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.example.reemy.utils.AllEvents
import com.applandeo.materialcalendarview.EventDay
import kotlinx.coroutines.*

class NotePreviewViewModel(val event: EventDay, application: Application): AndroidViewModel(application) {
    private var mEvent: EventDay
    private var viewModelJob : Job = Job()
    private var uiScope : CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init{
        mEvent = event
    }

}