package com.bignerdranch.android.pr_3kurs_5_2_v2_shitov

import android.content.Context
import androidx.room.Room

class Repository(context: Context) {
    private val database = Room.databaseBuilder(
        context.applicationContext,
        MovieDatabase::class.java,
        "pokedex.db"
    ).allowMainThreadQueries().build()

    private val dao = database.movieDao()

    fun getAllForUser(login: String): List<Movie> = dao.getByUser(login)

    fun insert(movie: Movie) = dao.insert(movie)
}