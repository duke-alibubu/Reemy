package com.android.example.reemy.activities.notelist

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.example.reemy.database.MyEventDay
import com.applandeo.materialcalendarview.EventDay
import android.text.format.DateFormat
import java.util.*

@BindingAdapter("noteDayFormatted")
fun TextView.setNoteDayFormatted(item: EventDay?){
    item?.let{
        val date: Date = item.calendar.time
        val day: String = DateFormat.format("dd", date).toString()
        val month: String = DateFormat.format("MMM", date).toString()
        val year: String = DateFormat.format("yyyy",  date).toString()
        val result = "$day $month $year"
        text = result
    }
}

@BindingAdapter("noteContentFormatted")
fun TextView.setNoteContentFormatted(item: EventDay?){
    item?.let{
        if (item is MyEventDay){
            text = item.getNote()
        }
        else
            text = ""
    }
}