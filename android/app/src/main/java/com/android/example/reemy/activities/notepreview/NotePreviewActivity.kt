package com.android.example.reemy.activities.notepreview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.example.reemy.activities.EditTextActivity
import com.android.example.reemy.activities.maincalendar.MainActivity
import com.android.example.reemy.databinding.ActivityNotePreviewBinding
import com.android.example.reemy.utils.AllEvents
import com.android.example.reemy.utils.IntentCode
import com.android.example.reemy.utils.IntentCode.Companion.CURRENT_STR
import com.android.example.reemy.utils.IntentCode.Companion.EDIT_NOTE
import com.android.example.reemy.utils.IntentCode.Companion.EVENT
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

        binding.editEventButton.setOnClickListener{
            it?.let{
                navigateToEditText()
            }
        }

        //get intent
        val mIntent = intent


        mIntent?.let {
            val event : Parcelable = mIntent.getParcelableExtra(EVENT)
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

    private fun navigateToEditText(){
        if (mEvent is MyEventDay){
            val intent = Intent(this, EditTextActivity::class.java)
            intent.putExtra(CURRENT_STR, binding.note.text.toString())
            startActivityForResult(intent, EDIT_NOTE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_NOTE && resultCode == Activity.RESULT_OK){
            val myText = data!!.getStringExtra(IntentCode.EDIT_TEXT_RESULT)
            if (mEvent is MyEventDay){
                (mEvent as MyEventDay).setNote(myText)
                binding.note.setText(myText)
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

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
    }

    companion object {
        fun getFormattedDate(date: Date): String {
            val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            return simpleDateFormat.format(date)
        }
    }
}