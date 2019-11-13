package com.android.example.reemy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.applandeo.materialcalendarview.EventDay
import java.util.*

@Entity(tableName = "event_table")
data class MyEventDay(val day: Calendar, val imgResource: Int, val note: String): EventDay(day, imgResource) {

    @PrimaryKey(autoGenerate = true)
    var eventId: Long = 0L

    @ColumnInfo(name = "note")
    var mNote: String = note
}