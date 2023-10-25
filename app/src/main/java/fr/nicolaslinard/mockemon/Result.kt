package fr.nicolaslinard.mockemon

data class Result<T> (
    val success: T? = null,
    val error: String? = null
)
