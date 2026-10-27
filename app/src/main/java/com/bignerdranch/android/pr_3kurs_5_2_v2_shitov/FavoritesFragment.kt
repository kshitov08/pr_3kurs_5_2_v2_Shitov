package com.bignerdranch.android.pr_3kurs_5_2_v2_shitov

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class FavoritesFragment : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View? =
        i.inflate(R.layout.fragment_favorites, c, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tv = view.findViewById<TextView>(R.id.textList)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        val currentLogin = PrefsHelper(requireContext()).getLogin()

        val list = Repository(requireContext()).getAllForUser(currentLogin)

        tv.text = if (list.isEmpty()) "Список пуст" else
            list.mapIndexed { i, m -> "${i+1}) ${m.name}\nРост: ${m.height}, Вес: ${m.weight}\nТипы: ${m.types}\nСпособности: ${m.abilities}" }.joinToString("\n\n")

        btnBack.setOnClickListener { (activity as? MainActivity)?.goToSearch() }
    }
}