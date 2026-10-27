package com.bignerdranch.android.pr_3kurs_5_2_v2_shitov

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie WHERE ownerLogin = :login")
    fun getByUser(login: String): List<Movie>

    @Insert
    fun insert(movie: Movie)
}