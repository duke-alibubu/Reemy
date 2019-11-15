package com.android.example.reemy.addnote

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.example.reemy.R
import com.android.example.reemy.databinding.ActivityEditTextBinding
import com.android.example.reemy.utils.IntentCode.Companion.CURRENT_STR
import com.android.example.reemy.utils.IntentCode.Companion.EDIT_TEXT_RESULT

class EditTextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_text)
        binding.screenEditText.setText(intent.getStringExtra(CURRENT_STR))

        binding.saveButton.setOnClickListener{
            val returnIntent = Intent()
            val returnText = binding.screenEditText.text.toString()
            returnIntent.putExtra(EDIT_TEXT_RESULT, returnText)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
