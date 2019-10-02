package com.android.example.reemy.activities

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.example.reemy.databinding.ActivityNotePreviewBinding
import com.android.example.reemy.utils.MyEventDay
import com.applandeo.materialcalendarview.EventDay
import java.text.SimpleDateFormat
import java.util.*

class NotePreviewActivity: AppCompatActivity() {
    private lateinit var binding: ActivityNotePreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.android.example.reemy.R.layout.activity_note_preview)

        //get intent
        val mIntent = intent

        mIntent.let {
            val event : Parcelable = mIntent.getParcelableExtra(MainActivity.EVENT)
            if (event is MyEventDay){
                // if a day with note
                val myEventDay: MyEventDay = event as MyEventDay
                supportActionBar!!.title = getFormattedDate(myEventDay.calendar.time)
                binding.note.text = myEventDay.getNote()

                return
            }

            if (event is EventDay){
                val eventDay: EventDay = event as EventDay
                supportActionBar!!.title = getFormattedDate(eventDay.calendar.time)
            }
        }

    }


    companion object {
        fun getFormattedDate(date: Date): String {
            val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            return simpleDateFormat.format(date)
        }
    }
}