package com.android.example.reemy.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.example.reemy.R
import com.android.example.reemy.databinding.ActivityEditTextBinding

class EditTextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_text)
        binding.screenEditText.setText(intent.getStringExtra(AddNoteActivity.CURRENT_STR))

        binding.saveButton.setOnClickListener{
            val returnIntent = Intent()
            val returnText = binding.screenEditText.text.toString()
            returnIntent.putExtra(AddNoteActivity.EDIT_TEXT_RESULT, returnText)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
