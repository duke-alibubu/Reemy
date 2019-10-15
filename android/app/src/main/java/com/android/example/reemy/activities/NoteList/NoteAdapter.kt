package com.android.example.reemy.activities.NoteList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.reemy.databinding.ListItemNoteBinding
import com.applandeo.materialcalendarview.EventDay

class NoteDiffCallBack: DiffUtil.ItemCallback<EventDay>(){
    override fun areItemsTheSame(oldItem: EventDay, newItem: EventDay): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: EventDay, newItem: EventDay): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}

class NoteAdapter: ListAdapter<EventDay, NoteAdapter.ViewHolder>(NoteDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    //this function is called by RecyclerView to display the data for one list item at the specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position)!!)
    }



    class ViewHolder private constructor(val binding: ListItemNoteBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: EventDay) {
            binding.noteObject = item

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context) // always pass in the context of the parent view group, which is the RecyclerView

                val binding =
                    ListItemNoteBinding.inflate(layoutInflater, parent, false)  //The third, boolean, argument is attachToRoot.
                // This argument needs to be false,
                // because RecyclerView adds this item to the view hierarchy for you when it's time.
                return ViewHolder(binding)
            }
        }
    }
}