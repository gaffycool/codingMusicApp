package com.hh.codingexercise.data.storage

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hh.codingexercise.data.storage.entity.AlbumDataDb

@Database(
    entities = [AlbumDataDb::class],
    version = 1,
    exportSchema = false
)
abstract class AlbumDatabase : RoomDatabase() {


    abstract fun albumDao(): AlbumDao

    companion object {

        private const val databaseName = "album-db"

        fun buildDefault(context: Context) =
            Room.databaseBuilder(context, AlbumDatabase::class.java, databaseName)
                .build()

        @VisibleForTesting
        fun buildTest(context: Context) =
            Room.inMemoryDatabaseBuilder(context, AlbumDatabase::class.java)
                .build()
    }
}