package fr.nicolaslinard.mockemon.model

import java.time.LocalDateTime

data class Mockemon(
    val time: LocalDateTime,
    val id: Int,
    val name: LocalizedName,
    val type: List<String>,
    val base: MockemonBase
)

data class LocalizedName (
    val english: String,
    val japanese: String,
    val chinese: String,
    val french: String
)

data class MockemonBase (
    val hP: Int,
    val attack: Int,
    val defense: Int,
    val spAttack: Int,
    val spDefense: Int,
    val speed: Int
)

