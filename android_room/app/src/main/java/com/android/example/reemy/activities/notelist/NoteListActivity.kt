package com.android.example.reemy.activities.notelist

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.example.reemy.R
import com.android.example.reemy.activities.maincalendar.MainActivity
import com.android.example.reemy.databinding.ActivityNoteListBinding
import androidx.lifecycle.Observer
import com.android.example.reemy.activities.notepreview.NotePreviewActivity
import com.android.example.reemy.utils.IntentCode.Companion.EVENT
import com.android.example.reemy.database.MyEventDay

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

        val adapter = NoteAdapter(NoteEventListener {
            it?.let{
                noteListViewModel.onNoteClicked(it)
            }
        })

        noteListViewModel.navigateToNotePreview.observe(this, Observer {
            it?.let{
                if(it is MyEventDay){
                    val intent = Intent(this, NotePreviewActivity::class.java)
                    intent.putExtra(EVENT, it )
                    startActivity(intent)
                }
                noteListViewModel.onNoteClickedFinish()
            }
        })
        
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
