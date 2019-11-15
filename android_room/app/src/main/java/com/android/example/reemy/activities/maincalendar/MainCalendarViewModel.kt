package com.android.example.reemy.activities.maincalendar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.example.reemy.database.EventDatabaseDao
import com.android.example.reemy.database.MyEventDay
import com.applandeo.materialcalendarview.EventDay
import kotlinx.coroutines.*


class MainCalendarViewModel(application: Application, dataSource: EventDatabaseDao): AndroidViewModel(application){

    val database = dataSource
    var mEventDays = MutableLiveData<List<EventDay>>()
    private var viewModelJob : Job = Job()
    private var uiScope : CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToNotePreview = MutableLiveData<Long>()
    val navigateToNotePreview: LiveData<Long>
        get() = _navigateToNotePreview

    init {
        loadEventDays()
    }

    private fun loadEventDays() {
        uiScope.launch{
            mEventDays.value = getEventsFromDatabase()
        }
    }

    private suspend fun getEventsFromDatabase(): List<EventDay>? {
        return withContext(Dispatchers.IO){
            val events = database.getAllEvents().value
            events
        }
    }

    fun onNoteClicked(eventID: Long){
        _navigateToNotePreview.value = eventID
    }

    fun onNoteClickedFinish(){
        _navigateToNotePreview.value = null
    }

    fun addNewEvent(event: MyEventDay){
        uiScope.launch {
            insertEvent(event)
            mEventDays.value = getEventsFromDatabase()
        }
    }

    private suspend fun insertEvent(event: MyEventDay){
        withContext(Dispatchers.IO){
            database.addEvent(event)
        }
    }
}
