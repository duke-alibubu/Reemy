package com.android.example.reemy.activities.maincalendar

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.reemy.database.EventDatabaseDao

class MainCalendarViewModelFactory (private val application: Application, private val dataSource:EventDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainCalendarViewModel::class.java)) {
            return MainCalendarViewModel(application, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}