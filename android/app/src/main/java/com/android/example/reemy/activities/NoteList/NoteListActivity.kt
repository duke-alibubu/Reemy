package com.android.example.reemy.activities.NoteList

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.example.reemy.R
import com.android.example.reemy.activities.MainActivity
import com.android.example.reemy.databinding.ActivityNoteListBinding
import com.android.example.reemy.utils.AllEvents
import androidx.lifecycle.Observer
import com.android.example.reemy.utils.MyEventDay
import com.applandeo.materialcalendarview.EventDay
import java.util.*

class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_list)
        val application : Application = requireNotNull (this.application)

        val viewModelFactory : NoteListViewModelFactory = NoteListViewModelFactory(application)
        val noteListViewModel : NoteListViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(NoteListViewModel::class.java)

        val adapter = NoteAdapter()
        
        binding.noteList.adapter = adapter
        noteListViewModel.mEventDays.observe(this, Observer{
            it?.let{
                adapter.submitList(it)
            }
        } )

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

        binding.setLifecycleOwner(this)
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
