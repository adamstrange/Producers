package com.strangea.producers.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Producer::class], version = 1)
abstract class ProducerRoomDatabase : RoomDatabase() {
    abstract fun producerDao(): ProducerDao

    companion object {

        @Volatile
        private var INSTANCE: ProducerRoomDatabase? = null

        internal fun getDatabase(context: Context): ProducerRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(ProducerRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ProducerRoomDatabase::class.java, "producer_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
