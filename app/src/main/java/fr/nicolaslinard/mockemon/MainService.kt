package fr.nicolaslinard.mockemon

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainService {
    private val httpClient = HttpClient()
    fun fetchData(coroutineScope: CoroutineScope, onResult: (Result<MockemonDTO>) -> Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            val result = httpClient.get<String>("https://65366c57bb226bb85dd21671.mockapi.io/api/v1/list")

            withContext(Dispatchers.Main) {
                onResult(Result(MockemonDTO(result)))
            }
        }
    }
}