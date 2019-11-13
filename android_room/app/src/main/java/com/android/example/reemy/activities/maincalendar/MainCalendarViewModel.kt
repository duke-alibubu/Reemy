package com.android.example.reemy.activities.maincalendar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.example.reemy.utils.AllEvents
import com.applandeo.materialcalendarview.EventDay
import kotlinx.coroutines.*


class MainCalendarViewModel(application: Application): AndroidViewModel(application){

    var mEventDays = MutableLiveData<MutableList<EventDay>>()
    private var viewModelJob : Job = Job()
    private var uiScope : CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToNotePreview = MutableLiveData<EventDay>()
    val navigateToNotePreview: LiveData<EventDay>
        get() = _navigateToNotePreview

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
            val events = AllEvents.mEventDays
            events
        }
    }

    fun onNoteClicked(event: EventDay){
        _navigateToNotePreview.value = event
    }

    fun onNoteClickedFinish(){
        _navigateToNotePreview.value = null
    }
}
