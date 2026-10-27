package com.bignerdranch.android.pr_3kurs_5_2_v2_shitov

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ownerLogin: String,
    val name: String,
    val height: String,
    val weight: String,
    val types: String,
    val abilities: String
)