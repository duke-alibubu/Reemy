package com.android.example.reemy.utils

import java.text.SimpleDateFormat
import java.util.*

class IntentCode {
    companion object {
        const val EDIT_NOTE = 50
        const val CURRENT_STR = "current_value"
        const val EDIT_TEXT_RESULT = "edit_result"
        const val RESULT = "RESULT"
        const val EVENT = "event"
        const val ADD_NOTE = 44

        fun getFormattedDate(date: Date): String {
            val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            return simpleDateFormat.format(date)
        }
    }
}