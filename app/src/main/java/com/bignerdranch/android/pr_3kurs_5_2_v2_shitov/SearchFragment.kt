package com.bignerdranch.android.pr_3kurs_5_2_v2_shitov

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class SearchFragment : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View? =
        i.inflate(R.layout.fragment_search, c, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val edit = view.findViewById<EditText>(R.id.editQuery)
        val btnSearch = view.findViewById<Button>(R.id.btnSearch)
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val btnFav = view.findViewById<Button>(R.id.btnFavorites)
        val tv = view.findViewById<TextView>(R.id.textResult)

        val repo = Repository(requireContext())

        var curName = ""; var curHeight = ""; var curWeight = ""
        var curTypes = ""; var curAbilities = ""
        var found = false

        btnSearch.setOnClickListener {
            val q = edit.text.toString().trim().lowercase()
            if (q.isEmpty()) {
                Toast.makeText(requireContext(), "Введите имя/ID", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            tv.text = "Поиск..."
            found = false

            val url = "https://pokeapi.co/api/v2/pokemon/$q"
            Volley.newRequestQueue(requireContext()).add(StringRequest(
                Request.Method.GET, url,
                { response ->
                    Log.d("MyLog", "Response: $response")
                    try {
                        val p = Gson().fromJson(response, PokemonResponse::class.java)
                        curName = p.name?.replaceFirstChar { it.uppercase() } ?: "?"
                        curHeight = "${(p.height ?: 0) / 10.0} м"
                        curWeight = "${(p.weight ?: 0) / 10.0} кг"
                        curTypes = p.types?.mapNotNull { it.type?.name }?.joinToString(", ") ?: "?"
                        curAbilities = p.abilities?.mapNotNull { it.ability?.name }?.joinToString(", ") ?: "?"
                        found = true

                        tv.text = buildString {
                            append("Имя: $curName\n")
                            append("Рост: $curHeight\n")
                            append("Вес: $curWeight\n")
                            append("Типы: $curTypes\n")
                            append("Способности: $curAbilities")
                        }
                    } catch (e: Exception) {
                        tv.text = "Ошибка парсинга"
                        Log.e("MyLog", "err", e)
                    }
                },
                {
                    tv.text = "Не найдено"
                    Log.d("MyLog", "Volley error: ${it.message}")
                }
            ))
        }

        btnSave.setOnClickListener {
            if (!found) {
                Toast.makeText(requireContext(), "Сначала найдите", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val currentLogin = PrefsHelper(requireContext()).getLogin()

            repo.insert(Movie(
                ownerLogin = currentLogin,
                name = curName,
                height = curHeight,
                weight = curWeight,
                types = curTypes,
                abilities = curAbilities
            ))
            Toast.makeText(requireContext(), "Сохранено", Toast.LENGTH_SHORT).show()
        }

        btnFav.setOnClickListener { (activity as? MainActivity)?.goToFavorites() }
    }
}