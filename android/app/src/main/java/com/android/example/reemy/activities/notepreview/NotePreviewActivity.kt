package com.android.example.reemy.activities.notepreview

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.example.reemy.activities.maincalendar.MainActivity
import com.android.example.reemy.databinding.ActivityNotePreviewBinding
import com.android.example.reemy.utils.AllEvents
import com.android.example.reemy.utils.MyEventDay
import com.applandeo.materialcalendarview.EventDay
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class NotePreviewActivity: AppCompatActivity() {
    private lateinit var binding: ActivityNotePreviewBinding
    private lateinit var mEvent: EventDay
    private var viewModelJob : Job = Job()
    private var uiScope : CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.android.example.reemy.R.layout.activity_note_preview)

        binding.deleteEventButton.setOnClickListener{
            it?.let{
                deleteEvent()
            }
        }
        //get intent
        val mIntent = intent


        mIntent?.let {
            val event : Parcelable = mIntent.getParcelableExtra(MainActivity.EVENT)
            if (event is MyEventDay){
                // if a day with note
                val myEventDay: MyEventDay = event as MyEventDay
                supportActionBar!!.title =
                    getFormattedDate(
                        myEventDay.calendar.time
                    )
                binding.note.text = myEventDay.getNote()
                mEvent = event
                return
            }

            if (event is EventDay){
                val eventDay: EventDay = event as EventDay
                supportActionBar!!.title =
                    getFormattedDate(
                        eventDay.calendar.time
                    )
            }
        }
    }

    private fun deleteEvent() {
        mEvent?.let{
            uiScope.launch{
                deleteEventFromDatabase()
            }
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private suspend fun deleteEventFromDatabase(){
        withContext(Dispatchers.IO){
            AllEvents.mEventDays.remove(mEvent)
        }
    }


    companion object {
        fun getFormattedDate(date: Date): String {
            val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            return simpleDateFormat.format(date)
        }
    }
}