package com.android.example.reemy.activities.NoteList

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.example.reemy.utils.MyEventDay
import com.applandeo.materialcalendarview.EventDay

@BindingAdapter("noteDayFormatted")
fun TextView.setNoteDayFormatted(item: EventDay?){
    item?.let{
        text = item.calendar.toString()
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