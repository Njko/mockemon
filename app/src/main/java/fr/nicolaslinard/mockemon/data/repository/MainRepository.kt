package fr.nicolaslinard.mockemon.data.repository

import fr.nicolaslinard.mockemon.Result
import fr.nicolaslinard.mockemon.model.Mockemon
import kotlinx.coroutines.CoroutineScope

interface MainRepository {
    suspend fun fetchData(
        coroutineScope: CoroutineScope,
        onResult: (Result<List<Mockemon>>) -> Unit
    )
}