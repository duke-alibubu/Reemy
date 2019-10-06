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

        binding.noteEditText.setOnClickListener{
            it?.let{
                val intent = Intent(this, EditTextActivity::class.java)
                intent.putExtra(CURRENT_STR, binding.noteEditText.text.toString())
                startActivityForResult(intent, EDIT_NOTE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == EDIT_NOTE && resultCode == Activity.RESULT_OK){
            val myText = data!!.getStringExtra(EDIT_TEXT_RESULT)
            binding.noteEditText.setText(myText)
        }
    }

    companion object {
        const val EDIT_NOTE = 50
        const val CURRENT_STR = "current_value"
        const val EDIT_TEXT_RESULT = "edit_result"
    }
}