package com.android.example.reemy

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.android.example.reemy.databinding.FragmentCalendarViewBinding
import com.applandeo.materialcalendarview.EventDay


class CalendarViewFragment : Fragment() {

    private lateinit var mEventDays: MutableList<EventDay>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mEventDays = mutableListOf()
        val binding = DataBindingUtil.inflate<FragmentCalendarViewBinding>(inflater,
            R.layout.fragment_calendar_view,container,false)

        binding.addNoteButton.setOnClickListener{
            findNavController().navigate(CalendarViewFragmentDirections.actionCalendarViewFragmentToAddNoteFragment())
        }

        return binding.root
    }

    @Override
    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_NOTE) run {
            val myEventDay: EventDay = data.getParcelableExtra(RESULT)
            mCalendarView.setDate(myEventDay.getCalendar());
            mEventDays.add(myEventDay);
            mCalendarView.setEvents(mEventDays);
        }
    }
    companion object {
        val RESULT: String = "result"
        val EVENT: String = "event"
        val ADD_NOTE: Int = 44
    }

}
