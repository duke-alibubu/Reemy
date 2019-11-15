package com.android.example.reemy.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.applandeo.materialcalendarview.EventDay
import java.util.*

@Entity(tableName = "event_table")
class MyEventDay: EventDay, Parcelable{

    @PrimaryKey(autoGenerate = true)
    var eventId: Long = 0L

    @ColumnInfo(name = "note")
    var mNote: String
    
    constructor (day: Calendar, imageResource: Int, note: String): super(day, imageResource){
        this.mNote = note
    }

    @Ignore
    private constructor (parcel: Parcel): super(parcel.readSerializable() as Calendar, parcel.readInt()){
        this.mNote = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<MyEventDay>{
        override fun createFromParcel(parcel: Parcel): MyEventDay{
            return MyEventDay(parcel)
        }

        override fun newArray(size: Int): Array<MyEventDay?> {
            return arrayOfNulls(size)
        }
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