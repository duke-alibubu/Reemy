package com.android.example.reemy.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.example.reemy.databinding.ActivityMainBinding
import com.android.example.reemy.utils.MyEventDay
import com.applandeo.materialcalendarview.EventDay




class MainActivity : AppCompatActivity() {

    private val mEventDays: MutableList<EventDay?> = mutableListOf<EventDay?>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.android.example.reemy.R.layout.activity_main)

        binding.addEventButton.setOnClickListener{
            addNote()
        }

        binding.calendarView.setOnDayClickListener{
            previewNote(it)
        }
    }



    companion object {
        val RESULT: String = "RESULT"
        val EVENT: String = "event"
        private val ADD_NOTE: Int = 44
    }

    private fun addNote() {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivityForResult(intent, ADD_NOTE)
    }

    private fun previewNote(eventDay: EventDay) {
        val intent = Intent(this, NotePreviewActivity::class.java);
        if(eventDay is MyEventDay){
            intent.putExtra(EVENT, eventDay )
        }
        startActivity(intent)
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_NOTE && resultCode == Activity.RESULT_OK){
            val myEventDay: MyEventDay = data!!.getParcelableExtra(RESULT)
            binding.calendarView.setDate(myEventDay.calendar)
            mEventDays.add(myEventDay)
        }
    }
}

