package com.bignerdranch.android.pr_3kurs_5_2_v2_shitov

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            goToWelcome()
        }
    }

    fun goToWelcome() = replaceFragment(WelcomeFragment())
    fun goToSearch() = replaceFragment(SearchFragment())
    fun goToFavorites() = replaceFragment(FavoritesFragment())

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}