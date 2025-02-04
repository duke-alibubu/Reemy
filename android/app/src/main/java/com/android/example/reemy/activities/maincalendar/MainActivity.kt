package com.android.example.reemy.activities.maincalendar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.example.reemy.activities.AddNoteActivity
import com.android.example.reemy.activities.notepreview.NotePreviewActivity
import com.android.example.reemy.activities.notelist.NoteListActivity
import com.android.example.reemy.databinding.ActivityMainBinding
import com.android.example.reemy.utils.AllEvents
import com.android.example.reemy.utils.IntentCode.Companion.ADD_NOTE
import com.android.example.reemy.utils.IntentCode.Companion.EVENT
import com.android.example.reemy.utils.IntentCode.Companion.RESULT
import com.android.example.reemy.utils.MyEventDay


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.android.example.reemy.R.layout.activity_main)

        val viewModelFactory : MainCalendarViewModelFactory = MainCalendarViewModelFactory(application)
        val mainCalendarViewModel : MainCalendarViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(MainCalendarViewModel::class.java)


        mainCalendarViewModel.mEventDays.observe(this, Observer {
            it?.let{
                binding.calendarView.setEvents(it)
            }
        })

        mainCalendarViewModel.navigateToNotePreview.observe(this, Observer {
            it?.let{
                if(it is MyEventDay){
                    val intent = Intent(this, NotePreviewActivity::class.java)
                    intent.putExtra(EVENT, it )
                    startActivity(intent)
                }
                mainCalendarViewModel.onNoteClickedFinish()
            }
        })


        binding.addEventButton.setOnClickListener{
            addNote()
        }

        binding.calendarView.setOnDayClickListener{
            it?.let{
                mainCalendarViewModel.onNoteClicked(it)
            }
        }

        binding.listSwitch.setOnClickListener{
            it?.let{
                navigateToNoteList()
            }
        }
    }

    private fun navigateToNoteList() {
        val intent = Intent(this, NoteListActivity::class.java)
        startActivity(intent)
    }

    private fun addNote() {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivityForResult(intent,
            ADD_NOTE
        )
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE && resultCode == Activity.RESULT_OK){
            val myEventDay: MyEventDay = data!!.getParcelableExtra(RESULT)
            binding.calendarView.setDate(myEventDay.calendar)
            AllEvents.mEventDays.add(myEventDay)
        }
    }

}

