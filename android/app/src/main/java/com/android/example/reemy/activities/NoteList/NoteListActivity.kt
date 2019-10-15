package com.android.example.reemy.activities.NoteList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.example.reemy.R
import com.android.example.reemy.activities.MainActivity
import com.android.example.reemy.databinding.ActivityNoteListBinding
import com.android.example.reemy.utils.AllEvents
import com.android.example.reemy.utils.MyEventDay
import com.applandeo.materialcalendarview.EventDay
import java.util.*

class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_list)

        val adapter = NoteAdapter()
        binding.noteList.adapter = adapter
        adapter.submitList(AllEvents.mEventDays)
        binding.addEventButton.setOnClickListener{
            it?.let{
                adapter.submitList(AllEvents.mEventDays)
                Toast.makeText(applicationContext, "${AllEvents.mEventDays.size} items",Toast.LENGTH_SHORT).show()
            }
        }
        binding.calendarSwitch.setOnClickListener{
            it?.let{
                navigateToMain()
            }
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
