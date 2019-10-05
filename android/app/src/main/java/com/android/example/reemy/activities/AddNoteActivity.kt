package com.android.example.reemy.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.example.reemy.R
import com.android.example.reemy.databinding.ActivityAddNoteBinding
import com.android.example.reemy.utils.MyEventDay
import com.applandeo.materialcalendarview.EventDay

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)

        binding.addNoteButton.setOnClickListener{
            val returnIntent = Intent()

            val myEventDay: MyEventDay = MyEventDay(binding.datePicker.selectedDate, R.drawable.ic_message_black_48dp, binding.noteEditText.text.toString())
            returnIntent.putExtra(MainActivity.RESULT, myEventDay)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}