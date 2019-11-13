package com.android.example.reemy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.applandeo.materialcalendarview.EventDay

@Dao
interface EventDatabaseDao {
    @Insert
    fun addEvent(event: MyEventDay)

    @Update
    fun editEvent(event: MyEventDay)

    @Query("SELECT * from event_table WHERE eventId = :key")
    fun getEvent(key: Long): MyEventDay?

    @Query("DELETE from event_table WHERE eventId = :key")
    fun deleteEvent(key: Long)

    @Query("SELECT * FROM event_table ORDER BY eventId DESC")
    fun getAllEvents(): LiveData<List<EventDay>>
}