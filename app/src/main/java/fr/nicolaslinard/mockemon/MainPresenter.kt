package fr.nicolaslinard.mockemon

import fr.nicolaslinard.mockemon.data.repository.MainRepository
import fr.nicolaslinard.mockemon.model.Mockemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@ExperimentalSerializationApi
class MainPresenter : KoinComponent {
    private val mainRepository: MainRepository by inject()

    fun getListMockemon(coroutineScope: CoroutineScope, displayListPokemon: (Result<List<Mockemon>>) -> Unit) {
        coroutineScope.launch {
            try {
                mainRepository.fetchData(coroutineScope) {
                    displayListPokemon(it)
                }
            } catch (e: Exception) {
                displayListPokemon(Result(error = e.message))
            }
        }
    }

}