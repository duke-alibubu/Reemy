package com.android.example.reemy.utils

import android.os.Parcel
import android.os.Parcelable
import com.applandeo.materialcalendarview.EventDay
import java.util.*

class MyEventDay: EventDay, Parcelable {
    private var mNote: String?

    constructor (day: Calendar, imageResource: Int, note: String): super(day, imageResource){
        this.mNote = note
    }

    private constructor (parcel: Parcel): super(parcel.readSerializable() as Calendar, parcel.readInt()){
        this.mNote = parcel.readString()
    }

    fun getNote(): String? {
        return this.mNote
    }

    fun setNote(note: String){
        this.mNote = note
    }

    companion object CREATOR : Parcelable.Creator<MyEventDay>{
        override fun createFromParcel(parcel: Parcel): MyEventDay{
            return MyEventDay(parcel)
        }

        override fun newArray(size: Int): Array<MyEventDay?> {
            return arrayOfNulls(size)
        }


    }

    override fun equals(other: Any?): Boolean {
        if (other is MyEventDay){
            if ((other.mNote.equals(mNote))&&(other.calendar == calendar))
                return true
        }
        return false
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeSerializable(this.calendar)
        dest.writeInt(this.imageResource)
        dest.writeString(this.mNote)
    }

    override fun describeContents(): Int {
        return 0
    }
}