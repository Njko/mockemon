package fr.nicolaslinard.mockemon

import androidx.compose.runtime.mutableStateOf
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter {
    private val httpClient = HttpClient()
    private val mockemonList = mutableStateOf(Mockemon(rawList = ""))

    fun fetchData(coroutineScope: CoroutineScope, onResult: (String) -> Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            val result = httpClient.get<String>("https://65366c57bb226bb85dd21671.mockapi.io/api/v1/list")

            withContext(Dispatchers.Main) {
                if (!result.isNullOrEmpty()) {
                    mockemonList.value.copy(rawList = result)
                    onResult(mockemonList.value.rawList)
                }
            }
        }
    }
}