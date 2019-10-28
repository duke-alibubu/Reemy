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

    private val _delete = MutableLiveData<Boolean>()
    val delete: LiveData<Boolean>
        get() = _delete

    fun deleteEvent() {
        uiScope.launch{
            deleteEventFromDatabase()
        }
    }

    private suspend fun deleteEventFromDatabase(){
        withContext(Dispatchers.IO){
            AllEvents.mEventDays.remove(mEvent)
        }
    }

    fun onStartDeleteEvent(){
        _delete.value = true
    }

    fun onDeleteEventComplete(){
        _delete.value = null
    }
}