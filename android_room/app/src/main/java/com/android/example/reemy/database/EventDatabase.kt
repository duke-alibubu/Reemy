package com.android.example.reemy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyEventDay::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase(){
    abstract val eventDatabaseDao: EventDatabaseDao

    companion object{

        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getInstance(context: Context): EventDatabase {

            //Making sure only one thread of execution at a time can enter this block of code - which makes sure the database only gets initialized once
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,EventDatabase::class.java,
                        "event_table").fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }

        }

    }
}