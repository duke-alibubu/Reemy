package com.android.example.reemy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.applandeo.materialcalendarview.EventDay

@Dao
interface EventDatabaseDao {
    @Insert
    fun addEvent(event: MyEventDay)

    @Query("UPDATE event_table SET note = :note WHERE eventId = :key ")
    fun updateNote(note: String, key: Long)

    @Query("SELECT * from event_table WHERE eventId = :key")
    fun getEvent(key: Long): MyEventDay?

    @Query("DELETE from event_table WHERE eventId = :key")
    fun deleteEvent(key: Long)

    @Query("SELECT * FROM event_table ORDER BY eventId DESC")
    fun getAllEvents(): LiveData<List<EventDay>>
}