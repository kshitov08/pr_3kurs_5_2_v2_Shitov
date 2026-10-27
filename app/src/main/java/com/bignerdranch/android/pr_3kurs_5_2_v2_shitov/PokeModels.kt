package com.bignerdranch.android.pr_3kurs_5_2_v2_shitov

data class PokemonResponse(
    val name: String?,
    val height: Int?,
    val weight: Int?,
    val types: List<TypeSlot>?,
    val abilities: List<AbilitySlot>?
)
data class TypeSlot(val type: TypeInfo?)
data class TypeInfo(val name: String?)
data class AbilitySlot(val ability: AbilityInfo?)
data class AbilityInfo(val name: String?)