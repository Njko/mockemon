package fr.nicolaslinard.mockemon

import fr.nicolaslinard.mockemon.model.Mockemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class MainPresenter {
    private val mainRepository = MainService()

    fun getListMockemon(coroutineScope: CoroutineScope, displayListPokemon: (List<Mockemon>) -> Unit) {
        mainRepository.fetchData(coroutineScope) { result ->
            result.success?.let {
               displayListPokemon(it)
            }
        }
    }
}