package com.android.example.reemy.activities.NoteList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.example.reemy.utils.AllEvents
import com.applandeo.materialcalendarview.EventDay
import kotlinx.coroutines.*

class NoteListViewModel(application: Application): AndroidViewModel(application){
    var mEventDays = MutableLiveData<MutableList<EventDay>>()
    private var viewModelJob : Job = Job()
    private var uiScope : CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    init {
        loadEventDays()
    }

    private fun loadEventDays() {
        uiScope.launch{
            mEventDays.value = getEventsFromDatabase()
        }
    }

    private suspend fun getEventsFromDatabase(): MutableList<EventDay>? {
        return withContext(Dispatchers.IO){
            var events = AllEvents.mEventDays
            events
        }
    }

}