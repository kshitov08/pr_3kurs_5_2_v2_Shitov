package com.bignerdranch.android.pr_3kurs_5_2_v2_shitov

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}