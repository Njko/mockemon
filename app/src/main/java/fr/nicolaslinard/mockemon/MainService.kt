package fr.nicolaslinard.mockemon

import fr.nicolaslinard.mockemon.dto.PokemonDTO
import fr.nicolaslinard.mockemon.dto.toMockemon
import fr.nicolaslinard.mockemon.model.Mockemon
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json


class MainService {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }
    fun fetchData(coroutineScope: CoroutineScope, onResult: (Result<List<Mockemon>>) -> Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val response: HttpResponse =
                    client.get(urlString = "https://65366c57bb226bb85dd21671.mockapi.io/api/v1/list")
                val data: List<PokemonDTO> = response.body()

                val mockemonList: List<Mockemon> = data.map { it.toMockemon() }

                withContext(Dispatchers.Main) {
                    onResult(Result(mockemonList))
                }
            }  catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(Result(error = e.message))
                }
            }
        }
    }
}